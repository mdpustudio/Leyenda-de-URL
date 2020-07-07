/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author Josseh
 */
public class Level {
    private TileClass tileSprites;
    private int fil = 20, col = 40;
    private String[][] levelDefinition;
    private String levelDefinitionURL;
    private Scanner fileReader;
    private File file;
    private TileObject[][] tileObjectMatrix = new TileObject[20][40];
    
    public Level(String levelDefinitionURL){
        this.levelDefinitionURL = levelDefinitionURL;
        levelDefinition = new String[fil][col];
        file = new File(levelDefinitionURL);
        tileSprites = new TileClass();
        defineLevel();
        defineTileObjectMatrix();
        
    }
    private void defineLevel(){
        try {
            fileReader = new Scanner(file);
            for(int i = 0; i < fil; i++){
                levelDefinition[i] = fileReader.nextLine().split(",");
            }
            System.out.println("Se ha cargado el nivel correctamente");
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error al cargar el archivo de definicion de nivel: " + ex.getMessage());
        }   
    }
    
    public void paint(Graphics g, ImageObserver observer){
        for(int i = 0; i < fil; i++){
                for(int j = 0; j < col; j++){
                    g.drawImage(tileSprites.getTileSprites().get(Integer.parseInt(levelDefinition[i][j])).getTileImg(), (j*32), (i*32), observer);
                }
           
            }
    }
    
    private void defineTileObjectMatrix(){
        for(int i = 0; i < fil; i++){
                for(int j = 0; j < col; j++){
                    tileObjectMatrix[i][j] = tileSprites.getTileSprites().get(Integer.parseInt(levelDefinition[i][j]));
                } 
            }
    }

    public String[][] getLevelDefinition() {
        return levelDefinition;
    }

    public TileObject[][] getTileObjectMatrix() {
        return tileObjectMatrix;
    }
    
}
