package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.dto.CambioPasswordDTO;
import co.edu.uniquindio.proyecto.dto.RegistroClienteDTO;
import co.edu.uniquindio.proyecto.dto.SesionDTO;
import co.edu.uniquindio.proyecto.repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional
public class ClienteServicioImpl implements ClienteServicio{
    private final ClienteRepo clienteRepo;
    public ClienteServicioImpl(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @Override
    public void registrarse(RegistroClienteDTO registroClienteDTO) throws Exception {

    }

    @Override
    public void editarPerfil(ActualizarClienteDTO actualizarClienteDTO) throws Exception {

    }

    @Override
    public void inicioarSersion(SesionDTO sesionDTO) throws Exception {

    }

    @Override
    public void eliminarCuenta(String idCuenta) throws Exception {

    }

    @Override
    public void enviarCodigoVerificacion(String email) throws Exception {

    }

    @Override
    public void cambiarContrasena(CambioPasswordDTO cambioPasswordDTO) throws Exception {

    }
}