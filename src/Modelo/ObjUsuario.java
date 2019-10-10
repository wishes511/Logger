/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Persistencia.ConexionServer;
import java.io.IOException;
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
public class ObjUsuario {
    ConexionServer s = new ConexionServer();
    PreparedStatement st;
    ResultSet rs = null;
    Statement sta = null;

    public boolean checkuser(int usuario){
        boolean resp = true; 
        try {
            st = s.getconexionS().prepareStatement("SELECT usuario from usuarios where usuario="+usuario);
            rs = st.executeQuery();
            while (rs.next()) {
                resp=false;
            }
            rs.close();
            st.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }
}
