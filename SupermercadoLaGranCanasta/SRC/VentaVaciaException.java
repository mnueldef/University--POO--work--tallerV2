// Excepcion venta sin productos
public class VentaVaciaException extends Exception {

    // Constructor
    public VentaVaciaException(String mensaje) {
        super(mensaje);
    }
}