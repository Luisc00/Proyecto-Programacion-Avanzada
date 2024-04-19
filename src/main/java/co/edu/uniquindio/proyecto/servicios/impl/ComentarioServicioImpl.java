package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ItemComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ResponderComentarioDTO;
import co.edu.uniquindio.proyecto.excepciones.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.Comentario;
import co.edu.uniquindio.proyecto.modelo.Negocio;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.NegocioRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;
    private final NegocioRepo negocioRepo;

    @Override
    public ItemComentarioDTO obtenerComentario(String codigo) throws Exception {
        Optional<Comentario> comentarioOptional = comentarioRepo.findById(codigo);

        if (comentarioOptional.isEmpty()) {
            throw new Exception("El comentario no pudo ser encontrado");
        }

        Comentario comentario = comentarioOptional.get();
        return new ItemComentarioDTO(comentario.getCodigo(), comentario.getMensaje(),
                comentario.getCalificacion(), comentario.getFecha(),
                comentario.getRespuesta(), comentario.getCodigoCliente(),
                comentario.getCodigoNegocio());
    }

    @Override
    public String crearComentario(CrearComentarioDTO crearComentarioDTO) throws Exception {

        if (!negocioRepo.existsNegocioByCodigo(crearComentarioDTO.codigoNegocio())) {
            throw new ResourceNotFoundException("El negocio no existe");
        }
        Comentario comentario = new Comentario();
        comentario.setCodigoNegocio(crearComentarioDTO.codigoNegocio());
        comentario.setMensaje(crearComentarioDTO.mensaje());
        comentario.setCodigoCliente(crearComentarioDTO.codigoCliente());
        comentario.setCodigo(crearComentarioDTO.codigo());
        comentario.setCalificacion(crearComentarioDTO.calificacion());
        comentario.setRespuesta("");

        Negocio negocio = negocioRepo.findByCodigo(crearComentarioDTO.codigoNegocio()).get();
        negocio.setCalificacionPromedio(calcularPromedioCalificaciones(crearComentarioDTO.codigoNegocio()));
        
        try {
            comentarioRepo.save(comentario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return comentario.getCodigo();
    }

    @Override
    public void responderComentario(ResponderComentarioDTO responderComentarioDTO) throws Exception {
        Optional<Comentario> comentarioOptional = comentarioRepo.findById(responderComentarioDTO.codigoComentario());

        if (comentarioOptional.isEmpty()) {
            throw new Exception("El comentario no pudo ser encontrado");
        }

        Comentario comentario = comentarioOptional.get();
        comentario.setRespuesta(responderComentarioDTO.respuesta());

        try {
            comentarioRepo.save(comentario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ItemComentarioDTO> listarComentariosNegocio(String codigoNegocio) throws Exception {
        List<Comentario> comentarios = comentarioRepo.findByCodigoNegocio(codigoNegocio);
        if (comentarios.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron comentarios para el negocio con código " + codigoNegocio);
        }
        List<ItemComentarioDTO> itemComentarioDTOS = new ArrayList<>();
        for (Comentario comentario : comentarios) {
            itemComentarioDTOS.add(new ItemComentarioDTO(comentario.getCodigo(), comentario.getMensaje(),
                    comentario.getCalificacion(), comentario.getFecha(),comentario.getRespuesta(),comentario.getCodigoCliente(), comentario.getCodigoNegocio()));
        }
        return itemComentarioDTOS;
    }


    @Override
    public float calcularPromedioCalificaciones(String codigoNegocio) throws Exception {
        List<Comentario> comentarios = comentarioRepo.findByCodigoNegocio(codigoNegocio);
        if (comentarios.isEmpty()) {
            return 0;
        }
        float promedio = 0;
        for (Comentario comentario : comentarios) {
            promedio += comentario.getCalificacion();
        }
        promedio = promedio / comentarios.size();

        return promedio;
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
