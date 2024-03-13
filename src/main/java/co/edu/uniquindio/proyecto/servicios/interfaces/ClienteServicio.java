package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.dto.CambioPasswordDTO;
import co.edu.uniquindio.proyecto.dto.RegistroClienteDTO;
import co.edu.uniquindio.proyecto.dto.SesionDTO;

public interface ClienteServicio extends CuentaServicio{


    String registrarseCliente(RegistroClienteDTO registroClienteDTO) throws Exception;

    void editarPerfilCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception;

    void inicioarSersion(SesionDTO sesionDTO) throws Exception;


    void eliminarCuenta(String idCuenta) throws Exception;


    void enviarCodigoVerificacion(String email) throws Exception;

     void cambiarContrasena(CambioPasswordDTO cambioPasswordDTO) throws Exception;


}
