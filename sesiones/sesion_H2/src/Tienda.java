public class Tienda {
    private Inventario inventario;

    public Tienda() {
        this.inventario = new Inventario(20);
    }

    public boolean agregarAlInventario(Objeto obj) {
        boolean agregado = inventario.agregar(obj);
        return agregado;
    }

    public boolean venderA(Personaje personaje, int indice) {
        boolean vendida = false;
        Objeto obj = inventario.sacar(indice);
        if (obj != null) {
            boolean comprada = personaje.comprar(obj);
            if (comprada) {
                vendida = true;
            } else {
                inventario.agregar(obj);
            }
        }
        return vendida;
    }

    @Override
    public String toString() {
        String catalogo = inventario.toString();
        return catalogo;
    }
}
