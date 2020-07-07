/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;
import java.sql.*;

/**
 *
 * @author Maestro
 */
public class Conexion {
    
    public void cerrarConexion(Connection con){
        try{
            con.close();
        }
        catch(SQLException e){
            System.out.println("ERROR "+e.getMessage());
        }
    }
    
    public Connection abrirCon(){
       
            String connectString = "jdbc:postgresql://localhost:5432/Zelda";
            //String connectString = "jdbc:postgresql://localhost:5432/poo16";
            String user = "postgres";
            //String user = "postgres";
            String password = "root";
            //String password = "root";
            Connection con = null;
        try {
            con = DriverManager.getConnection(connectString, user, password);
            
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        finally{
            if(con!=null)
                return con;
            else 
                return null;
        }
        
    
        
    }
}
