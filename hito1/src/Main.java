import java.io.File;
import java.util.Scanner;

public class Main {
    private static final String RUTA_PERSONAJES = "personaje.txt";

    public static void main(String[] args) throws Exception {
        Personaje personaje = cargarPersonaje(RUTA_PERSONAJES);
        System.out.println("Personaje cargado.");
        Menu menu = new Menu(personaje);
        menu.run();
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

    private static Objeto parsearObjeto(String[] p, int start) {
        Objeto obj;
        String tipo = p[start];
        if (tipo.equals("ARMA")) {
            obj = parsearArma(p, start);
        } else {
            String nombreMetal = p[start + 1];
            double precioMetal = Double.parseDouble(p[start + 2]);
            int pesoMetal = Integer.parseInt(p[start + 3]);
            int dureza = Integer.parseInt(p[start + 4]);
            int pureza = Integer.parseInt(p[start + 5]);
            String composicion = p[start + 6];
            obj = new Metal(nombreMetal, precioMetal, pesoMetal, dureza, pureza, composicion);
        }
        return obj;
    }

    private static Personaje cargarPersonaje(String ruta) throws Exception {
        Scanner sc = new Scanner(new File(ruta));

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
