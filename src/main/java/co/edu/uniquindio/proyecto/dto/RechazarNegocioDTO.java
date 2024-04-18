package co.edu.uniquindio.proyecto.dto;
import jakarta.validation.constraints.NotBlank;

public record RechazarNegocioDTO(
        @NotBlank String codigoNegocio,
        @NotBlank String mensaje
) {
}
