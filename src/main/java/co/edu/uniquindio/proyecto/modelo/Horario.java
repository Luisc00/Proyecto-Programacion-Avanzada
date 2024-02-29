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
public class Horario {

    private LocalTime horaFin;

    private LocalTime horaInicio;

    private String dia;
}
