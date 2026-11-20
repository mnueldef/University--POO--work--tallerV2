import java.time.LocalDateTime;

// Clase factura
public class Factura {

    // Atributos
    private final String numero;
    private final LocalDateTime fechaEmision;
    private final Venta venta;

    // Constructor
    public Factura(String numero, Venta venta) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("El numero de factura es obligatorio.");
        }
        if (venta == null) {
            throw new IllegalArgumentException("La venta es obligatoria.");
        }
        if (!venta.isPagada()) {
            throw new IllegalStateException("No se puede generar factura de una venta no pagada.");
        }
        this.numero = numero;
        this.fechaEmision = LocalDateTime.now();
        this.venta = venta;
    }

    // Generar detalle factura
    public String generarDetalle() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("FACTURA: ").append(numero).append("\n");
        sb.append("Fecha: ").append(fechaEmision).append("\n");
        sb.append("Cliente: ").append(venta.getCliente()).append("\n");
        sb.append("----------------------------------------\n");
        for (LineaVenta linea : venta.getLineas()) {
            sb.append(linea).append("\n");
        }
        sb.append("----------------------------------------\n");
        sb.append("Subtotal Bruto: $").append(venta.getSubtotalBruto()).append("\n");
        sb.append("Descuento Productos: -$").append(venta.getDescuentoProductos()).append("\n");
        sb.append("Subtotal Antes IVA: $").append(venta.getSubtotalAntesIva()).append("\n");
        sb.append("Descuento Promocion: -$").append(venta.getDescuentoPromocion()).append("\n");
        sb.append("Descuento Cliente: -$").append(venta.getDescuentoCliente()).append("\n");
        sb.append("Total IVA: $").append(venta.getTotalIva()).append("\n");
        sb.append("TOTAL PAGADO: $").append(venta.getTotal()).append("\n");
        sb.append("Pago: ").append(venta.getMedioPago().getDetallePago()).append("\n");
        sb.append("========================================");
        return sb.toString();
    }

    // Imprimir factura
    public void imprimir() {
        System.out.println(generarDetalle());
    }

    // Getters
    public String getNumero() {
        return numero;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public Venta getVenta() {
        return venta;
    }

    // toString
    @Override
    public String toString() {
        return generarDetalle();
    }
}