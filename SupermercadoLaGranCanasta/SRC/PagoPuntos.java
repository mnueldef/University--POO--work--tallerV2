// Pago puntos
public class PagoPuntos implements MedioPago {

    // Atributos
    private int puntosUsados;

    // Constructor
    public PagoPuntos() {
        this.puntosUsados = 0;
    }

    // Procesar pago polimorfico
    @Override
    public boolean procesarPago(Cliente cliente, double total) {
        if (cliente != null && cliente.usarPuntosParaPago(total)) {
            this.puntosUsados = (int) Math.ceil(total / 10.0);
            return true;
        }
        return false;
    }

    // Detalle factura
    @Override
    public String getDetallePago() {
        return "Puntos | Puntos redimidos: " + puntosUsados;
    }

    // Getter
    public int getPuntosUsados() {
        return puntosUsados;
    }
}