package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.modelo.*;
import co.edu.uniquindio.proyecto.repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.repositorios.NegocioRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.proyecto.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModeradorServicioImpl implements ModeradorServicio {

    private final ModeradorRepo moderadorRepo;
    private final NegocioRepo negocioRepo;
    private final ClienteRepo clienteRepo;
    private final EmailServicioImpl emailServicioImpl;
    private final AutenticacionServicioImpl autenticacionServicio;
    private final JWTUtils jwtUtils;



    public void inicializarModerador(Moderador moderador) throws Exception {

        if(existeId(moderador.getCodigo())){
            throw new Exception("el codigo ya esta en uso");
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


        moderadorRepo.save(moderador);
    }

    @Override
    public void eliminarCuenta(String idCuenta) throws Exception {
        // Verificar si el moderador existe
        if (!existeId(idCuenta)) {
            throw new Exception("El moderador con ID " + idCuenta + " no existe");
        }

        Moderador moderador = obtenerModerador(idCuenta);


        if (moderador.getEstadoRegistro() == EstadoRegistro.INACTIVO) {
            throw new Exception("La cuenta ya está eliminada");
        }


        moderador.setEstadoRegistro(EstadoRegistro.INACTIVO);


        moderadorRepo.save(moderador);
    }


    @Override
    public void actualizarModerador(ActualizarModeradorDTO actualizarModeradorDTO) throws Exception {
        Moderador moderador= obtenerModerador(actualizarModeradorDTO.codigo());

        if (existeCuentaEliminada(actualizarModeradorDTO.codigo())) {
            throw new Exception("La cuenta ya ha sido eliminada");
        }

        moderador.setNombre(actualizarModeradorDTO.nombre());
        moderador.setEmail(actualizarModeradorDTO.email());

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

        if (optionalModerador.isEmpty()) {
            throw new Exception("No se encontró el cliente con el id " + codigo);
        }
        Moderador moderador= optionalModerador.get();

        if (moderador.getEstadoRegistro() == EstadoRegistro.INACTIVO) {
            return true;
        } else {
            throw new Exception("La cuenta ya ha sido eliminada");
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

    @Override
    public void aprobarNegocio(String codigoNegocio) throws Exception{
        Optional<Negocio> negocioOptional = negocioRepo.findById(codigoNegocio);
        if (negocioOptional.isEmpty()){
            throw new Exception("El lugar no pudo ser encontrado");
        }

        Negocio negocio = negocioOptional.get();
        Optional<Cliente> usuarioOptional = clienteRepo.findById(negocio.getCodigoCliente());
        Cliente cliente = usuarioOptional.get();

        if (negocio.getEstadoNegocio() != EstadoNegocio.PENDIENTE){
            throw new Exception("Este lugar se encuentra activo o rechazado");
        }
        negocio.setEstadoNegocio(EstadoNegocio.APROBADO);

        try {
            negocioRepo.save(negocio);
            emailServicioImpl.enviarCorreo(new EmailDTO("Lugar Aprobado",
                    "Tu lugar ha sido aprovado en Unilical, felicitaciones!", cliente.getEmail()));
        }catch (Exception e){
            throw new Exception("Hubo un error con la base de datos");
        }
    }

    @Override
    public boolean rechazarNegocio(RechazarNegocioDTO rechazarNegocioDTO) throws Exception{
        Optional<Negocio> negocioOptional = negocioRepo.findById(rechazarNegocioDTO.codigoNegocio());

        if (negocioOptional.isEmpty()){
            throw new Exception("El lugar no pudo ser encontrado");
        }

        Negocio negocio = negocioOptional.get();
        Optional<Cliente> clienteOptional = clienteRepo.findById(negocio.getCodigoCliente());
        Cliente cliente = clienteOptional.get();
        if (negocio.getEstadoNegocio() != EstadoNegocio.PENDIENTE){
            throw new Exception("Este lugar se encuentra activo o inactivo");
        }
        negocio.setEstadoNegocio(EstadoNegocio.RECHAZADO);

        try {
            negocioRepo.save(negocio);
            emailServicioImpl.enviarCorreo(new EmailDTO("Lugar rechazado",
                    "El lugar ha sido rechazado ya que no cumple nuestros terminos y condiciones", cliente.getEmail()));
        }catch (Exception e){
            throw new Exception("Hubo un error con la base de datos");
        }
        return true;
    }

    @Override
    public void cambiarContrasena(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findByEmail(cambioPasswordDTO.email());

        if(clienteOptional.isEmpty()){
            throw new Exception("el cliente no existe");
        }
        Cliente cliente=clienteOptional.get();

        if (existeCuentaEliminada(cliente.getCodigo())) {
            throw new Exception("El cliente ha sido eliminado");
        }

        if (!existeEmail(cambioPasswordDTO.email())) {
            throw new Exception("El email no existe");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(cambioPasswordDTO.passwordNueva());
        cliente.setPassword(passwordEncriptada);
        clienteRepo.save(cliente);
    }

}
