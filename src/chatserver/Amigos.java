/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

/**
 *
 * @author Kevin_Medina
 */
public class Amigos {
    public int id, id_u1, id_u2;
    public String alias1, alias2, estado;

    /**
     * Coloca el id de la amistad
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Coloca el id de uno de los amigos
     * @param id_u1 
     */
    public void setId_u1(int id_u1) {
        this.id_u1 = id_u1;
    }

    /**
     * Coloca el id del otro amigo
     * @param id_u2 
     */
    public void setId_u2(int id_u2) {
        this.id_u2 = id_u2;
    }

    /**
     * Coloca el alias de uno de los amigos
     * @param alias1 
     */
    public void setAlias1(String alias1) {
        this.alias1 = alias1;
    }

    /**
     * Coloca el alias del segundo de los amigos
     * @param alias2 
     */
    public void setAlias2(String alias2) {
        this.alias2 = alias2;
    }

    /**
     * Pone el estado de la amistad que puede ser "invitado" o "aceptado"
     * @param estado 
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el id de la amistad
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el id de uno de los amigos
     * @return 
     */
    public int getId_u1() {
        return id_u1;
    }

    /**
     * Obtiene el id del segundo amigo
     * @return 
     */
    public int getId_u2() {
        return id_u2;
    }

    /**
     * Obtiene el alias de el primero de los amigos
     * @return 
     */
    public String getAlias1() {
        return alias1;
    }

    /**
     * Obtiene el alias del segundo de los amigos
     * @return 
     */
    public String getAlias2() {
        return alias2;
    }

    /**
     * Obtiene el estado de la amistad
     * @return 
     */
    public String getEstado() {
        return estado;
    }
    
    /**
     * COnstructor vacío que inicializa los valores a 0 o vacío
     */
    public Amigos() {
        this.id = 0;
        this.id_u1 = 0;
        this.id_u2 = 0;
        this.alias1 = "";
        this.alias2 = "";
    }

    /**
     * Constructor que recibe todos los parámetros de una amistad para construir el objeto
     * @param id
     * @param id_u1
     * @param id_u2
     * @param alias1
     * @param alias2 
     */
    public Amigos(int id, int id_u1, int id_u2, String alias1, String alias2) {
        this.id = id;
        this.id_u1 = id_u1;
        this.id_u2 = id_u2;
        this.alias1 = alias1;
        this.alias2 = alias2;
    }
    
    
}
