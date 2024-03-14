package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.repositorios.ClienteRepo;

import java.util.List;

public interface ClienteServicio extends CuentaServicio {


    String registrarseCliente(RegistroClienteDTO registroClienteDTO) throws Exception;

    void editarPerfilCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception;

    void iniciarSersion(SesionDTO sesionDTO) throws Exception;


    void eliminarCuenta(String idCuenta) throws Exception;


    void enviarCodigoVerificacion(String email) throws Exception;

     void cambiarContrasena(CambioPasswordDTO cambioPasswordDTO) throws Exception;


    List<ItemClienteDTO> listarCliente();

    List<ItemClienteDTO>listarCliente(int pagina);
}

