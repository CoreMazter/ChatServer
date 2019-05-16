/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        ResultSet rs;
        int id_u = 0;
        try 
        {
            PreparedStatement statement = con.prepareStatement("INSERT INTO usuario(nickname, password) VALUES ('"+nick+"', '"+pass+"')");
            statement.executeUpdate();
            statement = con.prepareStatement("SELECT id_u FROM usuario");
            rs = statement.executeQuery();
            while(rs.next())
            {
                id_u = rs.getInt("id_u");
            }
            return id_u;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public ArrayList<Pertenencia> selectAllPertenenciasFromUsuario(int id_u)
    {
        ResultSet rs;
        ArrayList<Pertenencia> res = new ArrayList();
        try 
        {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM pertenece WHERE usuario = "+id_u);    
            rs = statement.executeQuery();
            while(rs.next())
            {
                Pertenencia pertenencia = new Pertenencia();
                pertenencia.setId_p(rs.getInt("id_p"));
                pertenencia.setEstado(rs.getString("estado"));
                pertenencia.setUsuario(rs.getInt("usuario"));
                pertenencia.setGrupo(rs.getInt("grupo"));
                res.add(pertenencia);
            }
            return res;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Pertenencia> selectAllPertenenciasFromGrupo(int id_g)
    {
        ResultSet rs;
        ArrayList<Pertenencia> res = new ArrayList();
        try 
        {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM pertenece WHERE grupo = "+id_g);    
            rs = statement.executeQuery();
            while(rs.next())
            {
                Pertenencia pertenencia = new Pertenencia();
                pertenencia.setId_p(rs.getInt("id_p"));
                pertenencia.setEstado(rs.getString("estado"));
                pertenencia.setUsuario(rs.getInt("usuario"));
                pertenencia.setGrupo(rs.getInt("grupo"));
                res.add(pertenencia);
            }
            return res;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int insertPertenencia(int  usuario, int grupo)
    {
        ResultSet rs;
        int id_p = 0;
        try 
        {
            PreparedStatement statement = con.prepareStatement("INSERT INTO pertenece(estado, usuario, grupo) VALUES ('Invitado',"+usuario+", "+grupo+")");
            statement.executeUpdate();
            statement = con.prepareStatement("SELECT id_p FROM pertenece");
            rs = statement.executeQuery();
            while (rs.next())
            {
                id_p = rs.getInt("id_p");
            }
            return id_p;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int updatePertenencia(int id_p)
    {
        try 
        {
            PreparedStatement statement = con.prepareStatement("UPDATE pertenece SET estado = 'Aceptado' WHERE id_p = "+id_p);
            return statement.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
     
    public int deletePertenencia(int id_p)
    {
        try 
        {
            PreparedStatement statement = con.prepareStatement("DELETE FROM pertenece WHERE id_p = "+id_p);
            return statement.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
