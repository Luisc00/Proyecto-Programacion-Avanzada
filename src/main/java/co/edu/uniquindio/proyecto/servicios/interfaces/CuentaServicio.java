package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.CambioPasswordDTO;
import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;

public interface CuentaServicio {
    void eliminarCuenta(String idCuenta)throws Exception;
    TokenDTO solicitarCambioContraseña(CambioPasswordDTO cambioPasswordDTO)throws Exception;

    void cambiarContrasena(CambioPasswordDTO cambioPasswordDTO,TokenDTO tokenDTO)throws Exception;


}
