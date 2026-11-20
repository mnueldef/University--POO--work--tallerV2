// Clase hija electrodomestico
public class Electrodomestico extends Producto {

    // Atributos
    private Garantia garantia;

    // Constructor composicion
    public Electrodomestico(String codigo, String nombre, double precioBase, double cantidadInventario, double stockMinimo, int mesesGarantia, String proveedorGarantia) {
        super(codigo, nombre, precioBase, cantidadInventario, stockMinimo);
        this.garantia = new Garantia(mesesGarantia, proveedorGarantia);
    }

    // IVA 19%
    @Override
    public double getPorcentajeIva() {
        return 0.19;
    }

    // Getter
    public Garantia getGarantia() {
        return garantia;
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + " | Tipo: Electrodomestico | Garantia: " + garantia;
    }
}