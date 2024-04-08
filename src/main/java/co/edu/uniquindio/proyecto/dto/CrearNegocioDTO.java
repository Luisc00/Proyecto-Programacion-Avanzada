package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Horario;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;

import java.util.List;

public record CrearNegocioDTO(
        String codigo,
        String nombre,
        String descripcion,
        Ubicacion ubicacion,
        List<String> imagenes,
        TipoNegocio tipoNegocio,
        String codigoCliente,
        List<Horario> horarios,
        List<String> telefonos
) {
}
