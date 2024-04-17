package co.edu.uniquindio.proyecto.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Moderadores")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false, onlyExplicitlyIncluded = true)
public class Moderador extends Cuenta{

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

}
