package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class EmailServicioTest {
    @Autowired
    private EmailServicio emailServicio;
    @Test
    public void enviarCorreoPrueba() throws Exception {
        EmailDTO emailDTO=new EmailDTO("prueba","solo es para probar el envio"
                ,"");
        assertThrows(Exception.class, () -> emailServicio.enviarCorreo(emailDTO));

    }
}
