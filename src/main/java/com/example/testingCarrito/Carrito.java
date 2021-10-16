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

        if(cantidad<= producto.getCantidadStock() && cantidad>0){
            DetalleCarrito detalle= new DetalleCarrito(cantidad,producto.getDescripcionProducto(),
                    producto.getNombreProducto(), producto.getPrecioProducto(),
                    producto);
            detallesCarrito.add(detalle);
            System.out.println("Se agregaron " + cantidad +" "+ producto.getNombreProducto() + " al carrito");
        }else{
            System.out.println("ERROR: La cantidad seleccionada es mayor a la cantidad en stock, es negativa o cero");
        }
    }

    public void mostrarCantidadesAComprar(){

        for(DetalleCarrito d: detallesCarrito){
            System.out.println( "El producto "+ d.getNombreProducto() + " tiene "+ d.getCantidadProducto() + " unidades en el carrito");
        }

    }

    public int aumentarCantidadAIngresar(int cantidadAaumentar){

        int resultado= cantidadAaumentar+1;
        System.out.printf("Cantidad aumentada en 1 unidad");
        return resultado;


    }

    public int disminuirCantidadAIngresar(int cantidadAaumentar) {
        if (cantidadAaumentar>1) {
            int resultado = cantidadAaumentar - 1;
            System.out.printf("Cantidad disminuida en 1 unidad");
            return resultado;

        }else{
            System.out.println("ERROR: No se permite disminuir la cantidad si la misma es 1");
            return  cantidadAaumentar;
        }
    }
}



