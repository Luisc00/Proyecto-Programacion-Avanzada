package co.edu.uniquindio.proyecto.modelo;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Cuenta {
    private String nombre;
    private String password;
    private String email;
    private EstadoRegistro estadoRegistro;

}
