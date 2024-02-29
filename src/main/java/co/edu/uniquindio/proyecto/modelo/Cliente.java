package co.edu.uniquindio.proyecto.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente extends Cuenta{
    private String fotoPerfil;
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String nickname;
    private String ciudad;
}
