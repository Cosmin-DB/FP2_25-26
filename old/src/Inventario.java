public class Inventario {
    private Objeto[] items;

    public Inventario(int capacidad) {
        items = new Objeto[capacidad];
    }

    public int getCapacidad() {
        return items.length;
    }

    public int getOcupados() {
        int count = 0;
        int i = 0;
        while (i < items.length) {
            if (items[i] != null) {
                count = count + 1;
            }
            i = i + 1;
        }
        return count;
    }

    public boolean hayHueco() {
        boolean encontrado = false;
        int i = 0;
        while (i < items.length && !encontrado) {
            if (items[i] == null) {
                encontrado = true;
            }
            i = i + 1;
        }
        return encontrado;
    }

    public void add(Objeto o) throws InventarioLlenoException {
        int i = 0;
        boolean insertado = false;
        while (i < items.length && !insertado) {
            if (items[i] == null) {
                items[i] = o;
                insertado = true;
            }
            i = i + 1;
        }
        if (!insertado) {
            throw new InventarioLlenoException("Inventario lleno");
        }
    }

    public Objeto get(int indice) throws IndiceInvalidoException {
        if (indice < 0 || indice >= items.length || items[indice] == null) {
            throw new IndiceInvalidoException("Indice invalido: " + indice);
        }
        return items[indice];
    }

    public Objeto remove(int indice) throws IndiceInvalidoException {
        if (indice < 0 || indice >= items.length || items[indice] == null) {
            throw new IndiceInvalidoException("Indice invalido: " + indice);
        }
        Objeto obj = items[indice];
        items[indice] = null;
        return obj;
    }

    @Override
    public String toString() {
        String resultado = "";
        int i = 0;
        while (i < items.length) {
            if (items[i] == null) {
                resultado = resultado + "[" + i + "] (vacio)\n";
            } else {
                resultado = resultado + "[" + i + "] " + items[i].toString() + "\n";
            }
            i = i + 1;
        }
        return resultado;
    }
}
