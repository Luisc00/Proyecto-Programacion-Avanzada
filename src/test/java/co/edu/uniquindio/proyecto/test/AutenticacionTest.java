package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.servicios.impl.AutenticacionServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AutenticacionTest {

    @Autowired
    private AutenticacionServicioImpl autenticacionServicio;

    @Test
    public void inicioSesionTest() {
        String email = "juan@email.com";
        String password = "mipassword";
        LoginDTO loginDTO = new LoginDTO(email, password);
        try {
            TokenDTO tokenDTO = autenticacionServicio.iniciarSesionCliente(loginDTO);
            Assertions.assertNotNull(tokenDTO);
            System.out.println("Inicio de sesión exitoso");
            } catch (Exception e) {
            System.err.println("Error en el inicio de sesión: " + e.getMessage());
            }
        }

    @Test
    public void inicioSesionModeradorTest() {
        String email = "juan@email.com";
        String password = "mipassword";
        LoginDTO loginDTO = new LoginDTO(email, password);
        try {
            TokenDTO tokenDTO = autenticacionServicio.iniciarSesionModerador(loginDTO);
            Assertions.assertNotNull(tokenDTO);
            System.out.println("Inicio de sesión exitoso");
        } catch (Exception e) {
            System.err.println("Error en el inicio de sesión: " + e.getMessage());
        }
    }
}
