package ar.edu.unju.escmi.main;

import java.time.LocalDate;
import java.util.Scanner;

import antlr.collections.List;
import ar.edu.unju.escmi.entities.*;
import ar.edu.unju.escmi.dao.*;
import ar.edu.unju.escmi.dao.imp.*;

public class Main {
	
	private static IClienteDao ClienteDAO = new ClienteDAOImpl();
	private static IDetalleFacturaDao DetalleFacturaDAO = new DetalleFacturaDaoImpl();
	private static IFacturaDao FacturaDAO = new FacturaDAOImpl();
	private static IProductoDao ProductoDAO = new ProductoDaoImpl();

	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;
        
        do {
            System.out.println("=== Menu de Opciones ===");
            System.out.println("1. Alta de cliente");
            System.out.println("2. Alta de producto");
            System.out.println("3. Realizar venta de productos");
            System.out.println("4. Buscar factura por número");
            System.out.println("5. Eliminar factura");
            System.out.println("6. Eliminar producto");
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
                    altaCliente(scanner);
                    break;
                case 2:
                    altaProducto(scanner);
                    break;
                case 3:
                    realizarVenta(scanner);
                    break;
                case 4:
                    buscarFactura(scanner);
                    break;
                case 5:
                    eliminarFactura(scanner);
                    break;
                case 6:
                    eliminarProducto(scanner);
                    break;
                case 7:
                    modificarCliente(scanner);
                    break;
                case 8:
                    modificarPrecioProducto(scanner);
                    break;
                case 9:
                    eliminarProductoLogico(scanner);
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
    
    public static void altaCliente(Scanner scanner) {
        System.out.print("Ingrese nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese apellido del cliente: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese domicilio del cliente: ");
        String domicilio = scanner.nextLine();
        System.out.print("Ingrese DNI del cliente: ");
        int dni = scanner.nextInt();
        scanner.nextLine(); 

        Cliente cliente = new Cliente(nombre, apellido, domicilio, dni, true);
        ClienteDAO.guardar(cliente);  
        System.out.println("Cliente registrado exitosamente.");
    }
    
    public static void altaProducto(Scanner scanner) {
        System.out.print("Ingrese descripción del producto: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese precio unitario del producto: ");
        double precioUnitario = scanner.nextDouble();
        scanner.nextLine();  

        Producto producto = new Producto(descripcion, precioUnitario, true);
        ProductoDAO.guardar(producto);  
        System.out.println("Producto registrado exitosamente.");
    }

    public static void realizarVenta(Scanner scanner) {
    	double total = 0;
        System.out.print("Ingrese el ID del cliente: ");
        long clienteId = scanner.nextLong();
        scanner.nextLine();  

        Cliente cliente = ClienteDAO.buscarPorId(clienteId);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        
        boolean agregarProductos = true;
        while (agregarProductos) {
            System.out.print("Ingrese el ID del producto: ");
            long productoId = scanner.nextLong();
            System.out.print("Ingrese la cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); 

            Producto producto = ProductoDAO.buscarPorId(productoId);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
            } else {
            	total = total + producto.getPrecioUnitario();
                DetalleFactura detalle = new DetalleFactura(producto, cantidad, producto.getPrecioUnitario() * cantidad);
                DetalleFacturaDAO.guardar(detalle); 
            }

            System.out.print("¿Desea agregar otro producto? (s/n): ");
            agregarProductos = scanner.nextLine().equalsIgnoreCase("s");
        }

        FacturaDAO.guardar(new Factura(cliente, cliente.getDomicilio(), total, true)); 
        System.out.println("Venta registrada exitosamente.");
    }

    public static void buscarFactura(Scanner scanner) {
        System.out.print("Ingrese el número de factura: ");
        long facturaId = scanner.nextLong();
        scanner.nextLine();  

        Factura factura = FacturaDAO.buscarPorId(facturaId);
        if (factura != null) {
            System.out.println("Datos de la Factura:");
            System.out.println(factura);
        } else {
            System.out.println("Factura no encontrada.");
        }
    }

    public static void eliminarFactura(Scanner scanner) {
        System.out.print("Ingrese el número de factura a eliminar: ");
        long facturaId = scanner.nextLong();
        scanner.nextLine();  

        Factura factura = FacturaDAO.buscarPorId(facturaId);
        if (factura != null) {
            factura.setEstado(false); 
            FacturaDAO.actualizar(factura);
            System.out.println("Factura eliminada exitosamente.");
        } else {
            System.out.println("Factura no encontrada.");
        }
    }

    public static void eliminarProducto(Scanner scanner) {
        System.out.print("Ingrese el ID del producto a eliminar: ");
        long productoId = scanner.nextLong();
        scanner.nextLine(); 

        Producto producto = ProductoDAO.buscarPorId(productoId);
        if (producto != null) {
            producto.setEstado(false);  
            ProductoDAO.actualizar(producto);
            System.out.println("Producto eliminado exitosamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
    
	public static void modificarCliente(Scanner scanner) {
	    System.out.print("Ingrese el ID del cliente a modificar: ");
	    Long clienteId = scanner.nextLong();
	    scanner.nextLine();
	
	    Cliente cliente = ClienteDAO.buscarPorId(clienteId);
	    if (cliente != null) {
	        System.out.print("Ingrese el nuevo nombre: ");
	        cliente.setNombre(scanner.nextLine());
	        System.out.print("Ingrese el nuevo apellido: ");
	        cliente.setApellido(scanner.nextLine());
	        System.out.print("Ingrese el nuevo domicilio: ");
	        cliente.setDomicilio(scanner.nextLine());
	        System.out.print("Ingrese el nuevo DNI: ");
	        cliente.setDni(scanner.nextInt());
	
	        ClienteDAO.actualizar(cliente);
	        System.out.println("Cliente modificado exitosamente.");
	    } else {
	        System.out.println("Cliente no encontrado.");
	    }
	}
	
	public static void modificarPrecioProducto(Scanner scanner) {
	    System.out.print("Ingrese el ID del producto a modificar: ");
	    Long productoId = scanner.nextLong();

	    Producto producto = ProductoDAO.buscarPorId(productoId);
	    if (producto != null) {
	        System.out.print("Ingrese el nuevo precio: ");
	        producto.setPrecioUnitario(scanner.nextDouble());

	        ProductoDAO.actualizar(producto);
	        System.out.println("Precio del producto modificado exitosamente.");
	    } else {
	        System.out.println("Producto no encontrado.");
	    }
	}
	
	public static void eliminarProductoLogico(Scanner scanner) {
	    System.out.print("Ingrese el ID del producto a eliminar: ");
	    Long productoId = scanner.nextLong();

	    Producto producto = ProductoDAO.buscarPorId(productoId);
	    if (producto != null) {
	        producto.setEstado(false);
	        ProductoDAO.actualizar(producto);
	        System.out.println("Producto eliminado.");
	    } else {
	        System.out.println("Producto no encontrado.");
	    }
	}
	
	public static void mostrarFacturas() {
	    List<Factura> facturas = FacturaDAO.obtenerTodos();
	    if (!facturas.isEmpty()) {
	        for (Factura factura : facturas) {
	            System.out.println(factura);
	        }
	    } else {
	        System.out.println("No hay facturas registradas.");
	    }
	}

	public static void mostrarClientes() {
	    List<Cliente> clientes = ClienteDAO.obtenerTodos();
	    if (!clientes.isEmpty()) {
	        for (Cliente cliente : clientes) {
	            System.out.println(cliente);
	        }
	    } else {
	        System.out.println("No hay clientes registrados.");
	    }
	}
	
	public static void mostrarFacturasSuperiores() {
	    List<Factura> facturas = FacturaDAO.obtenerTodos();
	    for (Factura factura : facturas) {
	        if (factura.getTotal() > 500000) {
	            System.out.println(factura);
	        }
	    }
	}
}

