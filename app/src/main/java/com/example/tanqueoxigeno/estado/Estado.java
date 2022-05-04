package com.example.tanqueoxigeno.estado;

public class Estado {
    private int estado_id;
    private String tipoEstado;

    public int getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "estado_id=" + estado_id +
                ", tipoEstado='" + tipoEstado + '\'' +
                '}';
    }
}
