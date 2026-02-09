package p1_sesion_1;

import java.util.Scanner;
import java.io.File;

public class Ejemplo_4 {

    public static void main(String[] args) throws Exception {

        // === PASADA 1: contar lineas ===
        Scanner scanner = new Scanner(new File("datos.txt"));
        int count = 0;
        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            if (linea.trim().length() > 0) {
                count = count + 1;
            }
        }
        
        System.out.printf("El fichero tiene %d líneas \n", count);
        
        scanner.close();

        // === PASADA 2: cargar en array ===
        String[] lineas = new String[count];
        scanner = new Scanner(new File("datos.txt"));
        int idx = 0;
        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            if (linea.trim().length() > 0) {
                lineas[idx] = linea.trim();
                idx = idx + 1;
            }
        }
        
        scanner.close();

        // === MOSTRAR FORMATEADO ===
        int i = 0;
        while (i < lineas.length) {
            String[] p = lineas[i].split(";");
            String tipo = p[0];

            System.out.printf("[%d] %s \n", i, tipo);

            switch (tipo) {
                case "EQUIPAMIENTO":
                    System.out.printf("    nombre  = %s \n", p[1]);
                    System.out.printf("    precio  = %d \n", Integer.parseInt(p[2]));
                    System.out.printf("    deporte = %s \n", p[3]);
                    break;

                case "ROPA":
                    System.out.printf("    nombre       = %s \n", p[1]);
                    System.out.printf("    precio       = %d \n", Integer.parseInt(p[2]));
                    System.out.printf("    parte_cuerpo = %s \n", p[3]);
                    System.out.printf("    talla        = %c \n", p[4].charAt(0));
                    break;

                case "ACCESORIO":
                    System.out.printf("    nombre = %s \n", p[1]);
                    System.out.printf("    precio = %d \n", Integer.parseInt(p[2]));
                    System.out.printf("    tipo   = %s \n", p[3]);
                    break;

                default:
                    System.out.println("Tipo desconocido: " + tipo);
                    break;
            }

            System.out.println();
            i = i + 1;
        }
    }

}
