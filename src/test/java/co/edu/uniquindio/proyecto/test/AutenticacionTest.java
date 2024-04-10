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
        // Datos de inicio de sesión válidos
        String email = "juan@email.com";
        String password = "mipassword";
        // Creamos un objeto LoginDTO con los datos válidos
        LoginDTO loginDTO = new LoginDTO(email, password);
        try {
            // Realizamos el inicio de sesión
            TokenDTO tokenDTO = autenticacionServicio.iniciarSesionCliente(loginDTO);
            // Verificamos que se haya generado un token
            Assertions.assertNotNull(tokenDTO);
            Assertions.assertNotNull(tokenDTO.token());
            // Imprimimos mensaje de inicio exitoso en la consola
            System.out.println("Inicio de sesión exitoso");
            } catch (Exception e) {
            // Imprimimos mensaje de error en la consola
            System.err.println("Error en el inicio de sesión: " + e.getMessage());
            }
        }
}
