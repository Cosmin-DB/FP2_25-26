public class Tienda {
    private Inventario inventario;

    public Tienda() {
        this.inventario = new Inventario(20);
    }

    public void agregarAlInventario(Objeto obj) throws InventarioLlenoException {
        inventario.agregar(obj);
    }

    public void venderA(Personaje personaje, int indice)
            throws PosicionVaciaException, DineroInsuficienteException, InventarioLlenoException {
        Objeto obj = inventario.ver(indice);
        if (obj == null) {
            throw new PosicionVaciaException(indice);
        }
        personaje.comprar(obj);
        inventario.sacar(indice);
    }

    @Override
    public String toString() {
        String catalogo = inventario.toString();
        return catalogo;
    }
}
