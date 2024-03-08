package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.dto.RegistroClienteDTO;

public interface ClienteServicio extends CuentaServicio{

    void registrarse(RegistroClienteDTO registroClienteDTO) throws Exception;
    void editarPerfil(ActualizarClienteDTO actualizarClienteDTO) throws Exception;
}
