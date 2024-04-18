package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.excepciones.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.EstadoNegocio;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;

import java.util.List;

public interface NegocioServicio {

    String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws ResourceNotFoundException;

    void eliminarNegocio(String idNegocio) throws ResourceNotFoundException;

    ItemNegocioDTO obtenerNegocioPorCodigo(String idNegocio) throws ResourceNotFoundException;
    ItemNegocioDTO obtenerNegocioPorNombre(String nombre) throws ResourceNotFoundException;
    List<ItemNegocioDTO> filtrarPorEstado(EstadoNegocio estado);
    List<ItemNegocioDTO> filtrarPorUbicacion(Ubicacion ubicacion);
    List<ItemNegocioDTO> filtrarPorTipoNegocio(TipoNegocio tipoNegocio);
    List<ItemNegocioDTO> filtrarPorNombre(String nombre);
    List<ItemNegocioDTO> filtrarPorNombreYTipoNegocio(FiltrarNombreYTIpoDTO filtrarNombreYTIpoDTO);
    List<ItemNegocioDTO> filtrarPorNombreYUbicacion(FiltrarPorNombreYUbicacionDTO filtrarPorNombreYUbicacionDTO);
    List<ItemNegocioDTO> filtrarPorNombreYTipoNegocioYUbicacion(FiltrarPorNombreYTipoYUbicacionDTO filtrarPorNombreYTipoYUbicacionDTO);


    List<ItemNegocioDTO> listarNegociosPropietario(String idPropietario);

    void cambiarEstado(CambiarEstadoNegocioDTO cambiarEstadoNegocioDTO);



}