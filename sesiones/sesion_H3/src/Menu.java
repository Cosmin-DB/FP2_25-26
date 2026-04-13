import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private Scanner sc;
    private Personaje[] personajes;
    private Tienda tienda;

    public Menu(Personaje[] personajes, Tienda tienda) {
        this.sc = new Scanner(System.in);
        this.personajes = personajes;
        this.tienda = tienda;
    }

    public void run() {
        menuPrincipal();
        System.out.println("Hasta pronto!");
        
    }

    private void menuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Listar personajes");
            System.out.println("2. Entrar en personaje");
            System.out.println("3. Salir");
            System.out.print("Opcion: ");
            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    listarPersonajes();
                    break;
                case 2:
                    listarPersonajes();
                    System.out.print("Indice: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    menuPersonaje(personajes[idx]);
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    throw new RuntimeException("Opcion invalida");
            }
        }
    }

    private void listarPersonajes() {
        for (int i = 0; i < personajes.length; i = i + 1) {
            System.out.println("[" + i + "] " + personajes[i].toString());
        }
    }

    private void menuPersonaje(Personaje p) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== MENU PERSONAJE ===");
            System.out.println("1. Ver personaje");
            System.out.println("2. Ver inventario");
            System.out.println("3. Equipar desde inventario");
            System.out.println("4. Desequipar slot");
            System.out.println("5. Ir a tienda");
            System.out.println("6. Guardar personaje");
            System.out.println("7. Volver");
            System.out.print("Opcion: ");
            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    System.out.println(p.toString());
                    break;
                case 2:
                    System.out.println(p.listarInventario());
                    break;
                case 3:
                    System.out.println(p.listarInventario());
                    System.out.print("Indice: ");
                    int indiceInv = Integer.parseInt(sc.nextLine());
                    try {
                        p.equipar(indiceInv);
                        System.out.println("Equipado.");
                    } catch (PosicionVaciaException e) {
                        System.out.println(e.getMessage());
                    } catch (ObjetoNoEquipableException e) {
                        System.out.println(e.getMessage());
                    } catch (InventarioLlenoException e) {
                        System.out.println(e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Indice invalido.");
                    }
                    break;
                case 4:
                    System.out.println("0=ARMA, 1=CABEZA, 2=CUERPO, 3=PIES");
                    System.out.print("Slot: ");
                    int slot = Integer.parseInt(sc.nextLine());
                    try {
                        p.desequipar(slot);
                        System.out.println("Desequipado.");
                    } catch (InventarioLlenoException e) {
                        System.out.println(e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Slot invalido.");
                    }
                    break;
                case 5:
                    menuTienda(p);
                    break;
                case 6:
                    try {
                        p.guardar();
                        System.out.println("Personaje guardado.");
                    } catch (IOException e) {
                        System.out.println("No se ha podido guardar el personaje.");
                    }
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    throw new RuntimeException("Opcion invalida");
            }
        }
    }

    private void menuTienda(Personaje p) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== MENU TIENDA ===");
            System.out.println("1. Ver tienda");
            System.out.println("2. Comprar");
            System.out.println("3. Volver");
            System.out.print("Opcion: ");
            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    System.out.println(tienda.toString());
                    break;
                case 2:
                    System.out.println(tienda.toString());
                    System.out.print("Indice: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    try {
                        tienda.venderA(p, idx);
                        System.out.println("Compra realizada.");
                    } catch (PosicionVaciaException e) {
                        System.out.println(e.getMessage());
                    } catch (DineroInsuficienteException e) {
                        System.out.println(e.getMessage());
                    } catch (InventarioLlenoException e) {
                        System.out.println(e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Indice invalido.");
                    }
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    throw new RuntimeException("Opcion invalida");
            }
        }
    }
}
