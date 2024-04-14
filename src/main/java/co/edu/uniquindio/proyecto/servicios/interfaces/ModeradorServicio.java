package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ActualizarModeradorDTO;
import co.edu.uniquindio.proyecto.dto.ItemModeradorDTO;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.modelo.Moderador;

import java.util.List;


public interface ModeradorServicio extends CuentaServicio{
    void inicializarModerador(Moderador moderador)throws Exception;
    void actualizarModerador(ActualizarModeradorDTO actualizarModeradorDTO) throws Exception;
    List<ItemModeradorDTO> listarModeradores();

    boolean aprobarNegocio(String codigoNegocio) throws Exception;

    boolean rechazarNegocio(String codigoNegocio, String mensaje) throws Exception;
}
