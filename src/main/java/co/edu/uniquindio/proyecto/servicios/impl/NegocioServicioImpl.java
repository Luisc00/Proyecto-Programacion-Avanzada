package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.proyecto.dto.CambiarEstadoNegocioDTO;
import co.edu.uniquindio.proyecto.dto.CrearNegocioDTO;
import co.edu.uniquindio.proyecto.dto.ItemNegocioDTO;
import co.edu.uniquindio.proyecto.modelo.EstadoNegocio;
import co.edu.uniquindio.proyecto.servicios.interfaces.NegocioServicio;

import java.util.List;

public class NegocioServicioImpl implements NegocioServicio {
    @Override
    public String crearNegocio(CrearNegocioDTO crearNegocioDTO) {
        return null;
    }

    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) {

    }

    @Override
    public void eliminarNegocio(String idNegocio) {

    }

    @Override
    public List<ItemNegocioDTO> filtrarPorEstado(EstadoNegocio estado) {
        return null;
    }

    @Override
    public List<ItemNegocioDTO> listarNegociosPropietario(String idPropietario) {
        return null;
    }

    @Override
    public void cambiarEstado(CambiarEstadoNegocioDTO cambiarEstadoNegocioDTO) {

    }

    @Override
    public void registrarRevision() {

    }
}
