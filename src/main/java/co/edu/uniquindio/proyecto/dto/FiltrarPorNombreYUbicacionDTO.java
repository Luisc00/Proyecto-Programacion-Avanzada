package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import jakarta.validation.constraints.NotBlank;

public record FiltrarPorNombreYUbicacionDTO(
        @NotBlank String nombre,
        Ubicacion ubicacion
) {
}
