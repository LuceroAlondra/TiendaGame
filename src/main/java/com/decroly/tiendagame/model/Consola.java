package com.decroly.tiendagame.model;

public class Consola extends Product{
	TipoConsola consola;
	private boolean isNuevo;	

	public Consola(int id, String nombre, String sn, String descripcion, double precio, int cantidad, String edicion,
			long fLanzamiento, TipoConsola consola, boolean isNuevo) {
		super(id, nombre, sn, descripcion, precio, cantidad, edicion, fLanzamiento);
		this.consola = consola;
		this.isNuevo = isNuevo;
	}

	public TipoConsola getConsola() {
		return consola;
	}
	public void setConsola(TipoConsola consola) {
		this.consola = consola;
	}
	public boolean isNuevo() {
		return isNuevo;
	}
	public void setNuevo(boolean isNuevo) {
		this.isNuevo = isNuevo;
	}
}
