package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.EstadoNegocio;
import jakarta.validation.constraints.NotBlank;

public record FiltrarNombreYEstadoDTO(
        @NotBlank String nombre,
        @NotBlank EstadoNegocio estado 
) {
}
