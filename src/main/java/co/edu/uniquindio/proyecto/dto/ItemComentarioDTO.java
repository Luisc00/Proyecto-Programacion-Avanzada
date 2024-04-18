package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDate;

public record ItemComentarioDTO(

        String codigo,
        String mensaje,
        int calificacion,
        LocalDate fecha,
        String respuesta,
        String codigoCliente,
        String codigoNegocio
) {
}
