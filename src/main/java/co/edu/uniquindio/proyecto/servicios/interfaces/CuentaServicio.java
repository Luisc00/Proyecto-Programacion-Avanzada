package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.CambioPasswordDTO;
import co.edu.uniquindio.proyecto.dto.LoginDTO;

public interface CuentaServicio {
    void eliminarCuenta(String idCuenta)throws Exception;
    void cambiarContrasena(CambioPasswordDTO cambioPasswordDTO)throws Exception;
}
