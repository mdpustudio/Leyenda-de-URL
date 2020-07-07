/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import res.ResReference;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author UCA
 */
public class Panel extends JPanel{
    frmCrono frm  = new frmCrono();
    boolean juego=true;
    Level nivel1;
    int cantidad;
    Niveles nivel;
    Font f= new Font("Franklin Gothic Medium Cond",Font.BOLD,16);
    BufferedImage corazon;
    BufferedImage rupia;
    
    public Panel(){}
    
    public Panel(int w, int h){
        try {
            this.corazon = ImageIO.read(ResReference.class.getResource("img/zeldaHC.png"));
            this.rupia= ImageIO.read(ResReference.class.getResource("img/zeldaR.png"));
        } catch (IOException ex) {
            Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        nivel= new Niveles();
        frm.getHilo().start();
        //nivel1 = nivel.getEscenario();
        Thread t= new Thread(nivel);
        t.start();
        super.setSize(new Dimension(w,h));
        super.setBackground(Color.WHITE);
        super.setVisible(true);
        boolean keys[] = new boolean[256];
        KeyListener listener = new KeyListener(){
             @Override
        public void keyTyped(KeyEvent e) {
           
        }

        @Override
        public void keyPressed(KeyEvent e) {
             if(e.getKeyCode() >= 37 && e.getKeyCode()<=40 ){
                nivel.setTecla(e.getKeyCode());
            }
            
            nivel.setKeyeventPressed(e);
            nivel.setTecla2(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            nivel.setKeyeventRelesed(e);
            nivel.setTecla2(0);
        }
        };
        this.addKeyListener(listener);
        setFocusable(true);
    }
    
       
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        //nivel1.paint(g, this);
        nivel.getEscenario().paint(g, this);
        nivel.getHero().paint(g, this);
        nivel.getEnemy().getEnemigo().paint(g, this);
        nivel.getJefe().getJefe().paint(g, this);
        for(int i=0; i<nivel.getCantidad();i++){
            nivel.getEnemy().getEnemigos(i).paint(g, this);
        }
        g.setFont(f);
        g.setColor(Color.RED);
        g.drawString(nivel.getHero().getVida()+"", 580, 20);
        g.drawString(nivel.getHero().getPuntaje()+"", 680, 20);
        g.drawImage(rupia,650,3,30,30, this);
        g.drawImage(corazon,550,5,25,25,this);
    }
    
    public Niveles getNivel() {
        return nivel;
    }
    public int getNivelAct(){
        return nivel.getNivel();
    
    }
    
    
    
    


    
}

