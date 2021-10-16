package com.example.testingCarrito;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Carrito {
    private Integer subtotal;
    private List<DetalleCarrito> detallesCarrito= new ArrayList<>();

    public void agregarProductoAlCarrito(Producto producto, Integer cantidad){

        if(cantidad<= producto.getCantidadStock() && cantidad>=0){
            DetalleCarrito detalle= new DetalleCarrito(cantidad,producto.getDescripcionProducto(),
                    producto.getNombreProducto(), producto.getPrecioProducto(),
                    producto);
            detallesCarrito.add(detalle);
            System.out.println("Se agregaron" + cantidad + " productos al carrito");
        }else{
            System.out.println("ERROR: La cantidad seleccionada es mayor a la cantidad en stock o es negativa");
        }
    }
}
