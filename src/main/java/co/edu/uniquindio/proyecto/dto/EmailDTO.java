package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EmailDTO(
        @NotBlank @Length(max = 50)String asunto,
        @NotBlank @Length(max = 100)String cuerpo,
        @NotBlank String destinatario) {




}
