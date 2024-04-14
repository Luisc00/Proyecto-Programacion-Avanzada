package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EmailDTO(
        String asunto,
        String cuerpo,
        @NotBlank String destinatario) {
}
