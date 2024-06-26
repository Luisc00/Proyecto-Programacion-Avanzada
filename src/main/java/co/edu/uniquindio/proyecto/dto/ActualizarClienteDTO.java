package co.edu.uniquindio.proyecto.dto;


import co.edu.uniquindio.proyecto.modelo.Ciudad;
import co.edu.uniquindio.proyecto.modelo.Imagen;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public record ActualizarClienteDTO (
        @NotBlank @Id String id,
        @NotBlank String nombre,
        @NotBlank String fotoPerfil,
        @NotNull Ciudad ciudadResidencia
) {


}