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
public class Reproductor {
    private String nivel1= "src/msc/Wonder Boy The Dragon’s Trap O.S.T - Mind of Hero (Remaster).wav";
    private String nivel2= "src/msc/Uncharted 2 Among Thieves - Reunion.wav";
    private String nivel3= "src/msc/Gears of War 4 - 17 Windflare [OST].wav";
    private String nivel4= "src/msc/01 - Mountain Village.wav";
    
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
                    audio.open(AudioSystem.getAudioInputStream(new File(nivel1)));
                }
                if(i==2){
                    audio.open(AudioSystem.getAudioInputStream(new File(nivel2)));
                }
                if(i==3){
                    audio.open(AudioSystem.getAudioInputStream(new File(nivel3)));
                }
                if(i==4){
                    audio.open(AudioSystem.getAudioInputStream(new File(nivel4)));
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
