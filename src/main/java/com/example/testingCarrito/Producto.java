package com.example.testingCarrito;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Producto {

    private Integer cantidadStock;
    private String nombreProducto;
    private String descripcionProducto;
    private Integer precioProducto;


}
