import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Clase reportes del supermercado
public class Reportes {

    // Atributos
    private ServicioVentas servicioVentas;

    // Constructor
    public Reportes(ServicioVentas servicioVentas) {
        if (servicioVentas == null) {
            throw new IllegalArgumentException("El servicio de ventas es obligatorio.");
        }
        this.servicioVentas = servicioVentas;
    }

    // Total ingresos recaudados
    public double calcularTotalIngresos() {
        double total = 0.0;
        for (Venta venta : servicioVentas.getVentas()) {
            total += venta.getTotal();
        }
        return total;
    }

    // Total descuentos otorgados
    public double calcularTotalDescuentos() {
        double total = 0.0;
        for (Venta venta : servicioVentas.getVentas()) {
            total += venta.getDescuentoProductos()
                   + venta.getDescuentoPromocion()
                   + venta.getDescuentoCliente();
        }
        return total;
    }

    // Total IVA recaudado
    public double calcularTotalIva() {
        double total = 0.0;
        for (Venta venta : servicioVentas.getVentas()) {
            total += venta.getTotalIva();
        }
        return total;
    }

    // Productos en o bajo stock minimo
    public List<Producto> obtenerProductosBajoMinimo() {
        return servicioVentas.getInventario().obtenerProductosBajoMinimo();
    }

    // Ventas por documento de cliente
    public List<Venta> obtenerVentasPorCliente(String documento) {
        List<Venta> resultado = new ArrayList<>();
        for (Venta venta : servicioVentas.getVentas()) {
            if (venta.getCliente().getDocumento().equals(documento)) {
                resultado.add(venta);
            }
        }
        return resultado;
    }

    // Producto con mayor cantidad vendida
    public Producto obtenerProductoMasVendido() {
        Map<String, Double> conteo = new LinkedHashMap<>();
        Map<String, Producto> referencia = new LinkedHashMap<>();

        for (Venta venta : servicioVentas.getVentas()) {
            for (LineaVenta linea : venta.getLineas()) {
                String codigo = linea.getProducto().getCodigo();
                conteo.put(codigo, conteo.getOrDefault(codigo, 0.0) + linea.getCantidad());
                referencia.put(codigo, linea.getProducto());
            }
        }

        Producto masVendido = null;
        double maxCantidad = 0.0;
        for (Map.Entry<String, Double> entry : conteo.entrySet()) {
            if (entry.getValue() > maxCantidad) {
                maxCantidad = entry.getValue();
                masVendido = referencia.get(entry.getKey());
            }
        }
        return masVendido;
    }

    // Imprimir resumen general
    public void imprimirResumenGeneral() {
        System.out.println("\n================ REPORTE GENERAL ================");
        System.out.println("Tienda: " + servicioVentas.getNombreTienda());
        System.out.println("Ventas realizadas: " + servicioVentas.getVentas().size());
        System.out.println("Total ingresos: $" + calcularTotalIngresos());
        System.out.println("Total descuentos: $" + calcularTotalDescuentos());
        System.out.println("Total IVA: $" + calcularTotalIva());

        Producto top = obtenerProductoMasVendido();
        System.out.println("Producto mas vendido: " + (top != null ? top.getNombre() : "Ninguno"));

        System.out.println("\n--- ALERTAS STOCK MINIMO ---");
        for (Producto p : obtenerProductosBajoMinimo()) {
            System.out.println(p);
        }
        System.out.println("=================================================");
    }
}