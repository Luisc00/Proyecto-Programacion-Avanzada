package co.edu.uniquindio.proyecto.dto;
import co.edu.uniquindio.proyecto.modelo.Imagen;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public record ItemClienteDTO (
        String codigo,

    String nombre,

    String fotoPerfil,

    String nickname,

    String ciudad
){

}

