// Promocion 2x1
public class Promocion2x1 implements Promocion {

    // Atributos
    private String codigoProducto;

    // Constructor
    public Promocion2x1(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    // Calcular descuento 2x1
    @Override
    public double calcularDescuento(Venta venta) {
        double descuento = 0.0;
        for (LineaVenta linea : venta.getLineas()) {
            if (linea.getProducto().getCodigo().equals(codigoProducto)) {
                int unidadesGratis = (int) (linea.getCantidad() / 2);
                if (unidadesGratis > 0) {
                    double precioUnitario = linea.getSubtotal() / linea.getCantidad();
                    descuento += unidadesGratis * precioUnitario;
                }
            }
        }
        return descuento;
    }
}