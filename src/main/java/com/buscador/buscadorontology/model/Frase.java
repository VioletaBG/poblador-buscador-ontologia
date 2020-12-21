package com.buscador.buscadorontology.model;

public class Frase {
    private String frase;

    public Frase(String frase) {
        this.setFrase(frase);
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public Frase() {
        frase="";
    }
}
