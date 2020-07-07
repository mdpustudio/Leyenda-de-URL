/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import game.Player;

/**
 *
 * @author Maestro
 */
public class ZQuery {
    private Connection conex;
    private Conexion conexion;
    
    public ZQuery(){
        conexion = new Conexion();
    }
    
    public boolean addScore(int id, String player, String tiempo, int score){
        
        try {
            conex = conexion.abrirCon();
            //llenando tabla usuario
            String query = "INSERT INTO usuario (idUsuario,nomUsuario,tiempoUsuario) VALUES (?,?,?) ";
            PreparedStatement pstmt = conex.prepareStatement(query);
            pstmt.setInt(1,id);
            pstmt.setString(2, player);
            pstmt.setString(3, tiempo);
            pstmt.executeUpdate();
            //llenando tabla puntaje
            String Squery = "INSERT INTO puntaje (idUsuario,Puntaje) VALUES(?,?)";
            PreparedStatement Spstmt = conex.prepareStatement(Squery);
            Spstmt.setInt(1, id);
            Spstmt.setInt(2, score);
            
            Spstmt.executeUpdate();
            conexion.cerrarConexion(conex);
            
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return false;
        }
        return true;
    } 
    
   public int NewId(){
       int id = 0;
       try{
           conex = conexion.abrirCon();
           String query = "SELECT MAX(idUsuario) FROM usuario";
           PreparedStatement pstmt = conex.prepareStatement(query);
           ResultSet rs = pstmt.executeQuery();
           
           if(rs.next())
                id = rs.getInt(1)+1;
          
           pstmt.close();
           conexion.cerrarConexion(conex);
           
           
       }
       catch(SQLException err){
           System.out.println(err.getMessage());
       }
       finally{
           return id;
       }   
   }
   public int count(){
       int cuenta = 0;
       try{
           conex = conexion.abrirCon();
           String query = "SELECT count(idUsuario) FROM usuario";
           PreparedStatement pstmt = conex.prepareStatement(query);
           ResultSet rs = pstmt.executeQuery();
           
           if(rs.next())
                cuenta = rs.getInt(1);
           else
               cuenta = 0;
           pstmt.close();
           conexion.cerrarConexion(conex);
           
           
       }
       catch(SQLException err){
           System.out.println(err.getMessage());
       }
       finally{
           return cuenta;
       }   
   }
   
   public boolean delete(){
       try{
           conex = conexion.abrirCon();
           Statement stmt = conex.createStatement();
           stmt.execute("DELETE FROM usuario Where idUsuario = (SELECT puntaje.idUsuario FROM puntaje INNER JOIN (SELECT puntaje.idUsuario,Min(puntaje) punt FROM Puntaje GROUP BY idUsuario ORDER BY punt ASC limit 1) tabla2 ON Puntaje.idUsuario = tabla2.idUsuario)");
           stmt.close();
       }
       catch(SQLException sq){
           System.out.println("ERROR: " + sq.getMessage());
           return false;
       }
       return true;
   }
   
   public HashMap <Integer,Player> mostrar(){
       HashMap <Integer,Player> hm = new HashMap();
      
       try{
           conex = conexion.abrirCon();
           Statement stmt = conex.createStatement();
           ResultSet rs = stmt.executeQuery("SELECT  usuario.idUsuario,nomUsuario,Puntaje,tiempoUsuario FROM usuario,puntaje Where usuario.idUsuario = puntaje.idUsuario Order by Puntaje desc");
           
           while(rs.next()){
               Player p = new Player(rs.getString("nomUsuario"),rs.getInt("puntaje"),rs.getString("tiempoUsuario"));
               hm.put(rs.getInt("idUsuario"), p);
           }
           stmt.close();
           conexion.cerrarConexion(conex);
       }
       catch(SQLException ex){
           System.out.println("ERROR: " + ex.getMessage());
       }
       return hm;
       
   }
   
   public Player getLastPlayer(){
       Player player = new Player();
       
       try{
           conex = conexion.abrirCon();
           String query = "Select Usuario.idUsuario,nomUsuario,tiempoUsuario,puntaje from Usuario inner join puntaje on Usuario.idUsuario = puntaje.idUsuario order by Usuario.idUsuario DESC";
           PreparedStatement pstmt = conex.prepareStatement(query);
           ResultSet rs = pstmt.executeQuery();
           
            if(rs.next()){
                player.setAlias(rs.getString("nomUsuario"));
                player.setScore(rs.getInt("puntaje"));
                player.setTiempo(rs.getString("tiempoUsuario"));
            }
           pstmt.close();
           conexion.cerrarConexion(conex);   
           
       }
       catch(SQLException err){
           System.out.println(err.getMessage());
           
       }
       return player;
   }
   
    public boolean isHighscore(Player player){
        int Score = 0;
       try{
           conex = conexion.abrirCon();
           String query = "SELECT max(puntaje) From Puntaje";
           PreparedStatement pstmt = conex.prepareStatement(query);
           ResultSet rs = pstmt.executeQuery();
          
           if(rs.next()){
               Score = rs.getInt(1);
           }
                    
           
       }catch(SQLException err){
           System.out.println(err.getMessage());
       }
       if(player.getScore() == Score){
               return true;
           }
           else 
               return false;  
    }
    
    public boolean isLowScore(Player player){
        int Score = 0;
        try{
            conex = conexion.abrirCon();
            String query = "SELECT min(puntaje) from Puntaje";
            PreparedStatement pstmt = conex.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                Score = rs.getInt(1);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        if(player.getScore() < Score)
            return true;
        else 
            return false;
    }
   
}
