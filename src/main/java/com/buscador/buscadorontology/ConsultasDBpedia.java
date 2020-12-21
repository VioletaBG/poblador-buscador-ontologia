package com.buscador.buscadorontology;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class ConsultasDBpedia {
   public static String link;
   public static ResultSet results;

   public String GetDatoDbpedia(String propiedad, String nombreIndividuo) {
	 if(propiedad=="descripcion") {  
	   String prefixos= "PREFIX dbont: <http://dbpedia.org/ontology/>"+ 
	                    "PREFIX dbp: <http://dbpedia.org/property/>";
	   String queryAbstract= prefixos+ "SELECT ?abstract WHERE {{ <http://dbpedia.org/resource/"+nombreIndividuo+"> dbont:abstract ?abstract . "+
               "FILTER langMatches( lang(?abstract), 'es') } }" ;		 
      QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql",queryAbstract);
 
      results = queryExecution.execSelect();
      link=ResultSetFormatter.asText(results);
	  queryExecution.close() ;
	 }
	 
	 if(propiedad=="pasteurizado") {
		 String prefixos= "PREFIX dbont: <http://dbpedia.org/ontology/>"+ 
                 "PREFIX dbp: <http://dbpedia.org/property/>";
    	 String queryPasteurized= prefixos+ "SELECT ?pasteurized WHERE { <http://dbpedia.org/resource/"+nombreIndividuo+"> dbp:pasteurized ?pasteurized . }";
	 
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql",queryPasteurized);

        results = queryExecution.execSelect();
        link=ResultSetFormatter.asText(results);
        //System.out.println(link);
        queryExecution.close() ;
	 }
	 
	 if(propiedad=="pasteurisado") {
		 String prefixos= "PREFIX dbont: <http://dbpedia.org/ontology/>"+ 
                 "PREFIX dbp: <http://dbpedia.org/property/>";
    	 String queryPasteurized= prefixos+ "SELECT ?pasteurized WHERE { <http://dbpedia.org/resource/"+nombreIndividuo+"> dbp:pasteurised ?pasteurized . }";
	 
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql",queryPasteurized);
	
        results = queryExecution.execSelect();
        link=ResultSetFormatter.asText(results);
        queryExecution.close() ;
	 }

	 
	  


	if(propiedad.equals("name")){
		String prefixos= "PREFIX dbont: <http://dbpedia.org/ontology/>"+ 
                 "PREFIX dbp: <http://dbpedia.org/property/>";
		String queryPasteurized= prefixos+"select ?name where {<http://dbpedia.org/resource/"+nombreIndividuo+"> dbp:name ?name }";
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql",queryPasteurized);
		results = queryExecution.execSelect();
		link=ResultSetFormatter.asText(results);
		queryExecution.close();
		System.out.println(link);
	}
	
	if(propiedad.equals("fname")) {
		String prefixos="PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n";
        String queryNameF = prefixos +"select ?name where {<http://dbpedia.org/resource/"+nombreIndividuo+"> foaf:name ?name }"; 
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql",queryNameF);
        results = queryExecution.execSelect();
		link=ResultSetFormatter.asText(results);
		queryExecution.close();

	}
	
	if(propiedad.equals("source")){
		String prefixos= "PREFIX dbpr: <http://dbpedia.org/property/>";
		String queryPasteurized= prefixos+ "select ?source where {<http://dbpedia.org/resource/"+nombreIndividuo+"> dbpr:source ?source }";
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql",queryPasteurized);
		results = queryExecution.execSelect();
		link=ResultSetFormatter.asText(results);
		queryExecution.close();
	}
	 if(propiedad.equals("aging")) {
			String prefix ="PREFIX dbpr: <http://dbpedia.org/property/>";
			String queryAging= prefix+ "select ?valorAging \n"
					+ "where {<http://dbpedia.org/resource/"+nombreIndividuo+"> dbpr:aging ?valorAging }";
			QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql",queryAging); 
			results = queryExecution.execSelect(); 
			link=ResultSetFormatter.asText(results);
			//System.out.println(link);
			queryExecution.close() ; 
	}
	 if(propiedad=="pais") {
		 String prefixos= "PREFIX dbont: <http://dbpedia.org/ontology/>"+ 
		 "PREFIX dbp: <http://dbpedia.org/property/>";
		 String	queryPais=prefixos+"SELECT ?country WHERE { <http://dbpedia.org/resource/"+nombreIndividuo+"> dbp:country ?country .}";
		 QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql",queryPais);
		 results = queryExecution.execSelect();
		 link=ResultSetFormatter.asText(results);
		 queryExecution.close() ;
	  }
	 if(propiedad=="textura") {
		 String prefixos= "PREFIX dbont: <http://dbpedia.org/ontology/>"+ 
			 "PREFIX dbp: <http://dbpedia.org/property/>";
		 String queryTextura = prefixos+"SELECT ?texture WHERE { <http://dbpedia.org/resource/"+nombreIndividuo+"> dbp:texture ?texture .}";
		 QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql",queryTextura);
		 results = queryExecution.execSelect();
		 link=ResultSetFormatter.asText(results);
		 queryExecution.close() ;
	}
	   
		if(propiedad.equals("ingredientName")) {
			String prefix="PREFIX dpo: <http://dbpedia.org/ontology/>";
			String queryIngredient = prefix+"select ?valorIngredientName where {<http://dbpedia.org/resource/"+nombreIndividuo+"> dpo:ingredientName ?valorIngredientName }";
			QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql",queryIngredient); 
			results = queryExecution.execSelect(); 
			link=ResultSetFormatter.asText(results);
			//System.out.println(link);
			queryExecution.close() ; 
		}
		
		

	  return link;
   }

	public ConsultasDBpedia() {
	}
}
