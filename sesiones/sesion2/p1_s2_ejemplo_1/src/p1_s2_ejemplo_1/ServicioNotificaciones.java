package p1_s2_ejemplo_1;

public class ServicioNotificaciones {
	
	public void notificarCliente(Pedido pedido) {
		
		Cliente cliente = pedido.getCliente();
		
        System.out.println("========================================");
        System.out.println("NOTIFICACIÓN DE PEDIDO");
        System.out.println("========================================");
        System.out.println("Destinatario: " + cliente.getNombreCompleto());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Pedido " + pedido.getNumeroPedido() + " confirmado");
        System.out.println("Fecha: " + pedido.getFecha());
        System.out.printf("Total: %.2f€ %n", pedido.calcularTotal());
        System.out.println("El pedido será enviado a: " + cliente.getDireccion());
        System.out.println("========================================\n");
	}

}
