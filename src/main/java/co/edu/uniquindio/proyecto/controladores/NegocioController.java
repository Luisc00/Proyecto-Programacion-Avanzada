package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.modelo.EstadoNegocio;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import co.edu.uniquindio.proyecto.servicios.interfaces.NegocioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/negocio")
@RequiredArgsConstructor
public class NegocioController {

    private final NegocioServicio negocioServicio;

    @PostMapping("crear")
    public ResponseEntity<MensajeDTO<String>> crearNegocio(@Valid @RequestBody
                                                           CrearNegocioDTO crearNegocioDTO) throws Exception {
        negocioServicio.crearNegocio(crearNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio creado correctamente"));
    }
    @PutMapping("/actualizar")
    public ResponseEntity<MensajeDTO<String>> actualizarNegocio(@Valid @RequestBody
                                                                ActualizarNegocioDTO actualizarNegocioDTOrearNegocioDTO) throws Exception {
        negocioServicio.actualizarNegocio(actualizarNegocioDTOrearNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio actualizado correctamente"));
    }
    @DeleteMapping("/eliminar/{idNegocio}")
    public ResponseEntity<MensajeDTO<String>> eliminarNegocio(@Valid @PathVariable String idNegocio) throws
            Exception {
        negocioServicio.eliminarNegocio(idNegocio);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio eliminado correctamente"));
    }
    @GetMapping("/obtener/{idNegocio}")
    public ResponseEntity<MensajeDTO<ItemNegocioDTO>> obtenerNegocioPorCodigo(@Valid @PathVariable String idNegocio) throws
            Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerNegocioPorCodigo(idNegocio)));
    }
    @GetMapping("obtener-por-nombre/{nombre}")
    public ResponseEntity<MensajeDTO<ItemNegocioDTO>> obtenerNegocioPorNombre(@Valid @PathVariable String nombre) throws
            Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerNegocioPorNombre(nombre)));
    }
    @GetMapping("filtrar-por-estado/{estado}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> filtrarPorEstado(@Valid @PathVariable EstadoNegocio estado) throws
            Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.filtrarPorEstado(estado)));
    }
    @GetMapping("filtrar-por-tipo-negocio/{tipoNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> filtrarPorTipoNegocio(@Valid @PathVariable TipoNegocio tipoNegocio) throws
            Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.filtrarPorTipoNegocio(tipoNegocio)));
    }
    @GetMapping("filtrar-por-nombre/{nombre}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> filtrarPorNombre(@Valid @PathVariable String nombre) throws
            Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.filtrarPorNombre(nombre)));
    }
    @GetMapping("filtrar-por-nombre-y-tipo-negocio")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> filtrarPorNombreYTipoNegocio(@Valid @RequestBody FiltrarNombreYTIpoDTO filtrarNombreYTIpoDTO) throws
            Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.filtrarPorNombreYTipoNegocio(filtrarNombreYTIpoDTO)));
    }
    @GetMapping("filtrar-por-nombre-y-ubicacion")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> filtrarPorNombreYUbicacion(@Valid @RequestBody FiltrarPorNombreYUbicacionDTO filtrarPorNombreYUbicacionDTO) throws
            Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.filtrarPorNombreYUbicacion(filtrarPorNombreYUbicacionDTO)));
    }
    @GetMapping("filtrar-por-nombre-y-tipo-negocio-y-ubicacion")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> filtrarPorNombreYTipoNegocioYUbicacion(@Valid @RequestBody FiltrarPorNombreYTipoYUbicacionDTO filtrarPorNombreYTipoYUbicacionDTO) throws
            Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.filtrarPorNombreYTipoNegocioYUbicacion(filtrarPorNombreYTipoYUbicacionDTO)));
    }
    @GetMapping("listar-negocios-propietario/{idPropietario}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarNegociosPropietario(@Valid @PathVariable String idPropietario) throws
            Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listarNegociosPropietario(idPropietario)));
    }
    @GetMapping("listar-mejores-negocios")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarMejoresNegocios() throws
            Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listarMejoresNegocios()));
    }
    @GetMapping("listar-top-5-por-categoria/{tipoNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarTop5PorCategoria(@Valid @PathVariable TipoNegocio tipoNegocio) throws
            Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listarTop5PorCategoria(tipoNegocio)));
    }

}
