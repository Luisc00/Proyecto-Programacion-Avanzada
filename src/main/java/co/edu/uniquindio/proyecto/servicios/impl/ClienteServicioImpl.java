package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.modelo.Imagen;
import co.edu.uniquindio.proyecto.repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
public class ClienteServicioImpl implements ClienteServicio {
    private final ClienteRepo clienteRepo;
    private final JWTUtils jwtUtils;

    private final EmailServicioImpl emailServicioImpl;


    /**
     * Registrar Clientes
     *
     * @param registroClienteDTO
     * @return
     * @throws Exception
     */
    @Override
    public String registrarseCliente(RegistroClienteDTO registroClienteDTO) throws Exception {
        if (existeEmail(registroClienteDTO.email())) {
            throw new Exception("El correo ya se encuentra registrado");
        }
        if (existeNickname(registroClienteDTO.nickname())) {
            throw new Exception("El nickname ya se encuentra registrado");
        }
        if (existeId((registroClienteDTO.codigo()))) {
            throw new Exception("El codigo ya se encuentra registrado");
        }
        //Se crea el objeto Cliente
        Cliente cliente = new Cliente();
        //Se le asignan sus campos
        cliente.setCodigo(registroClienteDTO.codigo());
        cliente.setNombre(registroClienteDTO.nombre());
        cliente.setNickname(registroClienteDTO.nickname());
        cliente.setCiudad(registroClienteDTO.ciudadResidencia());
        cliente.setFotoPerfil(registroClienteDTO.fotoPerfil());
        cliente.setEmail(registroClienteDTO.email());

        // Encriptar la contraseña antes de guardarla
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(registroClienteDTO.password());
        cliente.setPassword(passwordEncriptada);

        cliente.setRegistro(EstadoRegistro.ACTIVO);
        //Se guarda en la base de datos y obtenemos el objeto registrado
        Cliente clienteGuardado = clienteRepo.save(cliente);
        //Retornamos el id (código) del cliente registrado
        return clienteGuardado.getCodigo();
    }


    /**
     * Actualizar Clientes
     *
     * @param actualizarClienteDTO
     * @throws Exception
     */
    @Override
    public void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception {

        Cliente cliente = obtenerCliente(actualizarClienteDTO.id());

        if (existeCuentaEliminada(actualizarClienteDTO.id())) {
            throw new Exception("La cuenta ya ha sido eliminada");
        }

        cliente.setNombre(actualizarClienteDTO.nombre());
        cliente.setFotoPerfil(actualizarClienteDTO.fotoPerfil());
        cliente.setCiudad(actualizarClienteDTO.ciudadResidencia());

        //Como el objeto cliente ya tiene un id, el save() no crea un nuevo registro sino que
        // actualiza el que ya existe

        clienteRepo.save(cliente);
    }

    /**
     * Obtener detalle cliente
     *
     * @param idCuenta
     * @return
     * @throws Exception
     */
    @Override
    public DetalleClienteDTO obtenerDetalleCliente(String idCuenta) throws Exception {

        Cliente cliente = obtenerCliente(idCuenta);

        if (existeCuentaEliminada(idCuenta)) {
            throw new Exception("La cuenta ya ha sido eliminada");
        }

        //Retornamos el cliente en formato DTO
        return new DetalleClienteDTO(cliente.getCodigo(), cliente.getNombre(),
                cliente.getFotoPerfil(), cliente.getNickname(), cliente.getEmail(), cliente.getCiudad());
    }

    /**
     * eliminar cuenta
     *
     * @param idCuenta
     * @throws Exception
     */
    @Override
    public void eliminarCuenta(String idCuenta) throws Exception {
        Cliente cliente = obtenerCliente(idCuenta);

        if (existeCuentaEliminada(idCuenta)) {
            throw new Exception("La cuenta ya esta eliminada");
        }
        if(!existeId(idCuenta)){
            throw new Exception("La cuenta no existe");
        }
        cliente.setRegistro(EstadoRegistro.INACTIVO);
        clienteRepo.save(cliente);
    }

