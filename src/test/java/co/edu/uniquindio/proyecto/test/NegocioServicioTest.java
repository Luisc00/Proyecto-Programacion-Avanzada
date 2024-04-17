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

        CrearNegocioDTO negocioDTO = new CrearNegocioDTO("N4",
                "Rosquillas ", "los mejores perros de la ciudad", ubicacion, imagenes,
                TipoNegocio.RESTAURANTE, "1091", horarios, telefonos);

        String codigo = negocioServicio.crearNegocio(negocioDTO);

        assertNotNull(codigo);

    }

    @Test
    public void actualizarNegocioTest() throws Exception {
        // Configuración del caso de prueba
        List<String> imagenes = Arrays.asList("imagen1.jpg", "imagen2.jpg", "imagen3.jpg");
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setLatitud(1234326);
        ubicacion.setLongitud(2134134546);

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
                "N2", "Nuevo nombre", "Nueva descripción", ubicacion, imagenes,
                TipoNegocio.TIENDA, "109", horarios, telefonos);


        negocioServicio.actualizarNegocio(actualizarNegocioDTO);

        Negocio negocioActualizado = negocioRepo.findByCodigo(actualizarNegocioDTO.codigo()).orElse(null);
        assertNotNull(negocioActualizado);
        assertEquals("Nuevo nombre", negocioActualizado.getNombre());
        assertEquals("Nueva descripción", negocioActualizado.getDescripcion());
        assertEquals(TipoNegocio.TIENDA, negocioActualizado.getTipoNegocio());
        assertEquals(telefonos, negocioActualizado.getTelefonos());
        assertEquals(horarios, negocioActualizado.getHorarios());
        assertEquals(imagenes, negocioActualizado.getImagenes());
    }

    @Test
    public void eliminarNegocioExistente() throws Exception {

        negocioServicio.eliminarNegocio("N1");

        assertFalse(negocioRepo.existsById("id_del_negocio"));
    }
    @Test
    public void eliminarNegocioNoExistente() {
        assertThrows(ResourceNotFoundException.class, () -> negocioServicio.eliminarNegocio("id_inexistente"));
    }
    @Test
    public void filtrarPorEstado() {
        List<ItemNegocioDTO> negociosActivos = negocioServicio.filtrarPorEstado(EstadoNegocio.PENDIENTE);

    }

}
