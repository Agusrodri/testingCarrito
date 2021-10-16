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
    public void verificarCantidadAgregadaNoSeaMayorAstockProducto(){
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









}
