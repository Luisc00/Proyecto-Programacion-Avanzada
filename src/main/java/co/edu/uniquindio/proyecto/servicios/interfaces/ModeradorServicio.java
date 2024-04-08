package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ActualizarModeradorDTO;
import co.edu.uniquindio.proyecto.dto.ItemModeradorDTO;

import java.util.List;

public interface ModeradorServicio extends CuentaServicio{
    void actualizarModerador(ActualizarModeradorDTO actualizarModeradorDTO);
    void eliminarModerador(String idModerador);
    List<ItemModeradorDTO> listarModeradores();
}
