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
public class Usuario 
{
    /**
     * id del usuario
     * Nickname del usuario
     * Contraseña del usuario
     */
    private int id;
    private String nickname;
    public String password;

    /**
     * Obtiene la id del usuario
     * @return 
     */
    public int getId() 
    {
        return id;
    }

    /**
     * Coloca la id del usuario
     * @param id 
     */
    public void setId(int id) 
    {
        this.id = id;
    }

    /**
     * Obtiene el nickname del usuario
     * @return 
     */
    public String getNickname() 
    {
        return nickname;
    }

    /**
     * Coloca el nickname del usuario
     * @param nickname 
     */
    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    /**
     * Obtiene la contraseña del usuario
     * @return 
     */
    public String getPassword() 
    {
        return password;
    }

    
    /**
     * Coloca la contraseña del usuario
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Constructor vacío que inicializa todo a 0, o vacío
     */
    public Usuario(){
        this.id=0;
        this.nickname="";
        this.password="";
    }
    
    /**
     * Constructor que recibe los tres parámetros del usuario
     * @param id
     * @param nickname
     * @param password 
     */
    public Usuario(int id, String nickname, String password)
    {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }
}