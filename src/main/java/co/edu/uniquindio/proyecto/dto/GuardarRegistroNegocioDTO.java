package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public class GuardarRegistroNegocioDTO {

    @NotBlank @Id String id,
    @NotBlank EstadoRegistro estadoRegistro



}
