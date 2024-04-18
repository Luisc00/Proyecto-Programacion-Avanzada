package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.modelo.Moderador;
import co.edu.uniquindio.proyecto.repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.repositorios.NegocioRepo;
import co.edu.uniquindio.proyecto.servicios.impl.AutenticacionServicioImpl;
import co.edu.uniquindio.proyecto.servicios.interfaces.AutenticacionServicio;
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
    private AutenticacionServicio autenticacionServicio;
    @Autowired
    private EmailServicio emailServicio;
    @Autowired
    private NegocioRepo negocioRepo;

    @Test
    public void inicializarModeradorTest() throws Exception {
        Moderador moderador1 = new Moderador();
        moderador1.setCodigo("1");
        moderador1.setNombre("Moderador1");
        moderador1.setEstadoRegistro(EstadoRegistro.ACTIVO);
        moderador1.setEmail("luisc.moralesc@uqvirtual.edu.co");
        moderador1.setPassword("3");

        moderadorServicio.inicializarModerador(moderador1);

        Optional<Moderador> moderadorGuardadoOptional = moderadorRepo.findByCodigo(moderador1.getCodigo());
        assertTrue(moderadorGuardadoOptional.isPresent(), "No se encontró el moderador guardado");

        Moderador moderadorGuardado = moderadorGuardadoOptional.get();
        assertEquals("Moderador1", moderadorGuardado.getNombre());
        assertEquals(EstadoRegistro.ACTIVO, moderadorGuardado.getEstadoRegistro());
        assertEquals("NOTIENE@GMAIL.COM", moderadorGuardado.getEmail());
        assertEquals("1", moderadorGuardado.getPassword());}

    @Test
    public void eliminarCuentaTest() throws Exception {

        assertThrows(Exception.class, () -> moderadorServicio.eliminarCuenta("1"));
        }

    @Test
    public void actualizarModeradores()throws Exception{
        ActualizarModeradorDTO actualizarModeradorDTO=new ActualizarModeradorDTO("1","luis","luismorales");
        assertThrows(Exception.class,()-> moderadorServicio.actualizarModerador(actualizarModeradorDTO));

    }
    CambioPasswordDTO cambioPasswordDTO = new CambioPasswordDTO("luisc.moralesc@uqvirtual.edu.co",
            "nueva", "1091");
    @Test
    public void solicitarCambioContrasenaTest() throws Exception {
        TokenDTO token = moderadorServicio.solicitarCambioContraseña(cambioPasswordDTO);

        assertNotNull(token);
        assertNotNull(token.token());
        assertFalse(token.token().isEmpty());
    }
    @Test
    public void cambiarContrasenaTest() throws Exception {
        TokenDTO token = new TokenDTO("eyJhbGciOiJIUzM4NCJ9.eyJJZCI6IjEwOTEiLCJSb2wiOiJDTElFTlRFIiwiZW1haWwiOiJsdWlzYy5tb3JhbGVzY0B1cXZpcnR1YWwuZWR1LmNvIiwic3ViIjoibHVpc2MubW9yYWxlc2NAdXF2aXJ0dWFsLmVkdS5jbyIsImlhdCI6MTcxMzA1NzIyOSwiZXhwIjoxNzEzMDYwODI5fQ.qb6hYG5bNru94WyoD-LVvEQjb0FvWfNUfwFA9Q2orLbuiPKWeJxpqKPuThmI2JSL");
        ;
        assertThrows(Exception.class, () -> moderadorServicio.cambiarContrasena(cambioPasswordDTO, token));
    }
    @Test
    public void listarModeradores()throws Exception{
        List<ItemModeradorDTO> moderadores = moderadorServicio.listarModeradores();

        assertNotNull(moderadores);
        assertFalse(moderadores.isEmpty());
    }
    @Test
    public void aprobarNegocio() throws Exception {
        assertThrows(Exception.class, () -> moderadorServicio.aprobarNegocio("Negocio1"));
    }

}


