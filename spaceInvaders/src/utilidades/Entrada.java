package utilidades;
import java.io.*;

public class Entrada {
	static String inicializar() {
		String buzon = "";
		InputStreamReader flujo = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(flujo);
		try {
			buzon = teclado.readLine();
		} catch (Exception e) {
			System.out.append("Entrada incorrecta)");
		}
		return buzon;
	}

	public static int entero() {
		int valor = Integer.parseInt(inicializar());
		return valor;
	}
	
	public static double real() {
		double valor = Double.parseDouble(inicializar());
		return valor;
	}
	
	public static String cadena() {
		String valor = inicializar();
		return valor;
	}

	public static char caracter() {
		String valor = inicializar();
		return valor.charAt(0);
	}

	public static int entero(String mensajeSolicitandoDato, String mensajeError) {
		// Si mensajeError es null se lanzará excepción en caso de error en vez
		// de volver a solicitar de nuevo el dato
		boolean bien;
		int n=0;
		do {
			bien = true;
			System.out.print(mensajeSolicitandoDato);
			try {
				n = Integer.parseInt(inicializar());
			} catch (NumberFormatException e) {
				if (mensajeError!=null) {
				System.out.println(mensajeError);
				bien = false;
				}
				else //null
					throw e; //Relanzamos la excepción manejada: e
			}
		} while (!bien);
		return n;
	}
	
	
	public static long enteroLargo(String mensajeSolicitandoDato, String mensajeError) {
		// Si mensajeError es null se lanzará excepción en caso de error en vez
		// de volver a solicitar de nuevo el dato
		boolean bien;
		long n=0;
		do {
			bien = true;
			System.out.print(mensajeSolicitandoDato);
			try {
				n = Long.valueOf(inicializar());
			} catch (NumberFormatException e) {
				if (mensajeError!=null) {
				System.out.println(mensajeError);
				bien = false;
				}
				else //null
					throw e; //Relanzamos la excepción manejada: e
			}
		} while (!bien);
		return n;
	}
	
	
	public static double real(String mensajeSolicitandoDato, String mensajeError) {
		// Si mensajeError es null se lanzará excepción en caso de error en vez
		// de volver a solicitar de nuevo el dato
		boolean bien;
		double d=0;
		do {
			bien = true;
			System.out.print(mensajeSolicitandoDato);
			try {
				d = Double.parseDouble(inicializar());
			} catch (NumberFormatException e) {
				if (mensajeError!=null) {
				System.out.println(mensajeError);
				bien = false;
				}
				else //null
					throw e; //Relanzamos la excepción manejada: e
			}
		} while (!bien);
		return d;
	}
}