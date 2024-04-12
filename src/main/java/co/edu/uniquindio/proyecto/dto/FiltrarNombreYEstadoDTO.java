package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.EstadoNegocio;

public record FiltrarNombreYEstadoDTO(
        String nombre,
        EstadoNegocio estado
) {
}
