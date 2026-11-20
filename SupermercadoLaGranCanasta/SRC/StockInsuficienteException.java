// Excepcion inventario insuficiente
public class StockInsuficienteException extends Exception {

    // Constructor
    public StockInsuficienteException(String nombreProducto, double disponible) {
        super("Stock insuficiente para " + nombreProducto + ". Disponible: " + disponible);
    }
}