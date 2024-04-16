package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.DetalleClienteDTO;
import co.edu.uniquindio.proyecto.dto.ItemComentarioDTO;
import co.edu.uniquindio.proyecto.modelo.Comentario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;
    private final ClienteServicioImpl clienteServicioImpl;

    public ComentarioServicioImpl(ComentarioRepo comentarioRepo, ClienteServicioImpl clienteServicioImpl) {
        this.comentarioRepo = comentarioRepo;
        this.clienteServicioImpl = clienteServicioImpl;
    }

    @Override
    public boolean crearComentario(CrearComentarioDTO crearComentarioDTO) throws Exception {

        Comentario comentario = new Comentario();
        comentario.setCodigoNegocio(crearComentarioDTO.codigoNegocio());
        comentario.setMensaje(crearComentarioDTO.mensaje());
        comentario.setCodigoCliente(crearComentarioDTO.codigoCliente());
        comentario.setCodigo(crearComentarioDTO.codigo());
        comentario.setCalificacion(crearComentarioDTO.calificacion());
        comentario.setRespuesta("");

        try {
            comentarioRepo.save(comentario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean responderComentario(String idComentario, String respuesta) throws Exception {
        Optional<Comentario> comentarioOptional = comentarioRepo.findById(idComentario);

        if (comentarioOptional.isEmpty()) {
            throw new Exception("El comentario no pudo ser encontrado");
        }

        Comentario comentario = comentarioOptional.get();
        comentario.setRespuesta(respuesta);

        try {
            comentarioRepo.save(comentario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public List<ItemComentarioDTO> listarComentariosNegocio(String codigoNegocio) throws Exception {
            return null;
    }

    private String obtenerNombreUsuario(String codigoCliente){
        DetalleClienteDTO cliente;
        try {
            cliente = clienteServicioImpl.obtenerDetalleCliente(codigoCliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cliente.nombre();
    }


    @Override
    public int calcularPromedioCalificaciones(String codigoNegocio) throws Exception
    {
     return 0;
    }

    @Override
    public void eliminarComentario(String idComentario) throws Exception {
        Optional<Comentario> comentarioOptional = comentarioRepo.findById(idComentario);

        if (comentarioOptional.isEmpty()){
            throw new Exception("El comentario no pudo ser encontrado");
        }

        try{
            comentarioRepo.delete(comentarioOptional.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
