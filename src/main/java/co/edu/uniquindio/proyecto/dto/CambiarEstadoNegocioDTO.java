package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.EstadoNegocio;

public record CambiarEstadoNegocioDTO(
        String id,
        EstadoNegocio estado
) {
}
