public class Personaje {
    private String nombre;
    private Objeto[] inventario;
    private Arma armaEquipada;

    public Personaje(String nombre, int capacidadInventario) {
        this.nombre = nombre;
        this.inventario = new Objeto[capacidadInventario];
        this.armaEquipada = null;
    }

    public boolean agregarAlInventario(Objeto obj) {
        boolean agregado = false;
        for (int i = 0; i < inventario.length; i = i + 1) {
            if (!agregado && inventario[i] == null) {
                inventario[i] = obj;
                agregado = true;
            }
        }
        return agregado;
    }

    public String listarInventario() {
        String resultado = "";
        for (int i = 0; i < inventario.length; i = i + 1) {
            if (inventario[i] == null) {
                resultado = resultado + "[" + i + "] (vacio)\n";
            } else {
                resultado = resultado + "[" + i + "] " + inventario[i].toString() + "\n";
            }
        }
        return resultado;
    }

    public boolean setArma(int indiceInv) {
        Objeto obj = null;
        if (indiceInv >= 0 && indiceInv < inventario.length && inventario[indiceInv] != null) {
            obj = inventario[indiceInv];
            inventario[indiceInv] = null;
        }
        Arma nuevaArma = (Arma) obj;
        return setArma(nuevaArma);
    }

    public boolean setArma(Arma arma) {
        boolean sePuedeEquipar = desequiparArma();
        if (sePuedeEquipar) {
            armaEquipada = arma;
        }
        return sePuedeEquipar;
    }

    public boolean desequiparArma() {
        boolean desequipado = true;
        if (armaEquipada != null) {
            boolean guardada = agregarAlInventario(armaEquipada);
            if (guardada) {
                armaEquipada = null;
            } else {
                desequipado = false;
            }
        }
        return desequipado;
    }

    @Override
    public String toString() {
        String arma = "(vacia)";
        int atk = 0;
        if (armaEquipada != null) {
            arma = armaEquipada.getNombre() + " (ATK=+" + armaEquipada.getAtaque() + ")";
            atk = armaEquipada.getAtaque();
        }
        int ocupados = 0;
        int i = 0;
        while (i < inventario.length) {
            if (inventario[i] != null) {
                ocupados = ocupados + 1;
            }
            i = i + 1;
        }
        return nombre + " | ATK=" + atk + " | ARMA=" + arma + " | INV=" + ocupados + "/" + inventario.length;
    }

}
