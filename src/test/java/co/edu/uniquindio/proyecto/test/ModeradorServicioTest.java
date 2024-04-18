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
            "nueva");

    @Test
    public void cambiarContrasenaTest() throws Exception {
        assertThrows(Exception.class, () -> moderadorServicio.cambiarContrasena(cambioPasswordDTO));
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
    @Test
    public void recharNegocio() throws Exception {
        RechazarNegocioDTO rechazarNegocioDTO = new RechazarNegocioDTO("Negocio1", "el negocio no sirve");
        assertThrows(Exception.class, () -> moderadorServicio.rechazarNegocio(rechazarNegocioDTO));
    }

}


