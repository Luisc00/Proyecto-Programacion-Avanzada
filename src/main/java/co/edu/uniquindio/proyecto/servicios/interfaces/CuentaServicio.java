package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.CambioPasswordDTO;
import co.edu.uniquindio.proyecto.dto.SesionDTO;

public interface CuentaServicio {

    void inicioarSersion(SesionDTO sesionDTO)throws Exception;
    void eliminarCuenta(String idCuenta)throws Exception;
    void enviarCodigoVerificacion(String email)throws Exception;
    void cambiarContrasena(CambioPasswordDTO cambioPasswordDTO)throws Exception;
}
