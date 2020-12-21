package com.buscador.buscadorontology;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
	    try {
			InputStream in = new FileInputStream("OntologiaQueso.owl");
			modelo.read(in,null);
			//in.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // or any windows path

		
		
	}
	
	public String GetDatoOntologia(String propiedad, String individuo) {
		
		if(propiedad=="descripcionQ") {
		  String prefixos= "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
		  		"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
		  		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
		  		"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n";
  			 
  		  String	querydescripcionq=prefixos+"SELECT ?descrip \r\n" + 
  		  		"     WHERE { ?n <queso:DescripcionQueso> ?descrip. \r\n" + 
  		  		"	?n <queso:Nombre> ?nomQ .\r\n" + 
  		  		"                        FILTER(regex(?nomQ,"+individuo+"))\r\n" + 
  		  		"                \r\n" + 
  		  		"                   }";
  		 
  		 
  		 
  		 queryExecution = QueryExecutionFactory.create(querydescripcionq, modelo);
  		 results = queryExecution.execSelect();
  		 link=ResultSetFormatter.asText(results);
  		 queryExecution.close() ; 
  		// System.out.println(link);
			
		}
		
		if(propiedad=="descripcionP") {
			  String prefixos="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			  		"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
			  		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
			  		"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";
	  			 
	  		  String	querydescripcionp=prefixos+"SELECT ?descrip \r\n" + 
	  		  		"     WHERE { ?n <queso:DescripcionPasta> ?descrip. \r\n" + 
	  		  		"	 ?n <queso:NombrePasta> ?nomP .\r\n"+
	  		  		"                        FILTER(regex(?nomP,"+individuo+"))\r\n" + 
	  		  		"                \r\n" + 
	  		  		"                   }";
	  		 
	  		 
	  		 
	  		 queryExecution = QueryExecutionFactory.create(querydescripcionp, modelo);
	  		 results = queryExecution.execSelect();
	  		 link=ResultSetFormatter.asText(results);
	  		 queryExecution.close() ; 
				
			}
		
		if(propiedad=="pasteurizado") {
			  String prefixos="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			  		"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
			  		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
			  		"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";
	  			 
	  		  String	querydescripcionp=prefixos+"                                  \r\n" + 
	  		  		"SELECT ?pasteurizado\r\n" + 
	  		  		"     WHERE { ?n <queso:Pateurizado> ?pasteurizado. \r\n" + 
	  		  		"	?n <queso:Nombre> ?nomQ .\r\n" + 
	  		  		"	FILTER(regex(?nomQ,"+individuo+"))\r\n" + 
	  		  		"                \r\n" + 
	  		  		"                   }";
	  		 
	  		 
	  		 
	  		 queryExecution = QueryExecutionFactory.create(querydescripcionp, modelo);
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

				  String queryPaisq=prefixos+"SELECT ?pais "+
									"WHERE { ?n <queso:PaisQueso> ?pais . "+
										"?n <queso:Nombre> ?nomQ . "
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
									"WHERE { ?n <queso:PaisPasta> ?pais . "
										      +"?n <queso:NombrePasta> ?nomP . "
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
								"?n <queso:Nombre> ?nomQ . "
						  +"FILTER(regex(?nomQ, "+individuo+"))}";
				 queryExecution = QueryExecutionFactory.create(queryTextura,modelo);
				 results = queryExecution.execSelect();
				 link=ResultSetFormatter.asText(results);
				 queryExecution.close() ; 

				}
			if(propiedad=="nombrep"){
				String prefix="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
							 +"PREFIX owl: <http://www.w3.org/2002/07/owl#>" 
							 +"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
							 +"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";
					String queryNombreP=prefix+"SELECT ?nomP "+
										"WHERE { ?n <queso:NombrePasta> ?nomP "
												  +"FILTER(regex(?nomP, "+individuo+"))}";
													   
					queryExecution = QueryExecutionFactory.create(queryNombreP, modelo);
					results = queryExecution.execSelect();
					link=ResultSetFormatter.asText(results);
					queryExecution.close() ;													
			}
			
			if(propiedad=="nombreq") {
				String prefix="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
						 +"PREFIX owl: <http://www.w3.org/2002/07/owl#>" 
						 +"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
						 +"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";
				String queryNombreP=prefix+"SELECT ?nomP "+
									"WHERE { ?n <queso:Nombre> ?nomP "
											  +"FILTER(regex(?nomP, "+individuo+"))}";
												   
				queryExecution = QueryExecutionFactory.create(queryNombreP, modelo);
				results = queryExecution.execSelect();
				link=ResultSetFormatter.asText(results);
				queryExecution.close() ;	
				
			}
		
			if(propiedad=="origen") {
				String prefix="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
						 +"PREFIX owl: <http://www.w3.org/2002/07/owl#>" 
						 +"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
						 +"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";
				String queryOrigen=prefix+"SELECT ?nomP "+
									"WHERE { ?n <queso:Procedencia> ?nomP "
											  +"FILTER(regex(?nomP, "+individuo+"))}";
												   
				queryExecution = QueryExecutionFactory.create(queryOrigen, modelo);
				results = queryExecution.execSelect();
				link=ResultSetFormatter.asText(results);
				queryExecution.close() ;	
				
			}

		
		return link;
	}
	
}
