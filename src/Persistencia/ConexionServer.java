/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author GATEWAY1-
 */
public class ConexionServer {
    //db clientes
    Connection connect;
    private final String URL = "jdbc:mysql://localhost:3306/soporte";
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

}
