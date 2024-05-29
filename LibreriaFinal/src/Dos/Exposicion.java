package Dos;


public abstract class Exposicion {

	protected boolean activa;

	// Constructor
	public Exposicion(boolean activa) {
		super();
		this.activa = activa;

	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

}