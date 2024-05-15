package co.edu.uniquindio.proyecto.dto;


import co.edu.uniquindio.proyecto.modelo.CiudadResidencia;
import co.edu.uniquindio.proyecto.modelo.Imagen;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

public record DetalleClienteDTO(
        @NotBlank @Id String codigo,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank String fotoPerfil,
        @NotBlank String nickname,
        @NotBlank @Email String email,
        @NotBlank CiudadResidencia ciudadResidencia) {

}
