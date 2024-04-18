package co.edu.uniquindio.proyecto.dto;
import jakarta.validation.constraints.NotBlank;
public record ValidacionDTO(
        @NotBlank String campo,
        @NotBlank String error
) {
}
