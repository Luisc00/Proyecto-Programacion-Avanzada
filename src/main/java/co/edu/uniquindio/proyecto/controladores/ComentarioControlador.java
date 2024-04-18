package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ItemComentarioDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.ResponderComentarioDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
@RequiredArgsConstructor
public class ComentarioControlador {

    private final ComentarioServicio comentarioServicio;

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO<String>> crearComentario(@Valid @RequestBody
                                                              CrearComentarioDTO crearComentarioDTO)throws Exception{
        comentarioServicio.crearComentario(crearComentarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario creado correctamente") );
    }

    @PutMapping("/responder")
    public ResponseEntity<MensajeDTO<String>> responderComentario(@Valid @RequestBody
                                                              ResponderComentarioDTO responderComentarioDTO)throws Exception{
        comentarioServicio.responderComentario(responderComentarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario respondido correctamente") );
    }

    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<MensajeDTO<ItemComentarioDTO>> obtenerComentario(@PathVariable String codigo) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.obtenerComentario(codigo)) );
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarComentario(@PathVariable String codigo)throws
            Exception{
        comentarioServicio.eliminarComentario(codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario eliminado correctamente") );
    }

    @GetMapping("/listar/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemComentarioDTO>>> listarComentariosNegocio(@PathVariable String codigoNegocio) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.listarComentariosNegocio(codigoNegocio)) );
    }

    @GetMapping("/calcular-promedio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<Float>> calcularPromedioCalificaciones(@PathVariable String codigoNegocio) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.calcularPromedioCalificaciones(codigoNegocio)) );
    }

}
