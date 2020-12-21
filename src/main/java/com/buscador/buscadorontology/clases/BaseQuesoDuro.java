package com.buscador.buscadorontology.clases;

import java.util.ArrayList;

public class BaseQuesoDuro extends BaseInformacion {
 
	private static ArrayList<QuesoDuro> quesosDuros = new ArrayList<>();
	
	private  ArrayList<String> nombresIndividuos;
	   
	   
	   public BaseQuesoDuro() {
		quesosDuros=new ArrayList<>();
		nombresIndividuos = new ArrayList<>();
		nombresIndividuos.add("\"Manchego\"");
		nombresIndividuos.add("\"Ricotta\"");
		nombresIndividuos.add("\"Majorero\"");
		nombresIndividuos.add("\"Parmigiano-Reggiano\"");
		nombresIndividuos.add("\"Corleggy_Cheese\"");
		nombresIndividuos.add("\"Halloumi\"");
	
		añadirBD();
		  
	   }
	   public void añadirBD() {
		int i=0;
		for(String individuo : nombresIndividuos) {
			String descrip= limpiarDescripcion(consultaBuscador.GetDatoOntologia("descripcionQ",individuo));
			String pais = limpiarPais(consultaBuscador.GetDatoOntologia("paisq", individuo));
			String nombre = limpiarNombre(consultaBuscador.GetDatoOntologia("nombreq", individuo));
			String origen = limpiarSimple(consultaBuscador.GetDatoOntologia("origen", individuo));
			String envejecimiento = limpiarSimple(consultaBuscador.GetDatoOntologia("envejecimiento", individuo));
			
			System.out.println("Descrip "+descrip+" " +pais+" "+nombre+" "+origen+" "+envejecimiento);
			QuesoDuro quesoDuro= new QuesoDuro(i+"",descrip,pais, nombre, origen, envejecimiento);
						   
			quesosDuros.add(quesoDuro);
		 i++;    
		}
		
	}
	
	   
	   public static ArrayList<QuesoDuro> getQuesos(){
		   return quesosDuros;
	   }
	
}
