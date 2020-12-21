package com.buscador.buscadorontology;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.buscador.buscadorontology.clases.BaseQuesoSemiSuave;
import com.buscador.buscadorontology.clases.QuesoSemiSuave;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.XSD;

public class Ontologia {
     private OntModel modelo; 
	 private String NS= "queso";
     private OntClass queso;
     private OntClass quesoDuro;
     private OntClass quesoSuave;
     private OntClass quesoSemiSuave;
     private OntClass Pasta;
	 private DatatypeProperty descripcion;
	 private DatatypeProperty nombre;
	 private DatatypeProperty pais;
	 private DatatypeProperty procedencia;
	 private DatatypeProperty envejecimiento;
	 private DatatypeProperty pasteurizado;
	 private DatatypeProperty textura;
	 private DatatypeProperty descripcionp;
	 private DatatypeProperty paisp;
	 private DatatypeProperty ingrediente;
	 private  ObjectProperty tieneComoIngrediente;
	 private Individual parmesano;
	 private Individual mozzarella;
	 private Individual lasania;
	 private Individual manchego;
	 
	 public Ontologia() {
		 crearOntologia();
	 }
	 
	 private void crearOntologia() {
		 modelo = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
	     modelo.setNsPrefix(NS, "http://www.owl-ontologies.com/OntologiaQueso.owl");
         //Clases
	     queso= modelo.createClass(NS+":"+"Queso");
	     quesoDuro= modelo.createClass(NS+":"+"QuesoDuro");
	     quesoSuave= modelo.createClass(NS+":"+"QuesoSuave");
	     quesoSemiSuave= modelo.createClass(NS+":"+"QuesoSemiSuave");
	     Pasta= modelo.createClass(NS+":"+"Pasta");
        //Subclases
	     queso.addSubClass(quesoDuro);
	     queso.addSubClass(quesoSuave);
	     queso.addSubClass(quesoSemiSuave);
	   //Propiedades
	     descripcion = modelo.createDatatypeProperty(NS+":"+"DescripcionQueso");
	     descripcion.addDomain(queso);
	     descripcion.addRange(XSD.xstring);
	     descripcion.convertToFunctionalProperty();
 
	     nombre = modelo.createDatatypeProperty(NS+":"+"Nombre");
	     nombre.addDomain(queso);
	     nombre.addRange(XSD.xstring);
	     nombre.convertToFunctionalProperty();
	     
	     pais = modelo.createDatatypeProperty(NS+":"+"PaisQueso");
	     pais.addDomain(queso);
	     pais.addRange(XSD.xstring);
	     pais.convertToFunctionalProperty();
	     
	     procedencia = modelo.createDatatypeProperty(NS+":"+"Procedencia");
	     procedencia.addDomain(queso);
	     procedencia.addRange(XSD.xstring);
	     procedencia.convertToFunctionalProperty();
	     
	     envejecimiento = modelo.createDatatypeProperty(NS+":"+"Envejecimiento");
	     envejecimiento.addDomain(quesoDuro);
	     envejecimiento.addRange(XSD.xstring);
	     envejecimiento.convertToFunctionalProperty();
	     
	     pasteurizado = modelo.createDatatypeProperty(NS+":"+"Pateurizado");
	     pasteurizado.addDomain(quesoSemiSuave);
	     pasteurizado.addRange(XSD.xstring);
	     pasteurizado.convertToFunctionalProperty();
	     
	     textura = modelo.createDatatypeProperty(NS+":"+"Textura");
	     textura.addDomain(quesoSemiSuave);
	     textura.addRange(XSD.xstring);
	     textura.convertToFunctionalProperty();
	     
	     descripcionp = modelo.createDatatypeProperty(NS+":"+"DescripcionPasta");
	     descripcionp.addDomain(Pasta);
	     descripcionp.addRange(XSD.xstring);
	     descripcionp.convertToFunctionalProperty();

	     paisp = modelo.createDatatypeProperty(NS+":"+"PaisPasta");
	     paisp.addDomain(Pasta);
	     paisp.addRange(XSD.xstring);
	     paisp.convertToFunctionalProperty();
	     
	     ingrediente = modelo.createDatatypeProperty(NS+":"+"Ingrediente");
	     ingrediente.addDomain(Pasta);
	     ingrediente.addRange(XSD.xstring);
	     ingrediente.convertToFunctionalProperty();
	     //Relaciones
	     tieneComoIngrediente = modelo.createObjectProperty(NS+":"+"TieneComoIngrediente");
	     tieneComoIngrediente.addDomain(Pasta);
	     tieneComoIngrediente.addRange(queso);
	     
	     
	   
	     CrearIndividuoQuesoDuro();
	     crearIndividuosQuesoSuave();
	     crearIndividuosQuesoSemiSuave();
	     
	     crearIndividuosPasta();
	     crearArchivoOwl();
	    
	     
	 }
	 
	 
	 private void crearArchivoOwl() {
		//Almacenamos la ontología en un fichero OWL (Opcional)
			File file = new File("C:\\Users\\HP 240-G5\\Downloads\\search-ontology-master\\OntologiaQueso.owl");
			//Hay que capturar las Excepciones
			if (!file.exists()){
			     try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				modelo.write(new PrintWriter(file));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	 }
	 
	 private OntModel getModelo() {
		 return modelo;
		 
	 }
	
	private void CrearIndividuoQuesoDuro(){
		ConsultasDBpedia consultaDBpedia = new ConsultasDBpedia();
		Individual brie = modelo.createIndividual(NS+":"+"Brie",quesoDuro);
		manchego = modelo.createIndividual(NS+":"+"Manchego",quesoDuro);
		Individual ricotta = modelo.createIndividual(NS+":"+"Ricotta",quesoDuro);
		Individual majorero = modelo.createIndividual(NS+":"+"Majorero",quesoDuro);
		parmesano = modelo.createIndividual(NS+":"+"Parmigiano-Reggiano",quesoDuro);
		Individual corleggy = modelo.createIndividual(NS+":"+"Corleggy_Cheese",quesoDuro);
		
		// propiedades para el individuo brie
		brie.setPropertyValue(envejecimiento, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("aging", "Brie")));
		brie.setPropertyValue(descripcion, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion", "Brie")));
      	brie.setPropertyValue(nombre, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name", "Brie")));
		brie.setPropertyValue(procedencia, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("source", "Brie")));
		brie.setPropertyValue(pais, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais", "Brie")));
		
		//propiedades para el individuo manchego
		manchego.setPropertyValue(envejecimiento, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("aging", "Manchego")));
		manchego.setPropertyValue(descripcion, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion", "Manchego")));
		manchego.setPropertyValue(procedencia, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("source", "Manchego")));
		manchego.setPropertyValue(nombre, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name", "Manchego")));
		manchego.setPropertyValue(pais, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais", "Manchego")));
		
		//propiedades para el individuo ricotta
		
		ricotta.setPropertyValue(envejecimiento, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("aging", "Ricotta")));
		ricotta.setPropertyValue(descripcion, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion", "Ricotta")));
		ricotta.setPropertyValue(procedencia, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("source", "Ricotta")));
		ricotta.setPropertyValue(nombre, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name", "Ricotta")));
		ricotta.setPropertyValue(pais, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais", "Ricotta")));
		
		
		//propiedades para el individuo majorero
		
	    majorero.setPropertyValue(envejecimiento, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("aging", "Majorero")));
	    majorero.setPropertyValue(descripcion, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion", "Majorero")));
	    majorero.setPropertyValue(procedencia, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("source", "Majorero")));
	    majorero.setPropertyValue(nombre, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name", "Majorero")));
		majorero.setPropertyValue(pais, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais", "Majorero")));
		
	    //propiedades para el individuo parmesano
		
	  	parmesano.setPropertyValue(envejecimiento, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("aging", "Parmigiano-Reggiano")));
	  	parmesano.setPropertyValue(descripcion, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion", "Parmigiano-Reggiano")));
	  	parmesano.setPropertyValue(procedencia, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("source", "Parmigiano-Reggiano")));
	  	parmesano.setPropertyValue(nombre, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name", "Parmigiano-Reggiano")));
	  	parmesano.setPropertyValue(pais, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais", "Parmigiano-Reggiano")));
		
	  	
        //propiedades para el individuo parmesano
		
	  	corleggy.setPropertyValue(envejecimiento, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("aging", "Corleggy_Cheese")));
	  	corleggy.setPropertyValue(descripcion, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion", "Corleggy_Cheese")));
	  	corleggy.setPropertyValue(procedencia, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("source", "Corleggy_Cheese")));
	  	corleggy.setPropertyValue(nombre, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name", "Corleggy_Cheese")));
	  	corleggy.setPropertyValue(pais, modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais", "Corleggy_Cheese")));
		
	}

	 
	 private void crearIndividuosQuesoSemiSuave() {
		 ConsultasDBpedia consultaDBpedia = new ConsultasDBpedia();
		 //Individuos de la clase quesoSemiSuave
		 
		 Individual ulloa = modelo.createIndividual(NS+":"+"Ulloa",quesoSemiSuave);
		 Individual reblochon = modelo.createIndividual(NS+":"+"Reblochon",quesoSemiSuave);
		 Individual waterloo =  modelo.createIndividual(NS+":"+"Waterloo",quesoSemiSuave);
		 Individual tetilla = modelo.createIndividual(NS+":"+"Tetilla",quesoSemiSuave);
		 Individual feta = modelo.createIndividual(NS+":"+"Feta",quesoSemiSuave);
		 Individual fontina = modelo.createIndividual(NS+":"+"Fontina",quesoSemiSuave);
		 Individual langres =  modelo.createIndividual(NS+":"+"Langres",quesoSemiSuave);
		 Individual livarot =  modelo.createIndividual(NS+":"+"Livarot",quesoSemiSuave);
		 Individual rocamadout =  modelo.createIndividual(NS+":"+"Rocamadout",quesoSemiSuave);
		 //propiedades para el individuo reblochon
		 
				 
		 reblochon.setPropertyValue(descripcion,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Reblochon")));
		 reblochon.setPropertyValue(pasteurizado,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pasteurisado","Reblochon")));
		 reblochon.setPropertyValue(pais,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Reblochon")));
		 reblochon.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name","Reblochon")));
		 reblochon.setPropertyValue(textura,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("textura","Reblochon")));
		 reblochon.setPropertyValue(procedencia,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("source","Reblochon")));
		 
		
		 
		 //propiedades para el individuo ulloa
		 ulloa.setPropertyValue(descripcion,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Arzúa-Ulloa_cheese")));
		 ulloa.setPropertyValue(pasteurizado,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pasteurizado","Arzúa-Ulloa_cheese")));
		 ulloa.setPropertyValue(pais,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Arzúa-Ulloa_cheese")));
		 ulloa.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name","Arzúa-Ulloa_cheese")));
		 ulloa.setPropertyValue(textura,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("textura","Arzúa-Ulloa_cheese")));
		 ulloa.setPropertyValue(procedencia,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("source","Arzúa-Ulloa_cheese")));
		 
		 
		 
		 //propiedades para el individuo waterloo
		 waterloo.setPropertyValue(descripcion,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Waterloo_cheese")));
		 waterloo.setPropertyValue(pasteurizado,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pasteurisado","Waterloo_cheese")));
		 waterloo.setPropertyValue(pais,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Waterloo_cheese")));
		 waterloo.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name","Waterloo_cheese")));
		 waterloo.setPropertyValue(textura,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("textura","Waterloo_cheese")));
		 waterloo.setPropertyValue(procedencia,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("source","Waterloo_cheese")));
		 
		 
	
		 
		 
		 //propiedades para el individuo tetilla
		 tetilla.setPropertyValue(descripcion,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Tetilla_cheese")));
		 tetilla.setPropertyValue(pasteurizado,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pasteurisado","Tetilla_cheese")));
		 tetilla.setPropertyValue(pais,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Tetilla_cheese")));
		 tetilla.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name","Tetilla_cheese")));
		 tetilla.setPropertyValue(textura,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("textura","Tetilla_cheese")));
		 tetilla.setPropertyValue(procedencia,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("source","Tetilla_cheese")));
		 
		
		 
		 //propiedades para el individuo feta
		 feta.setPropertyValue(descripcion,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Feta")));
		 feta.setPropertyValue(pasteurizado,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pasteurizado","Feta")));
		 feta.setPropertyValue(pais,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Feta")));
		 feta.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name","Feta")));
		 feta.setPropertyValue(textura,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("textura","Feta")));
		 feta.setPropertyValue(procedencia,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("source","Feta")));
	
		 
		 
		 //propiedades para el individuo fontina
		 fontina.setPropertyValue(descripcion,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Fontina")));
		 fontina.setPropertyValue(pasteurizado,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pasteurizado","Fotina")));
		 fontina.setPropertyValue(pais,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Fontina")));
		 fontina.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name","Fontina")));
		 fontina.setPropertyValue(textura,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("textura","Fotina")));
		 fontina.setPropertyValue(procedencia,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("source","Fontina")));
		 
		 //propiedades para el individuo langres
		 langres.setPropertyValue(descripcion,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Langres_cheese")));
		 langres.setPropertyValue(pasteurizado,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pasteurisado","Langres_cheese")));
		 langres.setPropertyValue(pais,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Langres_cheese")));
		 langres.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name","Langres_cheese")));
		 langres.setPropertyValue(textura,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("textura","Langres_cheese")));
		 langres.setPropertyValue(procedencia,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("source","Langres_cheese")));
		 
		 
		 //propiedades para el individuo livarot
		 livarot.setPropertyValue(descripcion,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Livarot_cheese")));
		 livarot.setPropertyValue(pasteurizado,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pasteurisado","Livarot_cheese")));
		 livarot.setPropertyValue(pais,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Livarot_cheese")));
		 livarot.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name","Livarot_cheese")));
		 livarot.setPropertyValue(textura,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("textura","Livarot_cheese")));
		 livarot.setPropertyValue(procedencia,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("source","Livarot_cheese")));
		 
		 
		 //propiedades para el individuo rocamadout
		 rocamadout.setPropertyValue(descripcion,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Rocamadour_cheese")));
		 rocamadout.setPropertyValue(pasteurizado,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pasteurizado","Rocamadour_cheese")));
		 rocamadout.setPropertyValue(pais,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Rocamadour_cheese")));
		 rocamadout.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name","Rocamadour_cheese")));
		 rocamadout.setPropertyValue(textura,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("textura","Rocamadour_cheese")));
		 rocamadout.setPropertyValue(procedencia,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("source","Rocamadour_cheese")));
		 
		 
		 
 	 
	 
	 
	 }
	 private void crearIndividuosQuesoSuave(){
		 ConsultasDBpedia consultasDBpedia = new ConsultasDBpedia();
		 //Individuos de la clase quesoSuave
		 
		 Individual bryndza = modelo.createIndividual(NS+":"+"Bryndza", quesoSuave);
		 Individual cottage = modelo.createIndividual(NS+":"+"Cottage", quesoSuave);
		 Individual gorgonzola = modelo.createIndividual(NS+":"+"Gorgonzola", quesoSuave);
		 Individual limburger = modelo.createIndividual(NS+":"+"Limburger", quesoSuave);
		 Individual mascarpone = modelo.createIndividual(NS+":"+"Mascarpone", quesoSuave);
		 mozzarella = modelo.createIndividual(NS+":"+"Mozzarella", quesoSuave);
		 Individual sirene = modelo.createIndividual(NS+":"+"Sirene", quesoSuave);
		 

		//propiedades para el individuo bryndza
		 bryndza.setPropertyValue(descripcion, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("descripcion","Bryndza")));
		 bryndza.setPropertyValue(nombre, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("name","Bryndza")));
		 bryndza.setPropertyValue(procedencia, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("source","Bryndza")));
		 
		//propiedades para el individuo cottage
		 cottage.setPropertyValue(descripcion, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("descripcion","Cottage_cheese")));
		 cottage.setPropertyValue(nombre, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("name","Cottage_cheese")));
		 cottage.setPropertyValue(procedencia, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("source","Cottage_cheese")));
		 
		//propiedades para el individuo gorgonzola
		 gorgonzola.setPropertyValue(descripcion, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("descripcion","Gorgonzola")));
		 gorgonzola.setPropertyValue(nombre, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("name","Gorgonzola")));
		 gorgonzola.setPropertyValue(pais, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("pais","Gorgonzola")));
		 gorgonzola.setPropertyValue(procedencia, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("source","Gorgonzola")));
		 
		//propiedades para el individuo limburger
		 limburger.setPropertyValue(descripcion, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("descripcion","Limburger")));
		 limburger.setPropertyValue(nombre, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("name","Limburger")));
		 limburger.setPropertyValue(pais, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("pais","Limburger")));
		 limburger.setPropertyValue(procedencia, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("source","Limburger")));
		 
		//propiedades para el individuo mascarpone
		 mascarpone.setPropertyValue(descripcion, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("descripcion","Mascarpone")));
		 mascarpone.setPropertyValue(nombre, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("name","Mascarpone")));
		 mascarpone.setPropertyValue(pais, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("pais","Mascarpone")));
		 mascarpone.setPropertyValue(procedencia, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("source","Mascarpone")));
		 
		//propiedades para el individuo mozzarella
		 mozzarella.setPropertyValue(descripcion, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("descripcion","Mozzarella")));
		 mozzarella.setPropertyValue(nombre, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("name","Mozzarella")));
		 mozzarella.setPropertyValue(procedencia, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("source","Mozzarella")));
		 
		//propiedades para el individuo sirene
		 sirene.setPropertyValue(descripcion, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("descripcion","Sirene")));
		 sirene.setPropertyValue(nombre, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("name","Sirene")));
		 sirene.setPropertyValue(pais, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("pais","Sirene")));
		 sirene.setPropertyValue(procedencia, modelo.createTypedLiteral(consultasDBpedia.GetDatoDbpedia("source","Sirene")));
		 
	 }
	 
	
	private void crearIndividuosPasta() {
		ConsultasDBpedia consultaDBpedia = new ConsultasDBpedia();
		//Individuos de la clase Pasta
		Individual spaghetti = modelo.createIndividual(NS+":"+"Spaghetti",Pasta);
		Individual maultasche = modelo.createIndividual(NS+":"+"Maultasche",Pasta);
		Individual ravioli =  modelo.createIndividual(NS+":"+"Ravioli",Pasta);
		Individual croxetti = modelo.createIndividual(NS+":"+"Croxetti",Pasta);
		Individual pastina = modelo.createIndividual(NS+":"+"Pastina",Pasta);
		Individual macaroni_casserole = modelo.createIndividual(NS+":"+"Macaroni_casserole",Pasta);
		Individual pasta_al_pomodoro =  modelo.createIndividual(NS+":"+"Pasta_al_pomodoro",Pasta);
		Individual pasta_primavera =  modelo.createIndividual(NS+":"+"Pasta_primavera",Pasta);
		Individual bigoli =  modelo.createIndividual(NS+":"+"Bigoli",Pasta);
		
		lasania = modelo.createIndividual(NS+":"+"Lasaña",Pasta);
		
		//props para el individuo lasania
		lasania.setPropertyValue(descripcionp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Lasagne")));
		lasania.setPropertyValue(paisp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Lasagne")));
		lasania.setPropertyValue(ingrediente,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("ingredientName","Lasagne")));
		lasania.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("fname","Lasagne")));
		
		lasania.addProperty(tieneComoIngrediente, mozzarella);
		lasania.addProperty(tieneComoIngrediente, manchego);
		
		//propiedades para el individuo spaghetti
		spaghetti.setPropertyValue(descripcionp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Spaghetti")));
		spaghetti.setPropertyValue(paisp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Spaghetti")));
		spaghetti.setPropertyValue(ingrediente,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("ingredientName","Spaghetti")));
		spaghetti.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("name","Spaghetti")));

		
		spaghetti.addProperty(tieneComoIngrediente,parmesano);
		
		
		//propiedades para el individuo maultasche
		maultasche.setPropertyValue(descripcionp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Maultasche")));
		maultasche.setPropertyValue(paisp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Maultasche")));
		maultasche.setPropertyValue(ingrediente,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("ingredientName","Maultasche")));
		maultasche.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("fname","Maultasche")));

		
		//propiedades para el individuo ravioli
		ravioli.setPropertyValue(descripcionp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Ravioli")));
		ravioli.setPropertyValue(paisp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Ravioli")));
		ravioli.setPropertyValue(ingrediente,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("ingredientName","Ravioli")));
		ravioli.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("fname","Ravioli")));

		
		
		//propiedades para el individuo croxetti
		croxetti.setPropertyValue(descripcionp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Croxetti")));
		croxetti.setPropertyValue(paisp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Croxetti")));
		croxetti.setPropertyValue(ingrediente,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("ingredientName","Croxetti")));
		croxetti.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("fname","Croxetti")));

		//propiedades para el individuo Pastina
		pastina.setPropertyValue(descripcionp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Pastina")));
		pastina.setPropertyValue(paisp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Pastina")));
		pastina.setPropertyValue(ingrediente,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("ingredientName","Pastina")));
		pastina.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("fname","Pastina")));

		//propiedades para el individuo Macaroni_casserole
		macaroni_casserole.setPropertyValue(descripcionp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Macaroni_casserole")));
		macaroni_casserole.setPropertyValue(paisp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Macaroni_casserole")));
		macaroni_casserole.setPropertyValue(ingrediente,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("ingredientName","Macaroni_casserole")));
		macaroni_casserole.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("fname","Macaroni_casserole")));

		
		macaroni_casserole.addProperty(tieneComoIngrediente,mozzarella);

		
		
		//propiedades para el individuo Pasta_al_pomodoro
		pasta_al_pomodoro.setPropertyValue(descripcionp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Pasta_al_pomodoro")));
		pasta_al_pomodoro.setPropertyValue(paisp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Pasta_al_pomodoro")));
		pasta_al_pomodoro.setPropertyValue(ingrediente,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("ingredient","Pasta_al_pomodoro")));
		pasta_al_pomodoro.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("fname","Pasta_al_pomodoro")));

		//propiedades para el individuo Pasta_primavera
		pasta_primavera.setPropertyValue(descripcionp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Pasta_primavera")));
		pasta_primavera.setPropertyValue(paisp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Pasta_primavera")));
		pasta_primavera.setPropertyValue(ingrediente,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("ingredient","Pasta_primavera")));
		pasta_primavera.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("fname","Pasta_primavera")));

		
		//propiedades para el individuo Bigoli
		bigoli.setPropertyValue(descripcionp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("descripcion","Bigoli")));
		bigoli.setPropertyValue(paisp,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("pais","Bigoli")));
		bigoli.setPropertyValue(ingrediente,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("ingredient","Bigoli")));
		bigoli.setPropertyValue(nombre,modelo.createTypedLiteral(consultaDBpedia.GetDatoDbpedia("fname","Bigoli")));

	}	 
}
