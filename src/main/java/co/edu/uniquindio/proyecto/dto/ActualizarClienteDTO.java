package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Imagen;

public record ActualizarClienteDTO (
        String id,
        String nombre,
        String nickname,
        Imagen fotoPerfil,
        String email,
        String ciudadResidencia
) {


}