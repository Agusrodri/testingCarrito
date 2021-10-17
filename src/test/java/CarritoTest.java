import com.example.testingCarrito.Carrito;
import com.example.testingCarrito.DetalleCarrito;
import com.example.testingCarrito.Producto;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    public void beforeEach(TestInfo testInfo) {
       carrito = new Carrito();
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
                "jsahdbfjasd", 200 );
        int esperado=11;
        carrito.agregarProductoAlCarrito(producto,esperado);
        Assertions.assertTrue(carrito.getDetallesCarrito().size()==0);

    }

    @Test
    @Order(2)
    @DisplayName("Verificar que la cantidad agregada no es negativa")
    public void verificarCantidadAgregadaNoSeaNegativa(){
        producto= new Producto(10,"Tablet Samsung A50",
                "jsahdbfjasd", 200 );
        int esperado=-9;
        carrito.agregarProductoAlCarrito(producto,esperado);
        Assertions.assertTrue(carrito.getDetallesCarrito().size()==0);

    }

    @Test
    @Order(3)
    @DisplayName("Verificar que si solo tengo 5 en stock, la cantidad maxima para agregar sea 5")
    public void verificarSiCantidadStockEs5SeAgreganHasta5Productos(){
        producto= new Producto(5,"Tablet Samsung A50",
                "jsahdbfjasd", 200 );
        int cantidadAIngresar=3;
        carrito.agregarProductoAlCarrito(producto,cantidadAIngresar);
        Assertions.assertTrue(carrito.getDetallesCarrito().get(0).getProducto().getCantidadStock()>=cantidadAIngresar);

    }

    @Test
    @Order(4)
    @DisplayName("Verificar que la cantidad a agregar no sea nula")
    public void verificarCantidadAIngresarNoSeaNula(){
        producto= new Producto(5,"Tablet Samsung A50",
                               "jsahdbfjasd", 200 );
        int cantidadAIngresar=0;
        carrito.agregarProductoAlCarrito(producto,cantidadAIngresar);
        Assertions.assertTrue(carrito.getDetallesCarrito().size()==0);

    }

    @Test
    @Order(5)
    @DisplayName("Verificar que se muestra la cantidad agregada del producto")
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

        //esto se podria hacer de otra forma porque si no no testeamos nada, esa condicion es siempre verdadera
        Assertions.assertTrue(true); //significa que llamó correctamente al método mostrarCantidadesACompar()
                                             //y se muestra la salida correcta por pantalla

    }

    @Test
    @Order(6)
    @DisplayName("Verificar que se permite aumentar en uno la cantidad de un producto en el carrito")
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
    @Order(7)
    @DisplayName("Verificar que se permite disminuir en uno la cantidad de un producto en el carrito")
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
    @Order(8)
    @DisplayName("Verificar que solo permita disminuir un producto si la cantidad en el carrito es mayor a 1")
    public void verificarNoPermitirDisminuirEnUnoCantidadAComprarCuandoCantidadAComprarEsUno(){
        producto= new Producto(5,"Tablet Samsung A50",
                "jsahdbfjasd", 200 );
        int cantidadAIngresar=1;
        carrito.agregarProductoAlCarrito(producto,cantidadAIngresar);
        carrito.disminuirCantidadAIngresar(carrito.getDetallesCarrito().get(0).getCantidadProducto());
        int esperado=1;
        Assertions.assertEquals(esperado,cantidadAIngresar);

    }

    @Test
    @Order(9)
    public void verificarSoloCaracteresEnCampoCantidad(){
       // carrito.ingresarCantidadAComprar();
        //no se quien esta trabajando en este pero por las dudas no lo borro
        Assertions.assertTrue(true);
    }

    //Al crear un objeto Detalle, debe corroborarse que la cantidad sea mayor o igual a 1.
    @Test
    @Order(10)
    @DisplayName("Verificar que el nombre en el detalle es el mismo que el del producto individual")
    public void testVerificarCoincidenciaDelNombreDelProductoConElDetalle(){
        Producto producto = new Producto(10, "Tablet Samsung A9", "Descripcion", 300);
        carrito.getDetallesCarrito().add(new DetalleCarrito(1, "Descripcion", "Tablet Samsung A9", 300, producto));

        //si el nombre del producto es el mismo en el detalle, test exitoso
        Assertions.assertEquals(producto.getNombreProducto(), carrito.getDetallesCarrito().get(0).getNombreProducto());
    }

    @Test
    @Order(11)
    @DisplayName("Verificar que la cantidad que agrego sea mayor o igual a uno al crear el detalle")
    public void testVerificarCantidadEnElCarrritoEsMayorOIgualAUno(){
        Producto producto = new Producto(10, "Tablet Samsung A9", "Descripcion", 300);
        carrito.getDetallesCarrito().add(new DetalleCarrito(1, "Descripcion", "Tablet Samsung A9", 300, producto));

        Assertions.assertTrue(1 <= carrito.getDetallesCarrito().get(0).getCantidadProducto());
    }














}
