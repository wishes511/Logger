/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import Modelo.ObjUsuario;
import Persistencia.Conexion;
import Persistencia.ConexionServer;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GATEWAY1-
 */
public class Test {
    public static void main (String [] args){
       
        try {
            ObjUsuario u = new ObjUsuario();
            Conexion c = new Conexion();
            System.out.println(u.checkuser(3));
            System.out.println(u.checkstate(3, c.getconexionC()));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
}
