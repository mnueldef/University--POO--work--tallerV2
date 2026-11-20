
// Promocion nula
public class SinPromocion implements Promocion {

    // Sin descuento
    @Override
    public double calcularDescuento(Venta venta) {
        return 0.0;
    }
}