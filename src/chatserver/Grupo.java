package chatserver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kevin_Medina
 */
public class Grupo {
    protected int id_g;
    protected String nombre;

    public void setId_g(int id_g) {
        this.id_g = id_g;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_g() {
        return id_g;
    }

    public String getNombre() {
        return nombre;
    }

    public Grupo(int id_g, String nombre) {
        this.id_g = id_g;
        this.nombre = nombre;
    }
    
    public Grupo() {
        this.id_g = 0;
        this.nombre = "";
    }
}