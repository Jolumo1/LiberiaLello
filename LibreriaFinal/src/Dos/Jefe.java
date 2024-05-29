package Dos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jefe extends Trabajador {

	// cambiar constructor de jefe, meter el contratado pero nunca poder despedirlo
	// Constructor
	public Jefe(String nombre, String apellido, String telefono, String dni, String contrasenia, boolean contratado) {
		// Llamar al constructor de la clase base Trabajador sin inicializar turno ni
		// contratado
		super(nombre, apellido, telefono, dni, contrasenia, contratado);
	}

	
	
	
	
	@Override
	public String toString() {
		return "ID: " + id_trabajador + ". JEFE: " + nombre + ", Apellidos: " + apellido + ", Teléfono" + telefono + ", Dni" + dni
				+ ", Contraseña: " + contrasenia + ".";
	}





	/* MENU CONTRATAR */
	public void contratar() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean pedirDatos = true;

		while (pedirDatos) {

			try {
				System.out.println("____________________ALTA TRABAJADOR____________________");
				System.out.println("|                                                      |");
				System.out.println("| 1. Dar de alta a un trabajador ya registrado         |");
				System.out.println("| 2. Contratar a un nuevo trabajador                   |");
				System.out.println("| 3. Regresar.                                         |");
				System.out.println("|______________________________________________________|");
				System.out.print("\u001B[1m\u001B[34mSelecciona una opción:\u001B[0m ");
				int opcionAccion = Integer.parseInt(br.readLine());

				switch (opcionAccion) {
				case 1: // da la opcion de dar de alta a un trabajador ya registrado en nuestro sistema
					darAltaTrabajadorRegistrado();
					break;
				case 2: // dar un nuevo alta
					contratarNuevoTrabajador();
					break;
				case 3://regresar
					System.out.println("Regresando al menú principal");
					pedirDatos = false;
					break;
				default:
					System.out.println("Opción no válida.");
					
				}
			} catch (IOException | NumberFormatException e) {
				System.out.println("Error en la entrada de datos.");
				
			}
		}
	}

	/*METODO DAR DE ALTA TRABAJADOR YA REGISTRADO*/
	private void darAltaTrabajadorRegistrado() {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    try {
	        // Mostramos listado para seleccionar la ID
	    	System.out.println("_______________________________________________________");
	        System.out.println(" Lista de trabajadores no contratados:                ");
	        for (Trabajador trabajador : listaTrabajadores) {
	            if (!trabajador.isContratado()) {
	                System.out.println(trabajador.toString());
	            }
	        }

	        System.out.print("Introduce la id del trabajador a dar de alta: ");
	        int idTrabajadorAlta = Integer.parseInt(br.readLine());

	        // Buscar trabajador
	        boolean encontrado = false;
	        for (Trabajador trabajador : listaTrabajadores) {
	            if (trabajador.getIdTrabajador() == idTrabajadorAlta) {
	                if (trabajador.isContratado()) {
	                    System.out.println("El trabajador con id " + idTrabajadorAlta + " ya está dado de alta.");
	                    System.out.println();
	                } else {
	                    trabajador.setContratado(true);
	                    System.out.println("El trabajador con id " + idTrabajadorAlta + " ha sido dado de alta.");
	                    System.out.println();
	                }
	                encontrado = true;
	                break;
	            }
	        }

	        // Si no se encuentra esa id te lanza un mensaje
	        if (!encontrado) {
	            System.out.println("No se encontró un trabajador con esa id.");
	        }

	    } catch (IOException | NumberFormatException e) {
	        System.out.println("Error en la entrada de datos.");
	    }
	}
	
	/*CREAR NUEVO TRABAJADOR*/
	private void contratarNuevoTrabajador() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.println("Elige el tipo de empleado a contratar:");
			System.out.println("1. Vigilante");
			System.out.println("2. Controlador de Acceso");
			System.out.println("3. Dependiente");
			System.out.println("4. Organizador / Reponedor");
			int opcion = Integer.parseInt(br.readLine());

			// Solicita datos del empleado que sea
			System.out.println("Introduce los datos del empleado:");
			System.out.print("Nombre: ");
			String nombre = br.readLine();

			System.out.print("Apellido: ");
			String apellido = br.readLine();

			System.out.print("Teléfono: ");
			String telefono = br.readLine();

			System.out.print("DNI: ");
			String dni = br.readLine();

			Turno turno = null;
			while (turno == null) {
				System.out.print("Introduce el turno (MANANA/TARDE): ");
				String turnoEntrada = br.readLine().toUpperCase();
				try {
					turno = Turno.valueOf(turnoEntrada);
				} catch (IllegalArgumentException e) {
					System.out.println("Turno inválido. Por favor, introduce 'MAÑANA' o 'TARDE'.");
				}
			}

			System.out.print("Contraseña: ");
			String contrasenia = br.readLine();

			boolean contratado = true; // Por defecto el trabajador estará contratado

			// Crea el empleado según la opción seleccionada
			switch (opcion) {
			case 1:
				Vigilante vigilante = new Vigilante(nombre, apellido, telefono, dni, turno, contratado, contrasenia);
				//listaTrabajadores.add(vigilante);
				System.out.println("Vigilante contratado: " + vigilante);
				break;
			case 2:
				ControladorAcceso controladorAcceso = new ControladorAcceso(nombre, apellido, telefono, dni, turno,
						contratado, contrasenia);
				//listaTrabajadores.add(controladorAcceso);
				System.out.println("Controlador de Acceso contratado: " + controladorAcceso);
				break;
			case 3:
				Dependiente dependiente = new Dependiente(nombre, apellido, telefono, dni, turno, contratado,
						contrasenia);
				//listaTrabajadores.add(dependiente);
				System.out.println("Dependiente contratado: " + dependiente);
				break;
			case 4:
				OrganizadorReponedor organizadorReponedor = new OrganizadorReponedor(nombre, apellido, telefono, dni,
						turno, contratado, contrasenia);
				//listaTrabajadores.add(organizadorReponedor);
				System.out.println("Organizador/Reponedor contratado: " + organizadorReponedor);
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error en la entrada de datos.");
		}
	}

	/*METODO DESPEDIR*/
	public void despedir() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			// Mostrar la lista de todos los trabajadores
	    	System.out.println("_______________________________________________________");
			System.out.println("Lista de todos los trabajadores:");
			for (Trabajador trabajador : listaTrabajadores) {
				System.out.println(trabajador.toString());
			}

			// Pedir al usuario la id del trabajador a despedir
			System.out.print("Introduce la id del trabajador a despedir: ");
			int idTrabajadorDespedir = Integer.parseInt(br.readLine());

			// Buscar el trabajador por id y cambiar su estado a false si no es un jefe
			boolean encontrado = false;
			for (Trabajador trabajador : listaTrabajadores) {
				if (trabajador.getIdTrabajador() == idTrabajadorDespedir) {
					if (trabajador instanceof Jefe) {
						System.out.println("No se puede despedir a un jefe.");
					} else {
						trabajador.setContratado(false);
						System.out.println("El trabajador con id " + idTrabajadorDespedir + " ha sido despedido.");
					}
					encontrado = true;
					break;
				}
			}

			// Si no se encontró el trabajador, imprimir un mensaje
			if (!encontrado) {
				System.out.println("No se encontró un trabajador con esa id.");
			}

		} catch (IOException | NumberFormatException e) {
			System.out.println("Error en la entrada de datos.");
		}
	}

	/* METODO PARA NUNCA PODER DESPEDIR AL JEFE */
	@Override
	public void setContratado(boolean contratado) {
		if (!contratado) {
			System.out.println("El jefe no puede ser dado de baja.");
		} else {
			super.setContratado(contratado);
		}
	}


}
