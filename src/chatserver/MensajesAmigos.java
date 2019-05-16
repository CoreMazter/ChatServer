/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.sql.Timestamp;

/**
 *
 * @author lghhs
 */
public class MensajesAmigos {
    private int id_ma;
    private int usuario;
    private int amistad;
    private Timestamp timestamp;
    private String mensaje;
    
    /**
     * Constructor vacío que inicializa todas las variables en 0 y null
     */
    public MensajesAmigos ()
    {
        id_ma = 0;
        usuario = 0;
        amistad = 0;
        timestamp = null;
    }
    
    /**
     * Constructor que recibe los valores necesarios para crear un mensaje
     * @param id_ma
     * @param usuario
     * @param amistad
     * @param timestamp 
     */
    public MensajesAmigos(int id_ma, int usuario, int amistad, Timestamp timestamp, String mensaje)
    {
        this.id_ma = id_ma;
        this.usuario = usuario;
        this.amistad = amistad;
        this.timestamp = timestamp;
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el id del mensaje
     * @return 
     */
    public int getId_ma() {
        return id_ma;
    }

    /**
     * Coloca un id de mensaje
     * @param id_ma 
     */
    public void setId_ma(int id_ma) {
        this.id_ma = id_ma;
    }

    /**
     * Obtiene el id del usaurio del mensaje
     * @return 
     */
    public int getUsuario() {
        return usuario;
    }

    /**
     * Coloca el id del usuario del mensaje
     * @param usuario 
     */
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el id de la relación de amistad en la cual está el mensaje
     * @return 
     */
    public int getAmistad() {
        return amistad;
    }

    /**
     * Coloca el id de la relación de amistad
     * @param amistad 
     */
    public void setAmistad(int amistad) {
        this.amistad = amistad;
    }

    /**
     * Obtiene la fecha y hora del mensaje
     * @return 
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Coloca la fecha y hora del mensaje
     * @param timestamp 
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Obtiene el mensaje
     * @return 
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Coloca el mensaje
     * @param mensaje 
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
