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

    /**
     * Coloca la id del grupo
     * @param id_g 
     */
    public void setId_g(int id_g) {
        this.id_g = id_g;
    }

    /**
     * Coloca el nombre del grupo
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la id del grupo
     * @return 
     */
    public int getId_g() {
        return id_g;
    }

    /**
     * Obtiene el nombre del grupo
     * @return 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Constrctor que recibe la id y el nombre del grupo para crear el objeto
     * @param id_g
     * @param nombre 
     */
    public Grupo(int id_g, String nombre) {
        this.id_g = id_g;
        this.nombre = nombre;
    }
   
    /**
     * Constructor que iniciaiza los valores a 0 y vacío
     */
    public Grupo() {
        this.id_g = 0;
        this.nombre = "";
    }
}