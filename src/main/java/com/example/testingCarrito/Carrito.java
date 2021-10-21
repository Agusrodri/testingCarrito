package com.example.testingCarrito;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Carrito {
    private Integer subtotal = 0;
    private List<DetalleCarrito> detallesCarrito= new ArrayList<>();

    public void agregarProductoAlCarrito(Producto producto, Integer cantidad){

        Integer precioDetalle = producto.getPrecioProducto() * cantidad;

        if(cantidad<= producto.getCantidadStock() && cantidad>0){
            DetalleCarrito detalle= new DetalleCarrito(cantidad,producto.getDescripcionProducto(),
                    producto.getNombreProducto(), producto.getPrecioProducto(),
                    producto);
            this.subtotal += precioDetalle;
            detallesCarrito.add(detalle);

            System.out.println("Se agregaron " + cantidad +" "+ producto.getNombreProducto() + " al carrito");
        }else{
            System.out.println("ERROR: La cantidad seleccionada es mayor a la cantidad en stock, es negativa o cero");
        }
    }

    public void eliminarProductoDelCarrito(DetalleCarrito detalle){
        try{
            this.subtotal -= detalle.getPrecioProducto()*detalle.getCantidadProducto();
            detallesCarrito.remove(detalle);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void mostrarCantidadesAComprar(){

        for(DetalleCarrito d: detallesCarrito){
            System.out.println( "El producto "+ d.getNombreProducto() + " tiene "+ d.getCantidadProducto() + " unidades en el carrito");
        }

    }

    public void aumentarCantidadAIngresar(DetalleCarrito detalle){
        Integer aumentado = detalle.getCantidadProducto() + 1;
        detalle.setCantidadProducto(aumentado);
        System.out.printf("Cantidad aumentada en 1 unidad");
    }

    public void disminuirCantidadAIngresar(DetalleCarrito detalle) {
        if (detalle.getCantidadProducto()>1) {
            Integer disminuido = detalle.getCantidadProducto() - 1;
            System.out.printf("Cantidad disminuida en 1 unidad");
            detalle.setCantidadProducto(disminuido);
        }else{
            System.out.println("ERROR: No se permite disminuir la cantidad si la misma es 1");
        }
    }

    public void ingresarCantidadAComprar(){
        Scanner scanner = new Scanner(System.in);
        Integer cantidad;

        try{
            System.out.println("Ingrese cantidad");
            cantidad = scanner.nextInt();
        }catch(Exception e){
            System.out.println(e.getMessage() + " Solo se pueden ingresar números");
        }
    }
    public void vaciarCarrito(){
        this.detallesCarrito.clear();
    }

}



