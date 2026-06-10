import java.io.IOException;
import java.io.RandomAccessFile;

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

    public void comprar(Objeto obj) throws DineroInsuficienteException, InventarioLlenoException {
        if (dinero < obj.getPrecio()) {
            throw new DineroInsuficienteException();
        }
        inventario.agregar(obj);
        dinero = dinero - obj.getPrecio();
    }

    public String listarInventario() {
        return inventario.toString();
    }

    public void agregarAlInventario(Objeto obj) throws InventarioLlenoException {
        inventario.agregar(obj);
    }

    public void equipar(int indiceInv)
            throws PosicionVaciaException, ObjetoNoEquipableException, InventarioLlenoException {
        Objeto obj = inventario.ver(indiceInv);
        if (obj == null) {
            throw new PosicionVaciaException(indiceInv);
        }
        if (obj instanceof IEquipable) {
            IEquipable equipable = (IEquipable) obj;
            equipar(equipable);
            inventario.sacar(indiceInv);
        } else {
            throw new ObjetoNoEquipableException();
        }
    }

    public void equipar(IEquipable equipable) throws InventarioLlenoException {
        int slot = equipable.getSlot();
        desequipar(slot);
        equipo[slot] = equipable;
    }

    public void desequipar(int slot) throws InventarioLlenoException {
        if (equipo[slot] != null) {
            inventario.agregar((Objeto) equipo[slot]);
            equipo[slot] = null;
        }
    }

    public void guardar() throws IOException {
        RandomAccessFile fichero = new RandomAccessFile(nombre + ".txt", "rw");
        fichero.setLength(0);
        fichero.writeBytes("PERSONAJE;" + nombre + ";" + dinero + "\n");
        for (int i = 0; i < equipo.length; i = i + 1) {
            if (equipo[i] == null) {
                fichero.writeBytes("EQUIPO;VACIO;" + textoSlot(i) + "\n");
            } else {
                fichero.writeBytes("EQUIPO;" + ((Objeto) equipo[i]).toString() + "\n");
            }
        }
        for (int i = 0; i < inventario.getCapacidad(); i = i + 1) {
            Objeto obj = inventario.ver(i);
            if (obj != null) {
                fichero.writeBytes("INV;" + obj.toString() + "\n");
            }
        }
        fichero.writeBytes("FIN\n");
        fichero.close();
    }

    private String textoSlot(int slot) {
        String nombreSlot = "ARMA";
        switch (slot) {
            case 1:
                nombreSlot = "CABEZA";
                break;
            case 2:
                nombreSlot = "CUERPO";
                break;
            case 3:
                nombreSlot = "PIES";
                break;
            default:
                break;
        }
        return nombreSlot;
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
            String slot = textoSlot(i);
            resumen = resumen + "\n\t" + slot + ": ";
            if (equipo[i] == null) {
                resumen = resumen + "(vacio)";
            } else {
                resumen = resumen + ((Objeto) equipo[i]).mostrar();
            }
        }
        return resumen;
    }
}
