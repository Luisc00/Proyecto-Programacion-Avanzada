package co.edu.uniquindio.proyecto.dto;

public record CrearComentarioDTO(
        String mensaje,
        String codigo,
        String codigoCliente,
        int calificacion,
        String codigoNegocio,
        String respuesta
) {
}
