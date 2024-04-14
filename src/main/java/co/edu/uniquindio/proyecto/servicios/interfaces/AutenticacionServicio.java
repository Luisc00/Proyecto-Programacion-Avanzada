package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.CambioPasswordDTO;
import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;

public interface AutenticacionServicio {
    public TokenDTO iniciarSesionCliente(LoginDTO loginDTO) throws Exception;

    public TokenDTO iniciarSesionModerador(LoginDTO loginDTO) throws Exception;
}
