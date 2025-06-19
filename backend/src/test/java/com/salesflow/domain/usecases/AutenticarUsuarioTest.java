package com.salesflow.domain.usecases;

import com.salesflow.adapter.dto.Saida;
import com.salesflow.domain.model.Papel;
import com.salesflow.domain.model.User;
import com.salesflow.domain.port.JwtProviderPort;
import com.salesflow.domain.port.SenhaEncoderPort;
import com.salesflow.domain.port.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutenticarUsuarioTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SenhaEncoderPort senhaEncoderPort;

    @Mock
    private JwtProviderPort jwtProviderPort;

    @InjectMocks
    private AutenticarUsuario autenticarUsuario;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "testuser", "encodedPassword", Papel.CLIENTE, true);
    }

    @Test
    @DisplayName("Deve autenticar usuário com sucesso e retornar Saida com tokens")
    void shouldAuthenticateUserSuccessfullyAndReturnToken() {
        // Arrange
        var tokens = new JwtProviderPort.Tokens("fake-access-token", "fake-refresh-token");
        when(userRepository.findByUsuario("testuser")).thenReturn(user);
        when(senhaEncoderPort.matches("rawPassword", "encodedPassword")).thenReturn(true);
        when(jwtProviderPort.gerar("testuser", "CLIENTE")).thenReturn(tokens);

        // Act
        Saida saida = autenticarUsuario.execute("testuser", "rawPassword");

        // Assert
        assertNotNull(saida);
        assertEquals("fake-access-token", saida.getTokenAcesso());
        assertEquals("fake-refresh-token", saida.getTokenRefresh());
        assertEquals("CLIENTE", saida.getPapel());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando a senha for inválida")
    void shouldThrowExceptionWhenPasswordIsInvalid() {
        // Arrange
        when(userRepository.findByUsuario("testuser")).thenReturn(user);
        when(senhaEncoderPort.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            autenticarUsuario.execute("testuser", "wrongPassword");
        });

        String expectedMessage = "Senha inválida";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Deve lançar NullPointerException quando o usuário não for encontrado")
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        when(userRepository.findByUsuario("nonexistentuser")).thenReturn(null);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            autenticarUsuario.execute("nonexistentuser", "anyPassword");
        });
    }
}