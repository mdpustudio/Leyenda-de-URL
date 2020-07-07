/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import DBConnection.ZQuery;

/**
 *
 * @author Maestro
 */
public class Result extends JPanel{
    //Jframe frame;
    private JTextField alias = new JTextField(10);
    private JLabel o;
    private BufferedImage image;
    private Font font;
    private int X;
    private int Y;
    private String Name;
    private boolean Next = false;
    private ZQuery query =  new ZQuery();
    Highscore p;
    Ventana ventana = new Ventana();
    private frmCrono frm = new frmCrono();
    
    

    public void setNext(boolean Next) {
        this.Next = Next;
    }
    
    public boolean getNext(){
        return Next;
    }

    public JTextField getAlias() {
        return alias;
    }
    
    
    public Result(){
        
        nuevaFont();
        add();
        //frame = new Jframe();
        //frame.getFrame().add(this);
        ventana.getVent().setAlwaysOnTop(true);
        ventana.getCurrentPanel().setVisible(false);
        ventana.getVent().add(this);
            addMouseListener(new MouseAdapter(){
                @Override
                public void mousePressed(MouseEvent me){
                    X = me.getX();
                    Y = me.getY();
                    if(!Next){
                        if(X > 1000 && X < 1160 && Y > 550 && Y < 600){

                            if(alias.getText().length() >7){
                                /*Result.this.font = Result.this.font.deriveFont(Font.TRUETYPE_FONT,18);
                                Result.this.setFont(font);
                                System.out.println(Result.this.getGraphics().getFont());
                                Result.this.getGraphics().drawString("Su Alias solo puede tener un largo de 10", 200, 550);*/
                                JOptionPane.showMessageDialog(null, "Su Alias solo puede tener un largo de 7" , "Oops :v", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{

                                Next= true;
                                Name = alias.getText();
                                query.addScore(query.NewId(), Name, frm.getTime(), ventana.getPuntaje() /*1500*/);
                                Result.this.setVisible(false);
                                ventana.getVent().getContentPane().removeAll();
                                ventana.getVent().add(new Highscore());
                            }


                        }
                    }    
                }

            });
        
    }
    
    
    public void add(){
        setLayout(null);
        ImageIcon O = new ImageIcon("src/res/gameover.png");
        o = new JLabel(O);
        o.setBounds(300,0,600,377);
        
        add(o);
         try {
            if(ventana.getPanel().getNivelAct()==5){
                image = ImageIO.read(new File("src/res/elmofire.jpg"));
            } 
            else
                image = ImageIO.read(new File("src/res/elmofirewin.jpg"));   
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        image = process(image);
        //alias = new TextField(10);
        alias.setForeground(Color.yellow);
        alias.setFont(font);
        alias.setBackground(Color.black);
        alias.setBounds(500, 450, 475, 45);
        add(alias);
        
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,null);
    }
    
    private BufferedImage process(BufferedImage old){
        
        int w = old.getWidth();
        int h = old.getHeight();
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(old, 0, 0, w, h, this);
        g2d.setPaint(Color.yellow);
        font = font.deriveFont(Font.TRUETYPE_FONT,26);
        g2d.setFont(font);
        
        g2d.drawString("Alias :", 250, 485);
        g2d.drawString("NEXT", 1000, 575);
        g2d.dispose();
        return img;

    }
    
    
    public void nuevaFont(){
        File file = new File("src/res/terminator.ttf");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, file);
            font = font.deriveFont(Font.TRUETYPE_FONT,36);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            
            
        } catch (FontFormatException | IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
   
}
