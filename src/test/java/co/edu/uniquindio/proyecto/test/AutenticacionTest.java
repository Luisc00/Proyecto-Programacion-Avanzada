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
    public void inicioSesionTest() throws Exception {
        // Datos de inicio de sesión válidos
        String email = "correo@example.com";
        String password = "contraseña123";

        // Creamos un objeto LoginDTO con los datos válidos
        LoginDTO loginDTO = new LoginDTO(email, password);

        // Realizamos el inicio de sesión
        TokenDTO tokenDTO = autenticacionServicio.iniciarSesionCliente(loginDTO);

        // Verificamos que se haya generado un token
        Assertions.assertNotNull(tokenDTO);
        Assertions.assertNotNull(tokenDTO.token());

        // Aquí podrías realizar más verificaciones sobre el token si es necesario
    }
}
