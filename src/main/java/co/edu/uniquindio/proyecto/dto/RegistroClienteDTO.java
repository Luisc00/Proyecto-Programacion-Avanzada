package co.edu.uniquindio.proyecto.dto;


import co.edu.uniquindio.proyecto.modelo.Ciudad;
import co.edu.uniquindio.proyecto.modelo.Imagen;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

public record RegistroClienteDTO (

        @Id
        String codigo,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank String fotoPerfil,
        @NotBlank String nickname,
        @NotBlank @Email String email,
        @NotNull String ciudadResidencia,
        @NotBlank @Length(min = 5) String password,
        @NotBlank String confirmarPassword



) {


}