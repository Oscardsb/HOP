/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import Clases.ClsEncriptar;
import db.Conexion;
import clases.ClsGlobales;
import entidades.Usuario;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author RYZEN5
 */
public class UsuarioDAO {
    public static boolean insertar(Usuario usuario){
        
        String sql = "INSERT INTO usuario(correo,clave,salt,username,nombreC) values (?,?,?,?,?)";
        
        try(
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ){
         
            ps.setString(1, usuario.getCorreo());
            ps.setString(2, usuario.getClave());
            ps.setString(3, usuario.getSalt());
            ps.setString(4, usuario.getUsername());
            ps.setString(5, usuario.getNombreC());
            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se ha registrado con exito","Exito",JOptionPane.INFORMATION_MESSAGE);
            
            return true;   
        }catch(SQLException e){
            
            String mensaje;
            
            switch (e.getErrorCode()) {
                case 1062:
                    mensaje = "El correo electronico ya se encuentra registrado";
                    break;
                default:
                   mensaje = e.getMessage();
            }
            
            
            JOptionPane.showMessageDialog(null, mensaje,"Error",JOptionPane.ERROR_MESSAGE);
            
            return false;
        }
        
    }
    
    public static boolean contraseñaCambio(String correo, String clave){
        String sql = "UPDATE usuario SET clave = ? WHERE correo = ?";
        
        try(
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
            ){
                ps.setString(1, clave);
                ps.setString(2, correo);
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Cambio realizado con exito","Exito",JOptionPane.INFORMATION_MESSAGE);
                
             return true;   
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            
            return false;
        }
    }
    
    
    public static boolean validarAcceso(String correo, String clave){
        String sql = "SELECT * FROM usuario WHERE correo = ?";
        
        try(
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
            ){
                ps.setString(1, correo);
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    //System.out.println(rs.getString("salt"));
                    
                    String salt = rs.getString("salt");
                    String claveEncriptada = ClsEncriptar.encriptaSHA256(clave, salt);
                    
                    if (!claveEncriptada.equals(rs.getString("clave"))) {
                        throw new SQLException("La clave es incorrecta", "LOGIN_PASS", 2);
                    }
                    
                    if (!rs.getBoolean("activo")) {
                        throw new SQLException("Su usuario esta inactivo", "LOGIN_ACT",3);
                    }
                     
                    
                    
            }else{
                    throw new SQLException("El correo no existe", "LOGIN_EMAIL", 1);
                }
                
                ClsGlobales.usesion.setCorreo(correo);
                
                
                
                
                     return true;
                
            
           
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            
            return false;
        }
    }
    
    public static boolean validarAccesoC(String correo){
        String sql = "SELECT * FROM usuario WHERE correo = ?";
        
        try(
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
            ){
                ps.setString(1, correo);
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    //System.out.println(rs.getString("salt"));
                    
                    
                    
                    if (!rs.getBoolean("activo")) {
                        throw new SQLException("Su usuario esta inactivo", "LOGIN_ACT",3);
                    }
                     
                    
                    
            }else{
                    throw new SQLException("El correo no existe", "LOGIN_EMAIL", 1);
                }
                
                ClsGlobales.usesion.setCorreo(correo);
                
                
                
                
                     return true;
                
            
           
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            
            return false;
        }
    }
}
