package p1_sesion_1;

public class Ejemplo_2 {

	public static void main(String[] args) {
		
		int suma = calcularSumaPares(1, 10);
		
        System.out.println("La suma de los números pares entre 1 y 10 (incluidos) es: " + suma);
        
    }
    
    public static int calcularSumaPares(int inicio, int fin) {
        int suma = 0;
        
        for (int i = inicio; i < fin; i++) { // Error en la condición (i <= fin)
            if (i % 2 == 1) {   // Error al calcular el módulo (1 en vez de 0)
                suma += i;
            }
        }
        
        return suma;
    }

}
