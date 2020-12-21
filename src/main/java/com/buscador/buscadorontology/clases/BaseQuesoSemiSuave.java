package com.buscador.buscadorontology.clases;

import java.util.ArrayList;

import com.buscador.buscadorontology.ConsultasBuscador;

public class BaseQuesoSemiSuave extends BaseInformacion{
   private static ArrayList<QuesoSemiSuave> quesosSemiSuaves=new ArrayList<>();

   private  ArrayList<String> nombresIndividuos;
   
   public BaseQuesoSemiSuave() {
	  nombresIndividuos = new ArrayList<>();
	  nombresIndividuos.add("\"Feta\"");
	  nombresIndividuos.add("\"Fontina\"");
	  nombresIndividuos.add("\"Langres\"");
	  nombresIndividuos.add("\"Livarot\"");
	  nombresIndividuos.add("\"Reblochon\"");
	  nombresIndividuos.add("\"Rocamadour\"");
	  nombresIndividuos.add("\"Tetilla\"");
	  nombresIndividuos.add("\"Waterloo\"");
      
	  añadirBD();
   }
   
   
   public void añadirBD() {
	   int i=0;
	   for(String individuo : nombresIndividuos) {
		   String descrip=consultaBuscador.GetDatoOntologia("descripcionQ",individuo);
		   String pais = consultaBuscador.GetDatoOntologia("paisq", individuo);
		   String nombre = consultaBuscador.GetDatoOntologia("nombreq", individuo);
		   String textura = consultaBuscador.GetDatoOntologia("textura", individuo);
		   String origen = consultaBuscador.GetDatoOntologia("origen", individuo);
		   String pasteurizado= consultaBuscador.GetDatoOntologia("pasteurizado", individuo);
		   
		   System.out.println("Descrip "+descrip+" " +pais+" "+nombre+" "+textura+" "+origen+" "+pasteurizado);
		   //QuesoSemiSuave quesoSMS = new QuesoSemiSuave(i+"",descrip,"","","","","");
		   QuesoSemiSuave quesoSMS= new QuesoSemiSuave(i+"",descrip,pais, nombre, textura, origen, pasteurizado);
		   			   
           quesosSemiSuaves.add(quesoSMS);
        i++;    
       }
	   
   }
   
   public static ArrayList<QuesoSemiSuave> getQuesos(){
	   return quesosSemiSuaves;
   }
}

