// Clase abstracta producto
public abstract class Producto {

    // Atributos
    private final String codigo;
    private String nombre;
    private double precioBase;
    private double cantidadInventario;
    private double stockMinimo;

    // Constructor
    public Producto(String codigo, String nombre, double precioBase, double cantidadInventario, double stockMinimo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo es obligatorio.");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.cantidadInventario = cantidadInventario;
        this.stockMinimo = stockMinimo;
    }

    // Metodos inventario visibilidad paquete
    void descontarInventario(double cantidad) throws StockInsuficienteException {
        if (cantidad > cantidadInventario) {
            throw new StockInsuficienteException(nombre, cantidadInventario);
        }
        this.cantidadInventario -= cantidad;
    }

    void recibirMercancia(double cantidad) {
        if (cantidad > 0) {
            this.cantidadInventario += cantidad;
        }
    }

    // Validar venta
    public void validarVenta(double cantidad) throws StockInsuficienteException, ProductoVencidoException {
        if (cantidad > cantidadInventario) {
            throw new StockInsuficienteException(nombre, cantidadInventario);
        }
    }

    // Metodos polimorficos impuestos y descuentos
    public abstract double getPorcentajeIva();

    public double getPorcentajeDescuento() {
        return 0.0;
    }

    // Alerta stock minimo
    public boolean estaEnStockMinimo() {
        return cantidadInventario < stockMinimo;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public double getCantidadInventario() {
        return cantidadInventario;
    }

    public double getStockMinimo() {
        return stockMinimo;
    }
    // Reponer stock
    public void reponerInventario(double cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a reponer debe ser mayor a cero.");
        }
        this.cantidadInventario += cantidad;
    }
    // toString
    @Override
    public String toString() {
        return "[" + codigo + "] " + nombre + " | Precio: $" + precioBase + " | Stock: " + cantidadInventario;
    }
}