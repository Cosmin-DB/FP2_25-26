public class Armadura extends Objeto implements IEquipable {
    private TipoArmadura tipo;
    private int defensa;

    public Armadura(String nombre, double precio, int peso, TipoArmadura tipo, int defensa) {
        super(nombre, precio, peso);
        this.tipo = tipo;
        this.defensa = defensa;
    }

    @Override
    public int getBonoAtaque() {
        int bono = 0;
        return bono;
    }

    @Override
    public int getBonoDefensa() {
        int bono = defensa;
        return bono;
    }

    @Override
    public int getSlot() {
        int slot = 1;
        if (tipo == TipoArmadura.CUERPO) {
            slot = 2;
        } else if (tipo == TipoArmadura.PIES) {
            slot = 3;
        }
        return slot;
    }

    @Override
    public String toString() {
        return "ARMADURA: " + getNombre() + " | precio=" + getPrecio() + " | peso=" + getPeso()
                + " | tipo=" + tipo + " | DEF=+" + defensa;
    }
}
