/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import res.ResReference;

import java.io.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author Carlos
 */
public class EfectoS {
    private URL efecto1= ResReference.class.getResource("ef/enemy hit.wav");
    private URL efecto2= ResReference.class.getResource("ef/fighter sword 1.wav");
    private URL efecto3= ResReference.class.getResource("ef/key 1.wav");
    private URL efecto4= ResReference.class.getResource("ef/73 - Game Over.wav");
    private URL efecto5= ResReference.class.getResource("ef/heart.wav");
    private URL efecto6= ResReference.class.getResource("ef/bomb explode.wav");
    private URL efecto7= ResReference.class.getResource("ef/boss hit.wav");
    private URL efecto8= ResReference.class.getResource("ef/thunder.wav");
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
                    audio.open(AudioSystem.getAudioInputStream(efecto1));
                    audio.start();
                    audio.loop(0);
                }
                if(i==2 && !audio.isRunning()){
                    audio.close();
                    audio.open(AudioSystem.getAudioInputStream(efecto2));
                    audio.start();
                    audio.loop(0);
                }
                if(i==3){
                    audio.close();
                    audio.open(AudioSystem.getAudioInputStream(efecto3));
                    audio.start();
                    audio.loop(0);
                }
                if(i==4){
                    if(audio.isRunning()){
                        audio.stop();
                    }
                    audio.close();
                    audio.open(AudioSystem.getAudioInputStream(efecto4));
                    audio.start();
                    audio.loop(0);
                }
                if(i==5 && !audio.isRunning()){
                    audio.close();
                    audio.open(AudioSystem.getAudioInputStream(efecto5));
                    audio.start();
                    audio.loop(0);
                }
                if(i==6 && !audio.isRunning()){
                    audio.close();
                    audio.open(AudioSystem.getAudioInputStream(efecto6));
                    audio.start();
                    audio.loop(0);
                }
                if(i==7 && !audio.isRunning()){
                    audio.close();
                    audio.open(AudioSystem.getAudioInputStream(efecto7));
                    audio.start();
                    audio.loop(0);
                }
                if(i==8){
                    if(audio.isRunning()){
                        audio.stop();
                    }
                    audio.close();
                    audio.open(AudioSystem.getAudioInputStream(efecto8));
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
