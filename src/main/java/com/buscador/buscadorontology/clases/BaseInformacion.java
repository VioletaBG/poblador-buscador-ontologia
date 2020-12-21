package com.buscador.buscadorontology.clases;

import java.util.ArrayList;

import com.buscador.buscadorontology.ConsultasBuscador;

public class BaseInformacion {
protected ConsultasBuscador consultaBuscador;

	public BaseInformacion() {
	  consultaBuscador = new ConsultasBuscador();	
	  
	}
	
	
	protected String limpiarNombre(String frase) {
		   String res = frase;
		   res = res.replaceAll("-","");
	    res = res.replaceAll("=","");
	   
	    res=res.split("\"")[2];
	    

		   return res;
	}

	protected String limpiarDescripcion(String frase) {
		  String [] separaciones = frase.split("\"");
		  String res ="";
		  for(String separacion: separaciones) {
			  char primerCarac= separacion.charAt(0);
			  if(primerCarac!='-') {
				  if(primerCarac!='@') {
					  if(primerCarac!='^') {
						  res=res+separacion;
					  }
				  }
				  
			  }  
		   }
		   
		  return res;
	}

	protected String limpiarPais(String frase) {
		   String res ="";
		   frase= frase.replaceAll("-","");
	    frase= frase.replaceAll("=","");
		   String [] separaciones = frase.split("\r\n");
		   String part3  = separaciones[3];
		   separaciones = part3.split("\"");
		   
		   for(int i=0; i<separaciones.length;i++) {
			   char primerCarac = separaciones[i].charAt(0);
			   String palabra = separaciones[i]; 
			   if(i==1) {
				  String [] separaciones2 = palabra.split("resource/");
				 
			
				    int j=0;
				     for(String sepa: separaciones2) {
				    	 if(j==1){
				    		res = sepa; 
				    		res = res.replaceAll("r","");
				    		res = res.replaceAll("n","");
				    		res = res.replace("\"\"","");
				    	 }
				    	 j++;
				      
				     }
				   
			   }else if(i==2 && primerCarac!='^') {
				   res=res+separaciones[i];
				   
			   }
			   
		   }
		   
		   return res;
	}

	protected String limpiarSimple(String frase) {
		   String res = frase;
		   res = res.replaceAll("-","");
	    res = res.replaceAll("=","");
	    return res;
	}
}
