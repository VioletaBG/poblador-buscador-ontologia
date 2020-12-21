package com.buscador.buscadorontology.clases;
import org.apache.lucene.document.Document;

public class Queso {
private String descripcion;
private String pais;
private String nombre;
private String origen;
private String id;

public Queso(String id,String d, String p, String n, String o) {
	this.id = id;
	descripcion =d;
	pais=p;
	nombre=n;
	origen=o;
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

public String getPais() {
	return pais;
}

public void setPais(String pais) {
	this.pais = pais;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}



public String getOrigen() {
	return origen;
}

public void setOrigen(String origen) {
	this.origen = origen;
}


}
