package co.edu.uniquindio.proyecto.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HistorialRevision {
    private String descripcion;

    private EstadoNegocio estadoNegocio;

    private LocalTime fecha;

    private String codigoModerador;
}
