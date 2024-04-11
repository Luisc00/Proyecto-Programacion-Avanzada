package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.utils.JWTUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class ClienteServicioImpl implements ClienteServicio {
    private final ClienteRepo clienteRepo;

    private final EmailServicioImpl emailServicio;

    private final Map<String, String> tokensRecuperacion = new HashMap<>();

    private final JWTUtils jwtUtils;

    @Override
    public Map<String, String> getTokensRecuperacion() {
        return tokensRecuperacion;
    }


    public ClienteServicioImpl(ClienteRepo clienteRepo,EmailServicioImpl emailServicio,JWTUtils jwtUtils) {
        this.clienteRepo = clienteRepo;
        this.emailServicio=emailServicio;
        this.jwtUtils = jwtUtils;

    }

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

        cliente.setEstado(EstadoRegistro.ACTIVO);
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
        if (existeEmail(actualizarClienteDTO.email())) {
            throw new Exception("El correo ya se encuentra registrado");
        }
        if (existeCuentaEliminada(actualizarClienteDTO.id())) {
            throw new Exception("La cuenta ya ha sido eliminada");
        }
        //Obtenemos el cliente que se quiere actualizar y le asignamos los nuevos valores (el
        // nickname no se puede cambiar)
        cliente.setNombre(actualizarClienteDTO.nombre());
        cliente.setFotoPerfil(actualizarClienteDTO.fotoPerfil());
        cliente.setCiudad(actualizarClienteDTO.ciudadResidencia());
        cliente.setEmail(actualizarClienteDTO.email());
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
        if(existeId(idCuenta)){
            throw new Exception("La cuenta no existe");
        }
        cliente.setEstado(EstadoRegistro.INACTIVO);
        clienteRepo.save(cliente);
    }

    @Override
    public void cambiarContrasena(CambioPasswordDTO cambioPasswordDTO) throws Exception {

    }

    @Override
    public String enviarTokenRecuperacion(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        if (existeTokenActivoParaCliente(cambioPasswordDTO.email())) {
            throw new Exception("Ya se ha generado un token de recuperación para este cliente");
        }

        // Generar el nuevo token de recuperación
        String token = emailServicio.enviarTokenRecuperacion(cambioPasswordDTO.email());

        // Almacenar el token generado junto con el ID del cliente
        tokensRecuperacion.put(token, cambioPasswordDTO.email());

        // Retornar el token generado
        return token;
    }

    @Override
    public void cambiarContrasenaConToken(String token, CambioPasswordDTO cambioPasswordDTO) throws Exception {
        // Verificar si el token existe en la lista
        if (!tokensRecuperacion.containsKey(token)) {
            throw new Exception("El token no es válido");
        }

        // Obtener el ID del cliente asociado al token
        String idCliente = tokensRecuperacion.get(token);

        // Actualizar la contraseña del cliente en la base de datos
        Cliente cliente = obtenerCliente(idCliente);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(cambioPasswordDTO.passwordNueva());
        cliente.setPassword(passwordEncriptada);
        clienteRepo.save(cliente);

        // Remover el token de la lista después de usarlo
        tokensRecuperacion.remove(token);
    }

    @Override
    public List<ItemClienteDTO> listarCliente() {
        //Obtenemos todos los clientes de la base de datos
        List<Cliente> clientes = clienteRepo.findAll();
        //Creamos una lista de DTOs de clientes
        List<ItemClienteDTO> items = new ArrayList<>();
        //Recorremos la lista de clientes y por cada uno creamos un DTO y lo agregamos a la lista
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
        // Si no se encontró el cliente, lanzamos una excepción
        if (optionalCliente.isEmpty()) {
            throw new Exception("No se encontró el cliente con el id " + codigo);
        }
        return optionalCliente.get();
    }
    private boolean existeCuentaEliminada(String codigo) throws Exception {
        Optional<Cliente> optionalCliente = clienteRepo.findById(codigo);
        // Si no se encontró el cliente, lanzamos una excepción
        if (optionalCliente.isEmpty()) {
            throw new Exception("No se encontró el cliente con el id " + codigo);
        }
        Cliente cliente = optionalCliente.get();
        // Verificamos si el estado del cliente es INACTIVO
        if (cliente.getEstado() == EstadoRegistro.INACTIVO) {
            return true; // La cuenta ha sido eliminada
        } else {
            return false; // La cuenta no ha sido eliminada
        }
    }
    private boolean existeTokenActivoParaCliente(String email) {
        // Iterar sobre el mapa de tokens de recuperación
        for (Map.Entry<String, String> entry : tokensRecuperacion.entrySet()) {
            String token = entry.getKey();
            String clienteId = entry.getValue();

            // Si el correo electrónico del cliente coincide con el proporcionado
            if (clienteRepo.findById(clienteId).orElse(null).getEmail().equals(email)) {
                // Verificar si el token está activo o ha expirado
                if (jwtUtils.tokenExpirado(token)) {
                    // Si el token ha expirado, lo eliminamos del mapa
                    tokensRecuperacion.remove(token);
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false; // No se encontró un token activo para el cliente
    }


}
