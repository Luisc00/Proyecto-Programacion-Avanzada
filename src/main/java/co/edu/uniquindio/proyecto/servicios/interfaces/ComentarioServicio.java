package co.edu.uniquindio.proyecto.servicios.interfaces;
import co.edu.uniquindio.proyecto.dto.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ItemComentarioDTO;

import java.util.List;
public interface ComentarioServicio {


    boolean crearComentario(CrearComentarioDTO crearComentarioDTO) throws Exception;

    boolean responderComentario(String idComentario, String respuesta) throws Exception;

    List<ItemComentarioDTO> listarComentariosNegocio(String codigoNegocio) throws Exception;

    // Ponerlo en lugar
    int calcularPromedioCalificaciones(String codigoNegocio) throws Exception;

    void eliminarComentario(String idComentario) throws Exception;
}
