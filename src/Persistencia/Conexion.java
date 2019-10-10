/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GATEWAY1-
 */
public class Conexion {
    //db clientes
    FileWriter fichero = null;
    Connection connect;
    String url = "C:\\Win\\af\\extra\\datas.db";
    File f = new File(url);
    //db server
    public Connection getconexionC() throws ClassNotFoundException, IOException, SQLException {
            Class.forName("org.sqlite.JDBC");
            if (!f.exists()) {
                fichero = new FileWriter(f);
                PrintWriter pw = new PrintWriter(fichero);
                fichero.close();
                connect = DriverManager.getConnection("jdbc:sqlite:" + url);
                Statement s = connect.createStatement();
                s.executeUpdate("create table movimientos(id integer primary key autoincrement, usuario int not null, palabra varchar(300),"
                        + " fecha date, hora varchar(10), imagen varchar(150),stat char(1))");
                s.executeUpdate("create table estado(id integer primary key, version varchar(5),sincronizacion char(1),"
                        + "sincronizacion_unit char(1),autocaptura varchar(50),actualizacion_server int, repeticiones_salto int, "
                        + "tiempo_espera_salto int )");
                System.out.println("Conectado");
            }else{
                connect = DriverManager.getConnection("jdbc:sqlite:" + url);
                System.out.println("Conectado");
            }
        return connect;
    }
    public void cerrarbd() throws SQLException{
        connect.close();
    }
}
