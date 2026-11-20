// Clase hija cliente empresarial
public class ClienteEmpresarial extends Cliente {

    // Atributos
    private String nit;
    private String razonSocial;

    // Constructor
    public ClienteEmpresarial(String documento, String nombre, String nit, String razonSocial) {
        super(documento, nombre);
        this.nit = nit;
        this.razonSocial = razonSocial;
    }

    // Descuento 5% desde 500000 antes IVA
    @Override
    public double calcularDescuento(double subtotalAntesIva) {
        if (subtotalAntesIva >= 500000) {
            return subtotalAntesIva * 0.05;
        }
        return 0.0;
    }

    // Getters
    public String getNit() {
        return nit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + " | Tipo: Empresarial | NIT: " + nit + " | Razon Social: " + razonSocial;
    }
}