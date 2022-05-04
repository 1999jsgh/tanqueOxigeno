package com.example.tanqueoxigeno.tamano;

public class Tamano {
    private int tamano_id;
    private String tipoTamano;

    public int getTamano_id() {
        return tamano_id;
    }

    public void setTamano_id(int tamano_id) {
        this.tamano_id = tamano_id;
    }

    public String getTipoTamano() {
        return tipoTamano;
    }

    public void setTipoTamano(String tipoTamano) {
        this.tipoTamano = tipoTamano;
    }

    @Override
    public String toString() {
        return "Tamano{" +
                "tamano_id=" + tamano_id +
                ", tipoTamano='" + tipoTamano + '\'' +
                '}';
    }
}
