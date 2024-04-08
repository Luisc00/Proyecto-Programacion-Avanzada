package co.edu.uniquindio.proyecto.dto;

public record ItemNegocioDTO(
        String id,
        String nombre,
        String descripcion,
        String direccion,
        String telefono,
        String email,
        String foto,
        String propietario
) {
}
