package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.Moderador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeradorRepo extends MongoRepository<Moderador, String>{

    Optional<Moderador> findByEmail(String email);
    Optional<Moderador> findByCodigo(String codigo);
    Boolean existsModeradorByEmail(String email);

}
