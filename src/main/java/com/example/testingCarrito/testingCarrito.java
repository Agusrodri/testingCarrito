package com.example.testingCarrito;


public class testingCarrito {
    public static void main(String[] args) {

        Producto producto= new Producto(10,"Tablet Samsung A50","jsahdbfjasd", 200 );

        Carrito carrito = new Carrito();
        carrito.ingresarCantidadAComprar();
    }
}
