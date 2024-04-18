package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.EstadoNegocio;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import jakarta.validation.constraints.NotBlank;

public record FiltrarPorNombreYTipoYEstadoDTO(
        @NotBlank String nombre,
        @NotBlank TipoNegocio tipoNegocio,
        @NotBlank EstadoNegocio estado
) {
}
