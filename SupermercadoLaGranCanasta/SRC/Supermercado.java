import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Clase principal gestion supermercado
public class Supermercado {

    // Atributos
    private String nombre;
    private Inventario inventario;
    private Map<String, Cliente> clientes;
    private List<Venta> ventas;
    private List<Factura> facturas;
    private int contadorVentas;
    private int contadorFacturas;

    // Constructor composicion
    public Supermercado(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del supermercado es obligatorio.");
        }
        this.nombre = nombre;
        this.inventario = new Inventario();
        this.clientes = new LinkedHashMap<>();
        this.ventas = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.contadorVentas = 1;
        this.contadorFacturas = 1;
    }

    // Registrar producto
    public void registrarProducto(Producto producto) {
        inventario.agregarProducto(producto);
    }

    // Registrar cliente
    public void registrarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente es obligatorio.");
        }
        clientes.put(cliente.getDocumento(), cliente);
    }

    // Buscar cliente
    public Cliente buscarCliente(String documento) {
        if (documento == null || !clientes.containsKey(documento)) {
            throw new IllegalArgumentException("Cliente no encontrado: " + documento);
        }
        return clientes.get(documento);
    }

    // Crear venta sin promocion
    public Venta crearVenta(String documentoCliente) {
        Cliente cliente = buscarCliente(documentoCliente);
        String idVenta = "V-" + contadorVentas++;
        return new Venta(idVenta, cliente);
    }

    // Sobrecarga crear venta con promocion
    public Venta crearVenta(String documentoCliente, Promocion promocion) {
        Cliente cliente = buscarCliente(documentoCliente);
        String idVenta = "V-" + contadorVentas++;
        return new Venta(idVenta, cliente, promocion);
    }

    // Agregar producto con cantidad
    public void agregarProductoAVenta(Venta venta, String codigoProducto, double cantidad) throws StockInsuficienteException, ProductoVencidoException {
        if (venta == null) {
            throw new IllegalArgumentException("La venta es obligatoria.");
        }
        Producto producto = inventario.buscarProducto(codigoProducto);
        venta.agregarLinea(producto, cantidad);
    }

    // Sobrecarga agregar unidad
    public void agregarProductoAVenta(Venta venta, String codigoProducto) throws StockInsuficienteException, ProductoVencidoException {
        agregarProductoAVenta(venta, codigoProducto, 1.0);
    }

    // Finalizar pago, inventario y factura
    public Factura finalizarVenta(Venta venta, MedioPago medioPago) throws VentaVaciaException, StockInsuficienteException, ProductoVencidoException {
        if (venta == null) {
            throw new IllegalArgumentException("La venta es obligatoria.");
        }
        if (!venta.procesarPago(medioPago)) {
            throw new IllegalStateException("No se pudo procesar el pago de la venta.");
        }
        inventario.descontarVenta(venta);
        ventas.add(venta);

        String numeroFactura = "F-" + contadorFacturas++;
        Factura factura = new Factura(numeroFactura, venta);
        facturas.add(factura);
        return factura;
    }

    // Consultar alertas stock minimo
    public List<Producto> obtenerAlertasStock() {
        return inventario.obtenerProductosBajoMinimo();
    }

    // Calcular ingresos totales
    public double calcularTotalVentas() {
        double total = 0.0;
        for (Venta venta : ventas) {
            total += venta.getTotal();
        }
        return total;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public Collection<Cliente> getClientes() {
        return Collections.unmodifiableCollection(clientes.values());
    }

    public List<Venta> getVentas() {
        return Collections.unmodifiableList(ventas);
    }

    public List<Factura> getFacturas() {
        return Collections.unmodifiableList(facturas);
    }
}