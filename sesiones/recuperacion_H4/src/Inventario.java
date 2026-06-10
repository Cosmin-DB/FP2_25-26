public class Inventario {
    private Objeto[] items;

    public Inventario(int capacidad) {
        this.items = new Objeto[capacidad];
    }

    public void agregar(Objeto obj) throws InventarioLlenoException {
        boolean agregado = false;
        for (int i = 0; i < items.length && !agregado; i = i + 1) {
            if (items[i] == null) {
                items[i] = obj;
                agregado = true;
            }
        }
        if (!agregado) {
            throw new InventarioLlenoException();
        }
    }

    public Objeto ver(int indice) {
        Objeto obj = items[indice];
        return obj;
    }

    public int getCapacidad() {
        int capacidad = items.length;
        return capacidad;
    }

    public Objeto sacar(int indice) throws PosicionVaciaException {
        Objeto obj = items[indice];
        if (obj == null) {
            throw new PosicionVaciaException(indice);
        }
        items[indice] = null;
        return obj;
    }

    @Override
    public String toString() {
        String resultado = "";
        for (int i = 0; i < items.length; i = i + 1) {
            if (items[i] == null) {
                resultado = resultado + "[" + i + "] (vacio)\n";
            } else {
                resultado = resultado + "[" + i + "] " + items[i].mostrar() + "\n";
            }
        }
        return resultado;
    }
}
