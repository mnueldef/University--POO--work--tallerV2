// Pago tarjeta
public class PagoTarjeta implements MedioPago {

    // Atributos
    private String ultimos4Digitos;

    // Constructor
    public PagoTarjeta(String ultimos4Digitos) {
        this.ultimos4Digitos = ultimos4Digitos;
    }

    // Procesar pago
    @Override
    public boolean procesarPago(Cliente cliente, double total) {
        return ultimos4Digitos != null && ultimos4Digitos.length() == 4;
    }

    // Detalle factura
    @Override
    public String getDetallePago() {
        return "Tarjeta (**** " + ultimos4Digitos + ")";
    }

    // Getter
    public String getUltimos4Digitos() {
        return ultimos4Digitos;
    }
}