public class Personaje {
    private String nombre;
    private int dinero;
    private int ataqueTotal;
    private int defensaTotal;
    private Inventario inventario;
    private IEquipable[] equipo;

    public Personaje(String nombre, int dinero, int capacidadInventario) {
        this.nombre = nombre;
        this.dinero = dinero;
        this.inventario = new Inventario(capacidadInventario);
        this.equipo = new IEquipable[4];
        this.ataqueTotal = 0;
        this.defensaTotal = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int n) {
        if (n < 0) {
            this.dinero = 0;
        } else {
            this.dinero = n;
        }
    }

    public int getAtaqueTotal() {
        return ataqueTotal;
    }

    public int getDefensaTotal() {
        return defensaTotal;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public IEquipable[] getEquipo() {
        return equipo;
    }

    public void equiparDirecto(SlotEquipo slot, IEquipable eq) {
        equipo[slotToIndex(slot)] = eq;
        recalcularStats();
    }

    public void equiparDesdeInventario(int indiceInv)
            throws IndiceInvalidoException, NoEquipableException, InventarioLlenoException {
        Objeto obj = inventario.remove(indiceInv);

        if (!(obj instanceof IEquipable)) {
            inventario.add(obj);
            throw new NoEquipableException("No es equipable");
        }

        IEquipable equipable = (IEquipable) obj;
        int idxSlot = slotToIndex(equipable.getSlot());

        if (equipo[idxSlot] != null) {
            inventario.add((Objeto) equipo[idxSlot]);
        }

        equipo[idxSlot] = equipable;
        recalcularStats();
    }

    public void desequipar(SlotEquipo slot) throws InventarioLlenoException {
        int idx = slotToIndex(slot);
        if (equipo[idx] != null) {
            inventario.add((Objeto) equipo[idx]);
            equipo[idx] = null;
            recalcularStats();
        }
    }

    public void desequiparTodo() throws InventarioLlenoException {
        SlotEquipo[] slots = { SlotEquipo.ARMA, SlotEquipo.CABEZA, SlotEquipo.CUERPO, SlotEquipo.PIES };
        int i = 0;
        while (i < slots.length) {
            desequipar(slots[i]);
            i = i + 1;
        }
    }

    public String equipoToString() {
        SlotEquipo[] slots = { SlotEquipo.ARMA, SlotEquipo.CABEZA, SlotEquipo.CUERPO, SlotEquipo.PIES };
        String resultado = "";
        int i = 0;
        while (i < slots.length) {
            int idx = slotToIndex(slots[i]);
            if (equipo[idx] == null) {
                resultado = resultado + slots[i] + ": (vacio)\n";
            } else {
                resultado = resultado + slots[i] + ": " + equipo[idx].toString() + "\n";
            }
            i = i + 1;
        }
        return resultado;
    }

    @Override
    public String toString() {
        return nombre + " | dinero=" + dinero + " | ATK=" + ataqueTotal + " | DEF=" + defensaTotal;
    }

    private int slotToIndex(SlotEquipo slot) {
        int idx = 0;
        if (slot == SlotEquipo.CABEZA) {
            idx = 1;
        } else if (slot == SlotEquipo.CUERPO) {
            idx = 2;
        } else if (slot == SlotEquipo.PIES) {
            idx = 3;
        }
        return idx;
    }

    private void recalcularStats() {
        int atk = 0;
        int def = 0;
        int i = 0;
        while (i < equipo.length) {
            if (equipo[i] != null) {
                atk = atk + equipo[i].getBonoAtaque();
                def = def + equipo[i].getBonoDefensa();
            }
            i = i + 1;
        }
        ataqueTotal = atk;
        defensaTotal = def;
    }
}
