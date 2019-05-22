/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

/**
 *
 * @author lghhs
 */
public class Pertenencia 
{
    private int id_p;
    private String estado;
    private int usuario;
    private int grupo;

    /**
     * Obtiene el id de la pertenencia en el grupo
     * @return 
     */
    public int getId_p() {
        return id_p;
    }

    /**
     * Coloca el id de la pertenencia en el grupo
     * @param id_p 
     */
    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    /**
     * Obtiene el estado de la pertenencia o invitación en el grupo
     * @return 
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Coloca un estado de pertenencia o invitación en el grupo
     * @param estado 
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el usuario que pertenece o es invitado al grupo
     * @return 
     */
    public int getUsuario() {
        return usuario;
    }

    /**
     * Coloca el usuario que pertenece o es invitado al grupo
     * @param usuario 
     */
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el grupo al que pertenece o se invita el usuario
     * @return 
     */
    public int getGrupo() {
        return grupo;
    }

    /**
     * Coloca el grupo al que pertenece o se invita al usaurio
     * @param grupo 
     */
    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }
    
    /**
     * Constructor que inicializa los valores en 0 y vacío
     */
    public Pertenencia()
    {
        id_p = 0;
        estado = "";
        usuario = 0;
        grupo = 0;
    }
    
    /**
     * Constructor que recibe la id, el estado,
     * el usuario y el grupo al que pertenece
     * @param id_p
     * @param estado
     * @param usuario
     * @param grupo 
     */
    public Pertenencia(int id_p, String estado, int usuario, int grupo)
    {
        this.id_p = id_p;
        this.estado = estado;
        this.usuario = usuario;
        this.grupo = grupo;
    }
}