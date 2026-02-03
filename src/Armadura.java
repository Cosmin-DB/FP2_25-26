public class Armadura extends Objeto implements IEquipable {
    private TipoArmadura tipo;
    private int defensa;

    public Armadura(String nombre, int precio, TipoArmadura tipo, int defensa) {
        super(nombre, precio);
        this.tipo = tipo;
        this.defensa = defensa;
    }

    public TipoArmadura getTipo() {
        return tipo;
    }

    public int getDefensa() {
        return defensa;
    }

    @Override
    public int getBonoAtaque() {
        return 0;
    }

    @Override
    public int getBonoDefensa() {
        return defensa;
    }

    @Override
    public SlotEquipo getSlot() {
        SlotEquipo slot = SlotEquipo.CABEZA;
        if (tipo == TipoArmadura.CUERPO) {
            slot = SlotEquipo.CUERPO;
        } else if (tipo == TipoArmadura.PIES) {
            slot = SlotEquipo.PIES;
        }
        return slot;
    }

    @Override
    public Objeto copia() {
        return new Armadura(getNombre(), getPrecio(), tipo, defensa);
    }

    @Override
    public String toString() {
        return "ARMADURA(" + tipo + "): " + getNombre() + " | precio=" + getPrecio() + " | DEF=+" + defensa;
    }
}
