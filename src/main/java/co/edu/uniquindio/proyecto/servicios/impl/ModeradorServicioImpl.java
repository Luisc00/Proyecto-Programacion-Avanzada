package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ActualizarModeradorDTO;
import co.edu.uniquindio.proyecto.dto.CambioPasswordDTO;
import co.edu.uniquindio.proyecto.dto.ItemModeradorDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.modelo.Moderador;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ModeradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModeradorServicioImpl implements ModeradorServicio {

    //El moderador se debe inicializar desde el programa

    private final ModeradorRepo moderadorRepo;


    public ModeradorServicioImpl(ModeradorRepo moderadorRepo) {
        this.moderadorRepo = moderadorRepo;
    }

    // Método de inicialización para crear un moderador
    public void inicializarModerador(Moderador moderador) throws Exception {

        if(existeId(moderador.getCodigo())){
            throw new Exception("el moderador ya existe");
        }
        if(existeEmail(moderador.getEmail())){
            throw new Exception("el correo ya esta en uso");
        }
        moderador.setNombre(moderador.getNombre());
        moderador.setPassword(moderador.getPassword());
        moderador.setEmail(moderador.getEmail());
        moderador.setEstadoRegistro(EstadoRegistro.ACTIVO);
        moderador.setCodigo(moderador.getCodigo());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(moderador.getPassword());
        moderador.setPassword(passwordEncriptada);

        // Guardar el moderador en la base de datos
        moderadorRepo.save(moderador);
    }

    @Override
    public void eliminarCuenta(String idCuenta) throws Exception {
        // Verificar si el moderador existe
        if (!existeId(idCuenta)) {
            throw new Exception("El moderador con ID " + idCuenta + " no existe");
        }

        Moderador moderador = obtenerModerador(idCuenta);

        // Verificar si el moderador ya está inactivo
        if (moderador.getEstadoRegistro() == EstadoRegistro.INACTIVO) {
            throw new Exception("La cuenta ya está eliminada");
        }

        // Cambiar el estado del moderador a inactivo
        moderador.setEstadoRegistro(EstadoRegistro.INACTIVO);

        // Guardar el moderador actualizado en el repositorio
        moderadorRepo.save(moderador);
    }

    @Override
    public TokenDTO solicitarCambioContraseña(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        return null;
    }

    @Override
    public void cambiarContrasena(CambioPasswordDTO cambioPasswordDTO,TokenDTO tokenDTO) throws Exception {

    }

    @Override
    public void actualizarModerador(ActualizarModeradorDTO actualizarModeradorDTO) throws Exception {
        Moderador moderador= obtenerModerador(actualizarModeradorDTO.codigo());
        if (existeEmail(actualizarModeradorDTO.email())) {
            throw new Exception("El correo ya se encuentra registrado");
        }
        if (existeCuentaEliminada(actualizarModeradorDTO.codigo())) {
            throw new Exception("La cuenta ya ha sido eliminada");
        }
        //Obtenemos el cliente que se quiere actualizar y le asignamos los nuevos valores (el
        // nickname no se puede cambiar)
        moderador.setNombre(actualizarModeradorDTO.nombre());
        moderador.setEmail(actualizarModeradorDTO.email());

        //Como el objeto cliente ya tiene un id, el save() no crea un nuevo registro sino que
        // actualiza el que ya existe
        moderadorRepo.save(moderador);
    }

    private Moderador obtenerModerador(String codigo) throws Exception {
        Optional<Moderador> optionalModerador= moderadorRepo.findById(codigo);
        // Si no se encontró el cliente, lanzamos una excepción
        if (optionalModerador.isEmpty()) {
            throw new Exception("No se encontró el cliente con el id " + codigo);
        }
        return optionalModerador.get();
    }
    private boolean existeId(String codigo) {
        return moderadorRepo.findById(codigo).isPresent();
    }
    private boolean existeEmail(String email) {
        return moderadorRepo.findByEmail(email).isPresent();
    }
    private boolean existeCuentaEliminada(String codigo) throws Exception {
        Optional<Moderador> optionalModerador = moderadorRepo.findById(codigo);
        // Si no se encontró el cliente, lanzamos una excepción
        if (optionalModerador.isEmpty()) {
            throw new Exception("No se encontró el cliente con el id " + codigo);
        }
        Moderador moderador= optionalModerador.get();
        // Verificamos si el estado del cliente es INACTIVO
        if (moderador.getEstadoRegistro() == EstadoRegistro.INACTIVO) {
            return true; // La cuenta ha sido eliminada
        } else {
            throw new Exception("La cuenta ya ha sido eliminada"); // La cuenta no ha sido eliminada
        }
    }
    @Override
    public List<ItemModeradorDTO> listarModeradores() {
        List<Moderador> moderadores = moderadorRepo.findAll();
        List<ItemModeradorDTO> listaModeradores = new ArrayList<>();
        for (Moderador moderador : moderadores) {
            ItemModeradorDTO itemModeradorDTO = new ItemModeradorDTO(moderador.getCodigo(),moderador.getNombre(),moderador.getEmail());
            listaModeradores.add(itemModeradorDTO);
        }

        return listaModeradores;
    }

}
