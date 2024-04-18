package co.edu.uniquindio.proyecto.dto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public record ImagenDTO(
        @NotBlank @Id String id,
        @NotBlank String url
) {
}
