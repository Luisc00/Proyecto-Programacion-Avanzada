package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.ActualizarModeradorDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.RechazarNegocioDTO;
import co.edu.uniquindio.proyecto.modelo.Moderador;
import co.edu.uniquindio.proyecto.servicios.interfaces.ModeradorServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moderador")
@RequiredArgsConstructor
public class ModeradorControlador{

    private final ModeradorServicio moderadorServicio;

    @PutMapping("/actualizar")
    public ResponseEntity<MensajeDTO<String>> actualizarModerador(@Valid @RequestBody ActualizarModeradorDTO actualizarModeradorDTO) throws Exception {
        moderadorServicio.actualizarModerador(actualizarModeradorDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Moderador actualizado correctamente"));
    }
    @PutMapping("/aprobar-negocio")
    public ResponseEntity<MensajeDTO<String>> aprobarNegocio(@Valid @RequestBody String codigoNegocio) throws Exception {
        moderadorServicio.aprobarNegocio(codigoNegocio);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio aprobado correctamente"));
    }
    @PutMapping("/rechazar-negocio")
    public ResponseEntity<MensajeDTO<String>> rechazarNegocio(@Valid @RequestBody RechazarNegocioDTO rechazarNegocioDTO) throws Exception {
        moderadorServicio.rechazarNegocio(rechazarNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio rechazado correctamente"));
    }
}
