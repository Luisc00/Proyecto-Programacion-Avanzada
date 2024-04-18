package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;

public record CrearComentarioDTO(
        @NotBlank String mensaje,
        @NotBlank @Id String codigo,
        @NotBlank String codigoCliente,
        @NotBlank @Min(1) @Max(5) int calificacion,
        @NotBlank String codigoNegocio,
        String respuesta
) {
}
