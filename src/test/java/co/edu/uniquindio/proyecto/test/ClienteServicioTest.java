package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.modelo.Ciudad;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.Imagen;
import co.edu.uniquindio.proyecto.repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClienteServicioTest {
    @Autowired
    private ClienteRepo clienteRepo;
    @Autowired
    private ClienteServicio clienteServicio;
    @Autowired
    private EmailServicio emailServicio;

    //Son los mismos del dataSet es para que quede cifrado
    @Test
    public void agregarDatosDeEjemplo() {
        List<RegistroClienteDTO> clientes = Arrays.asList(
                new RegistroClienteDTO("Cliente1", "Juan", "mi foto.jpg", "juanito", "juan@email.com", "mipassword",  Ciudad.ARMENIA),
                new RegistroClienteDTO("Cliente2", "Maria", "mi foto.jpg", "maria", "maria@email.com", "mipassword",  Ciudad.ARMENIA),
                new RegistroClienteDTO("Cliente3", "Pedro", "mi foto", "pedrito", "pedro@email.com", "mipassword",  Ciudad.ARMENIA),
                new RegistroClienteDTO("Cliente4", "Carlos", "mi foto.jpg", "carlitos", "carlos@email.com", "mipassword",  Ciudad.ARMENIA),
                new RegistroClienteDTO("Cliente5", "Oscar", "mi foto.jpg", "oscarin ", "oscar@email.com", "mipassword",  Ciudad.ARMENIA)
        );
        for (RegistroClienteDTO clienteDTO : clientes) {
            try {
                clienteServicio.registrarseCliente(clienteDTO);
            } catch (Exception e) {
                System.err.println("Error al registrar cliente: " + e.getMessage());
            }
        }
    }


    @Test
    public void registrarClienteTest() throws Exception {
        RegistroClienteDTO registroClienteDTO = new RegistroClienteDTO(
                "1091884092",
                "Luis",
                "foto.jpg",
                "luisito",
                "luis@email.com",
                "mipassword",
                Ciudad.ARMENIA
        );
        //Se registra el cliente
        String codigo = clienteServicio.registrarseCliente(registroClienteDTO);
        //Se verifica que el código no sea nulo, es decir, que se haya registrado el cliente
        Assertions.assertNotNull(codigo);
    }


    @Test
    public void actualizarTest() throws Exception {
        ActualizarClienteDTO actualizarClienteDTO = new ActualizarClienteDTO(
                "1091884092",
                "Oscar",
                "imagen.jpg",
                Ciudad.ARMENIA
        );
        clienteServicio.actualizarCliente(actualizarClienteDTO);
        DetalleClienteDTO detalleClienteDTO = clienteServicio.obtenerDetalleCliente(actualizarClienteDTO.id());
        assertEquals("imagen.jpg", detalleClienteDTO.fotoPerfil());
    }

    @Test
    public void eliminarCuentaTest() throws Exception {

        assertThrows(Exception.class, () -> clienteServicio.eliminarCuenta("Cliente0"));

    }

    CambioPasswordDTO cambioPasswordDTO = new CambioPasswordDTO("luisc.moralesc@uqvirtual.edu.co",
            "nuevaPassword");

    @Test
    public void cambiarContrasenaTest() throws Exception {
        assertThrows(Exception.class, () -> clienteServicio.cambiarContrasena(cambioPasswordDTO));
    }

    @Test
    public void listarClienteTest() {

        List<ItemClienteDTO> clientes = clienteServicio.listarCliente();


        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
    }
}

