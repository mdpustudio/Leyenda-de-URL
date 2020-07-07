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
public class ProcesoB implements Runnable{
    private boolean seguir=false;
    private int x,y;
    private boolean dentro=false;
    private boolean mover=true;
    private Boss jefe;
    private boolean sec=false;
    
    public ProcesoB(){
        jefe= new Boss(1300,700);
    } 

    public void setSeguir(boolean seguir) {
        this.seguir = seguir;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDentro(boolean dentro) {
        this.dentro = dentro;
    }

    public void setMover(boolean mover) {
        this.mover = mover;
    }

    public boolean isSeguir() {
        return seguir;
    }

    public boolean isDentro() {
        return dentro;
    }

    public boolean isMover() {
        return mover;
    }

    public Boss getJefe() {
        return jefe;
    }

    public void setSec(boolean sec) {
        this.sec = sec;
    }
    
    
    

    @Override
    public void run() {
        long c=80;
        jefe.setVida(800, 300);
        while(jefe.getVida()>=0){
          if(jefe.getVida()>75 ){
              if(!dentro){
                  jefe.seguir(x, y);
                  seguir=true;
              }
          }
          else{
              if(sec){
                  jefe.Secuencia();
              }
              else{
                  if(!dentro){
                    c=40;
                    jefe.seguir(x, y);
                  }
                  
              }
          }
          
            try {
                Thread.sleep(c);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProcesoB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
    }
    
}
