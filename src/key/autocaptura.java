/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package key;

import Modelo.Movimiento;
import Modelo.ObjMovimiento;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;
import javax.imageio.ImageIO;

/**
 *
 * @author GATEWAY1-
 */
public class autocaptura {
    public autocaptura(){

}
    public void captura(String a,String fecha,String nombre,String word,Connection c) throws AWTException, IOException{
    BufferedImage captura = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
    File f = new File("c:\\Win\\af\\data\\"+fecha+"\\"+nombre+"_"+a+".jpg");
    ImageIO.write(captura, "jpg", f);
    
    Calendar fechas = Calendar.getInstance(); //intanciar informacion del calendiario respecto al sistema
            int hora = fechas.get(Calendar.HOUR_OF_DAY);
            int minuto = fechas.get(Calendar.MINUTE);
            String horas = hora+":"+minuto;
            ObjMovimiento om = new ObjMovimiento();
            Movimiento m = new Movimiento();
            m.setFecha(fecha);
            m.setHora(horas);
            m.setUsuario(Integer.parseInt(nombre));
            m.setPalabra(word);
            m.setStatus("1");
            m.setImagen("c:\\Win\\af\\data\\"+fecha+"\\"+nombre+"_"+a+".jpg");
            om.Nuevomov(m, c);
            
    }
    
}
