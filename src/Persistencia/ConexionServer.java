/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GATEWAY1-
 */
public class ConexionServer {
    //db clientes
    Connection connect;
    private final String URL = "jdbc:mysql://localhost:3306/logger";
    private final String drive = "com.mysql.jdbc.Driver";
    //db server
    public Connection getconexionS() throws ClassNotFoundException, IOException, SQLException {
        Class.forName(drive);
          connect = DriverManager.getConnection(URL, "root", "416cronos");
          System.out.println("Conectado a server");
          return connect;
    }
    public void cerrarserver() throws SQLException{
        connect.close();
    }
    public String ipserver(){
        String ip = "";
        try {
            PreparedStatement st;
            ResultSet rs;
            getconexionS();
            st = connect.prepareStatement("SELECT server from servidor where stat='1'");
            rs = st.executeQuery();
            while(rs.next()){
                ip=rs.getString("server");
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConexionServer.class.getName()).log(Level.SEVERE, null, ex);
        }
       return ip; 
    }
    
}
