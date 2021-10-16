package com.example.testingCarrito;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DetalleCarrito {
    private Integer cantidadProducto;
    private String descripcionProducto;
    private String nombreProducto;
    private Integer precioProducto;
    private Producto producto;


}
