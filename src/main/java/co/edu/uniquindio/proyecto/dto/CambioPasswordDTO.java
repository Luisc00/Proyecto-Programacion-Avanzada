package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public record CambioPasswordDTO(
        @NotBlank @Email String email,
        @NotBlank String passwordNueva,
        @NotBlank @Id String id


) {
}