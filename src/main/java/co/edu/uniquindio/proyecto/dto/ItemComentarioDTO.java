package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotBlank;

public record ItemComentarioDTO(

        @NotBlank @Id String codigo,
        @NotBlank String mensaje,
        int calificacion,
        @NotBlank LocalDate fecha,
        @NotBlank String respuesta,
        @NotBlank String codigoCliente,
        @NotBlank @Id String codigoNegocio
) {
}
