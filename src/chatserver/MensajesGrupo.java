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
public class MensajesGrupo {
    private int id_mg;
    private int usuario;
    private int grupo;
    private Timestamp timestamp;
    
    public MensajesGrupo ()
    {
        id_mg = 0;
        usuario = 0;
        grupo = 0;
        timestamp = null;
    }
    
    public MensajesGrupo(int id_mg, int usuario, int grupo, Timestamp timestamp)
    {
        this.id_mg = id_mg;
        this.usuario = usuario;
        this.grupo = grupo;
        this.timestamp = timestamp;
    }

    public int getId_mg() {
        return id_mg;
    }

    public void setId_mg(int id_mg) {
        this.id_mg = id_mg;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
