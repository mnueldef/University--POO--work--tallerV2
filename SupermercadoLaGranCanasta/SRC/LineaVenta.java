// Clase linea de venta
public class LineaVenta {

    // Atributos
    private Producto producto;
    private double cantidad;

    // Constructor
    public LineaVenta(Producto producto, double cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto es obligatorio.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        this.producto = producto;
        this.cantidad = cantidad;
    }

    // Subtotal sin descuento ni IVA
    public double getSubtotalBruto() {
        return producto.getPrecioBase() * cantidad;
    }

    // Descuento propio del producto
    public double getDescuentoProducto() {
        return getSubtotalBruto() * producto.getPorcentajeDescuento();
    }

    // Subtotal neto antes de IVA
    public double getSubtotal() {
        return getSubtotalBruto() - getDescuentoProducto();
    }

    // Valor IVA de la linea
    public double getIva() {
        return getSubtotal() * producto.getPorcentajeIva();
    }

    // Total linea con IVA
    public double getTotal() {
        return getSubtotal() + getIva();
    }

    // Getters
    public Producto getProducto() {
        return producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    // toString
    @Override
    public String toString() {
        return producto.getNombre() + " x" + cantidad + " | Subtotal: $" + getSubtotal() + " | IVA: $" + getIva() + " | Total: $" + getTotal();
    }
}