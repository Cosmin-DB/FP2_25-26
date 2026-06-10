public class PosicionVaciaException extends Exception {
    public PosicionVaciaException(int posicion) {
        super("La posicion " + posicion + " esta vacia.");
    }
}
