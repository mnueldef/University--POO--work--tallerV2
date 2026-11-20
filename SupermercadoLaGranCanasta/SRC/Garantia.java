// Clase garantia
public class Garantia {

    // Atributos
    private int meses;
    private String proveedor;

    // Constructor
    public Garantia(int meses, String proveedor) {
        this.meses = meses;
        this.proveedor = proveedor;
    }

    // Getters
    public int getMeses() {
        return meses;
    }

    public String getProveedor() {
        return proveedor;
    }

    // toString
    @Override
    public String toString() {
        return meses + " meses (" + proveedor + ")";
    }
}