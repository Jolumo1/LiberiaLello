package Dos;

import java.util.ArrayList;

//Clase abstracta Trabajadores
public abstract class Trabajador {
	// Atributos
	protected String nombre;
	protected String apellido;
	protected String telefono;
	protected String dni;
	protected static int idContador = 2; // Contador estático para id_trabajador, empieza desde el 2 porque el 1 estará
	protected String contrasenia;										// pillado por el jefe
	protected int id_trabajador;
	protected Turno turno;
	protected boolean contratado; // true = alta, false = baja

	// arraylist para guardar todos los trabajadores creados de forma automatica
	static ArrayList<Trabajador> listaTrabajadores = new ArrayList<>();

	// Constructor
	public Trabajador(String nombre, String apellido, String telefono, String dni, Turno turno, boolean contratado, String contrasenia) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.dni = dni;
		this.turno = turno;
		this.contratado = contratado;
		this.contrasenia = contrasenia;

		// Asigna id_trabajador y aumenta el contador estático
		this.id_trabajador = idContador;
		idContador++;

		// Añade automáticamente el trabajador creado a ListaTrabajadores
		listaTrabajadores.add(this);
	}

	// constructor para el jefe
	public Trabajador(String nombre, String apellido, String telefono, String dni, String contrasenia, boolean contratado) {
		this.id_trabajador = 1;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.dni = dni;
		this.contrasenia = contrasenia;
		this.contratado = true;
		listaTrabajadores.add(this);
	}

	public static void mostrarTrabajadoresActivosNoActivos() {
		for (Trabajador trabajador : listaTrabajadores) {
			System.out.println(trabajador.toString());
		}
	}

	public static void mostrarTrabajadoresActivos() {
		for (Trabajador trabajador : listaTrabajadores) {
			if (trabajador.isContratado()) {
				System.out.println(trabajador.toString());
				}
		}
	}

	/* BUSCAR DATOS TRABAJADOR POR ID */
	public static Trabajador buscarTrabajadorPorId(int id) {
		for (Trabajador trabajador : Trabajador.listaTrabajadores) {
			if (trabajador.getIdTrabajador() == id) {
				return trabajador;
			}
		}
		return null;
	}

  
	

	// Getters y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getIdTrabajador() {
		return id_trabajador;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public boolean isContratado() {
		return contratado;
	}

	public void setContratado(boolean contratado) {
		this.contratado = contratado;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

}