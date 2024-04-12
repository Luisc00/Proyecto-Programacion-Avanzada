package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Ubicacion;

import java.util.List;

public record ItemNegocioDTO(
        String id,
        String nombre,
        String descripcion,
        Ubicacion Ubicacion,
        List<String> telefonos,
        List<String> imagenes,
        String codigoPropietario
) {
}
