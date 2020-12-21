package com.buscador.buscadorontology.clases;

import java.util.ArrayList;

public class BaseQuesoSemiSuave extends BaseInformacion{
   private static ArrayList<QuesoSemiSuave> quesosSemiSuaves;
   
   
   public BaseQuesoSemiSuave() {
	   quesosSemiSuaves=new ArrayList<>();
	  
   }
   
   
   public void añadirBD() {
	
   }
   
   public static ArrayList<QuesoSemiSuave> getQuesos(){
	   return quesosSemiSuaves;
   }
}

