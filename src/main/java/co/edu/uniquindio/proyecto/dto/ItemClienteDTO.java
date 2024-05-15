package co.edu.uniquindio.proyecto.dto;
import co.edu.uniquindio.proyecto.modelo.Ciudad;
import co.edu.uniquindio.proyecto.modelo.Imagen;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import lombok.*;

public record ItemClienteDTO (
        @NotBlank @Id String codigo,

        @NotBlank String nombre,

        @NotBlank String fotoPerfil,

        @NotBlank String nickname,

        @NotBlank Ciudad ciudad
){

}

