package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;

@NotBlank
public record ActualizarModeradorDTO(
        String codigo,
        String nombre,
        String email
) {
}
