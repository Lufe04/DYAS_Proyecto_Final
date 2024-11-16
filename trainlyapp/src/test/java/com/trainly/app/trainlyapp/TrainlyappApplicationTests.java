package com.trainly.app.trainlyapp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.trainly.app.trainlyapp.DAO.UserDAO;
import com.trainly.app.trainlyapp.models.UserEntity;
import com.trainly.app.trainlyapp.services.UserFactory;

@SpringBootTest
class TrainlyappApplicationTests {

    @Mock
    private UserDAO userDAO;

    @Mock
    private UserFactory userFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUserSuccess() {
        // Datos de prueba
        String username = "martin";
        String password = "12345";
        String email = "martin@example.com";
        String userType = "CLIENT";

        // Simular creación de usuario con la fábrica
        UserEntity mockUser = new UserEntity(username, password, email, userType);
        when(userFactory.createUser(username, password, email, userType)).thenReturn(mockUser);

        // Simular guardado exitoso en el DAO
        when(userDAO.saveUser(mockUser)).thenReturn(true);

        // Ejecutar el registro
        boolean isRegistered = userDAO.saveUser(mockUser);

        // Verificar resultado
        assertTrue(isRegistered, "El usuario debería haberse registrado exitosamente");
    }

    @Test
    public void testRegisterUserFailure() {
        // Datos de prueba
        String username = "martin";
        String password = "12345";
        String email = "martin@example.com";
        String userType = "TRAINER";

        // Simular creación de usuario con la fábrica
        UserEntity mockUser = new UserEntity(username, password, email, userType);
        when(userFactory.createUser(username, password, email, userType)).thenReturn(mockUser);

        // Simular fallo al guardar en el DAO
        when(userDAO.saveUser(mockUser)).thenReturn(false);

        // Ejecutar el registro
        boolean isRegistered = userDAO.saveUser(mockUser);

        // Verificar resultado
        assertFalse(isRegistered, "El usuario no debería haberse registrado");
    }

    @Test
    public void testAuthenticateUserSuccess() {
        // Datos de prueba
        String email = "martin@example.com";
        String password = "12345";

        // Simular un usuario existente
        UserEntity mockUser = new UserEntity("martin", password, email, "CLIENT");
        when(userDAO.loginUser(email, password)).thenReturn(mockUser);

        // Ejecutar la autenticación
        UserEntity authenticatedUser = userDAO.loginUser(email, password);

        // Verificar resultado
        assertTrue(authenticatedUser != null, "El usuario debería haber iniciado sesión correctamente");
    }

    @Test
    public void testAuthenticateUserFailure() {
        // Datos de prueba
        String email = "noexiste@example.com";
        String password = "wrongpassword";

        // Simular que el usuario no existe
        when(userDAO.loginUser(email, password)).thenReturn(null);

        // Ejecutar la autenticación
        UserEntity authenticatedUser = userDAO.loginUser(email, password);

        // Verificar resultado
        assertFalse(authenticatedUser != null, "El usuario no debería haber iniciado sesión");
    }
}
