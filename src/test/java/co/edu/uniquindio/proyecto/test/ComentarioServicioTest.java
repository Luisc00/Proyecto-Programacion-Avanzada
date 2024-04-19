package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.dto.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ResponderComentarioDTO;
import co.edu.uniquindio.proyecto.servicios.impl.ComentarioServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ComentarioServicioTest {

    @Autowired
    private ComentarioServicioImpl comentarioServicioImpl;

    @Test
    public void registrarComentarioTest() throws Exception {
        CrearComentarioDTO crearComentarioDTO = new CrearComentarioDTO(
                "mensaje",
                "000000",
                "1091884092",
                5,
                "000001",
                "respuesta"
        );
        String codigo = comentarioServicioImpl.crearComentario(crearComentarioDTO);

        Assertions.assertNotNull(codigo);
    }
    @Test
    public void obtenerComentariosTest(){
        assertThrows(Exception.class, () -> comentarioServicioImpl.obtenerComentario("codigoNegocio"));
    }
    @Test
    public void responderComentarioTest(){
        ResponderComentarioDTO responderComentarioDTO=new ResponderComentarioDTO("codigoNegocio","muy vacano");
        assertThrows(Exception.class, () -> comentarioServicioImpl.responderComentario(responderComentarioDTO));
    }
    @Test
    public void listarComentarios(){
        assertThrows(Exception.class, () -> comentarioServicioImpl.listarComentariosNegocio("codigoNegocio"));
    }
    @Test
    public void calificacionPromedio(){
        assertThrows(Exception.class,() -> comentarioServicioImpl.calcularPromedioCalificaciones("codigoNegocio"));
    }
    @Test
    public void eliminarComentario(){
        assertThrows(Exception.class, () -> comentarioServicioImpl.eliminarComentario("codigoNegocio"));
    }

}
