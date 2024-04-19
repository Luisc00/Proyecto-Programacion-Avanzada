package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import jakarta.validation.constraints.NotBlank;

public record FiltrarPorNombreYTipoYUbicacionDTO(
        @NotBlank String nombre,
        TipoNegocio tipoNegocio,
        Ubicacion ubicacion
) {
}
