package com.buscador.buscadorontology.clases;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

import com.buscador.buscadorontology.Ontologia;

public class Indexador {
	 /** Creates a new instance of Indexer */
    public Indexador() {
    }
 
    private IndexWriter escribirIndexado = null;
    
    public IndexWriter getIndexadoEscrito(boolean create) throws IOException {
        if (escribirIndexado == null) {
            escribirIndexado = new IndexWriter("index-directory",
                                          new StandardAnalyzer(),
                                          create);
        }
        return escribirIndexado;
   }    
   
    public void closeIndexadoEscrito() throws IOException {
        if (escribirIndexado != null) {
            escribirIndexado.close();
        }
   }
   
    public void indexQuesoSemiSuave(QuesoSemiSuave queso) throws IOException  {
        System.out.println("Indexando Quesos: " + queso);
        IndexWriter writer = getIndexadoEscrito(false);
        Document doc = new Document();
        doc.add(new Field("Id", queso.getId(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("Nombre", queso.getNombre(), Field.Store.YES, Field.Index.TOKENIZED));
        doc.add(new Field("Pais", queso.getPais(), Field.Store.YES, Field.Index.UN_TOKENIZED));
        doc.add(new Field("Descripcion", queso.getDescripcion(), Field.Store.YES, Field.Index.TOKENIZED));
        doc.add(new Field("origen", queso.getOrigen(), Field.Store.YES, Field.Index.TOKENIZED));
        doc.add(new Field("textura", queso.getTextura(), Field.Store.YES, Field.Index.TOKENIZED));
        doc.add(new Field("pasteurizado", queso.getPasteurizado(), Field.Store.YES, Field.Index.TOKENIZED));
 
        
        
        String TextoCompletoBuscado = queso.getNombre() + " " + queso.getPais() + " " + queso.getDescripcion();
        doc.add(new Field("Contenido", TextoCompletoBuscado, Field.Store.YES, Field.Index.TOKENIZED));
        writer.addDocument(doc);
    	
    }
   
    
    public void reconstruyeIndexado() throws IOException {
    	 // Elimina el Indice Existente
        //
        getIndexadoEscrito(true);
        //
        // Indexa todas las entradas
        //
        BaseQuesoSemiSuave quesoSemiSuaveBD  = new BaseQuesoSemiSuave();
        
        ArrayList<QuesoSemiSuave> quesos = quesoSemiSuaveBD.getQuesos();
        
        for(QuesoSemiSuave queso : quesos) {
            indexQuesoSemiSuave(queso);              
        }
        //
        // Cierra el indexado mientras se realiza
        //
        closeIndexadoEscrito();
    	
    }
    
}
