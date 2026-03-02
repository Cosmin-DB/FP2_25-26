public class Arma extends Objeto implements IEquipable {
    private int ataque;

    public Arma(String nombre, int precio, int peso, int ataque) {
        super(nombre, precio, peso);
        this.ataque = ataque;
    }

    public int getAtaque() {
        return ataque;
    }

    @Override
    public int getBonoAtaque() {
        return ataque;
    }

    @Override
    public int getBonoDefensa() {
        return 0;
    }

    @Override
    public SlotEquipo getSlot() {
        return SlotEquipo.ARMA;
    }

    @Override
    public Objeto copia() {
        return new Arma(getNombre(), getPrecio(), getPeso(), ataque);
    }

    @Override
    public String toString() {
        return "ARMA: " + getNombre() + " | precio=" + getPrecio() + " | peso=" + getPeso() + " | ATK=+" + ataque;
    }
}
