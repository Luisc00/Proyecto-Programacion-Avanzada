package co.edu.uniquindio.proyecto.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public enum TipoNegocio {
    PANADERIA,
    OTRO,
    CAFETERIA,
    BAR,
    RESTAURANTE,
    DISCOTECA,
    SUPERMERCADO,
    TIENDA;
}
