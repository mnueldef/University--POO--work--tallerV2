import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Clase hija perecedero
public class Perecedero extends Producto {

    // Atributos
    private LocalDate fechaVencimiento;

    // Constructor
    public Perecedero(String codigo, String nombre, double precioBase, double cantidadInventario, double stockMinimo, LocalDate fechaVencimiento) {
        super(codigo, nombre, precioBase, cantidadInventario, stockMinimo);
        this.fechaVencimiento = fechaVencimiento;
    }

    // Validar vencimiento
    @Override
    public void validarVenta(double cantidad) throws StockInsuficienteException, ProductoVencidoException {
        super.validarVenta(cantidad);
        if (fechaVencimiento.isBefore(LocalDate.now())) {
            throw new ProductoVencidoException(getNombre());
        }
    }

    // IVA 5%
    @Override
    public double getPorcentajeIva() {
        return 0.05;
    }

    // Descuento 30% si vence en 3 dias o menos
    @Override
    public double getPorcentajeDescuento() {
        long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), fechaVencimiento);
        if (diasRestantes >= 0 && diasRestantes <= 3) {
            return 0.30;
        }
        return 0.0;
    }

    // Getter
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + " | Tipo: Perecedero | Vence: " + fechaVencimiento;
    }
}