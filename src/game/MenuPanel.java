
package game;

import res.ResReference;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{
    private BufferedImage img;
    private JButton newGame, highScore, exit;
    public MenuPanel(Ventana ventana){
        newGame = new JButton("New Game");
        highScore = new JButton("High Score");
        exit = new JButton("Exit");
        this.setLayout(null);
        newGame.setVisible(true);
        highScore.setVisible(true);
        exit.setVisible(true);
        newGame.setBounds(545, 450, 200, 30);
        highScore.setBounds(545, 485, 200, 30);
        exit.setBounds(545, 520, 200, 30);
        this.add(newGame);
        this.add(highScore);
        this.add(exit);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEverything();
                ventana.startGame();
            }
        });
        highScore.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                removeEverything();
                ventana.Highscore();
            }
        
        });
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
    }
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        g.setColor(Color.red);
        try {
            img = ImageIO.read(ResReference.class.getResource("img/bgi.png"));
        } catch (IOException ex) {
            Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawImage(img, 0, 0, this);
    }
    public void removeEverything(){
        this.remove(newGame);
        this.remove(highScore);
        this.remove(exit);
        this.remove(this);
        
    }
    
}