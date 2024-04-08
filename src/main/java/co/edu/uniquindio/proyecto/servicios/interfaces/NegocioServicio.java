package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.proyecto.dto.CambiarEstadoNegocioDTO;
import co.edu.uniquindio.proyecto.dto.CrearNegocioDTO;
import co.edu.uniquindio.proyecto.dto.ItemNegocioDTO;
import co.edu.uniquindio.proyecto.modelo.EstadoNegocio;

import java.util.List;

public interface NegocioServicio {

    String crearNegocio(CrearNegocioDTO crearNegocioDTO);

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO);

    void eliminarNegocio(String idNegocio);

    List<ItemNegocioDTO> filtrarPorEstado(EstadoNegocio estado);

    List<ItemNegocioDTO> listarNegociosPropietario(String idPropietario);

    void cambiarEstado(CambiarEstadoNegocioDTO cambiarEstadoNegocioDTO);

    void registrarRevision();

}