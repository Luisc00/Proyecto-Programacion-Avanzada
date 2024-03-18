package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.dto.DetalleClienteDTO;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.impl.ClienteServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ClienteServicioTest {
    @Autowired
    private ClienteRepo clienteRepo;

    @Test
    public void registrarClienteTest(){
    //Creamos el cliente con sus propiedades
        Cliente cliente = new Cliente();
              cliente.setNombre("Omar");
              cliente.setNickname("N/A");
              cliente.setCiudad("Armenia");
              cliente.setFotoPerfil("url");
              cliente.setEmail("N/A");
              cliente.setPassword("123");
              cliente.setEstado(EstadoRegistro.ACTIVO);

    //Guardamos el cliente
        Cliente registro = clienteRepo.save( cliente );
        //Verificamos que se haya guardado validando que no sea null
        Assertions.assertNotNull(registro);
    }

    @Test
    public void actualizarTest() throws Exception{
    //Se crea un objeto de tipo ActualizarClienteDTO
        ActualizarClienteDTO actualizarClienteDTO = new ActualizarClienteDTO(
                "Cliente1",
                "Juan",
                "nueva foto",
                "juan@email.com",
                "Armenia"
        );
    //Se actualiza el cliente
         ClienteServicioImpl.actualizarCliente(actualizarClienteDTO);
         //Con el método obtenerCliente se obtiene el cliente con el id "Cliente1"
        DetalleClienteDTO detalleClienteDTO = clienteServicio.obtenerCliente("Cliente1");
    //Se verifica que la foto de perfil sea la misma que se actualizó
        Assertions.assertEquals("nueva foto", detalleClienteDTO.fotoPerfil());
    }
}

