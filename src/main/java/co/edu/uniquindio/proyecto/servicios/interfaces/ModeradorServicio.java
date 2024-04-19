package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.modelo.Moderador;

import java.util.List;


public interface ModeradorServicio extends CuentaServicio{
    void inicializarModerador(Moderador moderador)throws Exception;
    void actualizarModerador(ActualizarModeradorDTO actualizarModeradorDTO) throws Exception;
    List<ItemModeradorDTO> listarModeradores();
    void aprobarNegocio(String codigoNegocio) throws Exception;
    boolean rechazarNegocio(RechazarNegocioDTO rechazarNegocioDTO) throws Exception;

}
