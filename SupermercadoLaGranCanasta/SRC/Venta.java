import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Clase venta
public class Venta {

    // Atributos
    private final String id;
    private LocalDateTime fecha;
    private Cliente cliente;
    private List<LineaVenta> lineas;
    private Promocion promocion;
    private MedioPago medioPago;
    private boolean pagada;

    // Constructor basico
    public Venta(String id, Cliente cliente) {
        this(id, cliente, new SinPromocion());
    }

    // Constructor con promocion
    public Venta(String id, Cliente cliente, Promocion promocion) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El id de venta es obligatorio.");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente es obligatorio.");
        }
        this.id = id;
        this.fecha = LocalDateTime.now();
        this.cliente = cliente;
        this.lineas = new ArrayList<>();
        this.promocion = (promocion != null) ? promocion : new SinPromocion();
        this.pagada = false;
    }

    // Agregar linea con cantidad
    public void agregarLinea(Producto producto, double cantidad) throws StockInsuficienteException, ProductoVencidoException {
        if (producto == null) {
            throw new IllegalArgumentException("El producto es obligatorio.");
        }
        producto.validarVenta(cantidad);
        lineas.add(new LineaVenta(producto, cantidad));
    }

    // Sobrecarga agregar unidad
    public void agregarLinea(Producto producto) throws StockInsuficienteException, ProductoVencidoException {
        agregarLinea(producto, 1.0);
    }

    // Subtotal bruto
    public double getSubtotalBruto() {
        double total = 0.0;
        for (LineaVenta linea : lineas) {
            total += linea.getSubtotalBruto();
        }
        return total;
    }

    // Descuento por productos
    public double getDescuentoProductos() {
        double total = 0.0;
        for (LineaVenta linea : lineas) {
            total += linea.getDescuentoProducto();
        }
        return total;
    }

    // Subtotal antes de IVA
    public double getSubtotalAntesIva() {
        double total = 0.0;
        for (LineaVenta linea : lineas) {
            total += linea.getSubtotal();
        }
        return total;
    }

    // Descuento por promocion
    public double getDescuentoPromocion() {
        return promocion.calcularDescuento(this);
    }

    // Descuento por tipo cliente
    public double getDescuentoCliente() {
        return cliente.calcularDescuento(getSubtotalAntesIva());
    }

    // Total IVA
    public double getTotalIva() {
        double total = 0.0;
        for (LineaVenta linea : lineas) {
            total += linea.getIva();
        }
        return total;
    }

    // Total final a pagar
    public double getTotal() {
        double neto = getSubtotalAntesIva() - getDescuentoPromocion() - getDescuentoCliente();
        return Math.max(0.0, neto + getTotalIva());
    }

    // Procesar pago
    public boolean procesarPago(MedioPago medioPago) throws VentaVaciaException {
        if (lineas.isEmpty()) {
            throw new VentaVaciaException("No se puede procesar una venta sin productos.");
        }
        if (medioPago == null || pagada) {
            return false;
        }
        double total = getTotal();
        if (medioPago.procesarPago(cliente, total)) {
            this.medioPago = medioPago;
            this.pagada = true;
            cliente.acumularBeneficio(total);
            return true;
        }
        return false;
    }

    // Setter promocion
    public void setPromocion(Promocion promocion) {
        this.promocion = (promocion != null) ? promocion : new SinPromocion();
    }

    // Getters
    public String getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<LineaVenta> getLineas() {
        return Collections.unmodifiableList(lineas);
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public boolean isPagada() {
        return pagada;
    }

    // toString
    @Override
    public String toString() {
        return "Venta #" + id + " | Cliente: " + cliente.getNombre() + " | Total: $" + getTotal() + " | Pagada: " + pagada;
    }
}