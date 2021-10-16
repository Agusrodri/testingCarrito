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
        if(cantidad<= producto.getCantidadStock()){
            DetalleCarrito detalle= new DetalleCarrito(cantidad,producto.getDescripcionProducto(),
                    producto.getNombreProducto(), producto.getPrecioProducto(),
                    producto);
            detallesCarrito.add(detalle);
        }else{
            System.out.println("La cantidad seleccionada es mayor a la cantidad en stock");
        }
    }
    
}
