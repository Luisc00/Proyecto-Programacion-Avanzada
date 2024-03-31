package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EmailDTO(
        String asunto,
        String cuerpo,
        @NotBlank String destinatario) {
    public EmailDTO(String destinatario) {
        this("Recuperar contraseña", "con este token podra establecer su contraseña"
                , destinatario);
    }
}
