/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.event.KeyEvent;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;

/**
 *
 * @author Carlos
 */
public class Niveles implements Runnable{
    private int nivel=1;
    private ProcesoE enemy;
    private Level escenario;
    private ProcesoE[] enemies;
    private int posHx,posHy;
    private Heroe hero;
    private int cantidad;
    private int tecla=0;
    private int tecla2=0;
    private int a=0;
    private int Hvida;
    private boolean siguNivel=false;
    private boolean atack=false;
    private boolean seguir=false;
    private boolean adentro=false;
    private ProcesoB jefe;
    private Reproductor sonido;
    private EfectoS efecto;
    private int HPuntaje;
    private int puntaje;
    private Result result;
    
    public Niveles() {
        cantidad=2;
        escenario = new Level("src/lvl/nivel11.txt");
        hero= new Heroe(0,0,escenario,escenario.getTileObjectMatrix());
        enemy= new ProcesoE(cantidad,800,240);
        jefe= new ProcesoB();
        sonido= new Reproductor();
        efecto= new EfectoS();
    }
    
    public void playgame(){
        puntaje = hero.getPuntaje();
        if(nivel==1){
            sonido.setMusica(nivel);
            nivel= nivel1();
        }
        if(nivel==2){
            sonido= new Reproductor();
            sonido.setMusica(nivel);
            a=0;
            siguNivel=false;
            nivel= nivel2();
        }
        if(nivel==3){
            sonido= new Reproductor();
            sonido.setMusica(4);
            a=0;
            siguNivel=false;
            nivel=nivel3();
        }
        if(nivel==4){           
            System.out.println("GANASTE!!");
            result = new Result();
            
        }
        if(nivel==5){
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(Niveles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            efecto.setEfecto(4);
            System.out.println("Perdiste");
           
           result = new Result();
            //vent.getVent().add(new Result());
            
        }
        
    }
    
    private int nivel1(){
        sonido.getAudio().start();
        sonido.getAudio().loop(Clip.LOOP_CONTINUOUSLY);
        enemy.setCantidad(1);
        Thread t= new Thread(enemy);
        t.start();
        //m.start();
        while(!siguNivel){
        if(tecla2==32){
            atack=true;
            efecto.setEfecto(2);
        }
            
        Colision(enemy.getEnemigo().getPosx(), enemy.getEnemigo().getPosy());  
            
        if(enemy.getEnemigo().Entro(hero.getPosX(), hero.getPosY())){
            seguir=true;
            enemy.setX(hero.getPosX());
            enemy.setY(hero.getPosY());
                
        }
        if(seguir){
            enemy.setX(hero.getPosX());
            enemy.setY(hero.getPosY());
            enemy.setSeguir(true);
        }
        else{
            enemy.setSeguir(false);
        }
            
        if(adentro){
            enemy.setDentro(true);
            hero.atacado();
            efecto.setEfecto(1);
        }
        else{
            enemy.setDentro(false);
        }
            
        if(atack && adentro){
            enemy.getEnemigo().atacado(); 
            
        }
        if(enemy.getEnemigo().getVida()==0 && a==0){
            enemy.getEnemigo().setMuerte();
            hero.Puntaje();
            efecto.setEfecto(3);
            escenario = new Level("src/lvl/nivel12.txt");
            hero.setCurrentLevel(escenario);
            t.stop();
            a++;
        }
        if(enemy.getEnemigo().getVida()<=0 && hero.getPosX()> 1190 && hero.getPosX()<1230 && hero.getPosY()>288 && hero.getPosY()<352){
            siguNivel=true;
        }
        adentro=false;  
        
        try{
            Thread.sleep(5);
        }
        catch(InterruptedException e){
            e.getMessage();
        }
        atack=false;
        
        if(hero.getVida()<=0){
            sonido.getAudio().stop(); 
            
            return 5;
        }
    }
        Hvida=hero.getVida();
        HPuntaje= hero.getPuntaje();
        sonido.getAudio().stop();       
        return 2;
    }
    
    private int nivel2(){
        sonido.getAudio().start();
        sonido.getAudio().loop(Clip.LOOP_CONTINUOUSLY);
        enemy.setCantidad(2);
        escenario = new Level("src/lvl/level21.txt");
        hero= new Heroe(0,0,escenario,escenario.getTileObjectMatrix());
        hero.setVida(Hvida);
        hero.setPuntaje(HPuntaje);
        enemy.getEnemigos(0).setVida(936,304);
        enemy.getEnemigos(1).setVida(350,384);
        Thread t= new Thread(enemy);
        t.start();
        //m.start();
        while(!siguNivel){
        enemy.setX(hero.getPosX());
        enemy.setY(hero.getPosY());
        if(tecla2==32){
            atack=true;
            efecto.setEfecto(2);
        }
        for(int i=0;i<cantidad;i++){
        Colision(enemy.getEnemigos(i).getPosx(), enemy.getEnemigos(i).getPosy());
        if(atack && adentro){
            enemy.getEnemigos(i).atacado();
        }
            
        if(enemy.getEnemigos(i).Entro(hero.getPosX(), hero.getPosY())){
            enemy.setPSeguir(i, true);
        }
        if(!enemy.getEnemigos(i).Entro(hero.getPosX(), hero.getPosY())){
            enemy.setPSeguir(i, false);
        }
            
        if(adentro){
            hero.atacado();
            enemy.setPDentro(i, true);
            efecto.setEfecto(1);
        }
        else{
            enemy.setPDentro(i,false);
        }
            
        if(enemy.getEnemigos(i).getVida()==0){
            enemy.getEnemigos(i).setMuerte();
        }
            
        if(enemy.getEnemigos(1).getVida()==0 && enemy.getEnemigos(0).getVida()==0 && a==0){
            escenario = new Level("src/lvl/level22.txt");
            hero.setCurrentLevel(escenario);
            hero.Puntaje();
            hero.Puntaje();
            efecto.setEfecto(3);
            hero.setCurrentLevelMatrix(escenario.getTileObjectMatrix());
            a++;
        }
            
        if(enemy.getEnemigos(1).getVida()==0 && enemy.getEnemigos(0).getVida()==0 && hero.getPosX()>1116 && hero.getPosX()<1153 && hero.getPosY()>0 && hero.getPosY()<20){
            siguNivel=true;
            
            //sonido.setStop(false);
            t.stop();
            //m.stop();
        }
        adentro=false;
              
        }
        //System.out.println("vidaE:"+enemy.getEnemigo().getVida()+" VidaH: "+hero.getVida());
        atack=false;
        if(hero.getVida()<=0){
            sonido.getAudio().stop();
            
            return 5;
        }
        
        try{
            Thread.sleep(5);
        }
        catch(InterruptedException e){
            e.getMessage();
        }
    }
        Hvida=hero.getVida();
        HPuntaje= hero.getPuntaje();
        sonido.getAudio().stop();
        return 3;
    }
    
    private int nivel3(){
        //Thread m= new Thread(sonido);
        
        int hx,hy;
        sonido.getAudio().start();
        escenario = new Level("src/lvl/3nivel1.txt");
        hero= new Heroe(0,0,escenario,escenario.getTileObjectMatrix());
        hero.setVida(Hvida);
        hero.setPuntaje(HPuntaje);
        Thread j= new Thread(jefe);
        while(!siguNivel){    
        if(tecla2==32){
            atack=true;
            efecto.setEfecto(2);
        }
        
        Colision(jefe.getJefe().getPosx(), jefe.getJefe().getPosy());
        if(jefe.isSeguir()){
            jefe.setX(hero.getPosX());
            jefe.setY(hero.getPosY());
        }

        if(adentro){
            jefe.setDentro(true);
        }
        else{
            jefe.setDentro(false);
        }

        if(atack && adentro){
            jefe.getJefe().atacado();
        }
        if(jefe.getJefe().getVida()<=50 && adentro){
            hero.agredido();
            efecto.setEfecto(7);
        }
        if(jefe.getJefe().getVida()>50 && adentro){
            hero.atacado();
            efecto.setEfecto(1);
        }

        if(hero.getPosX()>235 && a==0){
            escenario = new Level("src/lvl/3nivel31.txt");
            hero.setCurrentLevel(escenario);
            efecto.setEfecto(6);
            j.start();
            sonido.getAudio().stop();
            sonido = new Reproductor();
            sonido.setMusica(3);
            sonido.getAudio().start();
            sonido.getAudio().loop(Clip.LOOP_CONTINUOUSLY);
            //m.start();
            a++;
        }
        if(hero.getVida()<=0){
            sonido.getAudio().stop();
            return 5;
        }

        if(jefe.getJefe().getVida()<=0){
            jefe.getJefe().setMuerte();
            hero.PuntajeFinal();
            siguNivel=true;
            
            //sonido.setStop(false);
            j.stop();
        }

        if(jefe.getJefe().getVida()<=50 && a==1){
            jefe.setSec(true);
            efecto.setEfecto(8);
            while(hero.getPosX()!=240 || hero.getPosY()!=300){
                hx=hero.getPosX();
                hy=hero.getPosY();
                hero.ObtenerCo();
                if(hx<240){
                    hx++;
                    hero.setPosX(hx);
                }
                if(hx>240){
                    hx--;
                    hero.setPosX(hx);
                }
                if(hy>300){
                    hy--;
                    hero.setPosY(hy);
                }
                if(hy<300){
                    hy++;
                    hero.setPosY(hy);
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ex1) {
                    Logger.getLogger(Niveles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex1);
                }
            }
            jefe.setSec(false);
            a++;

        }
        try{
            Thread.sleep(5);
        }
        catch(InterruptedException e){
            e.getMessage();
        }
        
        adentro=false;
        atack=false;
        //System.out.println(jefe.getJefe().getVida()+ " VidaH" +hero.getVida());
    }
      
        sonido.getAudio().stop();
        return 4;
    }

    
    public void setKeyeventPressed(KeyEvent e){
        hero.keyPressed(e);
    }
    
    public void setKeyeventRelesed(KeyEvent e){
        hero.keyReleased(e);
        
    }
    
    public int getNivel(){
        return nivel;
    }
    public int getPuntaje(){
        return puntaje;
    }

    public ProcesoE getEnemy() {
        return enemy;
    }
    

    public Level getEscenario() {
        return escenario;
    }


    public void setEscenario(Level escenario) {
        this.escenario = escenario;
    }

    public Heroe getHero() {
        return hero;
    }

    public void setHero(Heroe hero) {
        this.hero = hero;
    }

    @Override
    public void run() {
        playgame();
    }

    public void setTecla(int tecla) {
        this.tecla = tecla;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setTecla2(int tecla2) {
        this.tecla2 = tecla2;
    }

    public ProcesoB getJefe() {
        return jefe;
    }
    
    private void Colision(int x, int y){
        int ex,ey,fex,fey,temp;
        int hx=0,hy=0,hex=0,hey=0;
        hx=hero.getPosX(); hex= hx+32;
        hy=hero.getPosY(); hey= hy+32;
        ex=x;
        ey=y;
        fex= ex+32;
        fey= ey+40;
            
            if((hx>ex && hx<fex)||(hex>ex && hex<fex)){
               if((hy>ey && hy<fey)||(hey>ey && hey<fey)){
                   //seguir=true;
                if(tecla==37){
                    temp=hero.getPosX();
                    temp=temp+2;
                    hero.setPosX(temp);
                }
                if(tecla==38){
                    temp=hero.getPosY();
                    temp=temp+2;
                    hero.setPosY(temp);
            
                }
                if(tecla==39){
                    temp=hero.getPosX();
                    temp=temp-2;
                    hero.setPosX(temp);
            
                }
                if(tecla==40){
                    temp=hero.getPosY();
                    temp=temp-2;
                    hero.setPosY(temp);
            
                }
                
                } 
            }
            if((hx>ex-8 && hx<fex+8)||(hex>ex-8 && hex<fex+8)){
               if((hy>ey-8 && hy<fey+8)||(hey>ey-8 && hey<fey+8)){
                   adentro=true;
               }
            }
    }
    
    
    
}
