package com.buscador.buscadorontology.clases;

import java.util.ArrayList;

public class BaseQuesoDuro extends BaseInformacion {
 
	private static ArrayList<QuesoDuro> quesosDuros;
	   
	   
	   public BaseQuesoDuro() {
		   quesosDuros=new ArrayList<>();
		  
	   }
	   
	   
	   public void añadirBD() {
		
	   }
	   
	   public static ArrayList<QuesoDuro> getQuesos(){
		   return quesosDuros;
	   }
	
}
