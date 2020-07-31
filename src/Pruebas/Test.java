/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import Modelo.Movimiento;
import Modelo.ObjMovimiento;
import Modelo.ObjUsuario;
import Persistencia.Conexion;
import Persistencia.ConexionServer;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
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
            Movimiento m = new Movimiento();
            ObjMovimiento om = new ObjMovimiento();
            //System.out.println(u.checkuser(4));
//            System.out.println(u.checkstate(3, c.getconexionC()));
//            u.checkuser(3,c.getconexionC());
//            Calendar fecha = Calendar.getInstance(); //intanciar informacion del calendiario respecto al sistema
//            int año = fecha.get(Calendar.YEAR);
//            int mes = fecha.get(Calendar.MONTH) + 1;
//            int dia = fecha.get(Calendar.DAY_OF_MONTH);
//            int hora = fecha.get(Calendar.HOUR_OF_DAY);
//            int minuto = fecha.get(Calendar.MINUTE);
//            String horas = hora+":"+minuto;
//            String fechac = año + "-" + mes + "-" + dia;//fecha formada por Calendar.getInstance(); 
//            m.setUsuario(3);
//            m.setPalabra("facebook chinguesu");
//            m.setFecha(fechac);
//            m.setHora(horas);
//            m.setImagen("C:\\Win\\af\\data\\"+fechac);
//            om.Nuevomov(m, c.getconexionC());
//            om.sinchregs(m, c.getconexionC());
ConexionServer cs = new ConexionServer();
            System.out.println(cs.ipserver());
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
