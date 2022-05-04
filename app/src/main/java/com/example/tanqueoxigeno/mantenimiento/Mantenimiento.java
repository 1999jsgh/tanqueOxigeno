package com.example.tanqueoxigeno.mantenimiento;

public class Mantenimiento {
    private int botella_id;
    private String tipoMantenimiento;
    private String fechaMantenimiento;
    private String monto;

    public int getBotella_id() {
        return botella_id;
    }

    public void setBotella_id(int botella_id) {
        this.botella_id = botella_id;
    }

    public String getTipoMantenimiento() {
        return tipoMantenimiento;
    }

    public void setTipoMantenimiento(String tipoMantenimiento) {
        this.tipoMantenimiento = tipoMantenimiento;
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
                ", tipoMantenimiento_id=" + tipoMantenimiento +
                ", fechaMantenimiento='" + fechaMantenimiento + '\'' +
                ", monto='" + monto + '\'' +
                '}';
    }
}
