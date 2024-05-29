package Dos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Libro {

	private static int lastId = 0;

	private String isbn;
	private String titulo;
	private String autor;
	private boolean edicionPropia; // true = sí, false = no
	private Idioma idioma;
	private Genero genero;
	private int stock;
	private double precio;
	private Ubicacion ubicacion;

	// arraylist para guardar todos los libros creados de forma automatica
	static ArrayList<Libro> listadoLibros = new ArrayList<>();

	// Constructor
	public Libro(String isbn, String titulo, String autor, boolean edicionPropia, Idioma idioma, Genero genero,
                 int stock, double precio, Ubicacion ubicacion) {

		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.edicionPropia = edicionPropia;
		this.idioma = idioma;
		this.genero = genero;
		this.stock = stock;
		this.precio = precio;
		this.ubicacion = ubicacion;

		// Añade automáticamente el libro creado a ListadoLibros
		listadoLibros.add(this);

	}

	public void mostrarStock() {
		System.out.println("El stock actual del libro \"" + titulo + "\" es: " + stock);
	}

	public void reducirStock(int cantidad) {
		this.stock -= cantidad;
	}

	// metodo para mostrar todos los libros

	public static void mostrarLibros() {
		System.out.println("Listado de libros: ");
		for (Libro libro : listadoLibros) {
			System.out.println(libro.toString());
		}
	}

	// Método para verificar si hay suficiente stock para una venta
	public boolean tieneStock(int cantidad) {
		return stock >= cantidad;
	}

	// Método para realizar una venta y actualizar el stock del libro
	public void vender(int cantidad) {
		if (tieneStock(cantidad)) {
			stock -= cantidad;
			System.out.println("Se han vendido " + cantidad + " unidades del libro \"" + titulo + "\".");
		} else {
			System.out.println("No hay suficiente stock del libro \"" + titulo + "\" para realizar la venta.");
		}
	}

	/* LISTADO DE AUTORES QUE ME SERVIRÁ PARA LAS EXPOSICIONES */

	// Método estático para obtener autores disponibles
	public static List<String> obtenerAutoresDisponibles() {
		List<String> autores = new ArrayList<>();
		for (Libro libro : listadoLibros) {
			autores.add(libro.getAutor());
		}
		if (autores.isEmpty()) {
			System.out.println("No se encontraron autores disponibles.");
		}
		return autores;
	}

	/* METODO DAR DE ALTA LIBRO MANUAL */

	public static void darDeAltaLibro() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Por favor, introduce los datos del nuevo libro.");

			/* Pedida de ISBN */
			System.out.print("ISBN: ");
			String isbn = br.readLine();

			// Verificar si el ISBN ya existe
			if (buscarPorISBN(isbn) != null) {
				System.out.println("Ya existe un libro con el ISBN proporcionado.");
				return;
			}

			System.out.println("Puede utilizar esa ISBN.");

			/* Pedida de Titulo */
			System.out.print("Título: ");
			String titulo = br.readLine();

			/* Pedida de Autor */
			System.out.print("Autor: ");
			String autor = br.readLine();

			/* Pedida de Edición propia */
			boolean edicionPropiaValida = false;
			String edicionPropiaInput = "";
			while (!edicionPropiaValida) {
				System.out.println("¿Edición propia? (Si / No): ");
				System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");
				edicionPropiaInput = br.readLine().toLowerCase();
				if (edicionPropiaInput.equals("si") || edicionPropiaInput.equals("no")) {
					edicionPropiaValida = true;
				} else {
					System.out.println("Por favor, selecciona 'si' o 'no'.");
				}
			}

			boolean edicionPropia = edicionPropiaInput.equals("si");

			/* Pedida de Idioma */

			System.out.println("Idioma (ESPAÑOL, INGLES, FRANCES, PORTUGUES): ");
			System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");
			Idioma idioma = obtenerIdioma(br);

			// Introducción del género

			System.out.println(
					"Género (NOVELA, MISTERIO, FANTASIA, ROMANCE, CIENCIAFICCION, JUVENIL, TERROR, POLICIACO, BIOGRAFIA, HISTORIA): ");
			System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");
			Genero genero = obtenerGenero(br);

			int stock;
			double precio;
			while (true) {
				try {
					System.out.print("Stock: ");
					stock = Integer.parseInt(br.readLine());
					break;
				} catch (NumberFormatException e) {
					System.out.println("Por favor, introduce un número válido para el stock.");
				}
			}

			while (true) {
				try {
					System.out.print("Precio: ");
					precio = Double.parseDouble(br.readLine());
					break;
				} catch (NumberFormatException e) {
					System.out.println("Por favor, introduce un número válido para el precio.");
				}
			}

			Ubicacion ubicacion = null;
			boolean ubicacionValida = false;
			while (!ubicacionValida) {
				try {
					System.out.print("Ubicación (PLANTA1, PLANTA2): ");
					ubicacion = Ubicacion.valueOf(br.readLine().toUpperCase());
					ubicacionValida = true;
				} catch (IllegalArgumentException e) {
					System.out.println("Por favor, introduce una ubicación válida.");
				}
			}

			// Crear y agregar el nuevo libro
			new Libro(isbn, titulo, autor, edicionPropia, idioma, genero, stock, precio, ubicacion);

			System.out.println("El libro se ha dado de alta correctamente.");

		} catch (IOException e) {
			System.out.println("Error al ingresar los datos. Inténtalo de nuevo.");
		}
	}

	/* MODIFICAR DATOS LIBRO */

	public static void modificarLibro() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Por favor, introduce el ISBN del libro que quieres modificar:");
			String isbn = br.readLine();

			// Buscar el libro por ISBN
			Libro libro = buscarPorISBN(isbn);
			if (libro == null) {
				System.out.println("No se encontró ningún libro con el ISBN proporcionado.");
				return;
			} else {
				System.out.println("Datos actuales del libro a modificar: ");
				System.out.println(libro.toString());
			}

			boolean pedirDatos = true;

			while (pedirDatos) {
				System.out.println();
				System.out.println("_________________MODIFICAR DATOS LIBRO_________________");
				System.out.println("|                                                      |");
				System.out.println("|¿Qué dato quieres modificar?                          |");
				System.out.println("|                                                      |");
				System.out.println("| 1. Título                                            |");
				System.out.println("| 2. Autor                                             |");
				System.out.println("| 3. Tipo de edición                                   |");
				System.out.println("| 4. Idioma                                            |");
				System.out.println("| 5. Género                                            |");
				System.out.println("| 6. Stock                                             |");
				System.out.println("| 7. Precio                                            |");
				System.out.println("| 8. Ubicación                                         |");
				System.out.println("| 9. Regresar                                          |");
				System.out.println("|______________________________________________________|");
				System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");

				int opcion = Integer.parseInt(br.readLine());

				switch (opcion) {
				case 1:
					System.out.print("Nuevo título: ");
					String nuevoTitulo = br.readLine();
					libro.setTitulo(nuevoTitulo);
					break;
				case 2:
					System.out.print("Nuevo autor: ");
					String nuevoAutor = br.readLine();
					libro.setAutor(nuevoAutor);
					break;
				case 3:
					while (true) {
						System.out.print("¿Edición propia? (Si / No): ");
						String edicionPropiaInput = br.readLine().toLowerCase();
						if (edicionPropiaInput.equals("si")) {
							libro.setEdicionPropia(true);
							break;
						} else if (edicionPropiaInput.equals("no")) {
							libro.setEdicionPropia(false);
							break;
						} else {
							System.out.println("Entrada no válida. Por favor, introduce 'Si' o 'No'.");
						}
					}
					break;
				case 4:
					System.out.print("Nuevo idioma (ESPAÑOL, INGLES, FRANCES, PORTUGUES): ");
					Idioma nuevoIdioma = obtenerIdioma(br);
					libro.setIdioma(nuevoIdioma);
					break;
				case 5:
					System.out.print(
							"Nuevo género (NOVELA, MISTERIO, FANTASIA, ROMANCE, CIENCIAFICCION, JUVENIL, TERROR, POLICIACO, BIOGRAFIA, HISTORIA): ");
					Genero nuevoGenero = obtenerGenero(br);
					libro.setGenero(nuevoGenero);
					break;
				case 6:
					System.out.print("Nuevo stock: ");
					int nuevoStock = Integer.parseInt(br.readLine());
					libro.setStock(nuevoStock);
					break;
				case 7:
					boolean precioValido = false;
					double nuevoPrecio = 0.0;
					while (!precioValido) {
						System.out.print("Nuevo precio: ");
						try {
							nuevoPrecio = Double.parseDouble(br.readLine());
							precioValido = true;
						} catch (NumberFormatException e) {
							System.out.println("Por favor, introduce un número válido para el precio.");
						} catch (IOException e) {
							System.out.println("Error de entrada. Inténtalo de nuevo.");
						}
					}
					libro.setPrecio(nuevoPrecio);
					break;

				case 8:
					boolean ubicacionValida = false;
					Ubicacion nuevaUbicacion = null;
					while (!ubicacionValida) {
						System.out.print("Nueva ubicación (PLANTA1, PLANTA2): ");
						String ubicacionInput = br.readLine().toUpperCase();
						if (ubicacionInput.equals("PLANTA1") || ubicacionInput.equals("PLANTA2")) {
							ubicacionValida = true;
							nuevaUbicacion = Ubicacion.valueOf(ubicacionInput);
						} else {
							System.out.println("Error: Introduce una ubicación válida (PLANTA1 o PLANTA2).");
						}
					}
					libro.setUbicacion(nuevaUbicacion);
					break;

				case 9:
					System.out.println();
					pedirDatos = false;
					break;
				default:
					System.out.println("Opción no válida.");
				}

				if (opcion >= 1 && opcion <= 8) {
					System.out.println("El libro se ha modificado correctamente.");
					System.out.println("Datos actualizados del libro: ");
					System.out.println(libro.toString());
				}
			}

		} catch (IOException e) {
			System.out.println("Error al ingresar los datos. Inténtalo de nuevo.");
			modificarLibro();
		}
	}

	/* METODO PARA PONER EL IDIOMA TANTO AL CREAR EL LIBRO COMO PARA MODIFICARLO */
	private static Idioma obtenerIdioma(BufferedReader br) {
		while (true) {
			try {
				String idiomaInput = br.readLine().toUpperCase();
				switch (idiomaInput) {
				case "ESPAÑOL":
					return Idioma.ESPANOL;
				case "INGLES":
					return Idioma.INGLES;
				case "FRANCES":
					return Idioma.FRANCES;
				case "PORTUGUES":
					return Idioma.PORTUGUES;
				default:
					System.out.println("Introduce una opción válida");
					System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");
				}
			} catch (IOException e) {
				System.out.println("Error al ingresar los datos. Inténtalo de nuevo.");
			}
		}
	}

	/* METODO PARA ASIGNAR GENERO TANTO AL CREAR EL LIBRO COMO PARA MODIFICARLO */
	private static Genero obtenerGenero(BufferedReader br) {
		while (true) {
			try {
				String generoInput = br.readLine().toUpperCase();
				switch (generoInput) {
				case "NOVELA":
					return Genero.NOVELA;
				case "MISTERIO":
					return Genero.MISTERIO;
				case "FANTASIA":
					return Genero.FANTASIA;
				case "ROMANCE":
					return Genero.ROMANCE;
				case "CIENCIAFICCION":
					return Genero.CIENCIAFICCION;
				case "JUVENIL":
					return Genero.JUVENIL;
				case "TERROR":
					return Genero.TERROR;
				case "POLICIACO":
					return Genero.POLICIACO;
				case "BIOGRAFIA":
					return Genero.BIOGRAFIA;
				case "HISTORIA":
					return Genero.HISTORIA;
				default:
					System.out.println("Introduce una opción válida");
					System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");
				}
			} catch (IOException e) {
				System.out.println("Error al ingresar los datos. Inténtalo de nuevo.");
			}
		}
	}

	/* METODOS DE BÚSQUEDA DE LIBROS */
	// Método estático para buscar un libro por ISBN
	public static Libro buscarPorISBN(String isbn) {
		for (Libro libro : listadoLibros) {
			if (libro.getIsbn().equals(isbn)) {
				return libro;
			}
		}
		System.out.println("No se encontró ningún libro con el ISBN \"" + isbn + "\".");
		return null; // Si no se encuentra ningún libro con ese ISBN
	}

	public static List<Libro> buscarPorTitulo(String titulo) {
		List<Libro> librosEncontrados = new ArrayList<>();
		String[] palabrasClave = titulo.split("\\s+");

		for (Libro libro : listadoLibros) {
			String tituloLibro = libro.getTitulo().toLowerCase();
			boolean todasCoinciden = true;
			for (String palabra : palabrasClave) {
				if (!tituloLibro.contains(palabra.toLowerCase())) {
					todasCoinciden = false;
					break;
				}
			}
			if (todasCoinciden) {
				librosEncontrados.add(libro);
			}
		}

		if (librosEncontrados.isEmpty()) {
			System.out.println("No se encontraron libros que coincidan con la búsqueda.");
		}

		return librosEncontrados;
	}
	
	
	
	public static List<Libro> buscarPorAutor(String autor) {
		List<Libro> librosEncontrados = new ArrayList<>();
		String[] palabrasClave = autor.split("\\s+");

		for (Libro libro : listadoLibros) {
			String autorLibro = libro.getAutor().toLowerCase();
			boolean todasCoinciden = true;
			for (String palabra : palabrasClave) {
				if (!autorLibro.contains(palabra.toLowerCase())) {
					todasCoinciden = false;
					break;
				}
			}
			if (todasCoinciden) {
				librosEncontrados.add(libro);
			}
		}

		if (librosEncontrados.isEmpty()) {
			System.out.println("No se encontraron libros que coincidan con la búsqueda del autor.");
		}

		return librosEncontrados;
	}
	
	

	public static void buscarLibrosPorIdioma(Idioma idioma) {
		for (Libro libro : listadoLibros) {
			if (libro.getIdioma().equals(idioma)) {
				System.out.println(libro.toString());
			}
		}

	}

	public static void buscarPorGenero(Genero genero) {

		for (Libro libro : listadoLibros) {
			if (libro.getGenero().equals(genero)) {
				System.out.println(libro.toString());
			}
		}

	}

	@Override
	public String toString() {
		return "Isbn: " + isbn + ", Título: " + titulo + ", Autor: " + autor + ", Edición Propia: "
				+ (edicionPropia ? "Sí" : "No") + ", Idioma: " + idioma + ", Género: " + genero + ", Stock: " + stock
				+ ", Precio: " + precio + ", Ubicación: " + ubicacion;
	}

	// Getters y setters
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public boolean isEdicionPropia() {
		return edicionPropia;
	}

	public void setEdicionPropia(boolean edicionPropia) {
		this.edicionPropia = edicionPropia;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public boolean hasStock(int cantidad) {
		return stock >= cantidad;
	}

}
