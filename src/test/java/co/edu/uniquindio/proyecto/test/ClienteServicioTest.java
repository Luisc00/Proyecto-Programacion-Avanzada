package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    public void registrarClienteTest() throws Exception {
        // Creamos un objeto DTO para el cliente
        //Se crea un objeto de tipo RegistroClienteDTO
        RegistroClienteDTO registroClienteDTO = new RegistroClienteDTO(
                "1091",
                "Juan",
                "mi foto",
                "juanito",
                "juan@email.com",
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
                "1091",
                "Oscar",
                "nueva foto",
                "Oscar@email.com",
                "Armenia"
        );
        clienteServicio.actualizarCliente(actualizarClienteDTO);
        DetalleClienteDTO detalleClienteDTO = clienteServicio.obtenerDetalleCliente("1091");
        assertEquals("nueva foto", detalleClienteDTO.fotoPerfil());
    }

    @Test
    public void eliminarCuentaTest() throws Exception {

        assertThrows(Exception.class, () -> clienteServicio.eliminarCuenta("1091"));
        assertThrows(Exception.class, () -> clienteServicio.eliminarCuenta("1092"));
    }

    CambioPasswordDTO cambioPasswordDTO = new CambioPasswordDTO("luisc.moralesc@uqvirtual.edu.co",
            "nueva", "1091");
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
        // como ya se han agregado clientes
        List<ItemClienteDTO> clientes = clienteServicio.listarCliente();

        // Verificación
        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
    }
}

