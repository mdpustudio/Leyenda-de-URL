/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import java.awt.*;
import javax.swing.JLabel;
import DBConnection.ZQuery;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author Maestro
 */
public class Highscore extends JPanel{
    private JLabel l; 
    private Ventana ventana = new Ventana();
    private ZQuery query = new ZQuery();
    private HashMap<Integer,Player> hm;
    private Font font;
    private MenuPanel MenuPanel;
   
     private BufferedImage image;
    
    public Highscore(){
        nuevaFont();
        
        Imagenes();
        
        KeyListener listener = new KeyListener(){
             @Override
        public void keyTyped(KeyEvent e) {
           
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == 32){
                Highscore.this.setVisible(false);
                ventana.getVent().remove(Highscore.this);
               
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
            }
        };
        addKeyListener(listener);
        setFocusable(true);
    }
        
    
    //Colocando Nueva Fuente
    public void nuevaFont(){
        File file = new File("src/res/terminator.ttf");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, file);
            font = font.deriveFont(Font.TRUETYPE_FONT,18);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            
            
        } catch (FontFormatException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    
    //Mostrando los jugadores que estan en la base de datos
    public void addPlayers(Graphics2D g2d){
        if(query.count()>0){
            Player play = query.getLastPlayer();
            if(query.count() > 10){
                query.delete();
            }
            Player [] players = Ordenar();

            int x = 200;
            int y = 310;
            String usu;
            String sco;
            String time;
            for (Player player : players) {
                usu = player.getAlias();
                sco = String.valueOf(player.getScore());
                time = player.getTiempo();
                g2d.drawString(usu, x, y);
                g2d.drawString(sco, x+150, y);
                g2d.drawString(time, x+250, y);
                y += 40; 
                if(y>=500){
                    x += 400;
                    y = 310;
                }
            }        
            g2d.drawString("Press Space to Continue", 420, 600);
            if(query.count() >= 10){
                if(query.isHighscore(play)){
                    g2d.drawString("New HighScore:", 100, 550);
                    g2d.drawString(play.getAlias(), 500, 550);
                    g2d.drawString(String.valueOf(play.getScore()),700,550);
                }
                else{
                    g2d.drawString("Score muy bajo :v :", 200, 550);
                    g2d.drawString(play.getAlias(),550, 550);
                    g2d.drawString(String.valueOf(play.getScore()),800,550);
                }

            }else{
                if(query.isHighscore(play)){
                    g2d.drawString("New HighScore:", 100, 550);
                    g2d.drawString(play.getAlias(), 500, 550);
                    g2d.drawString(String.valueOf(play.getScore()),700,550);
                }
                else{
                    g2d.drawString("Your Score:", 100, 550);
                    g2d.drawString(play.getAlias(), 500, 550);
                    g2d.drawString(String.valueOf(play.getScore()),700,550);
                }
            }  
        }
        else{
            g2d.drawString("There are no Scores", 450, 420);
        }
         
    }
    
    //Se dibuja "image" 
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,null);
    }
    
    
    public Player [] Ordenar(){
        hm = query.mostrar();
        Player players[] = new Player[hm.size()];
        Player aux[] = new Player[2];
        hm.values().toArray(players);
        //Iterator <Player> i = hm.values().iterator();
        int j = 0;
        /*while(i.hasNext()){
            players[j] = i.next();
            j++;
        }*/
        for( j = 0;j<players.length-1;j++)
            for(int k = 0; k<players.length-1;k++){
                if(players[k].getScore() < players[k+1].getScore()){
                    aux[0] = players[k+1];
                    aux[1] = players[k];
                    players[k] = aux[0];
                    players[k+1] = aux[1];   
                }
        }
        return players;
        
        
    }
    public void Imagenes() {
        setLayout(null);
        ImageIcon g = new ImageIcon("src/res/Highscore.png"); 
        l = new JLabel(g);
        l.setBounds(100, 0, 1000, 294); 
        add(l);
        
        try {
            image = ImageIO.read(new File("src/res/background.jpg"));
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        image = process(image);
        
    }
    private BufferedImage process(BufferedImage old){
        
        int w = old.getWidth();
        int h = old.getHeight();
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(old, 0, 0, w, h, this);
        g2d.setPaint(Color.yellow);
        g2d.setFont(font);
        addPlayers(g2d);
        g2d.dispose();
        return img;

    }
}
