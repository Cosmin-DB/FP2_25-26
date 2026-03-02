import java.io.File;
import java.util.Scanner;

public class Main {
    private static final String RUTA_PERSONAJES = "personajes.txt";
    private static final String RUTA_TIENDA = "tienda.txt";

    public static void main(String[] args) throws Exception {
        Personaje[] personajes = cargarPersonajes(RUTA_PERSONAJES);
        Tienda tienda = cargarTienda(RUTA_TIENDA);
        System.out.println("Datos cargados.");
        Menu menu = new Menu(personajes, tienda);
        menu.run();
    }
 
    private static Arma parsearArma(String[] p, int start) {
    	
        String nombre = p[start + 1];
        double precio = Double.parseDouble(p[start + 2]);
        int peso = Integer.parseInt(p[start + 3]);
        int ataque = Integer.parseInt(p[start + 4]);
        double longitud = Double.parseDouble(p[start + 5]);
        int antiguedad = Integer.parseInt(p[start + 6]);
        int integridad = Integer.parseInt(p[start + 7]);
        Arma arma = new Arma(nombre, precio, peso, ataque, longitud, antiguedad, integridad);
        return arma;
    }

    private static Armadura parsearArmadura(String[] p, int start) {
        String nombre = p[start + 1];
        double precio = Double.parseDouble(p[start + 2]);
        int peso = Integer.parseInt(p[start + 3]);
        TipoArmadura tipo = TipoArmadura.valueOf(p[start + 4]);
        int defensa = Integer.parseInt(p[start + 5]);
        Armadura armadura = new Armadura(nombre, precio, peso, tipo, defensa);
        return armadura;
    }

    private static Metal parsearMetal(String[] p, int start) {
        String nombre = p[start + 1];
        double precio = Double.parseDouble(p[start + 2]);
        int peso = Integer.parseInt(p[start + 3]);
        int dureza = Integer.parseInt(p[start + 4]);
        int pureza = Integer.parseInt(p[start + 5]);
        String composicion = p[start + 6];
        Metal metal = new Metal(nombre, precio, peso, dureza, pureza, composicion);
        return metal;
    }

    private static Objeto parsearObjeto(String[] p, int start) throws Exception {
        Objeto obj = null;
        String tipo = p[start];
        switch (tipo) {
            case "ARMA":
                obj = parsearArma(p, start);
                break;
            case "ARMADURA":
                obj = parsearArmadura(p, start);
                break;
            case "METAL":
                obj = parsearMetal(p, start);
                break;
            default:
                throw new Exception("Tipo no soportado: " + tipo);
        }
        return obj;
    }

    private static void procesarEquipo(Personaje actual, String[] p) throws Exception {
        String subtipo = p[1];
        switch (subtipo) {
            case "ARMA":
                Arma arma = parsearArma(p, 1);
                actual.equipar(arma);
                break;
            case "ARMADURA":
                Armadura armadura = parsearArmadura(p, 1);
                actual.equipar(armadura);
                break;
            case "VACIO":
                break;
            default:
                throw new Exception("Subtipo EQUIPO no soportado: " + subtipo);
        }
    }

    private static int contarPersonajes(String ruta) throws Exception {
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

    private static Personaje[] cargarPersonajes(String ruta) throws Exception {
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
                    procesarEquipo(actual, p);
                    break;
                case "INV":
                    Objeto obj = parsearObjeto(p, 1);
                    actual.agregarAlInventario(obj);
                    break;
                default:
                    break;
            }
        }
        sc.close();
        return personajes;
    }

    private static Tienda cargarTienda(String ruta) throws Exception {
        Tienda tienda = new Tienda();
        Scanner sc = new Scanner(new File(ruta));
        while (sc.hasNextLine()) {
            String[] p = sc.nextLine().split(";");
            Objeto obj = parsearObjeto(p, 0);
            tienda.agregarAlInventario(obj);
        }
        sc.close();
        return tienda;
    }
}
