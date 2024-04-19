package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.excepciones.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.*;
import co.edu.uniquindio.proyecto.repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.repositorios.NegocioRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.NegocioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NegocioServicioTest {
    @Autowired
    private NegocioRepo negocioRepo;
    @Autowired
    private NegocioServicio negocioServicio;

    //Correr el dataSet de los negocios para confirmar los filtros
    @Test
    public void CrearNegocio() throws Exception {
        List<String> imagenes = new ArrayList<>();
        imagenes.add("imagen1.jpg");
        imagenes.add("imagen2.jpg");
        imagenes.add("imagen3.jpg");

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setLatitud(1234326);
        ubicacion.setLongitud(2134134546);


        List<Horario> horarios = new ArrayList<>();
        Horario horario1 = new Horario();
        horario1.setDia("Lunes-viernes");
        horario1.setHoraInicio(LocalTime.NOON);
        horario1.setHoraFin(LocalTime.MIDNIGHT);

        horarios.add(horario1);

        List<String> telefonos = new ArrayList<>();
        telefonos.add("7425169");
        telefonos.add("310248424");

        CrearNegocioDTO negocioDTO = new CrearNegocioDTO("N1",
                "LOS PERROS DEL MONO", "los mejores perros de la ciudad", ubicacion, imagenes,
                TipoNegocio.RESTAURANTE, "1091884092", horarios, telefonos);

        String codigo = negocioServicio.crearNegocio(negocioDTO);

        assertNotNull(codigo);

    }

    @Test
    public void actualizarNegocioTest() throws Exception {
        // Configuración del caso de prueba
        List<String> imagenes = Arrays.asList("imagen1.jpg", "imagen2.jpg", "imagen3.jpg");
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setLatitud(1234323246);
        ubicacion.setLongitud(2134134563);

        List<Horario> horarios = new ArrayList<>();
        Horario horario1 = new Horario();
        horario1.setDia("miercoles-viernes");
        horario1.setHoraInicio(LocalTime.NOON);
        horario1.setHoraFin(LocalTime.MIDNIGHT);
        horarios.add(horario1);

        List<String> telefonos = new ArrayList<>();
        telefonos.add("12435");
        telefonos.add("289421");


        ActualizarNegocioDTO actualizarNegocioDTO = new ActualizarNegocioDTO(
                "N1", "Nuevo nombre", "Nueva descripción", ubicacion, imagenes,
                TipoNegocio.TIENDA, "1091884092", horarios, telefonos);


        negocioServicio.actualizarNegocio(actualizarNegocioDTO);

        Negocio negocioActualizado = negocioRepo.findByCodigo(actualizarNegocioDTO.codigo()).orElse(null);
        assertNotNull(negocioActualizado);
        assertEquals("Nuevo nombre", negocioActualizado.getNombre());
        assertEquals("Nueva descripción", negocioActualizado.getDescripcion());
        assertEquals(TipoNegocio.TIENDA, negocioActualizado.getTipoNegocio());
        assertEquals(telefonos, negocioActualizado.getTelefonos());
        assertNotNull(negocioActualizado.getHorarios());
        assertEquals(imagenes, negocioActualizado.getImagenes());
    }

    @Test
    public void eliminarNegocioExistente() throws Exception {

        negocioServicio.eliminarNegocio("N1");

        assertFalse(negocioRepo.existsById("N1"));
    }
    @Test
    public void eliminarNegocioNoExistente() {
        assertThrows(ResourceNotFoundException.class, () -> negocioServicio.eliminarNegocio("id_inexistente"));
    }
    @Test
    public void filtrarPorEstadoTest() {
        List<ItemNegocioDTO> negociosPendientes = negocioServicio.filtrarPorEstado(EstadoNegocio.PENDIENTE);

        assertNotNull(negociosPendientes);
        assertFalse(negociosPendientes.isEmpty());

        // Imprimir la lista al final
        System.out.println("Negocios Pendientes:");
        for (ItemNegocioDTO negocio : negociosPendientes) {
            System.out.println(negocio);
        }
    }
    @Test
    public void filtrarPorUbicacionTest() {
        Ubicacion ubicacion=new Ubicacion();
        ubicacion.setLongitud(2134134563);
        ubicacion.setLatitud(1234323246);

        List<ItemNegocioDTO> negociosUbicacion=negocioServicio.filtrarPorUbicacion(ubicacion);
        assertNotNull(negociosUbicacion);
        assertFalse(negociosUbicacion.isEmpty());

        // Imprimir la lista al final
        System.out.println("Negocios ubicados:");
        for (ItemNegocioDTO negocio : negociosUbicacion) {
            System.out.println(negocio);
        }
    }
    @Test
    public void filtrarPorTipoNegocioTest() {
        TipoNegocio tipoNegocio=TipoNegocio.TIENDA;


        List<ItemNegocioDTO> negociosTipo=negocioServicio.filtrarPorTipoNegocio(tipoNegocio);
        assertNotNull(negociosTipo);
        assertFalse(negociosTipo.isEmpty());

        // Imprimir la lista al final
        System.out.println("Negocios ubicados por su tipo:");
        for (ItemNegocioDTO negocio : negociosTipo) {
            System.out.println(negocio);
        }
    }
    @Test
    public void filtrarPorNombreTest() {
        String nombre="Nuevo";

        List<ItemNegocioDTO> negociosNombre=negocioServicio.filtrarPorNombre(nombre);
        assertNotNull(negociosNombre);
        assertFalse(negociosNombre.isEmpty());

        System.out.println("Negocios por nombre:");
        for (ItemNegocioDTO negocio : negociosNombre) {
            System.out.println(negocio);
        }
    }
}
