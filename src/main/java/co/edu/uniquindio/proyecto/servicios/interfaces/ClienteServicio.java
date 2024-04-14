package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.*;

import java.util.List;
import java.util.Map;

public interface ClienteServicio extends CuentaServicio {

    String registrarseCliente(RegistroClienteDTO registroClienteDTO) throws Exception;

    void eliminarCuenta(String idCuenta) throws Exception;

    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception;

    DetalleClienteDTO obtenerDetalleCliente(String idCliente) throws Exception;

    List<ItemClienteDTO>listarCliente();

}

