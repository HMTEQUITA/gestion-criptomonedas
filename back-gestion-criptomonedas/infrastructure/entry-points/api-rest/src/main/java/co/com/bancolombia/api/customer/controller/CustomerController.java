package co.com.bancolombia.api.customer.controller;

import co.com.bancolombia.api.customer.dto.CustomerVO;
import co.com.bancolombia.api.customer.mapper.CustomerVoMapper;
import co.com.bancolombia.api.customer.view.CustomerViewDTO;
import co.com.bancolombia.cryptocurrency.usecase.CryptocurrencyUseCase;
import co.com.bancolombia.customer.usecase.CustomerUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerUseCase customerUseCase;
    private final CryptocurrencyUseCase cryptocurrencyUseCase;

    @GetMapping()
    public ResponseEntity<List<CustomerViewDTO>> getCustomers() {
        var customer = customerUseCase.getCustomers()
                .stream()
                .map(CustomerVoMapper::toView)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerVO> getCustomerById(@PathVariable(value = "customerId") Long customerId){
        var customer = customerUseCase.getCustomerById(customerId);
        return ResponseEntity.ok().body(CustomerVoMapper.toVo(customer));
    }


    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<CustomerViewDTO>> customerByCountry(@PathVariable(value = "countryId") Integer countryId) {
        var customerView = customerUseCase.getCustomerByCountry(countryId)
                .stream()
                .map(CustomerVoMapper::toView)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(customerView);
    }


    @GetMapping("/{customerId}/cryptocurrency/{cryptocurrencyId}")
    public ResponseEntity<CustomerVO> addCryptocurrency(@PathVariable(value = "customerId") Long customerId,
                                                      @PathVariable(value = "cryptocurrencyId") Integer cryptocurrencyId) {

        var currentCustomer = customerUseCase.getCustomerById(customerId);

        var cryptocurrency = cryptocurrencyUseCase
                .getByIdAndCountryId(cryptocurrencyId, currentCustomer.getCountry().getId());

        var customerUpdated = customerUseCase.updateCryptocurrency(currentCustomer, cryptocurrency);


        return ResponseEntity.ok().body(CustomerVoMapper.toVo(customerUpdated));
    }
}
