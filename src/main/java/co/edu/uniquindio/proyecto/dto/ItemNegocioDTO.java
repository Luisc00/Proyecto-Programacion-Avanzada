package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Ubicacion;

import java.util.List;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public record ItemNegocioDTO(
        @NotBlank @Id String id,
        @NotBlank String nombre,
        @NotBlank String descripcion,
        @NotBlank Ubicacion Ubicacion,
        @NotBlank List<String> telefonos,
        @NotBlank List<String> imagenes,
        @NotBlank String codigoPropietario,
        float calificacionPromedio,
        String estadoNegocio
) {
}
