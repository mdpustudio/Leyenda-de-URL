/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author Carlos
 */
public class EfectoS { 
    private String efecto1= "src/ef/enemy hit.wav";
    private String efecto2= "src/ef/fighter sword 1.wav";
    private String efecto3= "src/ef/key 1.wav";
    private String efecto4= "src/ef/73 - Game Over.wav";
    private String efecto5= "src/ef/heart.wav";
    private String efecto6= "src/ef/bomb explode.wav"; 
    private String efecto7= "src/ef/boss hit.wav"; 
    private String efecto8= "src/ef/thunder.wav"; 
    private Clip audio;
    
    public EfectoS() {
        try {
                audio= AudioSystem.getClip();
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void setEfecto(int i){
        try {
                if(i==1 && !audio.isRunning()){
                    audio.close();
                    audio.open(AudioSystem.getAudioInputStream(new File(efecto1)));
                    audio.start();
                    audio.loop(0);
                }
                if(i==2 && !audio.isRunning()){
                    audio.close();
                    audio.open(AudioSystem.getAudioInputStream(new File(efecto2)));
                    audio.start();
                    audio.loop(0);
                }
                if(i==3){
                    audio.close();
                    audio.open(AudioSystem.getAudioInputStream(new File(efecto3)));
                    audio.start();
                    audio.loop(0);
                }
                if(i==4){
                    if(audio.isRunning()){
                        audio.stop();
                    }
                    audio.close();
                    audio.open(AudioSystem.getAudioInputStream(new File(efecto4)));
                    audio.start();
                    audio.loop(0);
                }
                if(i==5 && !audio.isRunning()){
                    audio.close();
                    audio.open(AudioSystem.getAudioInputStream(new File(efecto5)));
                    audio.start();
                    audio.loop(0);
                }
                if(i==6 && !audio.isRunning()){
                    audio.close();
                    audio.open(AudioSystem.getAudioInputStream(new File(efecto6)));
                    audio.start();
                    audio.loop(0);
                }
                if(i==7 && !audio.isRunning()){
                    audio.close();
                    audio.open(AudioSystem.getAudioInputStream(new File(efecto7)));
                    audio.start();
                    audio.loop(0);
                }
                if(i==8){
                    if(audio.isRunning()){
                        audio.stop();
                    }
                    audio.close();
                    audio.open(AudioSystem.getAudioInputStream(new File(efecto8)));
                    audio.start();
                    audio.loop(0);
                }
                
                
                        
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public Clip getAudio() {
        return audio;
    }
    
    
    
}
