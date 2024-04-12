package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.EstadoNegocio;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;

public record FiltrarPorNombreYTipoYEstadoDTO(
        String nombre,
        TipoNegocio tipoNegocio,
        EstadoNegocio estado
) {
}
