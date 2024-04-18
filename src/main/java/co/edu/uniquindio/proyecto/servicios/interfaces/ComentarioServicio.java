package co.edu.uniquindio.proyecto.servicios.interfaces;
import co.edu.uniquindio.proyecto.dto.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ItemComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ResponderComentarioDTO;
import co.edu.uniquindio.proyecto.modelo.Comentario;

import java.util.List;
public interface ComentarioServicio {

    ItemComentarioDTO obtenerComentario(String codigo) throws Exception;

    String crearComentario(CrearComentarioDTO crearComentarioDTO) throws Exception;

    void responderComentario(ResponderComentarioDTO responderComentarioDTO) throws Exception;

    List<ItemComentarioDTO> listarComentariosNegocio(String codigoNegocio) throws Exception;

    float calcularPromedioCalificaciones(String codigoNegocio) throws Exception;

    void eliminarComentario(String idComentario) throws Exception;
}
