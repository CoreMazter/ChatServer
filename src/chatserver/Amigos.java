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

    public void setId(int id) {
        this.id = id;
    }

    public void setId_u1(int id_u1) {
        this.id_u1 = id_u1;
    }

    public void setId_u2(int id_u2) {
        this.id_u2 = id_u2;
    }

    public void setAlias1(String alias1) {
        this.alias1 = alias1;
    }

    public void setAlias2(String alias2) {
        this.alias2 = alias2;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public int getId_u1() {
        return id_u1;
    }

    public int getId_u2() {
        return id_u2;
    }

    public String getAlias1() {
        return alias1;
    }

    public String getAlias2() {
        return alias2;
    }

    public String getEstado() {
        return estado;
    }
    
    public Amigos() {
        this.id = 0;
        this.id_u1 = 0;
        this.id_u2 = 0;
        this.alias1 = "";
        this.alias2 = "";
    }

    public Amigos(int id, int id_u1, int id_u2, String alias1, String alias2) {
        this.id = id;
        this.id_u1 = id_u1;
        this.id_u2 = id_u2;
        this.alias1 = alias1;
        this.alias2 = alias2;
    }
    
    
}
