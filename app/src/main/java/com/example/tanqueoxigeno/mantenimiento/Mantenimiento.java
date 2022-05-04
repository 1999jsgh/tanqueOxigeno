package com.example.tanqueoxigeno.mantenimiento;

public class Mantenimiento {
    private int botella_id;
    private int tipoMantenimiento_id;
    private String fechaMantenimiento;
    private String monto;

    public int getBotella_id() {
        return botella_id;
    }

    public void setBotella_id(int botella_id) {
        this.botella_id = botella_id;
    }

    public int getTipoMantenimiento_id() {
        return tipoMantenimiento_id;
    }

    public void setTipoMantenimiento_id(int tipoMantenimiento_id) {
        this.tipoMantenimiento_id = tipoMantenimiento_id;
    }

    public String getFechaMantenimiento() {
        return fechaMantenimiento;
    }

    public void setFechaMantenimiento(String fechaMantenimiento) {
        this.fechaMantenimiento = fechaMantenimiento;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Mantenimiento{" +
                "botella_id=" + botella_id +
                ", tipoMantenimiento_id=" + tipoMantenimiento_id +
                ", fechaMantenimiento='" + fechaMantenimiento + '\'' +
                ", monto='" + monto + '\'' +
                '}';
    }
}
