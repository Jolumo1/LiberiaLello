package Dos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class ExposicionLibro extends Exposicion {

	// arraylist para guardar todos las exposiciones libro en el mismo sitio

	static ArrayList<Exposicion> listadoExposicionesLibro = new ArrayList<>();

	private String tituloLibro;

	public ExposicionLibro(boolean activa, String tituloLibro) {
		super(activa);
		this.tituloLibro = tituloLibro;

	}

	// metodo para generar una exposicion aleatoriamente para usar en la simulacion

	public static void generarExposicionLibroAleatorio() {

		Random aleatorio = new Random();

		int posicion = aleatorio.nextInt(Libro.listadoLibros.size());

		String titulo = Libro.listadoLibros.get(posicion).getTitulo();

		ExposicionAutor exposicion1 = new ExposicionAutor(true, titulo);

		listadoExposicionesLibro.add(exposicion1);

	}

	// metodo para crear una exposicion manualmente

	public static void generarExposicionLibroManual() {
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
//		String titulo = null; 

		ArrayList<String> listadoTitulos = new ArrayList<>();

		for (Libro posicion : Libro.listadoLibros) {
			listadoTitulos.add(posicion.getTitulo());
		}

		Libro.mostrarLibros();
		System.out.println();
		System.out.println("Elige el título del libro para crear la exposición: ");

		try {
			String titulo = entrada.readLine();

			if (!listadoTitulos.contains(titulo)) {
				System.out.println("El título no existe en la lista o lo has escrito malamente");
			} else {
				ExposicionLibro exposicion1 = new ExposicionLibro(true, titulo);
				// Si hay alguna exposición previa, desactivarla
				if (!listadoExposicionesLibro.isEmpty()) {
					listadoExposicionesLibro.get(listadoExposicionesLibro.size() - 1).setActiva(false);
				}
				listadoExposicionesLibro.add(exposicion1);
				System.out.println("Exposición sobre " + titulo + " creada correctamente");
			}

		} catch (IOException e) {
			System.out.println("Error al generar expo libros manualmente" + e);
		}

	}

	// metodo para listar todas las exposiciones

	public static void mostrarExposicionesLibro() {
		System.out.println("Listado de exposiciones de libros: ");
		for (Exposicion exposicion : listadoExposicionesLibro) {
			System.out.println(exposicion.toString());
		}
	}

	public static void mostrarExposicionLibroActiva() {
			System.out.println("Esta es la exposición sobre un libro que hay activa actualmente: ");
			System.out.println(listadoExposicionesLibro.get(listadoExposicionesLibro.size() - 1).toString());
		
	}

	@Override
	public String toString() {
		return "Exposicion sobre el libro: " + tituloLibro;
	}

	public String getTituloLibro() {
		return tituloLibro;
	}

	public void setTituloLibro(String tituloLibro) {
		this.tituloLibro = tituloLibro;
	}

}