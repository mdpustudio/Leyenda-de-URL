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
 * @author Carlos
 */
public class ProcesoE  implements Runnable {
    
    private Enemy[] enemigos;
    private Enemy enemigo;
    int cantidad;
    private int banderaD = 10, banderaR = 0, banderaL = 6, banderaU = 3, face;
    private boolean seguir=false;
    private int x,y;
    private boolean dentro=false;
    private boolean mover=true;
    private boolean[] Pdentro;
    private boolean[] Pmover;
    private boolean[] Pseguir;

    public ProcesoE(int cantidad, int posx,int posy) {
        this.cantidad = cantidad;
        if(cantidad >1){
            enemigos= new Enemy[cantidad];
            for(int i=0; i<cantidad;i++){
                enemigos[i]= new Enemy(1300,700);
            }
        }
        enemigo= new Enemy(posx,posy);
        Pdentro= new boolean[cantidad]; Pmover= new boolean[cantidad]; Pseguir= new boolean[cantidad];
        for(int i=0;i<cantidad;i++){
            Pdentro[i]=false; Pmover[i]=true; Pseguir[i]=false;
        }
    }

    
    public void setEnemigos(Enemy[] enemigos) {
        this.enemigos = enemigos;
    }

    public void setEnemigo(Enemy enemigo) {
        this.enemigo = enemigo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Enemy getEnemigos(int i) {
        return enemigos[i];
    }

    public Enemy getEnemigo() {
        return enemigo;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    
    
    @Override
    public void run() {
        if(cantidad==1){
            while(enemigo.getVida()>0){
                try {
                    if(!enemigo.Salio()){
                       if(seguir==false && mover){
                            enemigo.movimiento2();
                        }
                        else{
                            if(dentro==false && enemigo.Entro(x, y)){
                            enemigo.seguir(x, y);
                            }
                        } 
                    }
                    else{
                        enemigo.regresar();
                        mover=false;
                        dentro=false;
                    }
                    Thread.sleep(80);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProcesoE.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(cantidad>1){
            while((enemigos[0].getVida()>0) || (enemigos[1].getVida()>0)){
                for(int i=0;i<cantidad;i++){
                    try {
                    if(!enemigos[i].Salio()){
                       if(Pseguir[i]==false && Pmover[i]){
                            enemigos[i].movimiento2();
                        }
                        else{
                            if(Pdentro[i]==false && enemigos[i].Entro(x, y)){
                                Pmover[i]=false;
                                enemigos[i].seguir(x, y);
                            }
                        } 
                    }
                    else{
                        enemigos[i].regresar();
                        Pdentro[i]=false;
                    }
                    //enemigo.ObtenerO();
                    Thread.sleep(70);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ProcesoE.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }                
            }
        }
        
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSeguir(boolean seguir) {
        this.seguir = seguir;
    }

    public void setDentro(boolean dentro) {
        this.dentro = dentro;
    }
    
    public void setPDentro(int i,boolean a){
        this.Pdentro[i]=a;
    }
    public void setPSeguir(int i,boolean a){
        this.Pseguir[i]=a;
    }
    
}
