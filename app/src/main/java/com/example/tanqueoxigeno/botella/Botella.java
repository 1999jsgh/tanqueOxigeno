package com.example.tanqueoxigeno.botella;

public class Botella {
    private int botella_id;
    private int cliente_id;
    private int tamano_id;
    private int estado_id;
    private String codigo;
    private String valorManometro;
    private String valorRecarga;
    private String fechaRecarga;
    private String fechaVencimiento;

    public int getBotella_id() {
        return botella_id;
    }

    public void setBotella_id(int botella_id) {
        this.botella_id = botella_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getTamano_id() {
        return tamano_id;
    }

    public void setTamano_id(int tamano_id) {
        this.tamano_id = tamano_id;
    }

    public int getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getValorManometro() {
        return valorManometro;
    }

    public void setValorManometro(String valorManometro) {
        this.valorManometro = valorManometro;
    }

    public String getValorRecarga() {
        return valorRecarga;
    }

    public void setValorRecarga(String valorRecarga) {
        this.valorRecarga = valorRecarga;
    }

    public String getFechaRecarga() {
        return fechaRecarga;
    }

    public void setFechaRecarga(String fechaRecarga) {
        this.fechaRecarga = fechaRecarga;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Botella{" +
                "botella_id=" + botella_id +
                ", cliente_id=" + cliente_id +
                ", tamano_id=" + tamano_id +
                ", estado_id=" + estado_id +
                ", codigo='" + codigo + '\'' +
                ", valorManometro='" + valorManometro + '\'' +
                ", valorRecarga='" + valorRecarga + '\'' +
                ", fechaRecarga='" + fechaRecarga + '\'' +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                '}';
    }
}
