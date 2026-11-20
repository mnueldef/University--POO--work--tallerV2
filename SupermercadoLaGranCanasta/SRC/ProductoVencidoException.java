// Excepcion producto vencido
public class ProductoVencidoException extends Exception {

    // Constructor
    public ProductoVencidoException(String nombreProducto) {
        super("Producto vencido, no se puede vender: " + nombreProducto);
    }
}