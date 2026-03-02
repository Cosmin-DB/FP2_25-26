import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Objeto[] tiendaItems = cargarTienda(Config.RUTA_TIENDA);
            System.out.println("Tienda: " + tiendaItems.length + " objetos.");

            Personaje[] personajes = cargarPersonajes(Config.RUTA_PERSONAJES);
            System.out.println("Personajes: " + personajes.length);

            Tienda tienda = new Tienda("Tienda", tiendaItems, Config.PORCENTAJE_REVENTA);
            Menu menu = new Menu(personajes, tienda);
            menu.run();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Objeto[] cargarTienda(String ruta) throws IOException, FormatoFicheroException {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        int count = 0;
        String linea = br.readLine();
        while (linea != null) {
            if (linea.trim().length() > 0) {
                count = count + 1;
            }
            linea = br.readLine();
        }
        br.close();

        Objeto[] items = new Objeto[count];
        br = new BufferedReader(new FileReader(ruta));
        linea = br.readLine();
        int idx = 0;
        while (linea != null) {
            if (linea.trim().length() > 0) {
                items[idx] = parsearObjeto(linea.trim().split(";"), 0);
                idx = idx + 1;
            }
            linea = br.readLine();
        }
        br.close();
        return items;
    }

    private static Objeto parsearObjeto(String[] p, int start) throws FormatoFicheroException {
        Objeto resultado = null;
        try {
            String tipo = p[start].trim().toUpperCase();

            if (tipo.equals("ARMA")) {
                String nombre = p[start + 1].trim();
                int precio = Integer.parseInt(p[start + 2].trim());
                int peso = Integer.parseInt(p[start + 3].trim());
                int ataque = Integer.parseInt(p[start + 4].trim());
                resultado = new Arma(nombre, precio, peso, ataque);

            } else if (tipo.equals("ARMADURA")) {
                String nombre = p[start + 1].trim();
                int precio = Integer.parseInt(p[start + 2].trim());
                int peso = Integer.parseInt(p[start + 3].trim());
                TipoArmadura tipoArm = TipoArmadura.valueOf(p[start + 4].trim().toUpperCase());
                int defensa = Integer.parseInt(p[start + 5].trim());
                resultado = new Armadura(nombre, precio, peso, tipoArm, defensa);

            } else if (tipo.equals("METAL")) {
                String nombre = p[start + 1].trim();
                int precio = Integer.parseInt(p[start + 2].trim());
                int peso = Integer.parseInt(p[start + 3].trim());
                int dureza = Integer.parseInt(p[start + 4].trim());
                int pureza = Integer.parseInt(p[start + 5].trim());
                String composicion = p[start + 6].trim();
                resultado = new Metal(nombre, precio, peso, dureza, pureza, composicion);

            } else {
                throw new FormatoFicheroException("Tipo desconocido: " + tipo);
            }
        } catch (Exception e) {
            throw new FormatoFicheroException("Error parseando objeto");
        }
        return resultado;
    }

    private static Personaje[] cargarPersonajes(String ruta)
            throws IOException, FormatoFicheroException, InventarioLlenoException {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        int count = 0;
        String linea = br.readLine();
        while (linea != null) {
            if (linea.trim().startsWith("PERSONAJE;")) {
                count = count + 1;
            }
            linea = br.readLine();
        }
        br.close();

        Personaje[] personajes = new Personaje[count];
        br = new BufferedReader(new FileReader(ruta));
        linea = br.readLine();
        int idx = 0;
        Personaje actual = null;
        boolean bloqueAbierto = false;

        while (linea != null) {
            String l = linea.trim();

            if (l.startsWith("PERSONAJE;")) {
                String[] p = l.split(";");
                String nombre = p[1].trim();
                int dinero = Integer.parseInt(p[2].trim());
                int capacidad = Integer.parseInt(p[3].trim());
                actual = new Personaje(nombre, dinero, capacidad);
                personajes[idx] = actual;
                bloqueAbierto = true;

            } else if (l.startsWith("EQUIPO;") && actual != null) {
                String[] p = l.split(";");
                String subtipo = p[1].trim().toUpperCase();

                if (subtipo.equals("VACIO")) {
                    // No hacemos nada, slot queda null

                } else if (subtipo.equals("ARMA")) {
                    String nombre = p[2].trim();
                    int precio = Integer.parseInt(p[3].trim());
                    int peso = Integer.parseInt(p[4].trim());
                    int ataque = Integer.parseInt(p[5].trim());
                    Arma arma = new Arma(nombre, precio, peso, ataque);
                    actual.equiparDirecto(SlotEquipo.ARMA, arma);

                } else if (subtipo.equals("ARMADURA")) {
                    String nombre = p[2].trim();
                    int precio = Integer.parseInt(p[3].trim());
                    int peso = Integer.parseInt(p[4].trim());
                    TipoArmadura tipoArm = TipoArmadura.valueOf(p[5].trim().toUpperCase());
                    int defensa = Integer.parseInt(p[6].trim());
                    Armadura armadura = new Armadura(nombre, precio, peso, tipoArm, defensa);
                    actual.equiparDirecto(tipoArmaduraToSlot(tipoArm), armadura);
                }

            } else if (l.startsWith("INV;") && actual != null) {
                String[] p = l.split(";");
                Objeto obj = parsearObjeto(p, 1);
                actual.getInventario().add(obj);

            } else if (l.equals("FIN")) {
                idx = idx + 1;
                bloqueAbierto = false;
            }

            linea = br.readLine();
        }
        br.close();

        if (bloqueAbierto) {
            throw new FormatoFicheroException("Falta FIN al final del fichero");
        }

        return personajes;
    }

    private static SlotEquipo tipoArmaduraToSlot(TipoArmadura tipo) {
        SlotEquipo slot = SlotEquipo.CABEZA;
        if (tipo == TipoArmadura.CUERPO) {
            slot = SlotEquipo.CUERPO;
        } else if (tipo == TipoArmadura.PIES) {
            slot = SlotEquipo.PIES;
        }
        return slot;
    }
}
