package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Imagen;
import jakarta.validation.constraints.NotBlank;

public record ActualizarClienteDTO (
        @NotBlank String id,
        @NotBlank String nombre,
        @NotBlank String nickname,
        @NotBlank String fotoPerfil,
        @NotBlank String email,
        @NotBlank String ciudadResidencia
) {


}