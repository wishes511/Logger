/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package key;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author GATEWAY1-
 */
public class autocaptura {
    public autocaptura(){

}
    public void captura(String a,String fecha,String nombre) throws AWTException, IOException{
    BufferedImage captura = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
    File f = new File("c:\\Win\\af\\data\\"+fecha+"\\"+nombre+"_"+a+".jpg");
    ImageIO.write(captura, "jpg", f);
    }
    
}
