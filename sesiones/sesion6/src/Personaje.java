public class Personaje {
    private String nombre;
    private double dinero;
    private Inventario inventario;
    private IEquipable[] equipo;

    public Personaje(String nombre, double dinero) {
        this.nombre = nombre;
        this.dinero = dinero;
        this.inventario = new Inventario(5);
        this.equipo = new IEquipable[4];
    }

    public boolean comprar(Objeto obj) {
        boolean comprado = false;
        if (dinero >= obj.getPrecio()) {
            boolean agregado = inventario.agregar(obj);
            if (agregado) {
                dinero = dinero - obj.getPrecio();
                comprado = true;
            }
        }
        return comprado;
    }

    public String listarInventario() {
        return inventario.toString();
    }

    public boolean agregarAlInventario(Objeto obj) {
        boolean agregado = inventario.agregar(obj);
        return agregado;
    }

    public boolean equipar(int indiceInv) {

        IEquipable equipable = (IEquipable) inventario.sacar(indiceInv);
        boolean equipado = equipar(equipable);
        if (!equipado) {
            inventario.agregar((Objeto) equipable);
        }
        return equipado;
    }

    public boolean equipar(IEquipable equipable) {
        boolean equipado;
        int slot = equipable.getSlot();
        boolean sePuedeEquipar = desequipar(slot);
        if (sePuedeEquipar) {
            equipo[slot] = equipable;
            equipado = true;
        } else {
            equipado = false;
        }
        return equipado;
    }

    public boolean desequipar(int slot) {
        boolean desequipado = true;
        if (equipo[slot] != null) {
            boolean guardado = inventario.agregar((Objeto) equipo[slot]);
            if (guardado) {
                equipo[slot] = null;
            } else {
                desequipado = false;
            }
        }
        return desequipado;
    }

    public int getAtaqueTotal() {
        int atk = 0;
        for (int i = 0; i < equipo.length; i = i + 1) {
            if (equipo[i] != null) {
                atk = atk + equipo[i].getBonoAtaque();
            }
        }
        return atk;
    }

    public int getDefensaTotal() {
        int def = 0;
        for (int i = 0; i < equipo.length; i = i + 1) {
            if (equipo[i] != null) {
                def = def + equipo[i].getBonoDefensa();
            }
        }
        return def;
    }

    @Override
    public String toString() {
        String resumen = nombre + " | dinero=" + dinero + " | ATK=" + getAtaqueTotal() + " | DEF=" + getDefensaTotal();
        for (int i = 0; i < equipo.length; i = i + 1) {
            String slot = "ARMA";
            if (i == 1) {
                slot = "CABEZA";
            } else if (i == 2) {
                slot = "CUERPO";
            } else if (i == 3) {
                slot = "PIES";
            }
            resumen = resumen + "\n\t" + slot + ": ";
            if (equipo[i] == null) {
                resumen = resumen + "(vacio)";
            } else {
                resumen = resumen + ((Objeto) equipo[i]).toString();
            }
        }
        return resumen;
    }
}
