package p1_s2_ejemplo_1;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) throws Exception {
        // Crear libros del catálogo
        Libro libro1 = new Libro("L001", "El Quijote", "Miguel de Cervantes", 15.50);
        Libro libro2 = new Libro("L002", "Cien años de soledad", "Gabriel García Márquez", 18.90);
        Libro libro3 = new Libro("L003", "1984", "George Orwell", 12.95);
        Libro libro4 = new Libro("L004", "El principito", "Antoine de Saint-Exupéry", 9.95);
        Libro libro5 = new Libro("L005", "La sombra del viento", "Carlos Ruiz Zafón", 16.50);
        
        // Crear clientes
        Cliente cliente1 = new Cliente("María García López", 
                                      "maria.garcia@email.com", 
                                      "Calle Mayor 15, Toledo");
        Cliente cliente2 = new Cliente("Juan Martínez Rodríguez", 
                                      "juan.martinez@email.com", 
                                      "Avenida de España 42, Ciudad Real");
        Cliente cliente3 = new Cliente("Ana Sánchez Pérez", 
                                      "ana.sanchez@email.com", 
                                      "Plaza del Ayuntamiento 8, Albacete");
        
        // Crear pedidos y añadir libros
        Pedido pedido1 = new Pedido(1001, "16/02/2026", cliente1);
        pedido1.añadirLibro(libro1);
        pedido1.añadirLibro(libro2);
        pedido1.añadirLibro(libro3);
        
        Pedido pedido2 = new Pedido(1002, "16/02/2026", cliente2);
        pedido2.añadirLibro(libro4);
        pedido2.añadirLibro(libro1);
        
        Pedido pedido3 = new Pedido(1003, "16/02/2026", cliente3);
        pedido3.añadirLibro(libro5);
        pedido3.añadirLibro(libro3);
        pedido3.añadirLibro(libro4);
        
        Pedido[] pedidos = {pedido1, pedido2, pedido3};
        
        // Notificar a los clientes
        System.out.println("Enviando notificaciones a los clientes...\n");
        ServicioNotificaciones sn = new ServicioNotificaciones();
        for(int i=0; i<pedidos.length; i++) {
        	sn.notificarCliente(pedidos[i]);
        }

        // Generar fichero de resumen de pedidos. 
        PrintWriter writer = new PrintWriter(new FileWriter("resumen_pedidos.txt"));
        writer.println("RESUMEN DE PEDIDOS");
        writer.println("==================");
        writer.println();

        for(int i=0; i<pedidos.length; i++) {
            Pedido pedido = pedidos[i];
            Cliente cliente = pedido.getCliente();

            writer.printf("Pedido %d %n", pedido.getNumeroPedido());
            writer.printf("Fecha: %s %n", pedido.getFecha());
            writer.printf("Cliente: %s %n", cliente.getNombreCompleto());
            writer.printf("Email: %s %n", cliente.getEmail());
            writer.printf("Dirección: %s %n", cliente.getDireccion());
            writer.println("Libros:");

            Libro[] libros = pedido.getLibros();
            
            for (int j = 0; j < libros.length; j++) {
                Libro libro = libros[j];
                if (libro != null) {
                    writer.printf("  - Código: %s, Título: %s, Autor: %s, Precio: %.2f€ %n",
                                                 libro.getCodigo(), libro.getTitulo(), libro.getAutor(), libro.getPrecio());
                }
            }
            
            writer.printf("Total del pedido: %.2f€ %n", pedido.calcularTotal());
            writer.println();
        }
        writer.close();
    }

}
