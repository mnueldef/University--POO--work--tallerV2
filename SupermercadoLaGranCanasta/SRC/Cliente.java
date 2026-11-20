// Clase abstracta cliente
public abstract class Cliente {

    // Atributos
    private final String documento;
    private String nombre;

    // Constructor
    public Cliente(String documento, String nombre) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento es obligatorio.");
        }
        this.documento = documento;
        this.nombre = nombre;
    }

    // Descuento por tipo cliente
    public double calcularDescuento(double subtotalAntesIva) {
        return 0.0;
    }

    // Acumular puntos si aplica
    public void acumularBeneficio(double totalPagado) {
    }

    // Pagar con puntos si aplica
    public boolean usarPuntosParaPago(double total) {
        return false;
    }

    // Getters
    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    // toString
    @Override
    public String toString() {
        return nombre + " (Doc: " + documento + ")";
    }
}