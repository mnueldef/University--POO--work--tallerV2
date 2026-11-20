import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Clase inventario
public class Inventario {

    // Atributos agregacion
    private Map<String, Producto> productos;

    // Constructor
    public Inventario() {
        this.productos = new LinkedHashMap<>();
    }

    // Registrar producto
    public void agregarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto es obligatorio.");
        }
        productos.put(producto.getCodigo(), producto);
    }

    // Buscar por codigo
    public Producto buscarProducto(String codigo) {
        if (codigo == null || !productos.containsKey(codigo)) {
            throw new IllegalArgumentException("Producto no encontrado: " + codigo);
        }
        return productos.get(codigo);
    }

    // Reponer stock
    public void reponerStock(String codigo, double cantidad) {
        Producto producto = buscarProducto(codigo);
        producto.reponerInventario(cantidad);
    }

    // Descontar stock tras venta
    public void descontarVenta(Venta venta) throws StockInsuficienteException, ProductoVencidoException {
        if (venta == null || !venta.isPagada()) {
            throw new IllegalStateException("La venta debe estar pagada para descontar inventario.");
        }
        for (LineaVenta linea : venta.getLineas()) {
            Producto producto = buscarProducto(linea.getProducto().getCodigo());
            producto.descontarInventario(linea.getCantidad());
        }
    }

    // Alertas stock minimo
    public List<Producto> obtenerProductosBajoMinimo() {
        List<Producto> alertas = new ArrayList<>();
        for (Producto producto : productos.values()) {
            if (producto.getCantidadInventario() <= producto.getStockMinimo()) {
                alertas.add(producto);
            }
        }
        return alertas;
    }

    // Listar productos
    public Collection<Producto> getProductos() {
        return Collections.unmodifiableCollection(productos.values());
    }
}