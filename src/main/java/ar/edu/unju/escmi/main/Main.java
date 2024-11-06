package ar.edu.unju.escmi.main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("=== Menú de Opciones ===");
            System.out.println("1. Alta de cliente");
            System.out.println("2. Alta de producto");
            System.out.println("3. Realizar venta de productos");
            System.out.println("4. Buscar factura por número");
            System.out.println("5. Eliminar factura (lógico)");
            System.out.println("6. Eliminar producto (lógico)");
            System.out.println("7. Modificar datos de cliente");
            System.out.println("8. Modificar precio de producto");
            System.out.println("9. Eliminar producto (lógico)");
            System.out.println("10. Mostrar todas las facturas");
            System.out.println("11. Mostrar todos los clientes");
            System.out.println("12. Mostrar facturas con total > $500,000");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    altaCliente();
                    break;
                case 2:
                    altaProducto();
                    break;
                case 3:
                    realizarVenta();
                    break;
                case 4:
                    buscarFactura();
                    break;
                case 5:
                    eliminarFactura();
                    break;
                case 6:
                    eliminarProducto();
                    break;
                case 7:
                    modificarCliente();
                    break;
                case 8:
                    modificarPrecioProducto();
                    break;
                case 9:
                    eliminarProductoLogico();
                    break;
                case 10:
                    mostrarFacturas();
                    break;
                case 11:
                    mostrarClientes();
                    break;
                case 12:
                    mostrarFacturasSuperiores();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);

        scanner.close();
    }
}
	public static void modificarCliente() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Ingrese el ID del cliente a modificar: ");
	    Long clienteId = scanner.nextLong();
	    scanner.nextLine();
	
	    Cliente cliente = clienteDAO.find(clienteId);
	    if (cliente != null) {
	        System.out.print("Ingrese el nuevo nombre: ");
	        cliente.setNombre(scanner.nextLine());
	        System.out.print("Ingrese el nuevo apellido: ");
	        cliente.setApellido(scanner.nextLine());
	        System.out.print("Ingrese el nuevo domicilio: ");
	        cliente.setDomicilio(scanner.nextLine());
	        System.out.print("Ingrese el nuevo DNI: ");
	        cliente.setDni(scanner.nextInt());
	
	        clienteDAO.update(cliente);
	        System.out.println("Cliente modificado exitosamente.");
	    } else {
	        System.out.println("Cliente no encontrado.");
	    }
	}
	
	public static void modificarPrecioProducto() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Ingrese el ID del producto a modificar: ");
	    Long productoId = scanner.nextLong();

	    Producto producto = productoDAO.find(productoId);
	    if (producto != null) {
	        System.out.print("Ingrese el nuevo precio: ");
	        producto.setPrecioUnitario(scanner.nextDouble());

	        productoDAO.update(producto);
	        System.out.println("Precio del producto modificado exitosamente.");
	    } else {
	        System.out.println("Producto no encontrado.");
	    }
	}
	
	public static void eliminarProductoLogico() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Ingrese el ID del producto a eliminar: ");
	    Long productoId = scanner.nextLong();

	    Producto producto = productoDAO.find(productoId);
	    if (producto != null) {
	        producto.setEstado(false);
	        productoDAO.update(producto);
	        System.out.println("Producto eliminado.");
	    } else {
	        System.out.println("Producto no encontrado.");
	    }
	}
	
	public static void mostrarFacturas() {
	    List<Factura> facturas = facturaDAO.findAll();
	    if (!facturas.isEmpty()) {
	        for (Factura factura : facturas) {
	            System.out.println(factura);
	        }
	    } else {
	        System.out.println("No hay facturas registradas.");
	    }
	}

	public static void mostrarClientes() {
	    List<Cliente> clientes = clienteDAO.findAll();
	    if (!clientes.isEmpty()) {
	        for (Cliente cliente : clientes) {
	            System.out.println(cliente);
	        }
	    } else {
	        System.out.println("No hay clientes registrados.");
	    }
	}
	
	public static void mostrarFacturasSuperiores() {
	    List<Factura> facturas = facturaDAO.findAll();
	    for (Factura factura : facturas) {
	        if (factura.getTotal() > 500000) {
	            System.out.println(factura);
	        }
	    }
	}

