package Dos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class ExposicionAutor extends Exposicion {

	// arraylist para guardar todos las exposiciones autor en el mismo sitio

	static ArrayList<Exposicion> listadoExposicionesAutor = new ArrayList<>();

	private String autor;

	public ExposicionAutor(boolean activa, String autor) {
		super(activa);
		this.autor = autor;

	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return "Exposicion sobre el autor: " + autor;
	}

	// metodo para generar una exposicion aleatoriamente para usar en la simulacion

	public static void generarExposicionAutorAleatorio() {

		Random aleatorio = new Random();

		int posicion = aleatorio.nextInt(5);

		String autor = Libro.listadoLibros.get(posicion).getAutor();

		ExposicionAutor exposicion1 = new ExposicionAutor(true, autor);

		listadoExposicionesAutor.add(exposicion1);

	}

	public static void generarExposicionAutorManual() {
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<String> listadoAutores = new ArrayList<>();

		for (Libro posicion : Libro.listadoLibros) {
			listadoAutores.add(posicion.getAutor());
		}

		System.out.println("Listado de autores: ");
		System.out.println(listadoAutores);
		System.out.println();
		System.out.println("Elige el autor para crear la exposición: ");

		try {
			String autor = entrada.readLine();

			if (!listadoAutores.contains(autor)) {
				System.out.println("El autor no existe");
			} else {

				ExposicionAutor exposicion1 = new ExposicionAutor(true, autor);
				listadoExposicionesAutor.get(listadoExposicionesAutor.size()-1).setActiva(false);
				listadoExposicionesAutor.add(exposicion1);
				System.out.println("Exposición sobre " + autor + " creada correctamente");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void mostrarExposicionesAutor() {
		System.out.println("Listado de exposiciones de autores: ");
		for (Exposicion exposicion : listadoExposicionesAutor) {
			System.out.println(exposicion);
		}
	}

	
	public static void mostrarExposicionAutorActiva() {
		System.out.println("Esta es la exposición de autor que hay activa actualmente: ");
		System.out.println(listadoExposicionesAutor.get(listadoExposicionesAutor.size()-1).toString());
	}
	
	
	
}