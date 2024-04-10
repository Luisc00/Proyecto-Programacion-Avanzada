package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.modelo.Moderador;
import co.edu.uniquindio.proyecto.repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ModeradorServicioTest {
    @Autowired
    private ModeradorRepo moderadorRepo;
    @Autowired
    private ModeradorServicio moderadorServicio;
    @Autowired
    private EmailServicio emailServicio;
    @Test
    public void inicializarModeradorTest() throws Exception {
        Moderador moderador1 = new Moderador();
        moderador1.setCodigo("1");
        moderador1.setNombre("Moderador1");
        moderador1.setEstadoRegistro(EstadoRegistro.ACTIVO);
        moderador1.setEmail("NOTIENE@GMAIL.COM");
        moderador1.setPassword("1");

        moderadorServicio.inicializarModerador(moderador1);

        Optional<Moderador> moderadorGuardadoOptional = moderadorRepo.findByCodigo("1");
        assertTrue(moderadorGuardadoOptional.isPresent(), "No se encontró el moderador guardado");

        Moderador moderadorGuardado = moderadorGuardadoOptional.get();
        assertEquals("Moderador1", moderadorGuardado.getNombre());
        assertEquals(EstadoRegistro.ACTIVO, moderadorGuardado.getEstadoRegistro());
        assertEquals("NOTIENE@GMAIL.COM", moderadorGuardado.getEmail());
        assertEquals("1", moderadorGuardado.getPassword());
}
}

