public class Inventario {
    private Objeto[] items;

    public Inventario(int capacidad) {
        this.items = new Objeto[capacidad];
    }

    public boolean agregar(Objeto obj) {
        boolean agregado = false;
        for (int i = 0; i < items.length && !agregado; i = i + 1) {
            if (items[i] == null) {
                items[i] = obj;
                agregado = true;
            }
        }
        return agregado;
    }

    public Objeto sacar(int indice) {
        Objeto obj = null;
        if (indice >= 0 && indice < items.length) {
            obj = items[indice];
            items[indice] = null;
        }
        return obj;
    }

    @Override
    public String toString() {
        String resultado = "";
        for (int i = 0; i < items.length; i = i + 1) {
            if (items[i] == null) {
                resultado = resultado + "[" + i + "] (vacio)\n";
            } else {
                resultado = resultado + "[" + i + "] " + items[i].toString() + "\n";
            }
        }
        return resultado;
    }
}
