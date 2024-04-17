package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Imagen;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record DetalleClienteDTO(
        @NotBlank String codigo,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank Imagen fotoPerfil,
        @NotBlank String nickname,
        @NotBlank @Email String email,
        @NotBlank String ciudadResidencia) {

}
