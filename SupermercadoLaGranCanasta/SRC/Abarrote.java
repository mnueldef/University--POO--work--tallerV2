// Clase hija abarrote
public class Abarrote extends Producto {

    // Atributos
    private String marca;

    // Constructor
    public Abarrote(String codigo, String nombre, double precioBase, double cantidadInventario, double stockMinimo, String marca) {
        super(codigo, nombre, precioBase, cantidadInventario, stockMinimo);
        this.marca = marca;
    }

    // IVA 19%
    @Override
    public double getPorcentajeIva() {
        return 0.19;
    }

    // Getter
    public String getMarca() {
        return marca;
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + " | Tipo: Abarrote | Marca: " + marca;
    }
}