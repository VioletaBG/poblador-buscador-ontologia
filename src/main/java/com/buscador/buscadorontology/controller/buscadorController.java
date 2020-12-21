package com.buscador.buscadorontology.controller;

import com.buscador.buscadorontology.clases.Indexador;
import com.buscador.buscadorontology.clases.MotorBusqueda;
import com.buscador.buscadorontology.model.Frase;
import com.buscador.buscadorontology.model.Resultado;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Hit;
import org.apache.lucene.search.Hits;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class buscadorController {

    @GetMapping("/")
    public String buscador(Model model){
        model.addAttribute("frase", new Frase());
        return "form";
    }
    @PostMapping("/resultados")
    public ModelAndView resultados(@ModelAttribute("frase") Frase frase){
        
    	 String resultado ="";
    	 String nombre="";
    	 ArrayList<Resultado> respuesta=  new ArrayList<>();
    	 ArrayList<String> nombres= new ArrayList<>();
    	try {
    	// Se construye el indice con Lucene
        System.out.println("reconstruyeIndexado");
        Indexador  indexer = new Indexador();
        indexer.reconstruyeIndexado();
        System.out.println("reconstruccionIndexado realizado!!");

        // Se realiza una Busqueda Ejemplo: "Hombre grandeza"
        // y se devuelve el resultado...
        System.out.println("Busqueda....");
        MotorBusqueda instancia = new MotorBusqueda();
        Hits hits = instancia.funcionBuscar(frase.getFrase());

        System.out.println("Resultados Encontrados: " + hits.length());
        Iterator<Hit> iter =  hits.iterator();
        Document doc; 
        
        int i =0;
        
        while(iter.hasNext()){
        	
            Hit hit = iter.next();
            doc = hit.getDocument();
            String dato = doc.get("pasteurizado");
            nombre = doc.get("Nombre");
            
            nombre = nombre.replaceAll("-","");
            nombre = nombre.replaceAll("=","");
            nombre=nombre.replaceFirst("\"","");
            nombre=nombre.split("\"")[0];
            
            resultado= doc.get("Descripcion");
            resultado = resultado.replaceAll("-","");
            resultado = resultado.replaceAll("=","");
            
            Resultado res = new Resultado(nombre,resultado);
            respuesta.add(res);
            
           // System.out.println(doc.get("Nombre") + " " + doc.get("Pais") + " (" + hit.getScore() + ")" + dato );

      }
    	}catch(Exception e) {
    		
            System.out.println("Problemas..Excepcion.\n");

    	} 
    	
    	
        ModelAndView mav = new ModelAndView("resultados");
        mav.addObject("respuesta", respuesta);
        return mav;
    }
    

}
