package co.edu.uniquindio.proyecto.dto;
import co.edu.uniquindio.proyecto.modelo.Imagen;
import lombok.*;

public record ItemClienteDTO (
    String codigo,

    String nombre,

    Imagen fotoPerfil,

    String nickname,

    String ciudad
){

}

