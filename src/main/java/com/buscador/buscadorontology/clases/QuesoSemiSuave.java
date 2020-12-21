package com.buscador.buscadorontology.clases;

public class QuesoSemiSuave extends Queso{
	private String pasteurizado;
	private String textura;
	public QuesoSemiSuave(String id,String d, String p, String n, String t, String o,String pa) {
		 super(id,d,p,n,o);  
		 pasteurizado=pa; 
		 textura =t;
	}
	
	
	
	public String getPasteurizado() {
		return pasteurizado;
	}
    


	public void setPasteurizado(String pasteurizado) {
		this.pasteurizado = pasteurizado;
	}

   

	public String getTextura() {
		return textura;
	}



	public void setTextura(String textura) {
		this.textura = textura;
	}



	@Override
    public String toString() {
        return "Queso "
                + getNombre()
                + ": "
                + getDescripcion()
                + " ("
                + getOrigen()
                + ")";
    }
	
}
