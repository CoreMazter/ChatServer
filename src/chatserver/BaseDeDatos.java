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
    
    /*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Amigos     /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    
    public Amigos selectAmigos(int id_u1, int id_u2) {
        Amigos amigos = new Amigos();
        ResultSet rs;

        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM amigos "
                                                        + "WHERE id_u1 = " + id_u1 + "AND id_u2 = " + id_u2
                                                        + "OR id_u1 = " + id_u2 + "AND id_u2 = " + id_u1);
            rs = statement.executeQuery();
            while(rs.next()) {
                amigos.setId(rs.getInt("id_a"));
                amigos.setEstado(rs.getString("estado"));
                amigos.setAlias1(rs.getString("alias1"));
                amigos.setAlias2(rs.getString("alias2"));
                amigos.setId_u1(rs.getInt("id_u1"));
                amigos.setId_u2(rs.getInt("id_u2"));
            }
            return amigos;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public ArrayList<Amigos> selectAllAmigos(int id_u1) {
        ResultSet rs;
        Amigos amigos = new Amigos();
        ArrayList<Amigos> result = new ArrayList();
        
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM amigos "
                                                        + "WHERE id_u1 = " + id_u1 
                                                        + "OR id_u2 = " + id_u1);
            rs = stmt.executeQuery();
            while(rs.next()) {
                amigos.setId(rs.getInt("id_a"));
                amigos.setEstado(rs.getString("estado"));
                amigos.setAlias1(rs.getString("alias1"));
                amigos.setAlias2(rs.getString("alias2"));
                amigos.setId_u1(rs.getInt("id_u1"));
                amigos.setId_u2(rs.getInt("id_u2"));
                result.add(amigos);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int insertAmigos(String alias1, String alias2, int id_u1, int id_u2) {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO amigos"
                                                        + "(estado, alias1, alias2, id_u1, id_u2)"
                                                        + "VALUES('Pendiente', ?, ?, ?, ?)");
            stmt.setInt(1, id_u1);
            stmt.setInt(2, id_u2);
            stmt.setString(3, alias1);
            stmt.setString(4, alias2);
            
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int updateAmigos(int id_a) {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE amigos SET estado = 'Aceptado' "
                                                        + "WHERE id = " + id_a);
            
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int deleteAmigos(int id_u1, int id_u2) {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM amigos WHERE "
                                                        + "id_u1 = " + id_u1 + "AND id_u2 =" + id_u2 
                                                        + "OR id_u1 = " + id_u2 + "AND id_u2 =" + id_u1);
            
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Grupo       /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    
    public int insertGrupo(String nombre){
         Grupo grupo = new Grupo();
         ResultSet rs;
         
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO grupo (nombre) VALUES (?)");
            stmt.setString(1, nombre);
            
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return 0;
    }
    
    public Grupo selectGrupo(int id_g){
        Grupo grupo = new Grupo();
        ResultSet rs;
        
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM grupo WHERE id = " + id_g);
            
            rs = stmt.executeQuery();
            while(rs.next()) {
                grupo.setId_g(rs.getInt("id_g"));
                grupo.setNombre(rs.getString("nombre"));
            }
            return grupo;
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Grupo> selectAllGrupo(int usuario) {
        ArrayList<Grupo> result = new ArrayList();
        Grupo grupo = new Grupo();
        ResultSet rs;
        
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT id_g, nombre FROM grupo "
                                                        + "JOIN pertenece WHERE usuario = " + usuario
                                                        + "AND id_g = grupo");
            
            rs = stmt.executeQuery();
            while(rs.next()){
                grupo.setId_g(rs.getInt("id_g"));
                grupo.setNombre(rs.getString("nombre"));
                result.add(grupo);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public int updateGrupo(int id_g, String nombre) {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE grupo SET nombre = '" 
                                                        + nombre + "' WHERE id_g = " + id_g);
            
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    public int deleteGrupo(int id_g) {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM grupo WHERE id_g = " + id_g);
            
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
}
