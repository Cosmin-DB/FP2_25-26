import java.io.BufferedReader;
import java.io.FileReader;

public class Sesion1Main {

    public static void main(String[] args) throws Exception {

        // === PASADA 1: contar lineas ===
        BufferedReader br = new BufferedReader(new FileReader("sesiones/sesion1/datos.txt"));
        int count = 0;
        String linea = br.readLine();
        while (linea != null) {
            if (linea.trim().length() > 0) {
                count = count + 1;
            }
            linea = br.readLine();
        }
        br.close();

        // === PASADA 2: cargar en array ===
        String[] lineas = new String[count];
        br = new BufferedReader(new FileReader("sesiones/sesion1/datos.txt"));
        linea = br.readLine();
        int idx = 0;
        while (linea != null) {
            if (linea.trim().length() > 0) {
                lineas[idx] = linea.trim();
                idx = idx + 1;
            }
            linea = br.readLine();
        }
        br.close();

        // === MOSTRAR FORMATEADO ===
        int i = 0;
        while (i < lineas.length) {
            String[] p = lineas[i].split(";");
            String tipo = p[0];

            System.out.println("[" + i + "] " + tipo);

            if (tipo.equals("ARMA")) {
                System.out.println("    nombre  = " + p[1]);
                System.out.println("    precio  = " + p[2]);
                System.out.println("    ataque  = " + p[3]);

            } else if (tipo.equals("ARMADURA")) {
                System.out.println("    nombre  = " + p[1]);
                System.out.println("    precio  = " + p[2]);
                System.out.println("    slot    = " + p[3]);
                System.out.println("    defensa = " + p[4]);

            } else if (tipo.equals("MATERIAL")) {
                System.out.println("    nombre  = " + p[1]);
                System.out.println("    precio  = " + p[2]);
                System.out.println("    calidad = " + p[3]);
            }

            System.out.println();
            i = i + 1;
        }
    }
}
