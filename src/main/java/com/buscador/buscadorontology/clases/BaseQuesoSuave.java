package com.buscador.buscadorontology.clases;

import java.util.ArrayList;

public class BaseQuesoSuave extends BaseInformacion{

	private static ArrayList<QuesoSuave> quesosSuaves=new ArrayList<>();;
	private  ArrayList<String> nombresIndividuos;
   
	   public BaseQuesoSuave() {
		   nombresIndividuos = new ArrayList<>();
		   nombresIndividuos.add("\"Bryndza\"");
		   nombresIndividuos.add("\"Cottage\"");
		   nombresIndividuos.add("\"Gorgonzola\"");
		   nombresIndividuos.add("\"Limburger\"");
		   nombresIndividuos.add("\"Mascarpone\"");
		   nombresIndividuos.add("\"Mozzarella\"");
		   nombresIndividuos.add("\"Sirene\"");
		   añadirBD();
	   }
	   
	   
	   public void añadirBD() {
		int i=0;
		for(String individuo : nombresIndividuos) {
			String descrip=limpiarDescripcion(consultaBuscador.GetDatoOntologia("descripcionQ",individuo));
			String pais = limpiarPais(consultaBuscador.GetDatoOntologia("paisq", individuo));
			String nombre = limpiarNombre(consultaBuscador.GetDatoOntologia("nombreq", individuo));
			String origen = limpiarSimple(consultaBuscador.GetDatoOntologia("origen", individuo));
		
			QuesoSuave quesoSS= new QuesoSuave(i+"",descrip,pais, nombre, origen);
						   
			quesosSuaves.add(quesoSS);
		 i++;    
		}
	   }
	   
	   public static ArrayList<QuesoSuave> getQuesos(){
		   return quesosSuaves;
	   }
}
