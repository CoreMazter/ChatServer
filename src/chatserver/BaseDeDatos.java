/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author lghhs
 */
public class BaseDeDatos
{
    private Connection con;
    public BaseDeDatos()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/cocochat", "root", ""); 
        } 
        catch (ClassNotFoundException | SQLException ex) 
        {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Usuario selectUser(String nick)
    {
        ResultSet rs;
        Usuario usuario = new Usuario(0, "", "");
        try 
        {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM usuario WHERE nickname='"+nick+"'");    
            rs = statement.executeQuery();
            while(rs.next())
            {
                usuario.setId(rs.getInt("id_u"));
                usuario.setNickname(rs.getString("nickname"));
                usuario.setPassword(rs.getString("password").replaceAll("[^\\x20-\\x7e]", "").replaceAll("\\p{C}", ""));
            }
            return usuario;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
    public ArrayList<Usuario> selectAllUsers()
    {
        ResultSet rs;
        ArrayList<Usuario> res = new ArrayList();
        try 
        {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM usuario");    
            rs = statement.executeQuery();
            while(rs.next())
            {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_u"));
                usuario.setNickname(rs.getString("nickname"));
                usuario.setPassword(rs.getString("password"));
                res.add(usuario);
            }
            return res;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int insertUser (String nick, String pass)
    {
        try 
        {
            PreparedStatement statement = con.prepareStatement("INSERT INTO usuario(nickname, password) VALUES ('"+nick+"', '"+pass+"')");
            return statement.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
