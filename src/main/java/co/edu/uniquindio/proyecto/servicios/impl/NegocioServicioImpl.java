package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.excepciones.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.*;
import co.edu.uniquindio.proyecto.repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.repositorios.NegocioRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.NegocioServicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NegocioServicioImpl implements NegocioServicio {

    NegocioRepo negocioRepo;

    ClienteRepo clienteRepo;

    public NegocioServicioImpl(NegocioRepo negocioRepo,ClienteRepo clienteRepo) {
        this.negocioRepo = negocioRepo;
        this.clienteRepo = clienteRepo;
    }

    @Override
    public String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception{
        if(negocioRepo.existsNegocioByNombre(crearNegocioDTO.nombre()) ||
                negocioRepo.existsNegocioByCodigo(crearNegocioDTO.codigo())) {
            throw new Exception("El negocio ya existe") ;
        }
        Optional<Cliente> cliente=clienteRepo.findById(crearNegocioDTO.codigoCliente());

        if(cliente.isEmpty()){
            throw new Exception("El cliente no existe");
        }
        Cliente cliente1=cliente.get();
        if(cliente1.getRegistro()==EstadoRegistro.INACTIVO){
            throw new Exception("El cliente ya ha sido eliminado");
        }

        Negocio negocio = new Negocio();
        negocio.setNombre(crearNegocioDTO.nombre());
        negocio.setCodigo(crearNegocioDTO.codigo());
        negocio.setDescripcion(crearNegocioDTO.descripcion());
        negocio.setTipoNegocio(crearNegocioDTO.tipoNegocio());
        negocio.setUbicacion(crearNegocioDTO.ubicacion());
        negocio.setEstadoNegocio(EstadoNegocio.PENDIENTE);
        negocio.setCodigoCliente(crearNegocioDTO.codigoCliente());
        negocio.setTelefonos(crearNegocioDTO.telefonos());
        negocio.setHorarios(crearNegocioDTO.horarios());
        negocio.setImagenes(crearNegocioDTO.imagenes());
        negocioRepo.save(negocio);
        return negocio.getCodigo();
    }

    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws ResourceNotFoundException {
        if(!negocioRepo.existsNegocioByCodigo(actualizarNegocioDTO.codigo())) {
            throw new ResourceNotFoundException("El negocio no existe");
        }
            Negocio negocio = negocioRepo.findByCodigo(actualizarNegocioDTO.codigo()).get();
            negocio.setNombre(actualizarNegocioDTO.nombre());
            negocio.setDescripcion(actualizarNegocioDTO.descripcion());
            negocio.setTipoNegocio(actualizarNegocioDTO.tipoNegocio());
            negocio.setUbicacion(actualizarNegocioDTO.ubicacion());
            negocio.setTelefonos(actualizarNegocioDTO.telefonos());
            negocio.setHorarios(actualizarNegocioDTO.horarios());
            negocio.setImagenes(actualizarNegocioDTO.imagenes());
            negocioRepo.save(negocio);

    }

    @Override
    public void eliminarNegocio(String idNegocio) throws ResourceNotFoundException {
        if(negocioRepo.existsById(idNegocio)){
            negocioRepo.deleteById(idNegocio);
        }else{
            throw new ResourceNotFoundException("El negocio no existe");
        }
    }

    @Override
    public List<ItemNegocioDTO> filtrarPorEstado(EstadoNegocio estado) {
        List<Negocio> negocios = negocioRepo.findByEstadoNegocio(estado);
        List<ItemNegocioDTO> items;

        items = negocios.stream().
                map(negocio -> new ItemNegocioDTO(negocio.getCodigo(), negocio.getNombre(),
                        negocio.getDescripcion(), negocio.getUbicacion(),
                        negocio.getTelefonos(), negocio.getImagenes(),negocio.getCodigoCliente())).
                toList();
        return items;
    }

    @Override
    public List<ItemNegocioDTO> filtrarPorUbicacion(Ubicacion ubicacion) {
        List<Negocio> negocios = negocioRepo.findByUbicacion(ubicacion);
        List<ItemNegocioDTO> items;

        items = negocios.stream().
                map(negocio -> new ItemNegocioDTO(negocio.getCodigo(), negocio.getNombre(),
                        negocio.getDescripcion(), negocio.getUbicacion(),
                        negocio.getTelefonos(), negocio.getImagenes(),negocio.getCodigoCliente())).
                toList();
        return items;
    }

    @Override
    public List<ItemNegocioDTO> filtrarPorTipoNegocio(TipoNegocio tipoNegocio) {
        List<Negocio> negocios = negocioRepo.findByTipoNegocio(tipoNegocio);
        List<ItemNegocioDTO> items;

        items = negocios.stream().
                map(negocio -> new ItemNegocioDTO(negocio.getCodigo(), negocio.getNombre(),
                        negocio.getDescripcion(), negocio.getUbicacion(),
                        negocio.getTelefonos(), negocio.getImagenes(),negocio.getCodigoCliente())).
                toList();
        return items;
    }

    @Override
    public List<ItemNegocioDTO> filtrarPorNombre(String nombre) {
        List<Negocio> negocios = negocioRepo.findByNombreContaining(nombre);
        List<ItemNegocioDTO> items;

        items = negocios.stream().
                map(negocio -> new ItemNegocioDTO(negocio.getCodigo(), negocio.getNombre(),
                        negocio.getDescripcion(), negocio.getUbicacion(),
                        negocio.getTelefonos(), negocio.getImagenes(),negocio.getCodigoCliente())).
                toList();
        return items;
    }

    @Override
    public List<ItemNegocioDTO> filtrarPorNombreYTipoNegocio(FiltrarNombreYTIpoDTO filtrarNombreYTIpoDTO) {
        List<Negocio> negocios = negocioRepo.findByNombreContainingAndTipoNegocio(filtrarNombreYTIpoDTO.nombre(), TipoNegocio.valueOf(filtrarNombreYTIpoDTO.tipoNegocio()));
        List<ItemNegocioDTO> items;

        items = negocios.stream().
                map(negocio -> new ItemNegocioDTO(negocio.getCodigo(), negocio.getNombre(),
                        negocio.getDescripcion(), negocio.getUbicacion(),
                        negocio.getTelefonos(), negocio.getImagenes(),negocio.getCodigoCliente())).
                toList();
        return items;
    }

    @Override
    public List<ItemNegocioDTO> filtrarPorNombreYUbicacion(FiltrarPorNombreYUbicacionDTO filtrarPorNombreYUbicacionDTO) {
        List<Negocio> negocios = negocioRepo.findByNombreContainingAndUbicacion(filtrarPorNombreYUbicacionDTO.nombre(), filtrarPorNombreYUbicacionDTO.ubicacion());
        List<ItemNegocioDTO> items;

        items = negocios.stream().
                map(negocio -> new ItemNegocioDTO(negocio.getCodigo(), negocio.getNombre(),
                        negocio.getDescripcion(), negocio.getUbicacion(),
                        negocio.getTelefonos(), negocio.getImagenes(),negocio.getCodigoCliente())).
                toList();
        return items;
    }

    @Override
    public List<ItemNegocioDTO> filtrarPorNombreYTipoNegocioYUbicacion(FiltrarPorNombreYTipoYUbicacionDTO filtrarPorNombreYTipoYUbicacionDTO) {
        List<Negocio> negocios = negocioRepo.findByNombreContainingAndTipoNegocioAndUbicacion(filtrarPorNombreYTipoYUbicacionDTO.nombre(), filtrarPorNombreYTipoYUbicacionDTO.tipoNegocio(), filtrarPorNombreYTipoYUbicacionDTO.ubicacion());
        List<ItemNegocioDTO> items;

        items = negocios.stream().
                map(negocio -> new ItemNegocioDTO(negocio.getCodigo(), negocio.getNombre(),
                        negocio.getDescripcion(), negocio.getUbicacion(),
                        negocio.getTelefonos(), negocio.getImagenes(),negocio.getCodigoCliente())).
                toList();
        return items;
    }

    @Override
    public List<ItemNegocioDTO> listarNegociosPropietario(String idPropietario) {
        List<Negocio> negocios = negocioRepo.findByCodigoCliente(idPropietario);
        List<ItemNegocioDTO> items;

        items = negocios.stream().
                map(negocio -> new ItemNegocioDTO(negocio.getCodigo(), negocio.getNombre(),
                        negocio.getDescripcion(), negocio.getUbicacion(),
                        negocio.getTelefonos(), negocio.getImagenes(),negocio.getCodigoCliente())).
                toList();
        return items;
    }

    @Override
    public void cambiarEstado(CambiarEstadoNegocioDTO cambiarEstadoNegocioDTO) {
        if(negocioRepo.existsNegocioByCodigo(cambiarEstadoNegocioDTO.id())){
            Negocio negocio = negocioRepo.findByCodigo(cambiarEstadoNegocioDTO.id()).get();
            negocio.setEstadoNegocio(cambiarEstadoNegocioDTO.estado());
            negocioRepo.save(negocio);
        }
    }

    @Override
    public void registrarRevision() {

    }
}
