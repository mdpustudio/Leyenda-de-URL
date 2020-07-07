/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Rey
 */
public class MotorDeJuego {

    /**
     * @param args the command line arguments
     */
    private static Ventana ventana;
    private static boolean isRunning = true;
    public static void main(String[] args) {
        ventana = new Ventana("Juego", 1280, 669);
        while(isRunning){
            actualizarDatos();
            pintar();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                System.out.println("Error al dormir el hilo: " + ex.getMessage());
            }
            
        }
        
    }
    
    private static void pintar(){
        ventana.getCurrentPanel().repaint();
        
    }
    private static void actualizarDatos(){
        if(ventana.getI() == 1) ventana.getPanel().getNivel().getHero().MoveLogic();
       // ventana.getPanel().setX(ventana.getPanel().getX2()+10);
      
    }
    
}
