package co.edu.uniquindio.proyecto.modelo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("Clientes")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false, onlyExplicitlyIncluded = true)

public class Cliente extends Cuenta implements Serializable{
    private String fotoPerfil;
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String nickname;
    private EstadoRegistro registro;
    private String token;

    @NotNull
    private Ciudad ciudadResidencia;
}
