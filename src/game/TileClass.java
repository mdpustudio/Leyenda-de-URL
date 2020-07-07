/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import res.ResReference;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Josseh
 */
public class TileClass {
    private HashMap<Integer, TileObject> TileSprites= new HashMap<>();
    private int spriteId = 0;
    
    public TileClass(){
        for(int i = 0; i <96; i+=32){
            for(int j = 0; j < 576; j+=32){
                try {
                    BufferedImage imagenTemporal;
                    imagenTemporal = cropImage(ImageIO.read(ResReference.class.getResource("img/tilesheet.png")), new Rectangle(32,32), j,i);
                    if((spriteId >=0 && spriteId <= 2) || (spriteId >=8 && spriteId <= 14) || (spriteId >=18 && spriteId <= 20) || (spriteId >=26 && spriteId <= 31) || (spriteId >=36 && spriteId <= 38)|| (spriteId >=44 && spriteId <= 45)|| (spriteId >=47 && spriteId <= 49)) TileSprites.put(spriteId, new TileObject(imagenTemporal, false, false));
                    else if(spriteId >= 11 && spriteId <= 18) TileSprites.put(spriteId, new TileObject(imagenTemporal, true, true));
                    else TileSprites.put(spriteId, new TileObject(imagenTemporal, true, false));
                    spriteId++;
                } catch (IOException ex) {
                    Logger.getLogger(TileClass.class.getName()).log(Level.SEVERE, null, ex);
                }                    
            }
        }
    }
    private BufferedImage cropImage(BufferedImage imagenOriginal, Rectangle rect, int posx, int posy){
        BufferedImage subImagen = imagenOriginal.getSubimage(posx, posy, rect.width, rect.height);
        return subImagen;
    }

    public HashMap<Integer, TileObject> getTileSprites() {
        return TileSprites;
    }
    
    
}
