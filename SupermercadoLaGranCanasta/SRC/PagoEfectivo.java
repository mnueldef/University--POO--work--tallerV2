// Pago efectivo
public class PagoEfectivo implements MedioPago {

    // Atributos
    private double montoRecibido;
    private double cambio;

    // Constructor
    public PagoEfectivo(double montoRecibido) {
        this.montoRecibido = montoRecibido;
        this.cambio = 0.0;
    }

    // Procesar pago
    @Override
    public boolean procesarPago(Cliente cliente, double total) {
        if (montoRecibido < total) {
            return false;
        }
        this.cambio = montoRecibido - total;
        return true;
    }

    // Detalle factura
    @Override
    public String getDetallePago() {
        return "Efectivo | Recibido: $" + montoRecibido + " | Cambio: $" + cambio;
    }

    // Getter
    public double getCambio() {
        return cambio;
    }
}