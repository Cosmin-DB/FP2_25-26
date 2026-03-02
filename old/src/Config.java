
/*
 * Configuración centralizada de la aplicación.
 *
 * NOTA DE DISEÑO:
 * Esta clase sigue el patrón "Constant Utility Class" (Clase final + Constructor privado).
 *
 * JUSTIFICACIÓN:
 * Se rechaza el uso de Interfaces para almacenar constantes (conocido como "Constant Interface Anti-pattern")
 * porque las interfaces deben definir comportamiento, no datos. Usar una interfaz "contamina" la API pública
 * de las clases que la implementan y rompe la encapsulación.
 *
 * REFERENCIAS Y RESPALDO TÉCNICO:
 * - Effective Java (Joshua Bloch), Item 22: "Use interfaces only to define types".
 * - SonarQube (Regla de Calidad): https://rules.sonarsource.com/java/RSPEC-1214
 * - Wikipedia (Definición del Anti-patrón): https://en.wikipedia.org/wiki/Constant_interface
 */
public final class Config {
    private Config() {
    }

    public static final int CAPACIDAD_INVENTARIO = 8;
    public static final int PORCENTAJE_REVENTA = 50;

    public static final String RUTA_TIENDA = "tienda.txt";
    public static final String RUTA_PERSONAJES = "personajes.txt";
}
