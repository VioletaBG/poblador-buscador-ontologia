package com.buscador.buscadorontology.clases;

import java.util.ArrayList;

public class BasePasta extends BaseInformacion{

	private static ArrayList<Pasta> pastas=new ArrayList<>();
    private  ArrayList<String> nombresIndividuos;
	     
	   public BasePasta() {
		    pastas=new ArrayList<>();
			nombresIndividuos = new ArrayList<>();
			nombresIndividuos.add("\"Spaghetti\"");
			nombresIndividuos.add("\"Maultasche\"");
			nombresIndividuos.add("\"Ravioli\"");
			nombresIndividuos.add("\"Croxetti\"");
			nombresIndividuos.add("\"Pastina\"");
			nombresIndividuos.add("\"Macaroni_casserole\"");
			nombresIndividuos.add("\"Pasta_al_pomodoro\"");
			nombresIndividuos.add("\"Pasta_primavera\"");
			nombresIndividuos.add("\"Bigoli\"");
			añadirBD();
	   }
	   
	   
	   public void añadirBD() {
		int i=0;
		for(String individuo : nombresIndividuos) {
			String descrip=consultaBuscador.GetDatoOntologia("descripcionP",individuo);
			String pais = consultaBuscador.GetDatoOntologia("paisp", individuo);
			String nombre = consultaBuscador.GetDatoOntologia("nombrep", individuo);
			String ingrediente = consultaBuscador.GetDatoOntologia("ingrediente", individuo);
			
			System.out.println("Descrip "+descrip+" " +pais+" "+nombre+" "+ingrediente);
			//QuesoSemiSuave quesoSMS = new QuesoSemiSuave(i+"",descrip,"","","","","");
			Pasta pastaSMS= new Pasta(i+"",nombre,descrip,pais, ingrediente);
						   
			pastas.add(pastaSMS);
		 i++;    
		}
	   }
	   
	   public static ArrayList<Pasta> getPastas(){
		   return pastas;
	   }
}
