/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Persistencia.ConexionServer;
import java.io.IOException;
import java.sql.Connection;
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

    public boolean checkuser(int usuario) {
        boolean resp = true;
        try {
            st = s.getconexionS().prepareStatement("SELECT usuario from usuarios where startus='1' and tipo='S' and usuario=" + usuario);
            rs = st.executeQuery();
            while (rs.next()) {
                resp = false;
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

    //Actualizacion de estado en base de datos local.
    public Estado checkstate(int usuario, Connection dbLite) {
        Estado e = new Estado();
        try {
            //obtener todo de la tabla datos de un usuario.
            st = s.getconexionS().prepareStatement("SELECT * from estado where clave=" + usuario);
            rs = st.executeQuery();
            //    0           1                   2                   3               4                       5               6                   
            String arr[] = {"version", "sincronizacion", "sincronizacion_unit", "autocaptura", "actualizacion_server", "repeticiones_salto", "tiempo_espera_salto"};
            dbLite.setAutoCommit(false);
            while (rs.next()) {
                String version = rs.getString(arr[0]);
                String s1 = rs.getString(arr[1]);
                String s2 = rs.getString(arr[2]);
                String autoc = rs.getString(arr[3]);
                int aserver = rs.getInt("actualizacion_servidor");
                int rsalto = rs.getInt(arr[5]);
                int trsalto = rs.getInt(arr[6]);
                //Actualizar base de datos local por los datos alojados en el servidor de acuerdo al usuario.
                String sql = "update estado set version='" + version + "', " + arr[1] + "='" + s1 + "'," + arr[2] + "='" + s2 + "', autocaptura='"
                        + autoc + "'," + arr[4] + "=" + aserver + "," + arr[5] + "=" + rsalto + "," + arr[6] + "=" + trsalto + " where id=" + usuario;
                System.out.println(sql+"--");
                e.setVersion(version);
                e.setSincronizacion(s1);
                e.setSincronizacion_unit(s2);
                e.setAutocaptura(autoc);
                e.setAct_server(aserver);
                e.setRepeticiones_salto(rsalto);
                e.setTiempo_espera_salto(trsalto);
                st = dbLite.prepareStatement(sql);
                st.executeUpdate();
            }
            dbLite.commit();
            System.out.println("se cumplio");
        } catch (Exception ex) {
            try {
//                String arr[] = {"version", "sincronizacion", "sincronizacion_unit", "autocaptura", "actualizacion_server", "repeticiones_salto", "tiempo_espera_salto"};
//                dbLite.setAutoCommit(false);
//                st = dbLite.prepareStatement("SELECT * from estado where id=" + usuario);
//                rs = st.executeQuery();
//                while (rs.next()) {
//                    String version = rs.getString(arr[0]);
//                    String s1 = rs.getString(arr[1]);
//                    String s2 = rs.getString(arr[2]);
//                    String autoc = rs.getString(arr[3]);
//                    int aserver = rs.getInt("actualizacion_servidor");
//                    int rsalto = rs.getInt(arr[5]);
//                    int trsalto = rs.getInt(arr[6]);
//                    e.setVersion(version);
//                    e.setSincronizacion(s1);
//                    e.setSincronizacion_unit(s2);
//                    e.setAutocaptura(autoc);
//                    e.setAct_server(aserver);
//                    e.setRepeticiones_salto(rsalto);
//                    e.setTiempo_espera_salto(trsalto);
//                }
                Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex);
                dbLite.rollback();
            } catch (Exception ex1) {
                Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                rs.close();
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return e;
    }

    public usuarios checkuser(int usuario, Connection dbLite) {
        usuarios u = new usuarios();
        try {
            //obtener todo de la tabla datos de un usuario.
            st = s.getconexionS().prepareStatement("SELECT * from usuarios where usuario=" + usuario);
            rs = st.executeQuery();
            dbLite.setAutoCommit(false);
            while (rs.next()) {
                String status = rs.getString("startus");
                int clave = rs.getInt("usuario");
                int traspaso = rs.getInt("traspaso");
                String sql = "";
                if (status.equals("0")) {
                    //Actualizar base de datos local por los datos alojados en el servidor de acuerdo al usuario.
                    // solo si wl usuario ha sido dado de baja, sustituir por el nuevo usuario diponible
                    sql = "update estado set id=" + traspaso + " where id=" + usuario;
                    clave = traspaso;
                    st = dbLite.prepareStatement(sql);
                    st.executeUpdate();
                }
                u.setId(clave);
                u.setStatus("1");
                u.setTipo("C");
            }
            dbLite.commit();
        } catch (Exception ex) {
            try {
                dbLite.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } finally {
            try {
                rs.close();
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(ObjUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return u;
    }
}
