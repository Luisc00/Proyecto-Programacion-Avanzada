package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.dto.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.servicios.impl.ComentarioServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ComentarioServicioTest {

    @Autowired
    private ComentarioServicioImpl comentarioServicioImpl;

    @Test
    public void registrarComentarioTest() throws Exception {
        CrearComentarioDTO crearComentarioDTO = new CrearComentarioDTO(
                "mensaje",
                "codigoNegocio",
                "codigoCliente",
                5,
                "codigoNegocio",
                "respuesta"
        );
        String codigo = comentarioServicioImpl.crearComentario(crearComentarioDTO);

        Assertions.assertNotNull(codigo);
    }
}
