package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.Email;

public record ActualizarModeradorDTO(
        @NotBlank @Id String codigo,
        @NotBlank String nombre,
        @NotBlank @Email String email
) {
}
