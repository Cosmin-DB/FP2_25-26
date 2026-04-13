public class Armadura extends Objeto implements IEquipable {
    private TipoArmadura tipo;
    private int defensa;

    public Armadura(String nombre, double precio, int peso, TipoArmadura tipo, int defensa) {
        super(nombre, precio, peso);
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
        switch (tipo) {
            case CUERPO:
                slot = 2;
                break;
            case PIES:
                slot = 3;
                break;
            default:
                break;
        }
        return slot;
    }

    @Override
    public String mostrar() {
        return "ARMADURA: " + getNombre() + " | precio=" + getPrecio() + " | peso=" + getPeso()
                + " | tipo=" + tipo + " | DEF=+" + defensa;
    }

    @Override
    public String toString() {
        return "ARMADURA;" + getNombre() + ";" + getPrecio() + ";" + getPeso() + ";" + tipo + ";" + defensa;
    }
}
