package co.edu.uniquindio.proyecto.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
@Document("Comentarios")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Comentario {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private LocalDate fecha;

    private int calificacion;

    private String codigoCliente;

    private String codigoNegocio;

    private String mensaje;

    private String respuesta;



}
