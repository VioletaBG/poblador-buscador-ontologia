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
		if(propiedad=="envejecimiento"){
			String prefix="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
						 +"PREFIX owl: <http://www.w3.org/2002/07/owl#>" 
						 +"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
						 +"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";
		    String queryAging=prefix+"SELECT ?envQ"+
                                      "WHERE {  ?n <queso:Envejecimiento> ?envQ ."
											   +"?n <queso:Nombre> ?nomQ "
											   +"FILTER(regex(?nomQ, "+individuo+"))}";

			queryExecution = QueryExecutionFactory.create(queryAging, modelo);
			results = queryExecution.execSelect();
			link=ResultSetFormatter.asText(results);
			queryExecution.close() ;
		}
			
			if(propiedad=="ingrediente"){
			String prefix="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
						 +"PREFIX owl: <http://www.w3.org/2002/07/owl#>" 
						 +"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
						 +"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";
				String queryIngredient=prefix+"SELECT ?ingP"+
										     "WHERE {  ?n <queso:Ingrediente> ?ingP ."
												      +"?n <queso:NombrePasta> ?nomP "
												      +"FILTER(regex(?nomP, "+individuo+"))}";
												   
				queryExecution = QueryExecutionFactory.create(queryIngredient, modelo);
				results = queryExecution.execSelect();
				link=ResultSetFormatter.asText(results);
				queryExecution.close() ;
												
	
			}
		
			if(propiedad=="paisq"){
				  String prefixos= "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
						 +"PREFIX owl: <http://www.w3.org/2002/07/owl#>" 
						 +"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
						 +"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";

				  String queryPaisq=prefix+"SELECT ?pais "+
									"WHERE { ?n <queso:PaisQueso> ?pais . "+
										"?n <queso:Nombre> ?nomQ . "+
									  +"FILTER(regex(?nomQ, "+individuo+"))}";
				 queryExecution = QueryExecutionFactory.create(queryPaisq, modelo);
				 results = queryExecution.execSelect();
				 link=ResultSetFormatter.asText(results);
				 queryExecution.close() ; 

				}	
		
			if(propiedad=="paisp"){
			String prefix="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
						 +"PREFIX owl: <http://www.w3.org/2002/07/owl#>" 
						 +"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
						 +"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";
				String queryPaisp=prefix+"SELECT ?pais "+
									"WHERE { ?n <queso:PaisPasta> ?pais . "+
										      +"?n <queso:NombrePasta> ?nomP "
										      +"FILTER(regex(?nomP, "+individuo+"))}";
												   
				queryExecution = QueryExecutionFactory.create(queryPaisp, modelo);
				results = queryExecution.execSelect();
				link=ResultSetFormatter.asText(results);
				queryExecution.close() ;
												
	
			}
			if(propiedad=="textura"){
				  String prefixos= "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
						 +"PREFIX owl: <http://www.w3.org/2002/07/owl#>" 
						 +"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
						 +"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";
				String queryTextura=prefixos+"SELECT ?text "+
							"WHERE { ?n <queso:Textura> ?text . "+
								"?n <queso:Nombre> ?nomQ . "+
						  +"FILTER(regex(?nomQ, "+individuo+"))}";
				 queryExecution = QueryExecutionFactory.create(queryPais, modelo);
				 results = queryExecution.execSelect();
				 link=ResultSetFormatter.asText(results);
				 queryExecution.close() ; 

				}	   

		
		
		
		
		return link;
	}
	
}
