public class TipoObjetoInvalidoException extends Exception {
    public TipoObjetoInvalidoException(String tipo) {
        super("El tipo de objeto '" + tipo + "' no es valido.");
    }
}
