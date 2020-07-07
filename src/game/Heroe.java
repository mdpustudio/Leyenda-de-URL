/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
/**
 *
 * @author Rey
 */
public class Heroe {
    private int posX;
    private int posY;
    private int vida=100;
    private int CDamage=0;
    static private int puntaje=0;
    BufferedImage currentImage;
    SpriteContainer sprite;
    private int banderaD = 1, banderaR = 6 , banderaL = 3, banderaU = 9, face, lastDir = 0;
    protected boolean goingD, goingR, goingL, goingU, SPressed, attacking;
    private t1 t1;
    private t2 t2;
    private int[] matrixIndex = new int[2];
    private Level currentLevel;
    private TileObject[][] currentLevelMatrix = new TileObject[20][40];
    private Rectangle prevRect, currentRect;
    private int filIndex, colIndex;
    private boolean ataque=false;
    
    
    public Heroe(){}
    public Heroe(int posX, int posY, Level currentLevel, TileObject[][] currentLevelMatrix){
        this.posX = posX;
        this.posY = posY;
        setPlayerMatrixIndex();
        sprite = new SpriteContainer();
        currentImage = sprite.getSprites().get(0);
        this.currentLevel = currentLevel;
        this.currentLevelMatrix = currentLevelMatrix;
    }
    public void paint(Graphics g, ImageObserver observer){
        g.drawImage(currentImage, posX, posY, observer);
        
    }
    
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if(!goingD && !goingU && !goingR && !goingL){
                face = 0;
                t1 = new t1();
                t1.start();
                goingD = true;
                goingL = false;
                goingR = false;
                goingU = false;
            }
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(!goingR&& !goingL&& !goingD&& !goingU){
                face = 1;
                t1 = new t1();
                t1.start();
                goingR = true;
                goingD = false;
                goingL = false;
                goingU = false;
            }
            
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(!goingR&& !goingL&& !goingD&& !goingU){
                face = 2;
                t1 = new t1();
                t1.start();
                goingL = true;
                goingR = false;
                goingD = false;
                goingU = false;
            }
            
        }else if(e.getKeyCode() == KeyEvent.VK_UP){
            if(!goingR&& !goingL&& !goingD&& !goingU){
                face = 3;
                t1 = new t1();
                t1.start();
                goingU = true;
                goingL = false;
                goingR = false;
                goingD = false;
            }
            
            
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            if(!SPressed) SPressed = true;
        }else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(!attacking) attacking = true;
            if(!goingD && !goingR && !goingL && !goingU){
                switch(lastDir){
                        case 0:
                            currentImage = sprite.getATKSprites().get(2);
                            break;
                        case 1:
                            currentImage = sprite.getATKSprites().get(8);
                            break;
                        case 2: 
                            currentImage = sprite.getATKSprites().get(5);
                            break;
                        case 3: 
                            currentImage = sprite.getATKSprites().get(11);
                            break;
                    }
            }
        }
        
    }
    
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            attacking = false;
            goingD = false;
            banderaD = 0;
            try{t1.stop(); }catch(Exception ex){};
            currentImage = sprite.getSprites().get(banderaD);
            banderaD = 1;
            lastDir = 0;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            attacking = false;
            goingR = false;
            banderaR = 6;
            try{t1.stop(); }catch(Exception ex){};
            currentImage = sprite.getSprites().get(banderaR);
            banderaR = 7;
            lastDir = 1;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            attacking = false;
            goingL = false;
            banderaL = 3;
            try{t1.stop(); }catch(Exception ex){};
            currentImage = sprite.getSprites().get(banderaL);
            banderaL = 4;
            lastDir = 2;
            
        }else if(e.getKeyCode() == KeyEvent.VK_UP){
            attacking = false;
            goingU = false;
            banderaU = 9;
            try{t1.stop(); }catch(Exception ex){};
            currentImage = sprite.getSprites().get(banderaU);
            banderaU = 10;
            lastDir = 3;
        }else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(!goingD && !goingR && !goingL && !goingU){
                attacking = false;
                switch(lastDir){
                    case 0:
                        currentImage = sprite.getSprites().get(banderaD);
                        break;
                    case 1:
                        currentImage = sprite.getSprites().get(banderaR);
                        break;
                    case 2:
                        currentImage = sprite.getSprites().get(banderaL);
                        break;
                    case 3:
                        currentImage = sprite.getSprites().get(banderaU);
                        break;
                }
            }
        }
        
    }
    
    public synchronized void MoveAnimation(int face){
        switch(face){
            case 0:
                if(!attacking){
                    if(banderaD <= 2){
                        currentImage = sprite.getSprites().get(banderaD);
                        banderaD++;
                        System.out.println(banderaD);
                    }else if(banderaD >2){
                        banderaD = 0;
                        currentImage = sprite.getSprites().get(banderaD);
                        banderaD = 1;
                    }
                }else{
                    if(banderaD >1){
                        banderaD = 1;
                        currentImage = sprite.getSprites().get(banderaD);
                        banderaD = 2;
                        attacking = false;
                    }
                    currentImage = sprite.getATKSprites().get(2);
                    banderaD++;
                }
                break;
            case 1:
                if(!attacking){
                    if(banderaR <= 8){
                        currentImage = sprite.getSprites().get(banderaR);
                        banderaR++;
                        System.out.println(banderaR);
                    }else if(banderaR >8){
                        banderaR = 6;
                        currentImage = sprite.getSprites().get(banderaR);
                        banderaR = 7;
                    }
                }else{
                    if(banderaR >7){
                        banderaR = 7;
                        currentImage = sprite.getSprites().get(banderaR);
                        banderaR = 8;
                        attacking = false;
                    }
                    currentImage = sprite.getATKSprites().get(8);
                    banderaR++;
                }
                break;
            case 2:
                if(!attacking){
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
                }else{
                    if(banderaL >4){
                        banderaL = 4;
                        currentImage = sprite.getSprites().get(banderaL);
                        banderaL = 5;
                        attacking = false;
                    }
                    currentImage = sprite.getATKSprites().get(5);
                    banderaL++;
                }
                break;
            case 3:
                if(!attacking){
                    if(banderaU <= 11){
                        currentImage = sprite.getSprites().get(banderaU);
                        banderaU++;
                        System.out.println(banderaU);
                    }else if(banderaU > 11){
                        banderaU = 9;
                        currentImage = sprite.getSprites().get(banderaU);
                        banderaU = 10;
                    }
                }else{
                    if(banderaU >10){
                        banderaU = 10;
                        currentImage = sprite.getSprites().get(banderaU);
                        banderaU = 11;
                        attacking = false;
                    }
                    currentImage = sprite.getATKSprites().get(11);
                    banderaU++;
                }
                break;
        }
        
        
    }
    
    public synchronized void MoveLogic(){    
        switch(face){
            case 0:
                if(goingD && posY != 608){
                if(currentLevel.getTileObjectMatrix()[(posY+1)/32][posX/32].walkable == true &&
                        currentLevel.getTileObjectMatrix()[(posY+1)/32][(posX+31)/32].walkable == true &&
                        currentLevel.getTileObjectMatrix()[(posY+32)/32][posX/32].walkable == true &&
                        currentLevel.getTileObjectMatrix()[(posY+32)/32][(posX+31)/32].walkable == true
                        )  Limitepy(posY);
                }break;
            case 1:
                if(goingR && posX <1280){
                if(     currentLevel.getTileObjectMatrix()[(posY)/32][(posX+32)/32].walkable == true &&
                        currentLevel.getTileObjectMatrix()[(posY+31)/32][(posX+32)/32].walkable == true
                        ) Limitepx(posX);
                }break;
            case 2:
                if(goingL && posX != 0){
                if(currentLevel.getTileObjectMatrix()[(posY)/32][(posX-1)/32].walkable == true &&
                        currentLevel.getTileObjectMatrix()[(posY)/32][(posX+30)/32].walkable == true &&
                        currentLevel.getTileObjectMatrix()[(posY+31)/32][(posX-1)/32].walkable == true &&
                        currentLevel.getTileObjectMatrix()[(posY+31)/32][(posX+30)/32].walkable == true
                        ) Limitenx(posX);
                }break;
            case 3:
                if(goingU && posY != 0){
                if(currentLevel.getTileObjectMatrix()[(posY-1)/32][posX/32].walkable == true &&
                        currentLevel.getTileObjectMatrix()[(posY-1)/32][(posX+31)/32].walkable == true &&
                        currentLevel.getTileObjectMatrix()[(posY+30)/32][posX/32].walkable == true &&
                        currentLevel.getTileObjectMatrix()[(posY+30)/32][(posX+31)/32].walkable == true
                        ) Limiteny(posY);
                }break;
        }
    }
    
    public class t1 extends Thread{
        @Override
        public synchronized void run() {
            while(goingD ^ goingR ^ goingL ^ goingU){
                MoveAnimation(face);
                try{super.sleep(200);}catch(Exception e){};
            }
        }
    }
    
    public class t2 extends Thread{
        @Override
        public synchronized void run() {
            while((goingD ^ goingR ^ goingL ^ goingU) || attacking){
                MoveLogic();
                try{super.sleep(5);}catch(Exception e){};
            }
        }
    }
    
    public void setPlayerMatrixIndex(){ 
        switch(face){
            case 0:
                filIndex = posY/32; 
                break;
            case 1:
                colIndex = posX/32;
                break;
            case 2:
                if(posX == 0) colIndex = 0;
                else{
                    colIndex = (posX+31)/32;
                }
                break;
            case 3:
                if(posY == 0) filIndex = 0;
                else{
                    filIndex = (posY+31)/32;
                }
                break;
        }
        //System.out.println("Nueva posicion: " + filIndex + "," +colIndex);
    }
    
    public boolean nextTileWalkable(){
            switch(face){
            case 0:
                if(filIndex+1 > 19) return false;
                else{
                    return currentLevelMatrix[filIndex+1][colIndex].walkable;
                }
            case 1:
                if(colIndex+1 > 40) return false;
                else{
                    return currentLevelMatrix[filIndex][colIndex+1].walkable;
                }
            case 2:
                if(colIndex-1 < 0) return false;
                else{
                    return currentLevelMatrix[filIndex][colIndex-1].walkable;
                }
                
            case 3:
                if(filIndex-1 < 0){
                    return false;
                }
                else{
                    return currentLevelMatrix[filIndex-1][colIndex].walkable;
                }                
        }
            return false;
    }
    
    public int getVida() {
        return vida;
    }
    
    public void setPosX(int posX) {
        if(posX>=0 && posX<=1224){
            this.posX=posX;
        }
    }

    public void setPosY(int posY) {
        if(posY>=0 && posY<=560){
            this.posY=posY;
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean isAtaque() {
        return ataque;
    }
    
    public void ObtenerCo(){
        System.out.println(""+this.posX+" "+this.posY);
    }
    
    private void Limitepx(int x){
        int c=x+8;
        if(c>=0 && c<=1224){
            this.posX=c;
        }
    }
    
    private void Limitepy(int y){
        int c=y+8;
        if(c>=0 && c<=620){
            this.posY=c;
        }
    }
    
    private void Limitenx(int x){
        int c=x-8;
        if(c>=0 && c<=1224){
            this.posX=c;
        }
    }
    private void Limiteny(int y){
        int c=y-8;
        if(c>=0 && c<=620){
            this.posY=c;
        }
    }
    
    public void atacado(){
        this.CDamage++;
        int temp=vida;
        temp-=10;
        if(temp>0){
            if(CDamage%200==0){
            this.vida-=3;
            }
        }
        else{
            vida=0;
        }
        
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setCurrentLevelMatrix(TileObject[][] currentLevelMatrix) {
        this.currentLevelMatrix = currentLevelMatrix;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
    
    public void recuperacion(){
        this.vida+=20;
    }

    public void Puntaje() {
        this.puntaje+=20;
    }

    public int getPuntaje() {
        return puntaje;
    }
    
    public void PuntajeFinal(){
        int c= puntaje+vida;
        puntaje=c;
    }
    
    public void agredido(){
        this.CDamage++;
        int temp=vida;
        temp-=10;
        if(temp>0){
            if(CDamage%200==0){
            this.vida-=10;
            }
        }
        else{
            vida=0;
        }
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    
}