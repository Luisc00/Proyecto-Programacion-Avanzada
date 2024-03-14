package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.dto.CambioPasswordDTO;
import co.edu.uniquindio.proyecto.dto.RegistroClienteDTO;
import co.edu.uniquindio.proyecto.dto.ItemClienteDTO;
import co.edu.uniquindio.proyecto.dto.SesionDTO;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteServicioImpl implements ClienteServicio {
    private final ClienteRepo clienteRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }


    @Override
    public String registrarseCliente(RegistroClienteDTO registroClienteDTO) throws Exception {
        if (existeEmail(registroClienteDTO.email())) {
            throw new Exception("El correo ya se encuentra registrado");
        }
        if (existeNickname(registroClienteDTO.email())) {
            throw new Exception("El correo ya se encuentra registrado");
        }
        //Se crea el objeto Cliente
        Cliente cliente = new Cliente();
        //Se le asignan sus campos
        cliente.setNombre(registroClienteDTO.nombre());
        cliente.setNickname(registroClienteDTO.nickname());
        cliente.setCiudad(registroClienteDTO.ciudadResidencia());
        cliente.setFotoPerfil(registroClienteDTO.fotoPerfil());
        cliente.setEmail(registroClienteDTO.email());
        cliente.setPassword(registroClienteDTO.password());
        cliente.setEstado(EstadoRegistro.ACTIVO);
        //Se guarda en la base de datos y obtenemos el objeto registrado
        Cliente clienteGuardado = clienteRepo.save(cliente);
        //Retornamos el id (código) del cliente registrado
        return clienteGuardado.getCodigo();
    }

    private boolean existeEmail(String email) {
        return clienteRepo.findByEmail(email).isPresent();
    }
    private boolean existeNickname(String nickname) {
        return clienteRepo.findByNickname(nickname).isPresent();
    }

    @Override
    public void editarPerfilCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        //Buscamos el cliente que se quiere actualizar
        Optional<Cliente> optionalCliente = clienteRepo.findById( actualizarClienteDTO.id() );
         //Si no se encontró el cliente, lanzamos una excepción
        if(optionalCliente.isEmpty()){
            throw new Exception("No se encontró el cliente a actualizar");
        }
         //Obtenemos el cliente que se quiere actualizar y le asignamos los nuevos valores (el
        //nickname no se puede cambiar)
        Cliente cliente = optionalCliente.get();
        cliente.setNombre( actualizarClienteDTO.nombre() );
        cliente.setFotoPerfil( actualizarClienteDTO.fotoPerfil() );
        cliente.setCiudad( actualizarClienteDTO.ciudadResidencia() );
        cliente.setEmail( actualizarClienteDTO.email() );
//Como el objeto cliente ya tiene un id, el save() no crea un nuevo registro sino que
        //actualiza el que ya existe
        clienteRepo.save(cliente);
    }

    @Override
    public void iniciarSersion(SesionDTO sesionDTO) throws Exception {

    }

    @Override
    public void inicioarSersion(SesionDTO sesionDTO) throws Exception {

    }

    @Override
    public void eliminarCuenta(String idCuenta) throws Exception {
    

    }

    @Override
    public void enviarCodigoVerificacion(String email) throws Exception {

    }

    @Override
    public void cambiarContrasena(CambioPasswordDTO cambioPasswordDTO) throws Exception {

    }

    @Override
    public List<ItemClienteDTO> listarCliente() {
        return null;
    }

    @Override
    public List<ItemClienteDTO>listarCliente(int pagina){
        List<Cliente> clientes= (List<Cliente>) clienteRepo.findAll(PageRequest.of(pagina,10));
        return clientes.stream().map(c ->
                new ItemClienteDTO(c.getCodigo(),
                        c.getNombre(),
                        c.getFotoPerfil(),
                        c.getNickname(),
                        c.getNickname())
        ).toList();
    }
}