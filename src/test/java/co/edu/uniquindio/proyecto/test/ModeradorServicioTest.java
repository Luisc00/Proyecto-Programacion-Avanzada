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
        moderador1.setCodigo("3");
        moderador1.setNombre("Moderador3");
        moderador1.setEstadoRegistro(EstadoRegistro.ACTIVO);
        moderador1.setEmail("moderador3@gmailcom");
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
        //Al intentar obtener el moderador va a la lanzar una excepcion ya que no existe.
        assertThrows(Exception.class, () -> moderadorServicio.eliminarCuenta("1"));
        }

    @Test
    public void actualizarModeradores()throws Exception{
        ActualizarModeradorDTO actualizarModeradorDTO=new ActualizarModeradorDTO("1","luis","luismorales");
        assertThrows(Exception.class,()-> moderadorServicio.actualizarModerador(actualizarModeradorDTO));

    }
    @Test
    public void listarModeradores()throws Exception{
        List<ItemModeradorDTO> moderadores = moderadorServicio.listarModeradores();

        assertNotNull(moderadores);
        assertFalse(moderadores.isEmpty());
    }

}


