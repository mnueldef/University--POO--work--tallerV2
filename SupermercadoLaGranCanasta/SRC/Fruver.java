// Clase hija fruver
public class Fruver extends Producto {

    // Atributos
    private String origen;

    // Constructor
    public Fruver(String codigo, String nombre, double precioBase, double cantidadInventario, double stockMinimo, String origen) {
        super(codigo, nombre, precioBase, cantidadInventario, stockMinimo);
        this.origen = origen;
    }

    // IVA 0%
    @Override
    public double getPorcentajeIva() {
        return 0.0;
    }

    // Getter
    public String getOrigen() {
        return origen;
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + " | Tipo: Fruver | Origen: " + origen;
    }
}