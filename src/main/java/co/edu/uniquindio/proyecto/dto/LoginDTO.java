package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.*;

public record LoginDTO(
        @NotBlank @Email String email,
        @NotBlank  String password
) {
}