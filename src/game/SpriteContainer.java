/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author UCA
 */
public class SpriteContainer {
    
    private HashMap<Integer, BufferedImage> sprites = new HashMap<>();
    private HashMap<Integer, BufferedImage> ATKSprites = new HashMap<>();
    public SpriteContainer(){ 
        int idSprite = 0;
        try{
            for(int i = 0; i < 128; i+=32){
                for(int j = 0; j <96; j+=32){
                    sprites.put(idSprite, cropImage(ImageIO.read(new File("src/res/charactersprites.png")), new Rectangle(32,32), j, i));
                    idSprite++;
                } 
            }
            idSprite = 0;
            for(int i = 0; i < 128; i+=32){
                for(int j = 0; j <96; j+=32){
                    ATKSprites.put(idSprite, cropImage(ImageIO.read(new File("src/res/charactersprites2.png")), new Rectangle(32,32), j, i));
                    idSprite++;
                } 
            }
            
            
        }catch(IOException e){
            System.out.println("Error al cargar Imagenes: " + e.getMessage());
        }
        
    }

    public HashMap<Integer, BufferedImage> getSprites() {
        return sprites;
    }

    public HashMap<Integer, BufferedImage> getATKSprites() {
        return ATKSprites;
    }
    
    private BufferedImage cropImage(BufferedImage imagenOriginal, Rectangle rect, int posx, int posy){
        BufferedImage subImagen = imagenOriginal.getSubimage(posx, posy, rect.width, rect.height);
        return subImagen;
    }
    
    
}
