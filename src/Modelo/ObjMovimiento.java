/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Persistencia.ConexionServer;
import key.EnviarArchivo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GATEWAY1-
 */
public class ObjMovimiento {

    ConexionServer s = new ConexionServer();
    PreparedStatement st;
    ResultSet rs = null;
    Statement sta = null;

    public boolean Nuevomov(Movimiento m, Connection dbLite) {
        try {
            //obtener todo de la tabla datos de un usuario.
            dbLite.setAutoCommit(false);
            //Insertar nuevos datos hacia la db local
            String sql = "insert into movimientos(usuario,palabra,fecha,hora,imagen,stat) "
                    + "values(" + m.getUsuario() + ",'" + m.getPalabra() + "','" + m.getFecha() + "','" + m.getHora() + "','" + m.getImagen() + "','1')";
            System.out.println(sql);
            st = dbLite.prepareStatement(sql);
            st.executeUpdate();
            dbLite.commit();
            return true;
        } catch (Exception ex) {
            try {
                Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex);
                dbLite.rollback();
            } catch (Exception ex1) {
                Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean sinchregs(Connection dbLite,String ip) {
        Connection aa = null;
        try {
            aa = s.getconexionS();
            aa.setAutoCommit(false);
            s.getconexionS().setAutoCommit(false);
            dbLite.setAutoCommit(false);
            //obtener todo de la tabla datos de un usuario.
            st = dbLite.prepareStatement("SELECT * from movimientos where stat='1'");
            rs = st.executeQuery();
            PreparedStatement st1;
            while (rs.next()) {
                String usuario = rs.getString("usuario");
                String word = rs.getString("palabra");
                String fecha = rs.getString("fecha");
                int hora = rs.getInt("hora");
                String imagen = rs.getString("imagen");
                //insertar registros encontrados en la bse de datos local al servidor
                String sql = "insert into movimientos(usuario,palabra,fecha,hora,imagen,status) "
                        + "values(" + usuario + ",'" + word + "','" + fecha + "','" + hora + "','" + imagen + "','2')";
                System.out.println(sql);
                st1 = aa.prepareStatement(sql);
                st1.executeUpdate();
                st = dbLite.prepareStatement("update movimientos set stat ='0' where id=" + rs.getInt("id"));
                st.executeUpdate();
                EnviarArchivo ea = new EnviarArchivo(imagen,ip);
                ea.enviarArchivo();
            }
            System.out.println("Movimiento entre bds listo :D chingon prro");
            aa.commit();
            dbLite.commit();
            return true;
        } catch (Exception ex) {
            try {
                Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex);
                dbLite.rollback();
                aa.rollback();
            } catch (Exception ex1) {
                Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        } finally {
            try {
                rs.close();
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<String> sinchwordlist() {
        ArrayList<String> arr = new ArrayList<>();
        try {
            //obtener lista de filtro de palabras.
            st = s.getconexionS().prepareStatement("SELECT words from wordlist");
            rs = st.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString("words"));
            }
        } catch (Exception ex) {
            Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arr;
    }
}
