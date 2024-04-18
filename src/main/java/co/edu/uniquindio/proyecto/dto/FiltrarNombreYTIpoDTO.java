package co.edu.uniquindio.proyecto.dto;
import jakarta.validation.constraints.NotBlank;

public record FiltrarNombreYTIpoDTO(
        @NotBlank String nombre,
        @NotBlank String tipoNegocio
) {
}
