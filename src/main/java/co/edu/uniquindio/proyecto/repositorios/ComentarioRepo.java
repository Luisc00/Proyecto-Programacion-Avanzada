package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.modelo.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
public interface ComentarioRepo extends MongoRepository<Comentario,String>{

    Optional<Comentario> findByCodigo(String codigo);
    Optional<Comentario> findByCodigoCliente(String codigoCliente);
    Optional<Comentario> findByCodigoNegocio(String codigoNegocio);
    List<Comentario> findByFechaAndCodigoNegocio(LocalDate fecha, String codigoNegocio);
}
