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

        //Pase el test ya que la primera ya esta desabilitada y la otra no existe.
        assertThrows(Exception.class, () -> clienteServicio.eliminarCuenta("1091"));
        assertThrows(Exception.class, () -> clienteServicio.eliminarCuenta("1092"));
    }
    @Test
    void enviarTokenRecuperacionTest() {
        // Arrange
        CambioPasswordDTO cambioPasswordDTO = new CambioPasswordDTO("luisc.moralesc@uqvirtual.edu.co", "nuevaContraseña","1091");

        // Act & Assert
        assertThrows(Exception.class, () -> clienteServicio.enviarTokenRecuperacion(cambioPasswordDTO),
                "ya se ha generado un token de recuperación para este cliente");
    }
    @Test
    void cambiarContrasenaConTokenTest() {
        String token="eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJsdWlzYy5tb3JhbGVzY0B1cXZpcnR1YWwuZWR1LmNvIiwiaWF0IjoxNzEyODcwNzI1LCJleHAiOjE3MTI4NzQzMjV9.JxI21YU0U9SN8lpwMdBXidsDwaoaIZYWc5NbmmZIIXbbA1t-u3Ig039F6KPOwPZi";
        CambioPasswordDTO cambioPasswordDTO = new CambioPasswordDTO("luisc.moralesc@uqvirtual.edu.co", "nuevaContraseña","1091");

        assertDoesNotThrow(() -> clienteServicio.cambiarContrasenaConToken(token, cambioPasswordDTO),
                "No se esperaba ninguna excepción al cambiar la contraseña con un token válido");
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

