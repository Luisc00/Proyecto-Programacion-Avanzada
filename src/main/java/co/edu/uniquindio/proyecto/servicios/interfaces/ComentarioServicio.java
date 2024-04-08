package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ItemComentarioDTO;

import java.util.List;

public interface ComentarioServicio {

    void crearComentario(CrearComentarioDTO CrearComentarioDTO);
    void responderComentario(String respuesta);
    void eliminarComentario(String idComentario);
    List<ItemComentarioDTO>listarComentarios();
    float calcularPromedioCalificaciones(String idNegocio);
}
