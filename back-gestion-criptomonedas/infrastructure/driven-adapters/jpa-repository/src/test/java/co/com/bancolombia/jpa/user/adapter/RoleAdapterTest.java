package co.com.bancolombia.jpa.user.adapter;

import co.com.bancolombia.jpa.user.entity.RoleEntity;
import co.com.bancolombia.jpa.user.repository.RoleRepositoryAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class RoleAdapterTest {

    @InjectMocks
    RoleAdapter roleAdapter;
    @Mock
    RoleRepositoryAdapter roleRepositoryAdapter;

    @Test
    void findByRolNameTest() {
        Optional<RoleEntity> role = Optional.of(this.roleEntity()) ;

        Mockito.when(roleRepositoryAdapter.findByName(Mockito.anyString()))
                .thenReturn(role);

        Assertions.assertNotNull(roleAdapter.findByRolName(Mockito.anyString()));
    }
    private RoleEntity roleEntity(){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1);
        roleEntity.setName("name");
        return roleEntity;
    }
}