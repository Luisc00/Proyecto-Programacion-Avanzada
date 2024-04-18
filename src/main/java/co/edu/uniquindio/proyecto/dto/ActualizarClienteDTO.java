package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Imagen;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public record ActualizarClienteDTO (
        @NotBlank @Id String id,
        @NotBlank String nombre,
        @NotBlank String nickname,
        @NotBlank String fotoPerfil,
        @NotBlank @Email String email,
        @NotBlank String ciudadResidencia
) {


}