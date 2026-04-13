import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String RUTA_PERSONAJES = "personajes.txt";
    private static final String RUTA_TIENDA = "tienda.txt";

    public static void main(String[] args) {
        try {
            Personaje[] personajes = cargarPersonajes(RUTA_PERSONAJES);
            Tienda tienda = cargarTienda(RUTA_TIENDA);
            System.out.println("Datos cargados.");
            Menu menu = new Menu(personajes, tienda);
            menu.run();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado uno de los ficheros de entrada.");
        } catch (TipoObjetoInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (InventarioLlenoException e) {
            System.out.println(e.getMessage());
        }
    }
 
    private static Arma parsearArma(String[] p) {
        String nombre = p[1];
        double precio = Double.parseDouble(p[2]);
        int peso = Integer.parseInt(p[3]);
        int ataque = Integer.parseInt(p[4]);
        double longitud = Double.parseDouble(p[5]);
        int antiguedad = Integer.parseInt(p[6]);
        int integridad = Integer.parseInt(p[7]);
        Arma arma = new Arma(nombre, precio, peso, ataque, longitud, antiguedad, integridad);
        return arma;
    }

    private static Armadura parsearArmadura(String[] p) throws TipoObjetoInvalidoException {
        String nombre = p[1];
        double precio = Double.parseDouble(p[2]);
        int peso = Integer.parseInt(p[3]);
        TipoArmadura tipo = null;
        try {
            tipo = TipoArmadura.valueOf(p[4]);
        } catch (IllegalArgumentException e) {
            throw new TipoObjetoInvalidoException(p[4]);
        }
        int defensa = Integer.parseInt(p[5]);
        Armadura armadura = new Armadura(nombre, precio, peso, tipo, defensa);
        return armadura;
    }

    private static Metal parsearMetal(String[] p) {
        String nombre = p[1];
        double precio = Double.parseDouble(p[2]);
        int peso = Integer.parseInt(p[3]);
        int dureza = Integer.parseInt(p[4]);
        int pureza = Integer.parseInt(p[5]);
        String composicion = p[6];
        Metal metal = new Metal(nombre, precio, peso, dureza, pureza, composicion);
        return metal;
    }

    private static Objeto parsearObjeto(String[] p) throws TipoObjetoInvalidoException {
        Objeto obj = null;
        String tipo = p[0];
        switch (tipo) {
            case "ARMA":
                obj = parsearArma(p);
                break;
            case "ARMADURA":
                obj = parsearArmadura(p);
                break;
            case "METAL":
                obj = parsearMetal(p);
                break;
            default:
                throw new TipoObjetoInvalidoException(tipo);
        }
        return obj;
    }

    private static void procesarEquipo(Personaje actual, String[] p)
            throws TipoObjetoInvalidoException, InventarioLlenoException {
        String subtipo = p[0];
        switch (subtipo) {
            case "ARMA":
                Arma arma = parsearArma(p);
                actual.equipar(arma);
                break;
            case "ARMADURA":
                Armadura armadura = parsearArmadura(p);
                actual.equipar(armadura);
                break;
            case "VACIO":
                break;
            default:
                throw new TipoObjetoInvalidoException(subtipo);
        }
    }

    private static int contarPersonajes(String ruta) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(ruta));
        int total = 0;
        while (sc.hasNextLine()) {
            String[] p = sc.nextLine().split(";");
            switch (p[0]) {
                case "PERSONAJE":
                    total = total + 1;
                    break;
                default:
                    break;
            }
        }
        sc.close();
        return total;
    }

    private static Personaje[] cargarPersonajes(String ruta)
            throws FileNotFoundException, TipoObjetoInvalidoException, InventarioLlenoException {
        int total = contarPersonajes(ruta);
        Personaje[] personajes = new Personaje[total];

        Scanner sc = new Scanner(new File(ruta));
        int idx = -1;
        Personaje actual = null;
        while (sc.hasNextLine()) {
            String[] p = sc.nextLine().split(";");
            String tipoLinea = p[0];
            switch (tipoLinea) {
                case "PERSONAJE":
                    idx = idx + 1;
                    String nombre = p[1];
                    double dinero = Double.parseDouble(p[2]);
                    actual = new Personaje(nombre, dinero);
                    personajes[idx] = actual;
                    break;
                case "EQUIPO":
                    procesarEquipo(actual, Arrays.copyOfRange(p, 1, p.length));
                    break;
                case "INV":
                    Objeto obj = parsearObjeto(Arrays.copyOfRange(p, 1, p.length));
                    actual.agregarAlInventario(obj);
                    break;
                default:
                    break;
            }
        }
        sc.close();
        return personajes;
    }

    private static Tienda cargarTienda(String ruta)
            throws FileNotFoundException, TipoObjetoInvalidoException, InventarioLlenoException {
        Tienda tienda = new Tienda();
        Scanner sc = new Scanner(new File(ruta));
        while (sc.hasNextLine()) {
            String[] p = sc.nextLine().split(";");
            Objeto obj = parsearObjeto(p);
            tienda.agregarAlInventario(obj);
        }
        sc.close();
        return tienda;
    }
}
