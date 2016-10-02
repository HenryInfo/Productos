package com.unc.hbs.productos.Model;

/**
 * Created by hbs on 1/10/16.
 */

public class Producto {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public int getIdimage() {
        return idimage;
    }

    public void setIdimage(int idimage) {
        this.idimage = idimage;
    }

    private int id;
    private String nombre;
    private double precio;
    private int idimage;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private boolean checked;

    public Producto(int id, String nom, double precio, int idmagen, boolean checked)
    {
        this.id=id;
        this.nombre=nom;
        this.precio=precio;
        this.checked=checked;
        this.idimage=idmagen;
    }


}
