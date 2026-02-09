package p1_sesion_1;

public class Ejemplo_1 {

	public static void main(String[] args) {
		
		int edad = 30;
		
		for(int i=0; edad<10; i++) { // Error al evaluar la condición (edad en vez de i)
			edad += 1;
		}
		
		System.out.printf("La edad final es: %d", edad);

	}

}
