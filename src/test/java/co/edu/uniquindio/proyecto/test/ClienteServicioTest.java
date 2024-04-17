package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.*;
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
                new RegistroClienteDTO("Cliente1", "Juan", "mi foto.jpg", "juanito", "juan@email.com", "mipassword", "Armenia"),
                new RegistroClienteDTO("Cliente2", "Maria", "mi foto.jpg", "maria", "maria@email.com", "mipassword", "Armenia"),
                new RegistroClienteDTO("Cliente3", "Pedro", "mi foto", "pedrito", "pedro@email.com", "mipassword", "Armenia"),
                new RegistroClienteDTO("Cliente4", "Carlos", "mi foto.jpg", "carlitos", "carlos@email.com", "mipassword", "Armenia"),
                new RegistroClienteDTO("Cliente5", "Oscar", "mi foto.jpg", "oscarin ", "oscar@email.com", "mipassword", "Armenia ")
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
                "Armenia"
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
                "oscar123",
                "imagen.jpg",
                "luisc.moralesc@uqvirtual.edu.co",
                "Armenia"
        );
        clienteServicio.actualizarCliente(actualizarClienteDTO);
        DetalleClienteDTO detalleClienteDTO = clienteServicio.obtenerDetalleCliente(actualizarClienteDTO.id());
        assertEquals("nueva foto", detalleClienteDTO.fotoPerfil());
    }

    @Test
    public void eliminarCuentaTest() throws Exception {

        assertThrows(Exception.class, () -> clienteServicio.eliminarCuenta("Cliente1"));

    }

    CambioPasswordDTO cambioPasswordDTO = new CambioPasswordDTO("luisc.moralesc@uqvirtual.edu.co",
            "nuevaPassword", "1091");
    @Test
    public void solicitarCambioContrasenaTest() throws Exception {
        TokenDTO token = clienteServicio.solicitarCambioContraseña(cambioPasswordDTO);

        assertNotNull(token);
        assertNotNull(token.token());
        assertFalse(token.token().isEmpty());
    }
    @Test
    public void cambiarContrasenaTest() throws Exception {
        TokenDTO token = new TokenDTO("eyJhbGciOiJIUzM4NCJ9.eyJJZCI6IjEwOTEiLCJSb2wiOiJDTElFTlRFIiwiZW1haWwiOiJsdWlzYy5tb3JhbGVzY0B1cXZpcnR1YWwuZWR1LmNvIiwic3ViIjoibHVpc2MubW9yYWxlc2NAdXF2aXJ0dWFsLmVkdS5jbyIsImlhdCI6MTcxMzA1NzIyOSwiZXhwIjoxNzEzMDYwODI5fQ.qb6hYG5bNru94WyoD-LVvEQjb0FvWfNUfwFA9Q2orLbuiPKWeJxpqKPuThmI2JSL");
        ;
        assertThrows(Exception.class, () -> clienteServicio.cambiarContrasena(cambioPasswordDTO, token));
    }

    @Test
    public void listarClienteTest() {

        List<ItemClienteDTO> clientes = clienteServicio.listarCliente();


        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
    }
}

