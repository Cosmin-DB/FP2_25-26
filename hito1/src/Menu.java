import java.util.Scanner;

public class Menu {
    private Scanner sc;
    private Personaje personaje;

    public Menu(Personaje personaje) {
        this.sc = new Scanner(System.in);
        this.personaje = personaje;
    }

    public void run() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== MENU PERSONAJE ===");
            System.out.println(personaje.toString());
            System.out.println("1. Ver inventario");
            System.out.println("2. Equipar arma");
            System.out.println("3. Desequipar arma");
            System.out.println("4. Salir");

            System.out.print("Opcion: ");
            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    System.out.println("\n--- Inventario ---");
                    System.out.println(personaje.listarInventario());
                    break;
                case 2:
                    System.out.println(personaje.listarInventario());
                    System.out.print("Indice: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (personaje.setArma(idx)) {
                        System.out.println("Arma equipada.");
                    } else {
                        System.out.println("No se pudo equipar.");
                    }
                    break;
                case 3:
                    if (personaje.desequiparArma()) {
                        System.out.println("Arma desequipada.");
                    } else {
                        System.out.println("No se pudo desequipar.");
                    }
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Entrada invalida");
                    break;
            }
        }
        System.out.println("Hasta pronto!");
    }
}
