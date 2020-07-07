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
public class Reproductor {
    private URL nivel1= ResReference.class.getResource("msc/Wonder Boy The Dragon’s Trap O.S.T - Mind of Hero (Remaster).wav");
    private URL nivel2= ResReference.class.getResource("msc/Uncharted 2 Among Thieves - Reunion.wav");
    private URL nivel3= ResReference.class.getResource("msc/Gears of War 4 - 17 Windflare [OST].wav");
    private URL nivel4= ResReference.class.getResource("msc/01 - Mountain Village.wav");
    
    private Clip audio;
    private boolean stop=true;

    public Reproductor() {
        try {
                audio= AudioSystem.getClip();
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public void setMusica(int i){
        try {
                if(i==1){
                    audio.open(AudioSystem.getAudioInputStream(nivel1));
                }
                if(i==2){
                    audio.open(AudioSystem.getAudioInputStream(nivel2));
                }
                if(i==3){
                    audio.open(AudioSystem.getAudioInputStream(nivel3));
                }
                if(i==4){
                    audio.open(AudioSystem.getAudioInputStream(nivel4));
                }
                        
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public Clip getAudio() {
        return audio;
    }
    

    public void setStop(boolean stop) {
        this.stop = stop;
    }
    
    
    
    
}
