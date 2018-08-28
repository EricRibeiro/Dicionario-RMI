package com.ericribeiro.server.model;

import java.io.Serializable;
import java.util.Objects;

public class Palavra implements Serializable {

    private String palavra;
    private String significado;

    public Palavra() { }

    public Palavra(String palavra) {
        this.palavra = palavra;
    }

    public Palavra(String palavra, String significado) {
        this.palavra = palavra;
        this.significado = significado;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Palavra palavra1 = (Palavra) o;
        return Objects.equals(getPalavra(), palavra1.getPalavra());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPalavra());
    }
}
