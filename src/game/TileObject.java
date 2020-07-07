/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.image.BufferedImage;

/**
 *
 * @author Josseh
 */
public class TileObject {
    private BufferedImage tileImg;
    public boolean walkable;
    public boolean harmful;
    
    public TileObject(BufferedImage tileImg, boolean walkable, boolean harmful){
        this.tileImg = tileImg;
        this.walkable = walkable;
        this.harmful = harmful;
    }

    public BufferedImage getTileImg() {
        return tileImg;
    }
    
    
    
}
