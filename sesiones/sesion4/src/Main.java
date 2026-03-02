import java.io.File;
import java.util.Scanner;

public class Main {
    private static final String RUTA_PERSONAJE_LOCAL = "personaje.txt";
    private static final String RUTA_PERSONAJE_REPO = "sesiones/sesion4/personaje.txt";

    public static void main(String[] args) throws Exception {
        File ficheroPersonaje = resolverFicheroPersonaje();
        Personaje personaje = cargarPersonaje(ficheroPersonaje);
        System.out.println("Personaje cargado.");
        Menu menu = new Menu(personaje);
        menu.run();
    }

    private static File resolverFicheroPersonaje() throws Exception {
        File fichero = new File(RUTA_PERSONAJE_LOCAL);
        if (!fichero.exists()) {
            fichero = new File(RUTA_PERSONAJE_REPO);
        }
        if (!fichero.exists()) {
            throw new Exception("No se encuentra personaje.txt");
        }
        return fichero;
    }

    private static Arma parsearArma(String[] p, int start) {
        String nombreArma = p[start + 1];
        double precioArma = Double.parseDouble(p[start + 2]);
        int pesoArma = Integer.parseInt(p[start + 3]);
        int ataque = Integer.parseInt(p[start + 4]);
        double longitud = Double.parseDouble(p[start + 5]);
        int antiguedad = Integer.parseInt(p[start + 6]);
        int integridad = Integer.parseInt(p[start + 7]);
        return new Arma(nombreArma, precioArma, pesoArma, ataque, longitud, antiguedad, integridad);
    }

    private static Objeto parsearObjeto(String[] p, int start) throws Exception {
        Objeto obj;
        String tipo = p[start];
        if (tipo.equals("ARMA")) {
            obj = parsearArma(p, start);
        } else if (tipo.equals("METAL")) {
            String nombreMetal = p[start + 1];
            double precioMetal = Double.parseDouble(p[start + 2]);
            int pesoMetal = Integer.parseInt(p[start + 3]);
            int dureza = Integer.parseInt(p[start + 4]);
            int pureza = Integer.parseInt(p[start + 5]);
            String composicion = p[start + 6];
            obj = new Metal(nombreMetal, precioMetal, pesoMetal, dureza, pureza, composicion);
        } else {
            throw new Exception("Tipo no soportado en inventario: " + tipo);
        }
        return obj;
    }

    private static Personaje cargarPersonaje(File fichero) throws Exception {
        Scanner sc = new Scanner(fichero);

        String[] pPersonaje = sc.nextLine().split(";");
        String nombre = pPersonaje[1];
        int capacidad = Integer.parseInt(pPersonaje[2]);
        Personaje personaje = new Personaje(nombre, capacidad);

        String[] pArmaEquipada = sc.nextLine().split(";");
        Arma armaEquipada = parsearArma(pArmaEquipada, 0);
        personaje.setArma(armaEquipada);

        while (sc.hasNextLine()) {
            String[] pInv = sc.nextLine().split(";");
            Objeto obj = parsearObjeto(pInv, 1);
            personaje.agregarAlInventario(obj);
        }
        sc.close();

        return personaje;
    }
}
