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
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Listar personajes");
            System.out.println("2. Entrar en personaje");
            System.out.println("3. Salir");

            int op = leerInt("Opcion: ");

            if (op == 1) {
                System.out.println("\n--- Personajes ---");
                listarPersonajes();
                pausar();
            } else if (op == 2) {
                listarPersonajes();
                int idx = leerInt("Indice: ");
                if (idx >= 0 && idx < personajes.length) {
                    menuPersonaje(personajes[idx]);
                } else {
                    System.out.println("Indice invalido");
                }
            } else if (op == 3) {
                salir = true;
            } else {
                System.out.println("Entrada invalida");
            }
        }
        System.out.println("Hasta pronto!");
    }

    private void listarPersonajes() {
        int i = 0;
        while (i < personajes.length) {
            System.out.println("[" + i + "] " + personajes[i].toString());
            i = i + 1;
        }
    }

    private void menuPersonaje(Personaje p) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== " + p.getNombre() + " ===");
            System.out.println(p.toString());
            System.out.println("1. Ver inventario");
            System.out.println("2. Ver equipo");
            System.out.println("3. Equipar desde inventario");
            System.out.println("4. Desequipar slot");
            System.out.println("5. Desequipar todo");
            System.out.println("6. Ir a tienda");
            System.out.println("7. Volver");

            int op = leerInt("Opcion: ");

            if (op == 1) {
                System.out.println("\n--- Inventario ---");
                System.out.println(p.getInventario().toString());
                pausar();
            } else if (op == 2) {
                System.out.println("\n--- Equipo ---");
                System.out.println(p.equipoToString());
                pausar();
            } else if (op == 3) {
                System.out.println(p.getInventario().toString());
                int idx = leerInt("Indice: ");
                try {
                    p.equiparDesdeInventario(idx);
                    System.out.println("Equipado.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (op == 4) {
                System.out.println("1=ARMA, 2=CABEZA, 3=CUERPO, 4=PIES");
                int s = leerInt("Slot: ");
                if (s >= 1 && s <= 4) {
                    try {
                        p.desequipar(numToSlot(s));
                        System.out.println("Desequipado.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                } else {
                    System.out.println("Slot invalido");
                }
            } else if (op == 5) {
                try {
                    p.desequiparTodo();
                    System.out.println("Todo desequipado.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (op == 6) {
                menuTienda(p);
            } else if (op == 7) {
                salir = true;
            } else {
                System.out.println("Entrada invalida");
            }
        }
    }

    private void menuTienda(Personaje p) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== TIENDA ===");
            System.out.println("Tu dinero: " + p.getDinero());
            System.out.println("1. Ver catalogo");
            System.out.println("2. Comprar");
            System.out.println("3. Vender");
            System.out.println("4. Volver");

            int op = leerInt("Opcion: ");

            if (op == 1) {
                System.out.println("\n--- Catalogo ---");
                listarCatalogo();
                pausar();
            } else if (op == 2) {
                listarCatalogo();
                int idx = leerInt("Indice: ");
                try {
                    tienda.comprar(p, idx);
                    System.out.println("Comprado.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (op == 3) {
                System.out.println(p.getInventario().toString());
                int idx = leerInt("Indice: ");
                try {
                    tienda.vender(p, idx);
                    System.out.println("Vendido.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (op == 4) {
                salir = true;
            } else {
                System.out.println("Entrada invalida");
            }
        }
    }

    private void listarCatalogo() {
        int i = 0;
        while (i < tienda.size()) {
            try {
                System.out.println("[" + i + "] " + tienda.getItem(i).toString());
            } catch (IndiceInvalidoException e) {
                // No deberia pasar
            }
            i = i + 1;
        }
    }

    private SlotEquipo numToSlot(int n) {
        SlotEquipo slot = SlotEquipo.ARMA;
        if (n == 2) {
            slot = SlotEquipo.CABEZA;
        } else if (n == 3) {
            slot = SlotEquipo.CUERPO;
        } else if (n == 4) {
            slot = SlotEquipo.PIES;
        }
        return slot;
    }

    private int leerInt(String msg) {
        System.out.print(msg);
        String s = sc.nextLine().trim();
        int valor = Integer.MIN_VALUE;
        try {
            valor = Integer.parseInt(s);
        } catch (NumberFormatException e) {
        }
        return valor;
    }

    private void pausar() {
        System.out.print("[Pulsa ENTER para continuar]");
        sc.nextLine();
    }
}
