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
        //Se elimina el cliente con el id "Cliente1"
        clienteServicio.eliminarCuenta("cliente1");
        //Al intentar obtener el cliente con el id "Cliente1" se debe lanzar una excepción
        assertThrows(Exception.class, () -> clienteServicio.obtenerDetalleCliente("cliente1"));
    }


    @Test
    public void enviarCorreoTest() throws Exception {
        EmailDTO emailDTO = new EmailDTO("Prueba de correo", "Este es un correo de prueba", "j.kamilo3020@gmail.com");
        // Enviamos el correo
        emailServicio.enviarCorreo(emailDTO);
}
    @Test
    public void cambiarContrasena() throws Exception {
        String id="1091";
        CambioPasswordDTO cambio=new CambioPasswordDTO("hola","1091","juan@email.com");
        clienteServicio.cambiarContrasena(cambio);


    }

    @Test
    public void listarClienteTest() {
        // como ya se han agregado clientes
        List<ItemClienteDTO> clientes = clienteServicio.listarCliente();

        // Verificación
        assertNotNull(clientes); // Aseguramos que la lista no sea nula
        assertFalse(clientes.isEmpty()); // Aseguramos que la lista no esté vacía
    }
}

