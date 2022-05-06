package com.example.tanqueoxigeno.venta;

public class Venta {
    private int venta_id;
    private int botella_id;
    private String monto;
    private String fecha;

    public int getVenta_id() {
        return venta_id;
    }

    public void setVenta_id(int venta_id) {
        this.venta_id = venta_id;
    }

    public int getBotella_id() {
        return botella_id;
    }

    public void setBotella_id(int botella_id) {
        this.botella_id = botella_id;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "venta_id=" + venta_id +
                ", botella_id=" + botella_id +
                ", monto='" + monto + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
