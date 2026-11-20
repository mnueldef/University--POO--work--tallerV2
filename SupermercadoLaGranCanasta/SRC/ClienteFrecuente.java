// Clase hija cliente frecuente
public class ClienteFrecuente extends Cliente {

    // Atributos
    private int puntos;
    private int ultimosPuntosGanados;

    // Constructor
    public ClienteFrecuente(String documento, String nombre, int puntosIniciales) {
        super(documento, nombre);
        this.puntos = Math.max(0, puntosIniciales);
        this.ultimosPuntosGanados = 0;
    }

    // Acumular 1 punto por cada 1000
    @Override
    public void acumularBeneficio(double totalPagado) {
        this.ultimosPuntosGanados = (int) (totalPagado / 1000);
        this.puntos += this.ultimosPuntosGanados;
    }

    // Pagar con puntos 1 punto = 10
    @Override
    public boolean usarPuntosParaPago(double total) {
        int puntosNecesarios = (int) Math.ceil(total / 10.0);
        if (puntos >= puntosNecesarios) {
            puntos -= puntosNecesarios;
            return true;
        }
        return false;
    }

    // Getters
    public int getPuntos() {
        return puntos;
    }

    public int getUltimosPuntosGanados() {
        return ultimosPuntosGanados;
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + " | Tipo: Frecuente | Puntos: " + puntos;
    }
}