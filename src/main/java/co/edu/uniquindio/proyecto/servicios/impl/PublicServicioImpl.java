package co.edu.uniquindio.proyecto.servicios.impl;


import co.edu.uniquindio.proyecto.modelo.CiudadResidencia;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.servicios.interfaces.PublicServicio;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PublicServicioImpl implements PublicServicio {

    @Override
    public List<CiudadResidencia> listarCiudades() {
        return Arrays.asList(CiudadResidencia.values());
    }

    @Override
    public List<TipoNegocio> listarTipo() {
        return Arrays.asList(TipoNegocio.values());
    }
}
