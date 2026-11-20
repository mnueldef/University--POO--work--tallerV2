// Clase hija cliente ocasional
public class ClienteOcasional extends Cliente {

    // Constructor
    public ClienteOcasional(String documento, String nombre) {
        super(documento, nombre);
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + " | Tipo: Ocasional";
    }
}