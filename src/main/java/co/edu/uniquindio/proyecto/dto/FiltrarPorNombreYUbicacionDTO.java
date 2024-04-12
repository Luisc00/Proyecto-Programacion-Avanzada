package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Ubicacion;

public record FiltrarPorNombreYUbicacionDTO(
        String nombre,
        Ubicacion ubicacion
) {
}
