package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;

public record FiltrarPorNombreYTipoYUbicacionDTO(
        String nombre,
        TipoNegocio tipoNegocio,
        Ubicacion ubicacion
) {
}
