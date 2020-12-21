package com.buscador.buscadorontology.clases;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;

//import org.apache.lucene.search.
public class MotorBusqueda {
	 private IndexSearcher buscador = null;
	    private QueryParser analizador = null;
	    
	    /** Creates a new instance of SearchEngine */
	    public MotorBusqueda() throws IOException {
	        buscador = new IndexSearcher("index-directory");
	        analizador = new QueryParser("Contenido", new StandardAnalyzer());
	    }
	    
	    public Hits funcionBuscar(String textoConsulta)
	    throws IOException, ParseException {
	        Query query = analizador.parse(textoConsulta);        
	        Hits hits = buscador.search(query);
	        return hits;
	    }
}
