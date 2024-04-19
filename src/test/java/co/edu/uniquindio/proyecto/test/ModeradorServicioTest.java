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
    private ModeradorServicio moderadorServicio;


    @Test
    public void inicializarModeradorTest() throws Exception {
        Moderador moderador1 = new Moderador();
        moderador1.setCodigo("1");
        moderador1.setNombre("Moderador1");
        moderador1.setEstadoRegistro(EstadoRegistro.ACTIVO);
        moderador1.setEmail("luisc.moralesc@uqvirtual.edu.co");
        moderador1.setPassword("claveModerador");

        Moderador moderador2 = new Moderador();
        moderador2.setCodigo("1");
        moderador2.setNombre("Moderador2");
        moderador2.setEstadoRegistro(EstadoRegistro.ACTIVO);
        moderador2.setEmail("carlos@gmail.com");
        moderador2.setPassword("claveModerador");

        Moderador moderador3 = new Moderador();
        moderador3.setCodigo("3");
        moderador3.setNombre("Moderador3");
        moderador3.setEstadoRegistro(EstadoRegistro.ACTIVO);
        moderador3.setEmail("pedro@gmail.com");
        moderador3.setPassword("claveModerador");


        assertThrows(Exception.class, () -> moderadorServicio.inicializarModerador(moderador2));


    }

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
        assertThrows(Exception.class, () -> moderadorServicio.aprobarNegocio(new CambiarEstadoNegocioDTO("Negocio1", "Mensaje", "1")));
    }
    @Test
    public void recharNegocio() throws Exception {
        CambiarEstadoNegocioDTO cambiarEstadoNegocioDTO = new CambiarEstadoNegocioDTO("Negocio1", "Mensaje", "1");
        assertThrows(Exception.class, () -> moderadorServicio.rechazarNegocio(cambiarEstadoNegocioDTO));
    }

}


