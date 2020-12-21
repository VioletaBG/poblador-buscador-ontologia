package com.buscador.buscadorontology.model;

public class Resultado {

	private String nombre;
	private String datos;
	
	public Resultado(String n, String d) {
		nombre=n;
		datos=d;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDatos() {
		return datos;
	}

	public void setDatos(String datos) {
		this.datos = datos;
	}
	
}
