package co.edu.uniquindio.proyecto.repositorios;
import co.edu.uniquindio.proyecto.modelo.EstadoNegocio;
import co.edu.uniquindio.proyecto.modelo.Negocio;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NegocioRepo extends MongoRepository<Negocio, String> {

    Optional<Negocio> findByNombre(String nombre);
    Optional<Negocio> findByCodigo(String codigo);
    Boolean existsNegocioByNombre(String nombre);
    Boolean existsNegocioByCodigo(String codigo);
    List<Negocio> findByCodigoCliente(String codigoCliente);
    List<Negocio> findByEstadoNegocio(EstadoNegocio estadoRegistro);
    List<Negocio> findByTipoNegocio(TipoNegocio tipoNegocio);
    List<Negocio> findByNombreContaining(String nombre);
    List<Negocio> findByUbicacion(Ubicacion ubicacion);
    List<Negocio> findByNombreContainingAndTipoNegocio(String nombre, TipoNegocio tipoNegocio);
    List<Negocio> findByNombreContainingAndUbicacion(String nombre, Ubicacion ubicacion);
    List<Negocio> findByNombreContainingAndTipoNegocioAndUbicacion(String nombre, TipoNegocio tipoNegocio, Ubicacion ubicacion);
    List<Negocio> findTop5ByOrderByCalificacionPromedioDesc();


}