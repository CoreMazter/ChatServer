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
    private int id;

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getNickname() 
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private String nickname;
    private String password;
    
    public Usuario(){}
    
    public Usuario(int id, String nickname, String password)
    {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }
}
