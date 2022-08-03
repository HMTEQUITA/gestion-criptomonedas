package co.com.bancolombia.api.customer.controller;

import co.com.bancolombia.api.customer.dto.CustomerVO;
import co.com.bancolombia.api.customer.mapper.CustomerVoMapper;
import co.com.bancolombia.api.customer.view.CustomerViewDTO;
import co.com.bancolombia.cryptocurrency.usecase.CryptocurrencyUseCase;
import co.com.bancolombia.customer.usecase.CustomerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Search Customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of customers",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerViewDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Did not find any customer",
                    content = @Content) })
    @GetMapping()
    public ResponseEntity<List<CustomerViewDTO>> getCustomers() {
        var customer = customerUseCase.getCustomers()
                .stream()
                .map(CustomerVoMapper::toView)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(customer);
    }

    @Operation(summary = "Search customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerVO.class)) }),
            @ApiResponse(responseCode = "400", description = "Customer not found",
                    content = @Content) })
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerVO> getCustomerById(@PathVariable(value = "customerId") Long customerId){
        var customer = customerUseCase.getCustomerById(customerId);
        return ResponseEntity.ok().body(CustomerVoMapper.toVo(customer));
    }


    @Operation(summary = "Search customer by country id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of customers",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerViewDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Did not find any customer",
                    content = @Content) })
    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<CustomerViewDTO>> customerByCountry(@PathVariable(value = "countryId") Integer countryId) {
        var customerView = customerUseCase.getCustomerByCountry(countryId)
                .stream()
                .map(CustomerVoMapper::
                        toView)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(customerView);
    }

    @Operation(summary = "Add cryptocurrency by client id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerVO.class)) }),
            @ApiResponse(responseCode = "400", description = "Cryptocurrency already exist for the costumer",
                    content = @Content) })
    @GetMapping("/{customerId}/add_cryptocurrency/{cryptocurrencyId}")
    public ResponseEntity<CustomerVO> addCryptocurrency(@PathVariable(value = "customerId") Long customerId,
                                                      @PathVariable(value = "cryptocurrencyId") Integer cryptocurrencyId) {

        var currentCustomer = customerUseCase.getCustomerById(customerId);

        var cryptocurrency = cryptocurrencyUseCase
                .getByIdAndCountryId(cryptocurrencyId, currentCustomer.getCountry().getId());

        var customerUpdated = customerUseCase.addCryptocurrency(currentCustomer, cryptocurrency);


        return ResponseEntity.ok().body(CustomerVoMapper.toVo(customerUpdated));
    }

    @Operation(summary = "Remove cryptocurrency by client id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerVO.class)) }),
            @ApiResponse(responseCode = "400", description = "Cryptocurrency not exist for the costumer",
                    content = @Content) })
    @GetMapping("/{customerId}/remove_cryptocurrency/{cryptocurrencyId}")
    public ResponseEntity<CustomerVO> removeCryptocurrency(@PathVariable(value = "customerId") Long customerId,
                                                        @PathVariable(value = "cryptocurrencyId") Integer cryptocurrencyId) {

        var currentCustomer = customerUseCase.getCustomerById(customerId);

        var cryptocurrency = cryptocurrencyUseCase
                .getByIdAndCountryId(cryptocurrencyId, currentCustomer.getCountry().getId());

        var customerUpdated = customerUseCase.removeCryptocurrency(currentCustomer, cryptocurrency);


        return ResponseEntity.ok().body(CustomerVoMapper.toVo(customerUpdated));
    }
}
