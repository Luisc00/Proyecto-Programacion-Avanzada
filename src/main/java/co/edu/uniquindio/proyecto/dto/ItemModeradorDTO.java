package co.edu.uniquindio.proyecto.dto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.Email;

public record ItemModeradorDTO(
        @NotBlank @Id String codigo,
        @NotBlank String nombre,
        String  @NotBlank @Email email
) {
}
