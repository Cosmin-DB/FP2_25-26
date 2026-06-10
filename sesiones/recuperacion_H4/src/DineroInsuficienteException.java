public class DineroInsuficienteException extends Exception {
    public DineroInsuficienteException() {
        super("No hay dinero suficiente para realizar la compra.");
    }
}
