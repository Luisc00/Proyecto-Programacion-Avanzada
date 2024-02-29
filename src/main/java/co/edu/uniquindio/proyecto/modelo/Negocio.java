package co.edu.uniquindio.proyecto.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("Negocio")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Negocio implements Serializable{

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private Ubicacion ubicacion;

    private String nombre;

    private String descripcion;

    private List<Horario> horarios;

    private EstadoNegocio estadoRegistro;

    private List<String> imagenes;

    private List<HistorialRevision> historialRevisiones;



    private String codigoCliente;

    private TipoNegocio tipoNegocio;

    private List<String> telefonos;



}
