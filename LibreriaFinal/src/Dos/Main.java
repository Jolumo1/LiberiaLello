package Dos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_BLUE = "\u001B[34m";
	private static int limite = 100;
	private static ArrayList<Billete> aforo = new ArrayList<>();
	private static int idMenu;

	public static void main(String[] args) {
		creacionObjetos();
		accesoPrograma();
	}

	/* ACCESO AL PROGRAMA */
	public static void accesoPrograma() {

		boolean pedirDatos = true;

		while (pedirDatos) {
			System.out.println();
			System.out.println("--------------------------------------------------------");
			System.out.println("|                   \u001B[1m\u001B[30mLIBRERIA   LELLO\u001B[0m                   |"); // Texto
			// en
			// negrita
			// y
			// negro
			System.out.println("--------------------------------------------------------");
			System.out.println("|                                                      |");

			try {
				System.out.println("|    \u001B[1mIntroduzca su usuario: \u001B[0m                           |");
				System.out.println("|                                                      |");

				System.out.println("--------------------------------------------------------");
				idMenu = Integer.parseInt(br.readLine());

				Trabajador usuario = Trabajador.buscarTrabajadorPorId(idMenu);

				if (usuario == null) {
					System.out.println("ID incorrecto.");
					continue; // Vuelve a solicitar los datos
				}

				if (usuario instanceof Jefe || usuario instanceof Dependiente) {

				} else {
					System.out.println("No tienes acceso al sistema.");
					continue; // Vuelve a solicitar los datos
				}

				// Solo entran los usuarios que son Dependiente y JEFE
				System.out.println("|    \u001B[1mIntroduzca su contraseña: \u001B[0m                        |");
				System.out.println("--------------------------------------------------------");
				String contraseña = br.readLine();

				if (usuario instanceof Jefe && ((Jefe) usuario).getContrasenia().equals(contraseña)) {
					menuPrincipalJefe((Jefe) usuario);
				} else if (usuario instanceof Dependiente
						&& ((Dependiente) usuario).getContrasenia().equals(contraseña)) {
					menuPrincipalTrabajador(usuario);
				} else {
					System.out.println("Contraseña incorrecta.");
				}

			} catch (IOException | NumberFormatException e) {
				System.out.println("Error en la entrada de datos.");
			}
		}
	}

	/* MENU DE JEFE */
	public static void menuPrincipalJefe(Jefe jefe) {
		LocalDateTime ahora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("         dd-MM-yyyy  ||  HH:mm:ss");
		String fechaHoraActual = ahora.format(formatter);
		boolean pedirDatosJefe = true;
		try {
			while (pedirDatosJefe) {
				System.out.println();
				System.out.println("--------------------------------------------------------");
				System.out.println("|                   LIBRERIA   LELLO                   |");
				System.out.println("--------------------------------------------------------");
				System.out.println("--------------------------------------------------------");
				System.out.println("| \u001B[1mBIENVENIDO/A \u001B[32m" + jefe.getNombre() + "\u001B[0m "
						+ fechaHoraActual + " \u001B[0m|");
				System.out.println("|                                                      |");
				System.out.println("|                                                      |");
				System.out.println("|                    \u001B[1mMENÚ PRINCIPAL\u001B[0m                    |");
				System.out.println("|                                                      |");
				System.out.println("--------------------------------------------------------");
				System.out.println("| \u001B[1m¿QUÉ DESEA HACER?\u001B[0m                                    |");
				System.out.println("|                                                      |");
				System.out.println("| 1. Dar de Alta un Trabajador                         |");
				System.out.println("| 2. Dar de Baja un trabajador                         |");
				System.out.println("| 3. Mostrar listado de trabajadores                   |");
				System.out.println("| 4. Buscar Trabajador por ID                          |");
				System.out.println("| 5. Ver total de ventas en el día                     |");
				System.out.println("| 6. Exposiciones                                      |");
				System.out.println("| 7. Salir                                             |");
				System.out.println("--------------------------------------------------------");
				System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");

				int opcion = Integer.parseInt(br.readLine());

				switch (opcion) {
				case 1: // dar de alta un trabajador
					System.out.println();
					System.out.println("--------------------------------------------------------");
					System.out.println("|                   LIBRERIA   LELLO                   |");
					System.out.println("--------------------------------------------------------");
					System.out.println("--------------------------------------------------------");
					System.out.println("| \u001B[1mBIENVENIDO/A \u001B[32m" + jefe.getNombre() + "\u001B[0m "
							+ fechaHoraActual + " \u001B[0m|");
					System.out.println("|                                                      |");

					jefe.contratar();
					break;
				case 2: // dar de baja
					jefe.despedir();
					break;
				case 3: // listar trabajadores, con dos opciones, solo los activos o todos
					boolean pedirDatos2 = true;
					while (pedirDatos2) {
						System.out.println("--------------------------------------------------------");
						System.out.println("1. Trabajadores en Activo");
						System.out.println("2. Todos los trabajadores");
						System.out.println("3. Regresar al menú principal");
						System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");
						int opcionTrabajadores = Integer.parseInt(br.readLine());
						switch (opcionTrabajadores) {
						case 1:
							System.out.println("--------------------------------------------------------");
							System.out
									.println("Listado de todos los trabajadores que están actualmente dados de alta:");
							Trabajador.mostrarTrabajadoresActivos();
							break;
						case 2:
							System.out.println("--------------------------------------------------------");
							System.out.println("Listado de todos los trabajadores (Activos/No activos");
							Trabajador.mostrarTrabajadoresActivosNoActivos();
							break;
						case 3:
							System.out.println("Regresando al menú principal");
							pedirDatos2 = false;
							break;
						default:
							System.out.println("Introduce una opción correcta");
						}
					}
					break;
				case 4:// buscar por ID

					System.out.print("Introduce el ID del trabajador: ");
					int id = Integer.parseInt(br.readLine());

					Trabajador usuario = Trabajador.buscarTrabajadorPorId(id);

					if (usuario != null) {
						System.out.println(usuario.toString());
					} else {
						System.out.println("No se encontró ningún trabajador con el ID proporcionado.");
					}
					break;
				case 5: // ver total de ventas, hacerlo por dependiente y en general
					mostrarVentasDelDia();
					break;
				case 6:// me estoy volviendo loca y no consigo que funcione
					menuExposiciones(Libro.listadoLibros);
					break;
				case 7: // Salir
					System.out.println("Saliendo del Programa.");
					pedirDatosJefe = false;
					break;
				default:
					System.out.println("Opción no válida. Por favor, introduzca una opción entre 1 y 7.");
				}
			}
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error en la entrada de datos.");
			menuPrincipalJefe(jefe);
		}
	}

	/* MENU DE TRABAJADOR */
	public static void menuPrincipalTrabajador(Trabajador trabajador) {
		boolean pedirDatos = true;
		LocalDateTime ahora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("      dd-MM-yyyy  ||  HH:mm:ss");
		String fechaHoraActual = ahora.format(formatter);

		try {
			while (pedirDatos) {

				System.out.println();
				System.out.println("--------------------------------------------------------");
				System.out.println("|                   LIBRERIA   LELLO                   |");
				System.out.println("--------------------------------------------------------");
				System.out.println("--------------------------------------------------------");
				System.out.println("| \u001B[1mBIENVENIDO/A \u001B[32m" + trabajador.getNombre() + "\u001B[0m "
						+ fechaHoraActual + " \u001B[0m ");
				System.out.println("|                                                      |");
				System.out.println("|                                                      |");
				System.out.println("|                    \u001B[1mMENÚ PRINCIPAL\u001B[0m                    |");
				System.out.println("|                                                      |");
				System.out.println("--------------------------------------------------------");
				System.out.println("| \u001B[1m¿QUÉ DESEA HACER?\u001B[0m                                    |");
				System.out.println("| 1. Simulacion de venta.                              |");
				System.out.println("| 2. Venta manual de libros.                           |");
				System.out.println("| 3. Registrar un nuevo libro                          |");
				System.out.println("| 4. Búsqueda de libro                                 |");
				System.out.println("| 5. Modificar datos de un libro                       |");
				System.out.println("| 6. Reponer Stock                                     |");
				System.out.println("| 7. Vender merchandising                              |");
				System.out.println("| 8. Salir                                             |");
				System.out.println("--------------------------------------------------------");
				System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");

				int opcion = Integer.parseInt(br.readLine());

				switch (opcion) {
				case 1:
					generarEntrada();
					realizarVentaAleatoria();
					Vigilante.vigilar();
					break;
				case 2:
					generarEntradaManual();
					ventaManual();
					break;
				case 3: // dar de alta un libro, registrar un libro
					Libro.darDeAltaLibro();
					break;
				case 4:
					// Opción 3: Buscar Libro
					int opcionBuscar = 0;
					boolean pedirDatosBuscar = true;
					while (pedirDatosBuscar) {

						System.out.println();
						System.out.println("___________________BÚSQUEDA DE LIBROS__________________");
						System.out.println("|                                                      |");
						System.out.println("|Realizar Búsqueda por:                                |");
						System.out.println("|                                                      |");
						System.out.println("| 1. Título                                            |");
						System.out.println("| 2. ISBN                                              |");
						System.out.println("| 3. Autor                                             |");
						System.out.println("| 4. Idioma                                            |");
						System.out.println("| 5. Género                                            |");
						System.out.println("| 6. Listar todos los libros                           |");
						System.out.println("| 7. Salir                                             |");
						System.out.println("|______________________________________________________|");
						System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");

						opcionBuscar = Integer.parseInt(br.readLine());

						switch (opcionBuscar) {
						case 1:
							// Buscar por título
							System.out.print("Introduce el título: ");
							String titulo = br.readLine();
							List<Libro> librosPorTitulo = Libro.buscarPorTitulo(titulo);
							for (Libro libro : librosPorTitulo) {
								System.out.println(libro);
							}
							break;
						case 2:
							// Buscar por ISBN
							System.out.print("Introduce el ISBN: ");
							String isbn = br.readLine();
							Libro libroPorISBN = Libro.buscarPorISBN(isbn);
							if (libroPorISBN != null) {
								System.out.println(libroPorISBN);
							} else {
								System.out.println("No se encontró ningún libro con el ISBN proporcionado.");
							}
							break;
						case 3:
							// Buscar por Autor
							System.out.print("Introduce el autor: ");
							String autor = br.readLine();
							List<Libro> librosPorAutor = Libro.buscarPorAutor(autor);
							for (Libro libro : librosPorAutor) {
								System.out.println(libro);

							}

							break;

						case 4: // Buscar por Idioma

							System.out.println();
							System.out.println("___________________BÚSQUEDA DE LIBROS__________________");
							System.out.println("|                                                      |");
							System.out.println("|Está Buscando por idioma:                             |");
							System.out.println("|                                                      |");
							System.out.println("| 1. Español                                           |");
							System.out.println("| 2. Portugués                                         |");
							System.out.println("| 3. Francés                                           |");
							System.out.println("| 4. Inglés                                            |");
							System.out.println("| 5. Regresar                                          |");
							System.out.println("|______________________________________________________|");
							System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");

							int opcionIdioma = Integer.parseInt(br.readLine());

							Idioma idiomaSeleccionado = null;

							switch (opcionIdioma) {
							case 1:
								idiomaSeleccionado = Idioma.ESPANOL;
								Libro.buscarLibrosPorIdioma(idiomaSeleccionado);
								break;
							case 2:
								idiomaSeleccionado = Idioma.PORTUGUES;
								Libro.buscarLibrosPorIdioma(idiomaSeleccionado);
								break;
							case 3:
								idiomaSeleccionado = Idioma.FRANCES;
								Libro.buscarLibrosPorIdioma(idiomaSeleccionado);
								break;
							case 4:
								idiomaSeleccionado = Idioma.INGLES;
								Libro.buscarLibrosPorIdioma(idiomaSeleccionado);
								break;
							case 5:
								System.out.println("Regresando.");
								break;
							default:
								System.out.println("Opción no válida.");
								break;
							}

							if (idiomaSeleccionado != null) {
								List<Libro> librosPorIdioma = new ArrayList<>();
								for (Libro libro : Libro.listadoLibros) {
									if (libro.getIdioma().equals(idiomaSeleccionado)) {
										librosPorIdioma.add(libro);
									}
								}
								if (!librosPorIdioma.isEmpty()) {
									System.out.println("Libros encontrados en el idioma " + idiomaSeleccionado + ":");
									for (Libro libro : librosPorIdioma) {
										System.out.println(libro);
									}
								} else {
									System.out.println("No se encontraron libros en el idioma " + idiomaSeleccionado);
								}
							}
							break;

						case 5:

							System.out.println();
							System.out.println("___________________BÚSQUEDA DE LIBROS__________________");
							System.out.println("|                                                      |");
							System.out.println("|Realizar Búsqueda por Género:                         |");
							System.out.println("|                                                      |");
							System.out.println("| 1. Novela                                            |");
							System.out.println("| 2. Misterio                                          |");
							System.out.println("| 3. Fantasía                                          |");
							System.out.println("| 4. Romance                                           |");
							System.out.println("| 5. Ciencia Ficción                                   |");
							System.out.println("| 6. Juvenil                                           |");
							System.out.println("| 7. Terror                                            |");
							System.out.println("| 8. Policíaco                                         |");
							System.out.println("| 9. Biografía                                         |");
							System.out.println("| 10. Historia                                         |");
							System.out.println("|______________________________________________________|");
							System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");

							int opcionGenero = Integer.parseInt(br.readLine());

							Genero generoSeleccionado = null;

							switch (opcionGenero) {
							case 1:
								generoSeleccionado = Genero.NOVELA;
								Libro.buscarPorGenero(generoSeleccionado);
								break;
							case 2:
								generoSeleccionado = Genero.MISTERIO;
								Libro.buscarPorGenero(generoSeleccionado);
								break;
							case 3:
								generoSeleccionado = Genero.FANTASIA;
								Libro.buscarPorGenero(generoSeleccionado);
								break;
							case 4:
								generoSeleccionado = Genero.ROMANCE;
								Libro.buscarPorGenero(generoSeleccionado);
								break;
							case 5:
								generoSeleccionado = Genero.CIENCIAFICCION;
								Libro.buscarPorGenero(generoSeleccionado);
								break;
							case 6:
								generoSeleccionado = Genero.JUVENIL;
								Libro.buscarPorGenero(generoSeleccionado);
								break;
							case 7:
								generoSeleccionado = Genero.TERROR;
								Libro.buscarPorGenero(generoSeleccionado);
								break;
							case 8:
								generoSeleccionado = Genero.POLICIACO;
								Libro.buscarPorGenero(generoSeleccionado);
								break;
							case 9:
								generoSeleccionado = Genero.BIOGRAFIA;
								Libro.buscarPorGenero(generoSeleccionado);
								break;
							case 10:
								generoSeleccionado = Genero.HISTORIA;
								Libro.buscarPorGenero(generoSeleccionado);
								break;
							default:
								System.out.println("Opción no válida.");
								break;
							}
							break;
						case 6:
							Libro.mostrarLibros();
							break;
						case 7:
							System.out.println("Saliendo.");
							pedirDatosBuscar = false;
							break;
						default:
							System.out.println("Por favor, introduce una opción correcta");
							break;
						}
					}
					break;
				case 5: // modificar datos de un libro
					Libro.modificarLibro();
					break;
				case 6: // reponer
					System.out.println();
					System.out.println("______________________REPONER STOCK____________________");
					System.out.println("|                                                      |");
					System.out.println("|Como desea reponer stock?:                            |");
					System.out.println("|                                                      |");
					System.out.println("| 1. Reponer un libro por ISBN.                        |");
					System.out.println("| 2. Reponer todos los libros.                         |");
					System.out.println("| 3. Reponer un productos de merchandising.            |");
					System.out.println("| 4. Reponer todos los productos de merchandising.     |");
					System.out.println("| 5. Salir.                                            |");
					System.out.println("|______________________________________________________|");
					System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");

					try {
						int opcionReponer = Integer.parseInt(br.readLine());

						if (OrganizadorReponedor.listaReponedores.isEmpty()) {
							System.out.println("No hay reponedores disponibles.");
							return;
						}

						// Seleccionar aleatoriamente un organizador reponedor
						Random random = new Random();
						OrganizadorReponedor reponedor = (OrganizadorReponedor) OrganizadorReponedor.listaReponedores
								.get(random.nextInt(OrganizadorReponedor.listaReponedores.size()));

						switch (opcionReponer) {
						case 1:
							// Buscar por ISBN
							System.out.print("Introduce el ISBN: ");
							String isbn = br.readLine();
							Libro libroPorISBN = Libro.buscarPorISBN(isbn);
							if (libroPorISBN != null) {
								System.out.println(libroPorISBN);
								System.out.println("Cuanta cantidad desea anadirle al stock?");
								int cantidad = Integer.parseInt(br.readLine());
								libroPorISBN.setStock(libroPorISBN.getStock() + cantidad);
								System.out.println("La operacion se realizo con exito, el stock actual es de: "
										+ libroPorISBN.getStock());
							} else {
								System.out.println("No se encontró ningún libro con el ISBN proporcionado.");
							}
							break;
						case 2:
							// Reponer todos los libros
							System.out.print("¿A cuánto quieres poner el stock de todos los libros?");
							int cantidadTodos = Integer.parseInt(br.readLine());
							reponedor.reponerTodoLibros(Libro.listadoLibros, cantidadTodos);
							System.out.println("Se repuso el stock de todos los libros con éxito.");
							break;
						case 3:
							// Reponer un producto de merchandising por código
							System.out.print("Introduce el código del producto: ");
							int codigoProducto = Integer.parseInt(br.readLine());
							Merchandising productoPorCodigo = null;
							for (Merchandising producto : Merchandising.listadoMerchandising) {
								if (producto.getCodigoProducto() == codigoProducto) {
									productoPorCodigo = producto;
									break;
								}
							}
							if (productoPorCodigo != null) {
								System.out.println(productoPorCodigo);
								System.out.println("¿Cuánta cantidad desea añadirle al stock?");
								int cantidad = Integer.parseInt(br.readLine());
								reponedor.reponerUnProductoMerchandising(productoPorCodigo, cantidad);
								System.out.println("La operación se realizó con éxito, el stock actual es de: "
										+ productoPorCodigo.getStock());
							} else {
								System.out.println("No se encontró ningún producto con el código proporcionado.");
							}
							break;
						case 4:
							// Reponer todo el merchandising
							System.out.print("¿A cuánto quieres poner el stock de todo el merchandising?");
							int cantidadMerchandising = Integer.parseInt(br.readLine());
							reponedor.reponerTodoMerchandising(Merchandising.listadoMerchandising,
									cantidadMerchandising);
							System.out.println("Se repuso el stock de todo el merchandising con éxito.");
							break;
						default:
							System.out.println("Opción no válida.");
							break;
						}
					} catch (IOException | NumberFormatException e) {
						System.out.println("Error en la entrada de datos.");
					}
					break;
				case 7:
					ventaManualMerchandising();
					break;
				case 8:
					System.out.println("Cerrando la sesión de " + trabajador.getNombre());
					pedirDatos = false;
					break;
				default:
					System.out.println("Opción no válida.");
					break;
				}
			}

		} catch (IOException |

				NumberFormatException e) {
			System.out.println("Error en la entrada de datos.");
			menuPrincipalTrabajador(trabajador);

		}
	}

	/* MENU EXPOSICIONES */
	public static void menuExposiciones(List<Libro> libros) {
		boolean pedirDatosExposiciones = true;
		try {
			while (pedirDatosExposiciones) {
				System.out.println("--------------------------------------------------------");
				System.out.println("|                    MENU EXPOSICIONES                 |");
				System.out.println("--------------------------------------------------------");
				System.out.println("| \u001B[1m¿QUÉ TIPO DE EXPOSICIÓN QUIERES CONSULTAR?\u001B[0m");
				System.out.println("| 1. Exposiciones por Autor                              |");
				System.out.println("| 2. Exposiciones por Libro                              |");
				System.out.println("| 3. Regresar al menú principal                          |");
				System.out.println("--------------------------------------------------------");
				System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");

				int opcionExposiciones = Integer.parseInt(br.readLine());

				switch (opcionExposiciones) {
				case 1: // Exposiciones por Autor
					menuExposicionesAutor();
					break;
				case 2: // Exposiciones por Libro
					menuExposicionesLibro(libros);
					break;
				case 3: // Regresar al menú principal
					pedirDatosExposiciones = false;
					break;
				default:
					System.out.println("Opción no válida. Introduce una opción del 1 al 3.");
					break;
				}
			}
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error en la entrada de datos.");
		}
	}

	public static void menuExposicionesAutor() {
		boolean pedirDatosAutor = true;
		try {
			while (pedirDatosAutor) {
				System.out.println("--------------------------------------------------------");
				System.out.println("|          EXPOSICIONES POR AUTOR                        |");
				System.out.println("--------------------------------------------------------");
				System.out.println("| 1. Consultar exposición actual                         |");
				System.out.println("| 2. Actualizar exposición                               |");
				System.out.println("| 3. Regresar al menú de exposiciones                    |");
				System.out.println("--------------------------------------------------------");
				System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");

				int opcionAutor = Integer.parseInt(br.readLine());

				switch (opcionAutor) {
				case 1:
					ExposicionAutor.mostrarExposicionAutorActiva();
					break;
				case 2:
					ExposicionAutor.mostrarExposicionesAutor();
					ExposicionAutor.generarExposicionAutorManual();
					break;
				case 3: // Regresar al menú de exposiciones
					pedirDatosAutor = false;
					break;
				default:
					System.out.println("Introduce una opción correcta.");
					break;
				}
			}
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error en la entrada de datos.");
		}
	}

	public static void menuExposicionesLibro(List<Libro> libros) {
		boolean pedirDatosLibro = true;
		try {
			while (pedirDatosLibro) {
				System.out.println("--------------------------------------------------------");
				System.out.println("|          EXPOSICIONES POR LIBRO                        |");
				System.out.println("--------------------------------------------------------");
				System.out.println("| 1. Consultar exposición actual                         |");
				System.out.println("| 2. Actualizar exposición                               |");
				System.out.println("| 3. Regresar al menú de exposiciones                    |");
				System.out.println("--------------------------------------------------------");
				System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");

				int opcionLibro = Integer.parseInt(br.readLine());

				switch (opcionLibro) {
				case 1: // Consultar
					ExposicionLibro.mostrarExposicionLibroActiva();
					break;
				case 2: // Crear una nueva exposición por libro
					ExposicionLibro.generarExposicionLibroManual();
					break;
				case 3: // Regresar al menú de exposiciones
					pedirDatosLibro = false;
					break;
				default:
					System.out.println("Introduce una opción correcta.");
					break;
				}
			}
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error en la entrada de datos.");
		}
	}

	public static void creacionObjetos() {

		// CREACION DE TRABAJADORES DE PRUEBA

		Jefe belen = new Jefe("Belen", "NomeAcuerdo", "666666666", "267465574L", "1", true);

		Dependiente dependiente1 = new Dependiente("Pepe", "Dependencias", "999999999", "344443322L", Turno.TARDE, true,
				"2");

		Dependiente dependiente2 = new Dependiente("Jose", "Dependencias", "999999999", "354443322L", Turno.MANANA,
				true, "2");

		Dependiente dependiente3 = new Dependiente("Dani", "Dependencias", "999999999", "364443322L", Turno.TARDE, true,
				"2");

		ControladorAcceso Controlador1 = new ControladorAcceso("Manolo", "Controles", "674674674", "26453667L",
				Turno.MANANA, true, "contraseñaControlador");

		OrganizadorReponedor organizador1 = new OrganizadorReponedor("Jose", "Lujan", "674674674", "26453667L",
				Turno.MANANA, false, "c");

		Vigilante Vigilante1 = new Vigilante("Quino", "Kin", "674674674", "26453667L", Turno.TARDE, true, "c");

		// Novela (Planta 1)
		new Libro("330", "La sombra del viento", "Carlos Ruiz Zafón", false, Idioma.ESPANOL, Genero.NOVELA, 11, 18.75,
				Ubicacion.PLANTA1);
		new Libro("331", "Anna Karenina", "León Tolstói", false, Idioma.ESPANOL, Genero.NOVELA, 10, 17.99,
				Ubicacion.PLANTA1);
		new Libro("332", "Crimen y castigo", "Fyodor Dostoevsky", true, Idioma.INGLES, Genero.NOVELA, 9, 16.50,
				Ubicacion.PLANTA1);
		new Libro("333", "1984", "George Orwell", true, Idioma.ESPANOL, Genero.NOVELA, 8, 15.75, Ubicacion.PLANTA1);
		new Libro("334", "Les Misérables", "Victor Hugo", false, Idioma.FRANCES, Genero.NOVELA, 7, 14.99,
				Ubicacion.PLANTA1);
		new Libro("335", "Cien años de soledad", "Gabriel García Márquez", true, Idioma.ESPANOL, Genero.NOVELA, 12,
				20.25, Ubicacion.PLANTA1);
		new Libro("336", "El guardián entre el centeno", "J.D. Salinger", true, Idioma.INGLES, Genero.NOVELA, 11, 19.50,
				Ubicacion.PLANTA1);
		new Libro("337", "The Grapes of Wrath", "John Steinbeck", false, Idioma.INGLES, Genero.NOVELA, 10, 18.99,
				Ubicacion.PLANTA1);
		new Libro("338", "To Kill a Mockingbird", "Harper Lee", true, Idioma.INGLES, Genero.NOVELA, 9, 17.25,
				Ubicacion.PLANTA1);
		new Libro("339", "The Great Gatsby", "F. Scott Fitzgerald", true, Idioma.INGLES, Genero.NOVELA, 8, 16.50,
				Ubicacion.PLANTA1);
		new Libro("340", "Memórias póstumas de Brás Cubas", "Machado de Assis", false, Idioma.PORTUGUES, Genero.NOVELA,
				7, 15.99, Ubicacion.PLANTA1);
		new Libro("341", "Dom Casmurro", "Machado de Assis", true, Idioma.PORTUGUES, Genero.NOVELA, 6, 14.75,
				Ubicacion.PLANTA1);
		new Libro("342", "A Moreninha", "Joaquim Manuel de Macedo", false, Idioma.PORTUGUES, Genero.NOVELA, 5, 13.50,
				Ubicacion.PLANTA1);
		new Libro("343", "A Relíquia", "Eça de Queirós", true, Idioma.PORTUGUES, Genero.NOVELA, 4, 12.25,
				Ubicacion.PLANTA1);
		new Libro("344", "O Primo Basílio", "José Maria de Eça de Queirós", false, Idioma.PORTUGUES, Genero.NOVELA, 3,
				11.00, Ubicacion.PLANTA1);
		new Libro("345", "A Ilustre Casa de Ramires", "José Maria de Eça de Queirós", true, Idioma.PORTUGUES,
				Genero.NOVELA, 2, 10.75, Ubicacion.PLANTA1);
		new Libro("346", "Os Maias", "José Maria de Eça de Queirós", false, Idioma.PORTUGUES, Genero.NOVELA, 1, 9.50,
				Ubicacion.PLANTA1);

		// Misterio (Planta 1)
		new Libro("300", "El código Da Vinci", "Dan Brown", true, Idioma.ESPANOL, Genero.MISTERIO, 10, 16.99,
				Ubicacion.PLANTA1);
		new Libro("301", "The Girl with the Dragon Tattoo", "Stieg Larsson", false, Idioma.INGLES, Genero.MISTERIO, 9,
				19.75, Ubicacion.PLANTA1);
		new Libro("302", "Le Mystère de la chambre jaune", "Gaston Leroux", true, Idioma.FRANCES, Genero.MISTERIO, 8,
				15.50, Ubicacion.PLANTA1);
		new Libro("303", "O Assassinato de Roger Ackroyd", "Agatha Christie", false, Idioma.PORTUGUES, Genero.MISTERIO,
				7, 14.75, Ubicacion.PLANTA1);
		new Libro("304", "Sherlock Holmes: A Study in Scarlet", "Arthur Conan Doyle", true, Idioma.INGLES,
				Genero.MISTERIO, 6, 13.99, Ubicacion.PLANTA1);
		new Libro("305", "El nombre de la rosa", "Umberto Eco", false, Idioma.ESPANOL, Genero.MISTERIO, 5, 18.50,
				Ubicacion.PLANTA1);
		new Libro("306", "Les dix petits nègres", "Agatha Christie", true, Idioma.FRANCES, Genero.MISTERIO, 10, 17.99,
				Ubicacion.PLANTA1);
		new Libro("307", "O Caso dos Dez Negrinhos", "Agatha Christie", false, Idioma.PORTUGUES, Genero.MISTERIO, 9,
				16.75, Ubicacion.PLANTA1);
		new Libro("308", "The Hound of the Baskervilles", "Arthur Conan Doyle", true, Idioma.INGLES, Genero.MISTERIO, 8,
				15.99, Ubicacion.PLANTA1);
		new Libro("309", "El silencio de los corderos", "Thomas Harris", false, Idioma.ESPANOL, Genero.MISTERIO, 7,
				14.50, Ubicacion.PLANTA1);

		// Fantasía (Planta 1)
		new Libro("320", "Harry Potter y la piedra filosofal", "J.K. Rowling", true, Idioma.INGLES, Genero.FANTASIA, 12,
				22.50, Ubicacion.PLANTA1);
		new Libro("321", "Harry Potter y la cámara secreta", "J.K. Rowling", true, Idioma.ESPANOL, Genero.FANTASIA, 11,
				21.99, Ubicacion.PLANTA1);
		new Libro("322", "The Fellowship of the Ring", "J.R.R. Tolkien", true, Idioma.INGLES, Genero.FANTASIA, 10,
				20.75, Ubicacion.PLANTA1);
		new Libro("323", "Le Seigneur des Anneaux: La Communauté de l'Anneau", "J.R.R. Tolkien", true, Idioma.FRANCES,
				Genero.FANTASIA, 9, 19.99, Ubicacion.PLANTA1);
		new Libro("324", "O Senhor dos Anéis: A Irmandade do Anel", "J.R.R. Tolkien", true, Idioma.PORTUGUES,
				Genero.FANTASIA, 8, 18.50, Ubicacion.PLANTA1);
		new Libro("325", "El Hobbit", "J.R.R. Tolkien", true, Idioma.ESPANOL, Genero.FANTASIA, 9, 18.99,
				Ubicacion.PLANTA1);
		new Libro("326", "Narnia: El león, la bruja y el armario", "C.S. Lewis", true, Idioma.ESPANOL, Genero.FANTASIA,
				10, 19.99, Ubicacion.PLANTA1);
		new Libro("327", "Le Lion, la Sorcière Blanche et l'Armoire Magique", "C.S. Lewis", true, Idioma.FRANCES,
				Genero.FANTASIA, 11, 20.50, Ubicacion.PLANTA1);
		new Libro("328", "As Crônicas de Nárnia: O Leão, a Feiticeira e o Guarda-Roupa", "C.S. Lewis", true,
				Idioma.PORTUGUES, Genero.FANTASIA, 12, 21.75, Ubicacion.PLANTA1);
		new Libro("329", "Alice's Adventures in Wonderland", "Lewis Carroll", true, Idioma.INGLES, Genero.FANTASIA, 10,
				19.99, Ubicacion.PLANTA1);

		// Romance (Planta 1)
		new Libro("310", "Orgullo y prejuicio", "Jane Austen", false, Idioma.ESPANOL, Genero.ROMANCE, 8, 12.75,
				Ubicacion.PLANTA1);
		new Libro("311", "Pride and Prejudice", "Jane Austen", true, Idioma.INGLES, Genero.ROMANCE, 9, 14.99,
				Ubicacion.PLANTA1);
		new Libro("312", "Jane Eyre", "Charlotte Brontë", false, Idioma.INGLES, Genero.ROMANCE, 7, 16.50,
				Ubicacion.PLANTA1);
		new Libro("313", "Romeo and Juliet", "William Shakespeare", true, Idioma.INGLES, Genero.ROMANCE, 6, 12.99,
				Ubicacion.PLANTA1);
		new Libro("314", "Cinquante nuances de Grey", "E.L. James", false, Idioma.FRANCES, Genero.ROMANCE, 8, 17.50,
				Ubicacion.PLANTA1);
		new Libro("315", "Fifty Shades of Grey", "E.L. James", true, Idioma.INGLES, Genero.ROMANCE, 9, 18.99,
				Ubicacion.PLANTA1);
		new Libro("316", "O Morro dos Ventos Uivantes", "Emily Brontë", false, Idioma.PORTUGUES, Genero.ROMANCE, 7,
				15.75, Ubicacion.PLANTA1);
		new Libro("317", "Wuthering Heights", "Emily Brontë", true, Idioma.INGLES, Genero.ROMANCE, 6, 13.99,
				Ubicacion.PLANTA1);
		new Libro("318", "Amor en los tiempos del cólera", "Gabriel García Márquez", false, Idioma.ESPANOL,
				Genero.ROMANCE, 8, 16.75, Ubicacion.PLANTA1);
		new Libro("319", "Love in the Time of Cholera", "Gabriel García Márquez", true, Idioma.INGLES, Genero.ROMANCE,
				9, 18.50, Ubicacion.PLANTA1);

		// Ciencia Ficción (Planta 1)
		new Libro("350", "El fin de la eternidad", "Isaac Asimov", true, Idioma.ESPANOL, Genero.CIENCIAFICCION, 11,
				22.99, Ubicacion.PLANTA1);
		new Libro("351", "Dune", "Frank Herbert", false, Idioma.INGLES, Genero.CIENCIAFICCION, 10, 21.50,
				Ubicacion.PLANTA1);
		new Libro("352", "Neuromante", "William Gibson", true, Idioma.INGLES, Genero.CIENCIAFICCION, 9, 20.75,
				Ubicacion.PLANTA1);
		new Libro("353", "Solaris", "Stanisław Lem", false, Idioma.ESPANOL, Genero.CIENCIAFICCION, 8, 19.99,
				Ubicacion.PLANTA1);
		new Libro("354", "El hombre en el castillo", "Philip K. Dick", true, Idioma.ESPANOL, Genero.CIENCIAFICCION, 12,
				24.25, Ubicacion.PLANTA1);
		new Libro("355", "Fundación", "Isaac Asimov", true, Idioma.INGLES, Genero.CIENCIAFICCION, 11, 23.50,
				Ubicacion.PLANTA1);
		new Libro("356", "La máquina del tiempo", "H.G. Wells", false, Idioma.INGLES, Genero.CIENCIAFICCION, 10, 22.99,
				Ubicacion.PLANTA1);
		new Libro("357", "Snow Crash", "Neal Stephenson", true, Idioma.INGLES, Genero.CIENCIAFICCION, 9, 21.75,
				Ubicacion.PLANTA1);
		new Libro("358", "La guerra de los mundos", "H.G. Wells", false, Idioma.ESPANOL, Genero.CIENCIAFICCION, 8,
				20.99, Ubicacion.PLANTA1);
		new Libro("359", "Ready Player One", "Ernest Cline", true, Idioma.INGLES, Genero.CIENCIAFICCION, 7, 19.25,
				Ubicacion.PLANTA1);
		new Libro("360", "Hyperion", "Dan Simmons", true, Idioma.ESPANOL, Genero.CIENCIAFICCION, 6, 18.50,
				Ubicacion.PLANTA1);
		new Libro("361", "El fin de la eternidad", "Isaac Asimov", true, Idioma.PORTUGUES, Genero.CIENCIAFICCION, 5,
				17.99, Ubicacion.PLANTA1);
		new Libro("362", "Duna", "Frank Herbert", false, Idioma.PORTUGUES, Genero.CIENCIAFICCION, 4, 16.50,
				Ubicacion.PLANTA1);
		new Libro("363", "Neuromante", "William Gibson", true, Idioma.PORTUGUES, Genero.CIENCIAFICCION, 3, 15.75,
				Ubicacion.PLANTA1);
		new Libro("364", "Solaris", "Stanisław Lem", false, Idioma.PORTUGUES, Genero.CIENCIAFICCION, 2, 14.99,
				Ubicacion.PLANTA1);
		new Libro("365", "El hombre en el castillo", "Philip K. Dick", true, Idioma.PORTUGUES, Genero.CIENCIAFICCION, 1,
				13.25, Ubicacion.PLANTA1);

		// Juvenil (Planta 2)
		new Libro("400", "Harry Potter y la piedra filosofal", "J.K. Rowling", true, Idioma.ESPANOL, Genero.JUVENIL, 11,
				19.99, Ubicacion.PLANTA2);
		new Libro("401", "Crepúsculo", "Stephenie Meyer", false, Idioma.INGLES, Genero.JUVENIL, 10, 18.50,
				Ubicacion.PLANTA2);
		new Libro("402", "Los juegos del hambre", "Suzanne Collins", true, Idioma.FRANCES, Genero.JUVENIL, 9, 17.75,
				Ubicacion.PLANTA2);
		new Libro("403", "Percy Jackson y el ladrón del rayo", "Rick Riordan", false, Idioma.PORTUGUES, Genero.JUVENIL,
				8, 16.99, Ubicacion.PLANTA2);
		new Libro("404", "Divergente", "Veronica Roth", true, Idioma.ESPANOL, Genero.JUVENIL, 7, 15.50,
				Ubicacion.PLANTA2);
		new Libro("405", "El señor de los anillos", "J.R.R. Tolkien", false, Idioma.INGLES, Genero.JUVENIL, 6, 14.99,
				Ubicacion.PLANTA2);
		new Libro("406", "Las crónicas de Narnia", "C.S. Lewis", true, Idioma.FRANCES, Genero.JUVENIL, 5, 13.75,
				Ubicacion.PLANTA2);
		new Libro("407", "El alquimista", "Paulo Coelho", false, Idioma.PORTUGUES, Genero.JUVENIL, 4, 12.99,
				Ubicacion.PLANTA2);
		new Libro("408", "El principito", "Antoine de Saint-Exupéry", true, Idioma.ESPANOL, Genero.JUVENIL, 3, 11.50,
				Ubicacion.PLANTA2);
		new Libro("409", "Matilda", "Roald Dahl", false, Idioma.INGLES, Genero.JUVENIL, 2, 10.99, Ubicacion.PLANTA2);
		new Libro("410", "La historia interminable", "Michael Ende", true, Idioma.FRANCES, Genero.JUVENIL, 1, 9.75,
				Ubicacion.PLANTA2);

		// Terror (Planta 2)
		new Libro("450", "It", "Stephen King", true, Idioma.ESPANOL, Genero.TERROR, 11, 20.99, Ubicacion.PLANTA2);
		new Libro("451", "El resplandor", "Stephen King", false, Idioma.INGLES, Genero.TERROR, 10, 19.50,
				Ubicacion.PLANTA2);
		new Libro("452", "Drácula", "Bram Stoker", true, Idioma.FRANCES, Genero.TERROR, 9, 18.75, Ubicacion.PLANTA2);
		new Libro("453", "Frankenstein", "Mary Shelley", false, Idioma.PORTUGUES, Genero.TERROR, 8, 17.99,
				Ubicacion.PLANTA2);
		new Libro("454", "El exorcista", "William Peter Blatty", true, Idioma.ESPANOL, Genero.TERROR, 7, 16.50,
				Ubicacion.PLANTA2);
		new Libro("455", "El cuento de la criada", "Margaret Atwood", false, Idioma.INGLES, Genero.TERROR, 6, 15.99,
				Ubicacion.PLANTA2);
		new Libro("456", "Entrevista con el vampiro", "Anne Rice", true, Idioma.FRANCES, Genero.TERROR, 5, 14.75,
				Ubicacion.PLANTA2);
		new Libro("457", "Carrie", "Stephen King", false, Idioma.PORTUGUES, Genero.TERROR, 4, 13.99, Ubicacion.PLANTA2);
		new Libro("458", "El silencio de los corderos", "Thomas Harris", true, Idioma.ESPANOL, Genero.TERROR, 3, 12.50,
				Ubicacion.PLANTA2);
		new Libro("459", "El retrato de Dorian Gray", "Oscar Wilde", false, Idioma.INGLES, Genero.TERROR, 2, 11.99,
				Ubicacion.PLANTA2);
		new Libro("460", "El perfume", "Patrick Süskind", true, Idioma.FRANCES, Genero.TERROR, 1, 10.75,
				Ubicacion.PLANTA2);

		// Policiaco (Planta 2)
		new Libro("500", "El misterio de Salem's Lot", "Stephen King", true, Idioma.ESPANOL, Genero.POLICIACO, 11,
				21.99, Ubicacion.PLANTA2);
		new Libro("501", "El nombre de la rosa", "Umberto Eco", false, Idioma.INGLES, Genero.POLICIACO, 10, 20.50,
				Ubicacion.PLANTA2);
		new Libro("502", "La chica del tren", "Paula Hawkins", true, Idioma.FRANCES, Genero.POLICIACO, 9, 19.75,
				Ubicacion.PLANTA2);
		new Libro("503", "El código Da Vinci", "Dan Brown", false, Idioma.PORTUGUES, Genero.POLICIACO, 8, 18.99,
				Ubicacion.PLANTA2);
		new Libro("504", "El detective moribundo", "Agatha Christie", true, Idioma.ESPANOL, Genero.POLICIACO, 7, 17.50,
				Ubicacion.PLANTA2);
		new Libro("505", "Los crímenes de la calle Morgue", "Edgar Allan Poe", false, Idioma.INGLES, Genero.POLICIACO,
				6, 16.99, Ubicacion.PLANTA2);
		new Libro("506", "Asesinato en el Orient Express", "Agatha Christie", true, Idioma.FRANCES, Genero.POLICIACO, 5,
				15.75, Ubicacion.PLANTA2);
		new Libro("507", "El leopardo", "Jo Nesbø", false, Idioma.PORTUGUES, Genero.POLICIACO, 4, 14.99,
				Ubicacion.PLANTA2);
		new Libro("508", "La chica que soñaba con una cerilla y un bidón de gasolina", "Stieg Larsson", true,
				Idioma.ESPANOL, Genero.POLICIACO, 3, 13.50, Ubicacion.PLANTA2);
		new Libro("509", "La ventana siniestra", "Raymond Chandler", false, Idioma.INGLES, Genero.POLICIACO, 2, 12.99,
				Ubicacion.PLANTA2);
		new Libro("510", "La muerte de Artemio Cruz", "Carlos Fuentes", true, Idioma.FRANCES, Genero.POLICIACO, 1,
				11.75, Ubicacion.PLANTA2);

		// Biografía (Planta 2)
		new Libro("550", "Steve Jobs", "Walter Isaacson", true, Idioma.ESPANOL, Genero.BIOGRAFIA, 11, 22.99,
				Ubicacion.PLANTA2);
		new Libro("551", "Albert Einstein: Su vida, su obra", "Walter Isaacson", false, Idioma.INGLES, Genero.BIOGRAFIA,
				10, 21.50, Ubicacion.PLANTA2);
		new Libro("552", "Madame Curie", "Eva Curie", true, Idioma.FRANCES, Genero.BIOGRAFIA, 9, 20.75,
				Ubicacion.PLANTA2);
		new Libro("553", "Gandhi", "Romain Rolland", false, Idioma.PORTUGUES, Genero.BIOGRAFIA, 8, 19.99,
				Ubicacion.PLANTA2);
		new Libro("554", "Leonardo da Vinci", "Walter Isaacson", true, Idioma.ESPANOL, Genero.BIOGRAFIA, 7, 18.50,
				Ubicacion.PLANTA2);
		new Libro("555", "Pablo Neruda: Confieso que he vivido", "Pablo Neruda", false, Idioma.INGLES, Genero.BIOGRAFIA,
				6, 17.99, Ubicacion.PLANTA2);
		new Libro("556", "Nelson Mandela: El largo camino hacia la libertad", "Nelson Mandela", true, Idioma.FRANCES,
				Genero.BIOGRAFIA, 5, 16.75, Ubicacion.PLANTA2);
		new Libro("557", "Agatha Christie: An Autobiography", "Agatha Christie", false, Idioma.PORTUGUES,
				Genero.BIOGRAFIA, 4, 15.99, Ubicacion.PLANTA2);
		new Libro("558", "Frida: A Biography of Frida Kahlo", "Hayden Herrera", true, Idioma.ESPANOL, Genero.BIOGRAFIA,
				3, 14.50, Ubicacion.PLANTA2);
		new Libro("559", "Mahatma Gandhi: His Life and Ideas", "C.Rajagopalachari", false, Idioma.INGLES,
				Genero.BIOGRAFIA, 2, 13.99, Ubicacion.PLANTA2);
		new Libro("560", "Che Guevara: Una vida revolucionaria", "Jon Lee Anderson", true, Idioma.FRANCES,
				Genero.BIOGRAFIA, 1, 12.75, Ubicacion.PLANTA2);

		// Historia (Planta 2)
		new Libro("600", "Breve historia del mundo", "Ernst H. Gombrich", true, Idioma.ESPANOL, Genero.HISTORIA, 11,
				24.99, Ubicacion.PLANTA2);
		new Libro("601", "Sapiens: De animales a dioses", "Yuval Noah Harari", false, Idioma.INGLES, Genero.HISTORIA,
				10, 23.50, Ubicacion.PLANTA2);
		new Libro("602", "Los hijos de los días", "Eduardo Galeano", true, Idioma.FRANCES, Genero.HISTORIA, 9, 22.75,
				Ubicacion.PLANTA2);
		new Libro("603", "História de Portugal", "José Hermano Saraiva", false, Idioma.PORTUGUES, Genero.HISTORIA, 8,
				21.99, Ubicacion.PLANTA2);
		new Libro("604", "El arte de la guerra", "Sun Tzu", true, Idioma.ESPANOL, Genero.HISTORIA, 7, 20.50,
				Ubicacion.PLANTA2);
		new Libro("605", "La guerra civil ESPANOLa", "Hugh Thomas", false, Idioma.INGLES, Genero.HISTORIA, 6, 19.99,
				Ubicacion.PLANTA2);
		new Libro("606", "Roma", "Steven Saylor", true, Idioma.FRANCES, Genero.HISTORIA, 5, 18.75, Ubicacion.PLANTA2);
		new Libro("607", "Brasil: Una biografía", "Lilia Moritz Schwarcz", false, Idioma.PORTUGUES, Genero.HISTORIA, 4,
				17.99, Ubicacion.PLANTA2);
		new Libro("608", "La guerra del Peloponeso", "Tucídides", true, Idioma.ESPANOL, Genero.HISTORIA, 3, 16.50,
				Ubicacion.PLANTA2);
		new Libro("609", "Los dioses del Olimpo", "Bernard Evslin", false, Idioma.INGLES, Genero.HISTORIA, 2, 15.99,
				Ubicacion.PLANTA2);
		new Libro("610", "Historia de las civilizaciones antiguas", "Jacques Benoist-Méchin", true, Idioma.FRANCES,
				Genero.HISTORIA, 1, 14.75, Ubicacion.PLANTA2);

		new Merchandising(1, Tipo.Gorra, "Gorra Azul", 10.0, 20);
		new Merchandising(2, Tipo.Gorra, "Gorra Roja", 12.0, 15);
		new Merchandising(3, Tipo.Camiseta, "Camiseta Blanca", 15.0, 25);
		new Merchandising(4, Tipo.Camiseta, "Camiseta Negra", 18.0, 10);
		new Merchandising(5, Tipo.Bolsa, "Bolsa de Tela", 8.0, 30);
		new Merchandising(6, Tipo.Taza, "Taza con Logo", 5.0, 50);
		new Merchandising(7, Tipo.Taza, "Taza Edición Especial", 7.0, 20);

		// CREACIÓN DE EXPOSICIONES

		ExposicionLibro.generarExposicionLibroAleatorio();
		ExposicionLibro.generarExposicionLibroAleatorio();

		// System.out.println(ExposicionLibro.listadoExposicionesLibro.size());

		ExposicionAutor.generarExposicionAutorAleatorio();
		ExposicionAutor.generarExposicionAutorAleatorio();

	}

	public static void generarEntrada() {
		int idCounter = 1; // Contador para asignar IDs
		Random random = new Random();

		for (int i = 0; i < limite; i++) {
			if (random.nextDouble() < 0.8) { // 80% de probabilidad para billete normal
				if (random.nextInt(10) != 0) {
					aforo.add(new BilleteNormal(idCounter++));
				} else {
					System.out.println(ANSI_RED + "El cliente " + idCounter + " se marchó sin comprar." + ANSI_RESET);
					idCounter++;
				}
			} else { // 20% de probabilidad para billete premium
				aforo.add(new BilletePremium(idCounter++));
			}
		}
	}

	public static void generarEntradaManual() {
		int idCounter = 101; // Contador para asignar IDs
		Random random = new Random();

		if (random.nextDouble() < 0.8) { // 80% de probabilidad para billete normal
			aforo.add(new BilleteNormal(idCounter++));
		} else { // 20% de probabilidad para billete premium
			aforo.add(new BilletePremium(idCounter++));
		}
	}

	public static void ventaManual() {
		Random random = new Random();
		ArrayList<Dependiente> dependientes = Dependiente.listadoDependientes;
		List<Libro> librosDisponibles = Libro.listadoLibros;

		System.out.println("Libros disponibles:");
		for (Libro libro : librosDisponibles) {
			System.out.println(libro);
		}

		try {
			System.out.print("Introduce el ISBN del libro que quieres comprar: ");
			String isbn = br.readLine();

			Libro libroSeleccionado = null;
			for (Libro libro : librosDisponibles) {
				if (libro.getIsbn().equals(isbn)) {
					libroSeleccionado = libro;
					break;
				}
			}

			if (libroSeleccionado == null) {
				System.out.println("ISBN no encontrado.");
				return;
			}

			System.out.print("Introduce la cantidad: ");
			int cantidad = Integer.parseInt(br.readLine());

			if (cantidad > libroSeleccionado.getStock()) {
				System.out.println("No hay suficiente stock disponible.");
				return;
			}

			Dependiente dependiente = dependientes.get(idMenu-2);

			Billete billete;
			if (random.nextInt(100) < 80) {
				billete = new BilleteNormal(1000 + random.nextInt(9000)); // ID aleatorio
				libroSeleccionado.setPrecio(libroSeleccionado.getPrecio() - 8); // Descuento de 8 euros
			} else {
				billete = new BilletePremium(1000 + random.nextInt(9000)); // ID aleatorio
				libroSeleccionado.setPrecio(libroSeleccionado.getPrecio() - 15); // Descuento de 15 euros
			}

			dependiente.venderLibro(billete, libroSeleccionado, cantidad);

			System.out.println("Venta realizada exitosamente.");
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error en la entrada de datos.");
		}
	}

	public static void ventaManualMerchandising() {
		Random random = new Random();
		ArrayList<Dependiente> dependientes = Dependiente.listadoDependientes;
		// List<Merchandising> merchandisingDisponible =
		// Merchandising.listadoMerchandising;

		System.out.println("Productos de merchandising disponibles:");
		for (Merchandising merchandising : Merchandising.listadoMerchandising) {
			System.out.println(merchandising);
		}

		try {
			System.out.print("Introduce el código del producto que quieres comprar: ");
			int codigo = Integer.parseInt(br.readLine());

			Merchandising merchandisingSeleccionado = null;

			for (Merchandising merchandising : Merchandising.listadoMerchandising) {
				if (merchandising.getCodigoProducto() == codigo) {
					merchandisingSeleccionado = merchandising;
					break;
				}
			}

			if (merchandisingSeleccionado == null) {
				System.out.println("Producto no encontrado.");
				return;
			}

			System.out.print("Introduce la cantidad: ");
			int cantidad = Integer.parseInt(br.readLine());

			if (cantidad > merchandisingSeleccionado.getStock()) {
				System.out.println("No hay suficiente stock disponible.");
				return;
			}

			Dependiente dependiente = dependientes.get(idMenu-2);

			Billete billete;
			if (random.nextInt(100) < 80) {
				billete = new BilleteNormal(1000 + random.nextInt(9000)); // ID aleatorio
			} else {
				billete = new BilletePremium(1000 + random.nextInt(9000)); // ID aleatorio
			}

			dependiente.venderMerchandising(billete, merchandisingSeleccionado, cantidad);

			System.out.println("Venta realizada exitosamente.");
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error en la entrada de datos.");
		}
	}

	private static void realizarVentaAleatoria() {
		if (!aforo.isEmpty() && !Libro.listadoLibros.isEmpty() && !Dependiente.listadoDependientes.isEmpty()) {
			Random random = new Random();
			ArrayList<Billete> billetesNormales = new ArrayList<>();
			ArrayList<Billete> billetesPremium = new ArrayList<>();

			// Recorrer todos los billetes del aforo
			for (Billete billete : aforo) {
				Dependiente dependiente = Dependiente.listadoDependientes
						.get(random.nextInt(Dependiente.listadoDependientes.size()));

				ArrayList<Integer> indicesDisponibles = new ArrayList<>();
				for (int i = 0; i < Libro.listadoLibros.size(); i++) {
					indicesDisponibles.add(i);
				}

				ArrayList<Libro> librosComprados = new ArrayList<>();
				int cantidad = random.nextInt(3) + 1; // Entre 1 y 3 libros

				for (int i = 0; i < cantidad; i++) {
					if (!indicesDisponibles.isEmpty()) {
						int index = random.nextInt(indicesDisponibles.size());
						Libro libro = Libro.listadoLibros.get(indicesDisponibles.get(index));
						librosComprados.add(libro);
						indicesDisponibles.remove(index);
					} else {
						break;
					}
				}

				// Realizar la venta de los libros seleccionados
				for (Libro libro : librosComprados) {
					dependiente.venderLibro(billete, libro, 1);
				}

				// Añadir el billete a la lista correspondiente
				if (billete instanceof BilletePremium) {
					billetesPremium.add(billete);
				} else {
					billetesNormales.add(billete);
				}
			}

			// Mostrar la información de las ventas agrupadas y ordenadas
			System.out.println("\nVentas por billete premium:");
			for (Billete billete : billetesPremium) {
				for (Dependiente dependiente : Dependiente.listadoDependientes) {
					if (dependiente.getVentasPorBillete().containsKey(billete)) {
						System.out.println(ANSI_BLUE + "Billete ID: " + billete.getId()
								+ " ha comprado los siguientes libros (Dependiente: " + dependiente.getNombre() + " "
								+ dependiente.getApellido() + "):" + ANSI_RESET);
						for (Libro libro : dependiente.getVentasPorBillete().get(billete)) {
							System.out.println(ANSI_BLUE + " - " + libro.getTitulo() + ANSI_RESET);
						}
						System.out.println(); // Agregar una línea en blanco para separación
					}
				}
			}

			System.out.println("\nVentas por billete normal:");
			for (Billete billete : billetesNormales) {
				for (Dependiente dependiente : Dependiente.listadoDependientes) {
					if (dependiente.getVentasPorBillete().containsKey(billete)) {
						System.out.println(
								"Billete ID: " + billete.getId() + " ha comprado los siguientes libros (Dependiente: "
										+ dependiente.getNombre() + " " + dependiente.getApellido() + "):");
						for (Libro libro : dependiente.getVentasPorBillete().get(billete)) {
							System.out.println(" - " + libro.getTitulo());
						}
						System.out.println(); // Agregar una línea en blanco para separación
					}
				}
			}
		} else {
			System.out.println(
					"No hay más billetes disponibles para realizar la venta o la lista de libros/dependientes está vacía.");
		}
	}

	public static void mostrarVentasDelDia() {
		System.out.println("Resumen de ventas del día:");
		for (Dependiente dependiente : Dependiente.listadoDependientes) {
			dependiente.mostrarResumenVentas();
			System.out.println();
		}
	}

}
