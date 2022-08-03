package co.com.bancolombia.security.enums;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class RoleEnumTest {

    @ParameterizedTest(name = "{index} => role=''{0}''")
    @EnumSource(value = RoleEnum.class, names = {"ADMIN_ROLE"})
    void adminRolTest(RoleEnum role) {
        assertNotNull(role);
    }

    @ParameterizedTest(name = "{index} => role=''{0}''")
    @EnumSource(value = RoleEnum.class, names = {"USER_ROLE"})
    void userRolTest(RoleEnum role) {
        assertNotNull(role);
    }
}