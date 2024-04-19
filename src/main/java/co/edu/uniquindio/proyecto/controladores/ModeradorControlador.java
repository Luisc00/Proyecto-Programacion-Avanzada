package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.ActualizarModeradorDTO;
import co.edu.uniquindio.proyecto.dto.CambiarEstadoNegocioDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ModeradorServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable String codigo)throws
            Exception{
        moderadorServicio.eliminarCuenta(codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Moderador eliminado")
        );
    }
    @PutMapping("/aprobar-negocio")
    public ResponseEntity<MensajeDTO<String>> aprobarNegocio(@Valid @RequestBody CambiarEstadoNegocioDTO cambiarEstadoNegocioDTO) throws Exception {
        moderadorServicio.aprobarNegocio(cambiarEstadoNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio aprobado correctamente"));
    }
    @PutMapping("/rechazar-negocio")
    public ResponseEntity<MensajeDTO<String>> rechazarNegocio(@Valid @RequestBody CambiarEstadoNegocioDTO cambiarEstadoNegocioDTO) throws Exception {
        moderadorServicio.rechazarNegocio(cambiarEstadoNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio rechazado correctamente"));
    }
}
