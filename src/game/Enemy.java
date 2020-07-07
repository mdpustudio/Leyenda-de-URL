/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Rey
 */
public class Enemy {
    private int posX;
    private int posY;
    private int vida=40;
    private int a=0;
    private int xi;
    private int yi;
    private int damage=0;
    private int banderaD = 1, banderaR = 6 , banderaL = 3, banderaU = 9, face;
    protected boolean goingD = true, goingR = false, goingL = false, goingU = false, notStarted = true;
    private t3 t1;
    BufferedImage currentImage;
    EnemySpriteContainer sprite;
    private int[] matrixIndex = new int[2];

    public Enemy(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        this.xi=posX;
        this.yi=posY;
        sprite = new EnemySpriteContainer();
        currentImage = sprite.getSprites().get(0);
        movimiento2();        
    }
    public void paint(Graphics g, ImageObserver observer){
        g.drawImage(currentImage, posX, posY, observer);
    }
    
    public int getPosx() {
        return posX;
    }

    public int getPosy() {
        return posY;
    }

    public void setPosx(int posX) {
        this.posX = posX;
    }

    public void setPosy(int posY) {
        this.posY = posY;
    }

    
   
    public void atacado(){
        this.damage++;
        
        if(damage%50==0){
            this.vida-=5;
        }
    }
    
    
    public int getVida() {
        return vida;
    }
    
    
    public void movimiento2(){
        if(notStarted){
           t1 = new t3();
            t1.start();
            notStarted = false;
        }
        int c=0;
        if(vida>0){
            if(a<=20 && a>=1){
            face = 0;
            goingD = true;
            goingU = false;
            goingR = false;
            goingL = false;
            c= getPosy();
            setPosy(c+8);
            }
            if(a>20 && a<=40 ){
                face = 2;
                goingD = false;
                goingU = false;
                goingR = false;
                goingL = true;
                c= getPosx();
                setPosx(c-8);
            }
            if(a>40 && a<60 ){
                face = 3;
                goingD = false;
                goingU = true;
                goingR = false;
                goingL = false;
                c= getPosy();
                setPosy(c-8);
            }
            if(a>60 && a<=80 ){
                face = 1;
                goingD = false;
                goingU = false;
                goingR = true;
                goingL = false;
                c= getPosx();
                setPosx(c+8);
            }
            
            if(a==80){
                a=1;
            }
                a++;            
        }
    }
    /*public void movimiento3(){
        int c= getPosy();
        c--;
        setPosy(c);
    }*/
    
    public void seguir(int x, int y){
        int tempx= getPosx();
        int tempy= getPosy();
        if(x > getPosx()){
            tempx=tempx+8;
            face = 1;
            setPosx(tempx);
        }
        if(x < getPosx()){
            tempx=tempx-8;
            face = 2;
            setPosx(tempx);
        }
        if(y > getPosy()){
            tempy=tempy+8;
            face =0;
            setPosy(tempy);
        }if(y < getPosy()){
            tempy=tempy-8;
            face = 3;
            setPosy(tempy);
        }
    }  
    
    public void regresar(){
        int tempx= getPosx(), tempy= getPosy();
        if(xi > getPosx()){
            tempx=tempx+5;
            setPosx(tempx);
        }
        if(xi < getPosx()){
            tempx=tempx-5;
            setPosx(tempx);
        }
        if(yi > getPosy()){
            tempy=tempy+5;
            setPosy(tempy);
        }
        if(yi < getPosy()){
            tempy=tempy-5;
            setPosy(tempy);
        }
        
    }
    
    public boolean Salio(){
        int xf=xi+200, yf=yi+200;
        int xini=xi-200, yini=yi-200;
        if(getPosx()>xf || getPosx()<xini){
            return true;
        }
        if(getPosy()>yf || getPosy()<yini){
            return true;
        }
        
        return false;
    }
    
    public boolean Entro(int x, int y){
        int xf=xi+200, yf=yi+200;
        int xini=xi-200, yini=yi-200;
        boolean inx=false, iny=false;
        if(x<=xf && x>=xini){
            inx=true;
        }
        if(y<=yf && y>=yini){
            iny=true;
        }
        return inx && iny;
    }
    
    public void ObtenerO(){
        System.out.println(posX+" "+posY);
    } 

    public int getXi() {
        return xi;
    }

    public int getYi() {
        return yi;
    }
    
    public void setMuerte(){
        this.posX=1300;
        this.posY=700;
    }
    public void setVida(int x, int y){
        this.posX=x;
        this.posY=y;
        this.xi=x;
        this.yi=y;
    }
    private void animateEnemy(){
        switch(face){
            case 0:
                //System.out.println("Holiwis");
                if(banderaD <= 2){
                    currentImage = sprite.getSprites().get(banderaD);
                    banderaD++;
                }else if(banderaD >2){
                    banderaD = 0;
                    currentImage = sprite.getSprites().get(banderaD);
                    banderaD = 1;
                }
                break;
            case 1:
                if(banderaR <= 8){
                    currentImage = sprite.getSprites().get(banderaR);
                    banderaR++;
                }else if(banderaR >8){
                    banderaR = 6;
                    currentImage = sprite.getSprites().get(banderaR);
                    banderaR = 7;
                }
                break;
            case 2:
                if(banderaL <= 5){
                    currentImage = sprite.getSprites().get(banderaL);
                    banderaL++;
                }else if(banderaL > 5){
                    banderaL = 3;
                    currentImage = sprite.getSprites().get(banderaL);
                    banderaL = 4;
                }else{
                    currentImage = sprite.getSprites().get(banderaL);
                    banderaL = 6;
                }
                break;
            case 3:
                if(banderaU <= 11){
                    currentImage = sprite.getSprites().get(banderaU);
                    banderaU++;
                }else if(banderaU > 11){
                    banderaU = 9;
                    currentImage = sprite.getSprites().get(banderaU);
                    banderaU = 10;
                }
                break;
        }
    }
    
    public class t3 extends Thread{
        @Override
        public synchronized void run() {
            while(goingD ^ goingR ^ goingL ^ goingU){
                animateEnemy();
                try{super.sleep(200);}catch(Exception e){};
            }
        }
    }
}
