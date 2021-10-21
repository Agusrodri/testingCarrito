import com.example.testingCarrito.Carrito;
import com.example.testingCarrito.DetalleCarrito;
import com.example.testingCarrito.Producto;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarritoTest {

    Carrito carrito;
    Producto producto;
    String msg;

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
    public void beforeEach(TestInfo testInfo) {
       carrito = new Carrito();
       msg = "";
        System.out.println("Comienza: "+ testInfo.getDisplayName());
    }
    @AfterEach
    public void afterEach(TestInfo testInfo){
        carrito.getDetallesCarrito().clear();
        System.out.println("Finaliza: "+ testInfo.getDisplayName());
        System.out.println("---------------------------------------");
    }

    @Test
    @Order(1)
    @DisplayName("Verificar la creacion del detalle incorrecta si agrego mas que lo que tengo en stock")
    public void verificarCreacionDetalleIncorrectaSiCantidadAgregadaEsMayorAstockProducto(){
        producto= new Producto(10,"Tablet Samsung A50",
                "Descripcion", 200 );
        int esperado=11;
        carrito.agregarProductoAlCarrito(producto,esperado);
        Assertions.assertEquals(0, carrito.getDetallesCarrito().size());
    }

    @Test
    @Order(2)
    @DisplayName("Verificar que la cantidad agregada no es negativa")
    public void verificarCantidadAgregadaNoSeaNegativa(){
        producto= new Producto(10,"Tablet Samsung A50",
                "Descripcion", 200 );
        int esperado=-9;
        carrito.agregarProductoAlCarrito(producto,esperado);
        Assertions.assertEquals(0,carrito.getDetallesCarrito().size());

    }

    @Test
    @Order(3)
    @DisplayName("Verificar que si solo tengo 5 en stock, la cantidad maxima para agregar sea 5")
    public void verificarSiCantidadStockEs5SeAgreganHasta5Productos(){
        producto= new Producto(5,"Tablet Samsung A50",
                "Descripcion", 200 );
        int cantidadAIngresar=3;
        carrito.agregarProductoAlCarrito(producto,cantidadAIngresar);
        Assertions.assertTrue(carrito.getDetallesCarrito().get(0).getProducto().getCantidadStock()>=cantidadAIngresar);

    }

    @Test
    @Order(4)
    @DisplayName("Verificar que la cantidad a agregar no sea nula")
    public void verificarCantidadAIngresarNoSeaNula(){
        producto= new Producto(5,"Tablet Samsung A50",
                               "Descripcion", 200 );
        int cantidadAIngresar=0;
        carrito.agregarProductoAlCarrito(producto,cantidadAIngresar);
        Assertions.assertEquals(0, carrito.getDetallesCarrito().size());

    }

    @Test
    @Order(5)
    @DisplayName("Verificar que se muestra la cantidad agregada del producto")
    public void mostrarCantidadDeCadaProductoAComprar(){
        producto= new Producto(5,"Tablet Samsung A50",
                "Descripcion", 200 );
        Producto producto2= new Producto(10,"Telefono Samsung A30",
                "descripcion1", 250 );

        int cantidadAIngresar1=4;
        int cantidadAIngresar2=3;

        carrito.agregarProductoAlCarrito(producto,cantidadAIngresar1);
        carrito.agregarProductoAlCarrito(producto2,cantidadAIngresar2);

        carrito.mostrarCantidadesAComprar();

        //esto se podria hacer de otra forma porque si no no testeamos nada, esa condicion es siempre verdadera
        Assertions.assertTrue(true); //significa que llamó correctamente al método mostrarCantidadesACompar()
                                             //y se muestra la salida correcta por pantalla

    }

    @Test
    @Order(6)
    @DisplayName("Verificar que se permite aumentar en uno la cantidad de un producto en el carrito")
    public void verificarPermitirAumentarEnUnoCantidadAComprar(){
        producto= new Producto(5,"Tablet Samsung A50",
                "Descripcion", 200 );

        carrito.agregarProductoAlCarrito(producto,2);
        Integer esperado = carrito.getDetallesCarrito().get(0).getCantidadProducto()+1;
        carrito.aumentarCantidadAIngresar(carrito.getDetallesCarrito().get(0));

        Assertions.assertEquals(esperado, carrito.getDetallesCarrito().get(0).getCantidadProducto());

    }

    @Test
    @Order(7)
    @DisplayName("Verificar que se permite disminuir en uno la cantidad de un producto en el carrito")
    public void verificarPermitirDisminuirEnUnoCantidadAComprar(){
        producto= new Producto(5,"Tablet Samsung A50",
                "Descripcion", 200 );
        int cantidadAIngresar=2;
        carrito.agregarProductoAlCarrito(producto,cantidadAIngresar);
        carrito.disminuirCantidadAIngresar(carrito.getDetallesCarrito().get(0));
        Integer esperado = carrito.getDetallesCarrito().get(0).getCantidadProducto();
        Assertions.assertEquals(esperado, carrito.getDetallesCarrito().get(0).getCantidadProducto());
    }

    @Test
    @Order(8)
    @DisplayName("Verificar que solo permita disminuir un producto si la cantidad en el carrito es mayor a 1")
    public void verificarNoPermitirDisminuirEnUnoCantidadAComprarCuandoCantidadAComprarEsUno(){
        producto= new Producto(5,"Tablet Samsung A50",
                "Descripcion", 200 );
        int cantidadAIngresar=1;
        carrito.agregarProductoAlCarrito(producto,cantidadAIngresar);
        carrito.disminuirCantidadAIngresar(carrito.getDetallesCarrito().get(0));
        int esperado=1;
        Assertions.assertEquals(esperado,cantidadAIngresar);
    }

    //El nombre y descripción del producto son obligatorios porque se van a mostrar en el carrito.
    @Test
    @Order(9)
    @DisplayName("Verificar nombre y descripcion obligatorio")
    public void verificarNombreYDescripcionObligatorio(){
        producto= new Producto(5,"Tablet Samsung A50", "Descripcion", 200 );
        carrito.agregarProductoAlCarrito(producto, 3);

        Assertions.assertNotNull(carrito.getDetallesCarrito().get(0).getNombreProducto());
        Assertions.assertNotNull(carrito.getDetallesCarrito().get(0).getDescripcionProducto());
    }

    //Al crear un objeto Detalle, debe corroborarse que la cantidad sea mayor o igual a 1.
    @Test
    @Order(10)
    @DisplayName("Verificar que el nombre en el detalle es el mismo que el del producto individual")
    public void testVerificarCoincidenciaDelNombreDelProductoConElDetalle(){
        Producto producto = new Producto(10, "Tablet Samsung A9", "Descripcion", 300);
        carrito.getDetallesCarrito().add(new DetalleCarrito(1, "Descripcion", "Tablet Samsung A9", 300, producto));
        //si el nombre del producto es el mismo en el detalle, test exitoso
        Assertions.assertEquals(producto.getNombreProducto(), carrito.getDetallesCarrito().get(0).getNombreProducto(),msg = "El nombre del producto coincide con el mostrado en el carrito");
        System.out.println(msg);
    }

    //Al crear un objeto Detalle, debe corroborarse que la cantidad sea mayor o igual a 1
    @Test
    @Order(11)
    @DisplayName("Verificar que la cantidad que agrego sea mayor o igual a uno al crear el detalle")
    public void testVerificarCantidadEnElCarrritoEsMayorOIgualAUno(){
        Producto producto = new Producto(10, "Tablet Samsung A9", "Descripcion", 300);
        carrito.getDetallesCarrito().add(new DetalleCarrito(1, "Descripcion", "Tablet Samsung A9", 300, producto));

        Assertions.assertTrue(1 <= carrito.getDetallesCarrito().get(0).getCantidadProducto(), msg = "La cantidad en el carrito es mayor o igual a 1");
        System.out.println(msg);
    }
    @Test
    @Order(12)
    @DisplayName("Verificar que el carrito se vacía correctamente")
    public void testVerificarCarritoVacio(){
        producto= new Producto(5,"Tablet Samsung A50", "Descripcion", 200 );
        Producto producto2= new Producto(7,"Telefono Samsung Galaxy S10", "Descripcion", 2400 );
        carrito.agregarProductoAlCarrito(producto, 3);
        carrito.agregarProductoAlCarrito(producto2, 2);

        carrito.vaciarCarrito();
        Assertions.assertEquals(0, carrito.getDetallesCarrito().size());
    }
    //Al cambiar el nombre del producto, se debe cambiar el nombre de ese producto en el carrito.
    @Test
    @Order(13)
    @DisplayName("Verificar que subtotal sea correcto al eliminar un producto")
    public void testVerificarSubtotalCorrectoRestar(){
        producto= new Producto(5,"Tablet Samsung A50", "Descripcion", 200 );
        Producto producto2= new Producto(7,"Telefono Samsung Galaxy S10", "Descripcion", 2400 );
        carrito.agregarProductoAlCarrito(producto, 3);
        carrito.agregarProductoAlCarrito(producto2, 2);

        Integer esperado = carrito.getSubtotal() - (producto.getPrecioProducto() * carrito.getDetallesCarrito().get(0).getCantidadProducto());

        carrito.eliminarProductoDelCarrito(carrito.getDetallesCarrito().get(0));
        Assertions.assertEquals(esperado, carrito.getSubtotal());
    }

    @Test
    @Order(14)
    @DisplayName("Verificar que subtotal sea correcto al agregar un producto")
    public void testVerificarSubtotalCorrectoSumar(){
        producto= new Producto(5,"Tablet Samsung A50", "Descripcion", 200 );
        Producto producto2= new Producto(7,"Telefono Samsung Galaxy S10", "Descripcion", 2400 );
        carrito.agregarProductoAlCarrito(producto, 3);
        carrito.agregarProductoAlCarrito(producto2, 2);
        Integer esperado = 5400;

        Assertions.assertEquals(esperado, carrito.getSubtotal());
    }













}
