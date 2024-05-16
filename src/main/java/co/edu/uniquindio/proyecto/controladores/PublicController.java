package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.ItemClienteDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.Ciudad;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.servicios.interfaces.PublicServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/publico")
@RequiredArgsConstructor
public class PublicController {

    private final PublicServicio publicServicio;

    @GetMapping("/listar-ciudades")
    public ResponseEntity<MensajeDTO<List<Ciudad>>> listarCiudades(){
        return ResponseEntity.ok().body( new MensajeDTO<>(false, publicServicio.listarCiudades()));
    }

    @GetMapping("/listar-tipos-negocio")
    public ResponseEntity<MensajeDTO<List<TipoNegocio>>> listarTipoNegocio(){
        return ResponseEntity.ok().body( new MensajeDTO<>(false, publicServicio.listarTipo()));
    }
}
