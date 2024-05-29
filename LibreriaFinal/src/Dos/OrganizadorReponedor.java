package Dos;

import java.util.ArrayList;
import java.util.List;

public class OrganizadorReponedor extends Trabajador {
	static ArrayList<Trabajador> listaReponedores = new ArrayList<>();

	// Constructor
	public OrganizadorReponedor(String nombre, String apellido, String telefono, String dni, Turno turno,
			boolean contratado, String contrasenia) {

		super(nombre, apellido, telefono, dni, turno, contratado, contrasenia);

		listaReponedores.add(this);
	}

	@Override
	public String toString() {
		return "ID: " + id_trabajador + "REPONEDOR. Nombre: " + nombre + ", Apellidos: " + apellido
				+ ", Teléfono: " + telefono + ", DNI: " + dni + ", Contraseña: " + contrasenia + ", Turno: " + turno
				+ ", Contratado: " + contratado + ".";
	}

	// Metodo que repone el stock de todos los libros a una cierta cantidad.
	public void reponerTodoLibros(List<Libro> libros, int cantidad) {
		int num = cantidad;
		for (Libro libro : libros) {
			libro.setStock(libro.getStock() + num);

		}
	}

	// Metodo que repone el stock de un producto de merchandising especifico.
	public void reponerUnProductoMerchandising(Merchandising producto, int cantidad) {
		producto.setStock(producto.getStock() + cantidad);
		System.out.println("Repuesto el stock del producto: " + producto.getDescripcion() + " a " + producto.getStock()
				+ " unidades");
	}

	public void reponerTodoMerchandising(List<Merchandising> productos, int cantidad) {
		for (Merchandising producto : productos) {
			producto.setStock(producto.getStock() + cantidad);
		}
		System.out.println("Repuesto el stock de todos los productos de merchandising a " + cantidad + " unidades.");
	}

}
