//JUAN JOSE ZAPATA GUTIERREZ
//MANUEL DAVID FERNANDEZ BEDOYA

import java.time.LocalDate;

// Clase principal demostracion
public class Main {

    public static void main(String[] args) {
        // Instanciar servicio y reportes
        ServicioVentas servicio = new ServicioVentas("Supermercado La Gran Canasta");
        Reportes reportes = new Reportes(servicio);

        // Registrar productos
        servicio.registrarProducto(new Abarrote("A-01", "Arroz 1kg", 4500, 20, 5, "Diana"));
        servicio.registrarProducto(new Perecedero("P-01", "Leche Entera 1L", 4200, 15, 5, LocalDate.now().plusDays(10)));
        servicio.registrarProducto(new Perecedero("P-02", "Yogur Griego", 6000, 8, 3, LocalDate.now().minusDays(2)));
        servicio.registrarProducto(new Fruver("F-01", "Manzana Roja kg", 7500, 12.0, 4.0, "Nacional"));
        servicio.registrarProducto(new Electrodomestico("E-01", "Licuadora Pro", 220000, 4, 2, 12, "Oster"));

        // Registrar clientes
        servicio.registrarCliente(new ClienteOcasional("1", "James Rodriguez"));
        servicio.registrarCliente(new ClienteFrecuente("2", "Vinicius Jr", 5000));
        servicio.registrarCliente(new ClienteEmpresarial("3", "Luis Suarez", "109876543-2", "Messi SAS"));

        try {
            // Venta 1: Cliente frecuente + Promocion 2x1 + Efectivo
            Venta venta1 = servicio.crearVenta("2", new Promocion2x1("A-01"));
            servicio.agregarProductoAVenta(venta1, "A-01", 4);
            servicio.agregarProductoAVenta(venta1, "F-01", 2.5);
            Factura factura1 = servicio.finalizarVenta(venta1, new PagoEfectivo(50000));
            factura1.imprimir();

            // Venta 2: Cliente empresarial (descuento 5%) + Tarjeta
            Venta venta2 = servicio.crearVenta("3");
            servicio.agregarProductoAVenta(venta2, "E-01", 3);
            Factura factura2 = servicio.finalizarVenta(venta2, new PagoTarjeta("4829"));
            factura2.imprimir();

            // Venta 3: Pago redimiendo puntos acumulados
            Venta venta3 = servicio.crearVenta("2");
            servicio.agregarProductoAVenta(venta3, "P-01", 2);
            Factura factura3 = servicio.finalizarVenta(venta3, new PagoPuntos());
            factura3.imprimir();

            // Reponer stock de electrodomestico
            servicio.reponerStock("E-01", 5);

        } catch (VentaVaciaException | StockInsuficienteException | ProductoVencidoException e) {
            System.out.println("Error en venta: " + e.getMessage());
        }

        // Prueba excepcion producto vencido
        try {
            Venta ventaVencida = servicio.crearVenta("1");
            servicio.agregarProductoAVenta(ventaVencida, "P-02", 1);
        } catch (ProductoVencidoException | StockInsuficienteException e) {
            System.out.println("\nExcepcion controlada (Vencimiento): " + e.getMessage());
        }

        // Prueba excepcion stock insuficiente
        try {
            Venta ventaSinStock = servicio.crearVenta("1");
            servicio.agregarProductoAVenta(ventaSinStock, "E-01", 20);
        } catch (StockInsuficienteException | ProductoVencidoException e) {
            System.out.println("Excepcion controlada (Stock): " + e.getMessage());
        }

        // Prueba excepcion venta vacia
        try {
            Venta ventaVacia = servicio.crearVenta("1");
            servicio.finalizarVenta(ventaVacia, new PagoEfectivo(10000));
        } catch (VentaVaciaException | StockInsuficienteException | ProductoVencidoException e) {
            System.out.println("Excepcion controlada (Venta vacia): " + e.getMessage());
        }

        // Imprimir reportes generales
        reportes.imprimirResumenGeneral();
    }
}
