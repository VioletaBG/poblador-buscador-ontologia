package com.buscador.buscadorontology.clases;

public class Pasta {
 private String descripcion;
 private String nombre;
 private String pais;
 private String ingredientes;
 private String id;
 
 public Pasta(String id, String nom, String des, String pais, String ingre) {
	 this.setId(id);
	 this.descripcion =des;
	 this.nombre = nom;
	 this.pais=pais;
	 this.ingredientes=ingre;
	 
 }

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getPais() {
	return pais;
}

public void setPais(String pais) {
	this.pais = pais;
}

public String getIngredientes() {
	return ingredientes;
}

public void setIngredientes(String ingredientes) {
	this.ingredientes = ingredientes;
}
 
 
}
