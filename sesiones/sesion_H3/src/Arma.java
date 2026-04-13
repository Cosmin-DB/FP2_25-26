public class Arma extends Objeto implements IEquipable {
    private int ataque;
    private double longitud;
    private int antiguedad;
    private int integridad;

    public Arma(String nombre, double precio, int peso, int ataque, double longitud, int antiguedad, int integridad) {
        super(nombre, precio, peso);
        this.ataque = ataque;
        this.longitud = longitud;
        this.antiguedad = antiguedad;
        this.integridad = integridad;
    }

    public int getAtaque() {
        return ataque;
    }

    public double getLongitud() {
        return longitud;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public int getIntegridad() {
        return integridad;
    }

    @Override
    public int getBonoAtaque() {
        int bono = ataque;
        return bono;
    }

    @Override
    public int getBonoDefensa() {
        int bono = 0;
        return bono;
    }

    @Override
    public int getSlot() {
        int slot = 0;
        return slot;
    }

    @Override
    public String mostrar() {
        return "ARMA: " + getNombre() + " | precio=" + getPrecio() + " | peso=" + getPeso()
                + " | ATK=+" + ataque + " | long=" + longitud + " | ant=" + antiguedad + " | int=" + integridad;
    }

    @Override
    public String toString() {
        return "ARMA;" + getNombre() + ";" + getPrecio() + ";" + getPeso() + ";" + ataque + ";" + longitud + ";"
                + antiguedad + ";" + integridad;
    }
}
