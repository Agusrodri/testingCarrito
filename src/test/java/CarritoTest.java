import com.example.testingCarrito.Carrito;
import com.example.testingCarrito.DetalleCarrito;
import com.example.testingCarrito.Producto;

import org.junit.jupiter.api.*;

public class CarritoTest {

    Carrito carrito;
    Producto producto;


    @BeforeAll
    public static void beforeAll(){
        System.out.println();
        System.out.println("Comienzan los tests de Carrito");
        System.out.println();
    }
    @AfterAll
    public static void afterAll(){
        System.out.println();
        System.out.println("Finalizan los tests de Carrito");
        System.out.println();
    }
    @BeforeEach
    public void beforeEach() {
       carrito = new Carrito();


    }

    @Test
    public void verificarCreacionDetalleIncorrectaSiCantidadAgregadaEsMayorAstockProducto(){
        producto= new Producto(10,"Tablet Samsung A50",
                "jsahdbfjasd", 200 );
        int esperado=11;
        carrito.agregarProductoAlCarrito(producto,esperado);
        Assertions.assertTrue(carrito.getDetallesCarrito().size()==0);

    }

    @Test
    public void verificarCantidadAgregadaNoSeaNegativa(){
        producto= new Producto(10,"Tablet Samsung A50",
                "jsahdbfjasd", 200 );
        int esperado=-9;
        carrito.agregarProductoAlCarrito(producto,esperado);
        Assertions.assertTrue(carrito.getDetallesCarrito().size()==0);

    }

    @Test
    public void verificarSiCantidadStockEs5SeAgreganHasta5Productos(){
        producto= new Producto(5,"Tablet Samsung A50",
                "jsahdbfjasd", 200 );
        int cantidadAIngresar=3;
        carrito.agregarProductoAlCarrito(producto,cantidadAIngresar);
        Assertions.assertTrue(carrito.getDetallesCarrito().get(0).getProducto().getCantidadStock()>=cantidadAIngresar);

    }

    @Test
    public void verificarCantidadAIngresarNoSeaNula(){
        producto= new Producto(5,"Tablet Samsung A50",
                               "jsahdbfjasd", 200 );
        int cantidadAIngresar=0;
        carrito.agregarProductoAlCarrito(producto,cantidadAIngresar);
        Assertions.assertTrue(carrito.getDetallesCarrito().size()==0);

    }

    @Test
    public void mostrarCantidadDeCadaProductoAComprar(){
        producto= new Producto(5,"Tablet Samsung A50",
                "jsahdbfjasd", 200 );
        Producto producto2= new Producto(10,"Telefono Samsung A30",
                "descripcion1", 250 );

        int cantidadAIngresar1=4;
        int cantidadAIngresar2=3;

        carrito.agregarProductoAlCarrito(producto,cantidadAIngresar1);
        carrito.agregarProductoAlCarrito(producto2,cantidadAIngresar2);

        carrito.mostrarCantidadesAComprar();

        Assertions.assertTrue(true); //significa que llamó correctamente al método mostrarCantidadesACompar()
                                             //y se muestra la salida correcta por pantalla

    }

    @Test
    public void verificarPermitirAumentarEnUnoCantidadAComprar(){
        producto= new Producto(5,"Tablet Samsung A50",
                "jsahdbfjasd", 200 );
        int cantidadAIngresar=2;
        carrito.agregarProductoAlCarrito(producto,cantidadAIngresar);
        carrito.aumentarCantidadAIngresar(carrito.getDetallesCarrito().get(0).getCantidadProducto());
        int esperado=3;
        Assertions.assertEquals(esperado,cantidadAIngresar+1);

    }

    @Test
    public void verificarPermitirDisminuirEnUnoCantidadAComprar(){
        producto= new Producto(5,"Tablet Samsung A50",
                "jsahdbfjasd", 200 );
        int cantidadAIngresar=2;
        carrito.agregarProductoAlCarrito(producto,cantidadAIngresar);
        carrito.disminuirCantidadAIngresar(carrito.getDetallesCarrito().get(0).getCantidadProducto());
        int esperado=1;
        Assertions.assertEquals(esperado,cantidadAIngresar-1);

    }

    @Test
    public void verificarNoPermitirDisminuirEnUnoCantidadAComprarCuandoCantidadAComprarEsUno(){
        producto= new Producto(5,"Tablet Samsung A50",
                "jsahdbfjasd", 200 );
        int cantidadAIngresar=1;
        carrito.agregarProductoAlCarrito(producto,cantidadAIngresar);
        carrito.disminuirCantidadAIngresar(carrito.getDetallesCarrito().get(0).getCantidadProducto());
        int esperado=1;
        Assertions.assertEquals(esperado,cantidadAIngresar);

    }















}
