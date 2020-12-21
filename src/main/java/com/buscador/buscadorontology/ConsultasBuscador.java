package com.buscador.buscadorontology;

import com.buscador.buscadorontology.clases.BaseInformacion;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.query.ResultSet;

public class ConsultasBuscador  {
	OntModel modelo; 
	public static String link;
	private ResultSet results;
	private QueryExecution queryExecution;
	
	public ConsultasBuscador() {
	    modelo = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		modelo.read("Direccion archivo owl","OWL/XML");
		
	}
	
	public String GetDatoOntologia(String propiedad, String individuo) {
		
		if(propiedad=="descripcion") {
		  String prefixos= "";
  			 
  		  String	queryPrueba=prefixos+"";
  		 
  		 
  		 
  		 queryExecution = QueryExecutionFactory.create(queryPrueba, modelo);
  		 results = queryExecution.execSelect();
  		 link=ResultSetFormatter.asText(results);
  		 queryExecution.close() ; 
			
  		 }
		
		
		
		
		return link;
	}
	
}
