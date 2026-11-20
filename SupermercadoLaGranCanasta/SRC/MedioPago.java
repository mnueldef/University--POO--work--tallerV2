// Interfaz medio pago
public interface MedioPago {

    // Procesar pago
    boolean procesarPago(Cliente cliente, double total);

    // Detalle factura
    String getDetallePago();
}