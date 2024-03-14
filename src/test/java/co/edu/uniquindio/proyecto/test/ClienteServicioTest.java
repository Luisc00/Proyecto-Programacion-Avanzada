package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.repositorios.ClienteRepo;
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
              cliente.setEstado(EstadoRegistro.INACTIVO);

    //Guardamos el cliente
        Cliente registro = clienteRepo.save( cliente );
        //Verificamos que se haya guardado validando que no sea null
        Assertions.assertNotNull(registro);
    }
}

