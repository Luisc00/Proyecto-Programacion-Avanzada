package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.EstadoNegocio;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public record CambiarEstadoNegocioDTO(
        @NotBlank String idNegocio,
        @NotBlank String mensaje,
        @NotBlank String idModerador
) {
}
