/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;


import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Carlos
 */
public class Ventana extends JPanel{
    private int i,w,h;
    static private Panel panel;
    static private JPanel currentPanel;
    private MenuPanel MenuPanel;
    private static JFrame vent;
    private Highscore high;
    
    public Ventana(){
        
    }
    public Ventana(String titulo, int w, int h){
        this.w = w;
        this.h = h;
        vent= new JFrame(titulo);
        //TecladoO tecO= new TecladoO();
        //vent.add(tecO);
        vent.setSize(w,h);
        //panel = new Panel(w,h);
        MenuPanel = new MenuPanel(this);
        currentPanel = MenuPanel;
        
        vent.add(currentPanel);
        vent.setResizable(false);
        vent.setVisible(true);
        vent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    
    public  JFrame getVent() {
        return vent;
    }
    public int getPuntaje(){
        return panel.getNivel().getPuntaje();
    }
    
    
    public int getI() {
        return i;
    }

    public Panel getPanel() {
        return panel;
    }
    public JPanel getCurrentPanel() {
        return currentPanel;
    }
    public void startGame(){
        panel = new Panel(w,h);
        vent.remove(currentPanel);
        currentPanel = panel;
        currentPanel.setFocusable(false);
        vent.add(panel);
        panel.setFocusable(true);
        panel.requestFocus();
        i = 1;
    }
    public void Highscore(){
        currentPanel.setVisible(false);
        vent.remove(currentPanel);
        high = new Highscore();
        currentPanel =  high;
        currentPanel.setVisible(true);
        currentPanel.setFocusable(false);
        vent.add(high);
        high.setFocusable(true);
        high.requestFocus();
        
    }
    
    
    
}
