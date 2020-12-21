package com.buscador.buscadorontology.clases;

import java.util.ArrayList;

public class BasePasta extends BaseInformacion{

	private static ArrayList<Pasta> pastas;
	   
	   
	   public BasePasta() {
		   pastas=new ArrayList<>();
		  
	   }
	   
	   
	   public void añadirBD() {
		
	   }
	   
	   public static ArrayList<Pasta> getPastas(){
		   return pastas;
	   }
}
