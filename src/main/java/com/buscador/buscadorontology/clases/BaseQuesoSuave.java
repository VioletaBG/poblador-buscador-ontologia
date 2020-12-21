package com.buscador.buscadorontology.clases;

import java.util.ArrayList;

public class BaseQuesoSuave {

	private static ArrayList<QuesoSuave> quesosSuaves;
	   
	   
	   public BaseQuesoSuave() {
		   quesosSuaves=new ArrayList<>();
		  
	   }
	   
	   
	   public void añadirBD() {
		
	   }
	   
	   public static ArrayList<QuesoSuave> getQuesos(){
		   return quesosSuaves;
	   }
}
