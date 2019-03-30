/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splashactivity;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Poncoe
 */
public class SplashActivity {
public static SplashScreen loadingScreen;
public static Double loadingProgressArea;
public static Graphics2D loadingGraphics;
public static Font myfont;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        loadingMethod();
        mainMethod();
        
        if (loadingScreen != null) {
            loadingScreen.close();
        }
    }
    public static void loadingMethod() {
        loadingScreen = SplashScreen.getSplashScreen();
        if (loadingScreen !=null){
            // Jika ada masalah menampilkan splash ini akan null
            
            //Dimensi gambar Anda
            Dimension dim = loadingScreen.getSize();
            int ht = dim.height;
            int wd = dim.width;            
            //Mempatin textArea sama progressArea
            loadingProgressArea = new Rectangle2D.Double(0, ht*0.78, wd*0.40, 20);
            // menciptakan lingkungan Graphics untuk menggambar status info
            loadingGraphics = loadingScreen.createGraphics();
            //Mensetting font untuk tulinsa di status info
            myfont = new Font("ComicSans", Font.BOLD, 14);
            loadingGraphics.setFont(myfont);
            
            
        }
    }
           
           
           public static void loadingProgress(int prog) {
               if (loadingScreen != null){
                   
                   int x = (int) loadingProgressArea.getMinX();
                   int y = (int) loadingProgressArea.getMinY();
                   
                   int width = (int) loadingProgressArea.getWidth();
                   int height = (int) loadingProgressArea.getHeight();
                   //membaginya dengan 80 karena gw bakalan memperbarui kemajuan lima kali
                   int doneProg = prog*width/80;
                   
                   loadingGraphics.setPaint(Color.BLUE);//Color for the total progress done
                   loadingGraphics.fillRect(x, y+1, doneProg, height);
                   
                   loadingScreen.update();
               }
           }
           
           public static void mainMethod() {
               for (int i=1;i<=100;i++){
                   loadingProgress(i*2);
                   
                   try {
                       // membuat URL untuk halaman yang diinginkan
                       URL url = new URL("www.facebook.com/kataponcoe");
                       
                       BufferedReader in = new BufferedReader( new InputStreamReader(url.openStream()));
                       
                       String str;
                       while ((str = in.readLine()) != null){
                       System.out.println(str);
                   }
                       in.close();
                   }                 
                   catch(MalformedURLException e) {                       
                   }
                   catch(IOException e){                      
                   }
           }
               java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new halomain().setVisible(true);
            }
            // jadi setelah splash bakalan masuk ke form selanjutnya
        });
      }
}