    @Override
    public TokenDTO solicitarCambioContraseña(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        HashMap<String, Object> map = new HashMap<>();

        if(!existeId(cambioPasswordDTO.id())){
            throw new Exception("el cliente no existe");
        }
        if(existeCuentaEliminada(cambioPasswordDTO.id())){
            throw new Exception("el cliente ya ha sido eliminado");
        }
        map.put("Rol","CLIENTE");
        map.put("Id",cambioPasswordDTO.id());
        map.put("email",cambioPasswordDTO.email());
        TokenDTO token = new TokenDTO(jwtUtils.generarToken(cambioPasswordDTO.email(), map));

        EmailDTO email = new EmailDTO("restablecer contraseña",
                "Toque su link o copie su token: " + token, cambioPasswordDTO.email());
        emailServicioImpl.enviarCorreo(email);

        return token;
    }

    @Override
    public void cambiarContrasena(CambioPasswordDTO cambioPasswordDTO, TokenDTO tokenDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findById(cambioPasswordDTO.id());
        Cliente cliente = clienteOptional.get();

        if(!existeId(cambioPasswordDTO.id())){
            throw new Exception("el cliente no existe");
        }
        if(existeCuentaEliminada(cambioPasswordDTO.id())){
            throw new Exception("el cliente ha sido eliminado");
        }
        if(!existeEmail(cambioPasswordDTO.email())){
            throw new Exception("el email no existe");
        }

        String token = tokenDTO.token();
        Jws<Claims> jwtClaims = jwtUtils.parseJwt(token);

        String emailFromToken =(String) jwtClaims.getBody().get("email");
        String idFromToken = (String) jwtClaims.getBody().get("Id");

        if (emailFromToken.equals(cliente.getEmail()) && idFromToken.equals(cambioPasswordDTO.id())) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordEncriptada = passwordEncoder.encode(cambioPasswordDTO.passwordNueva());
            cliente.setPassword(passwordEncriptada);
            clienteRepo.save(cliente);
            }
    }

    @Override
    public List<ItemClienteDTO> listarCliente() {

        List<Cliente> clientes = clienteRepo.findAll();

        List<ItemClienteDTO> items = new ArrayList<>();

        for (Cliente cliente : clientes) {
            items.add(new ItemClienteDTO(cliente.getCodigo(), cliente.getNombre(),
                    cliente.getFotoPerfil(), cliente.getNickname(), cliente.getCiudad()));
        }
        return items;
    }

    //Validaciones

    private boolean existeEmail(String email) {
        return clienteRepo.findByEmail(email).isPresent();
    }

    private boolean existeId(String codigo) {
        return clienteRepo.findById(codigo).isPresent();
    }

    private boolean existeNickname(String nickname) {
        return clienteRepo.findByNickname(nickname).isPresent();
    }
    private Cliente obtenerCliente(String codigo) throws Exception {
        Optional<Cliente> optionalCliente = clienteRepo.findById(codigo);

        if (optionalCliente.isEmpty()) {
            throw new Exception("No se encontró el cliente con el id " + codigo);
        }
        return optionalCliente.get();
    }
    private boolean existeCuentaEliminada(String codigo) throws Exception {
        Optional<Cliente> optionalCliente = clienteRepo.findById(codigo);

        if (optionalCliente.isEmpty()) {
            throw new Exception("No se encontró el cliente con el id " + codigo);
        }
        Cliente cliente = optionalCliente.get();

        if (cliente.getRegistro() == EstadoRegistro.INACTIVO) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param actualizarClienteDTO
     * @return
     * @throws Exception

    @Override
    public boolean editarPerfil(ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findById(actualizarClienteDTO.id());

        if (clienteOptional.isEmpty()){
            throw  new Exception("No existe ningun cliente");
        }

        Cliente cliente = clienteOptional.get();

        if (cliente.getRegistro() == EstadoRegistro.INACTIVO) {
            throw new Exception("El usuario está inactivo");
        }
        imagenesServicioImpl.eliminarImagen(cliente.getFotoPerfil().getId());
        Map imagenInfo = imagenesServicioImpl.subirImagen((MultipartFile) actualizarClienteDTO.fotoPerfil());
        Imagen imagen = new Imagen((String) imagenInfo.get("secure_url"), (String) imagenInfo.get("public_id"));

        cliente.setNombre(actualizarClienteDTO.nombre());
        cliente.setNickname(actualizarClienteDTO.nickname());
        cliente.setCiudad(actualizarClienteDTO.ciudadResidencia());
        cliente.setFotoPerfil(imagen);

        try{
            clienteRepo.save(cliente);
        }catch (Exception e){
            throw new Exception("Error del servidor");
        }
        emailServicioImpl.enviarCorreo(new EmailDTO("Cuenata actualizada", "Tu cuenta ha sido actualizada exitosamente", cliente.getEmail()));

        return true;
    }
     */
}
