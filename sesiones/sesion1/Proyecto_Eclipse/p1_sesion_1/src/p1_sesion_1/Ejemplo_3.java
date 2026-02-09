package p1_sesion_1;

import java.util.Scanner;

public class Ejemplo_3 {

	public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce tu nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Introduce tu edad: ");
        int edad = scanner.nextInt();

        System.out.printf("Hola %s, tienes %d años. \n", nombre, edad);

        scanner.close();

    }

}
