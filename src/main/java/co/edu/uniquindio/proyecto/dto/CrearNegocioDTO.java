package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Horario;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.util.List;

public record CrearNegocioDTO(
        @NotBlank @Id String codigo,
        @NotBlank String nombre,
        @NotBlank String descripcion,
        Ubicacion ubicacion,
        List<String> imagenes,
        @NotBlank TipoNegocio tipoNegocio,
        @NotBlank String codigoCliente,
        List<Horario> horarios,
        List<String> telefonos
) {
}
