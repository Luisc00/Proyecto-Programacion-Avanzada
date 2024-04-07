package co.edu.uniquindio.proyecto.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
@Document("comentarios")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Comentario {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private LocalTime fecha;

    private int calificacion;

    private String codigoCliente;

    private String codigoNegocio;


    private String mensaje;

    private String respuesta;



}
