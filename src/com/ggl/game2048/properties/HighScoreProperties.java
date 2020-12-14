package com.ggl.game2048.properties;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
 
import com.ggl.game2048.model.Game2048Model;
 
public class HighScoreProperties {
     
    private static final String fileName =
            "game2048.properties";
     
    private static final String highCell  = "highCell";
    private static final String highScore = "highScore";
     
    private Game2048Model model;
 
    public HighScoreProperties(Game2048Model model) {
        this.model = model;
    }
 
    public void loadProperties() {
        Properties properties = new Properties();
         
        InputStream is = null;
        File file = new File(fileName);
        try {
            is = new FileInputStream(file);
            properties.load(is);
            model.setHighScore(Integer.parseInt(
                    properties.getProperty(highScore)));
            model.setHighCell(Integer.parseInt(
                    properties.getProperty(highCell)));
        } catch (FileNotFoundException e) {
             
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    public void saveProperties() {
        Properties properties = new Properties();
        properties.setProperty(highScore,
                Integer.toString(model.getHighScore()));
        properties.setProperty(highCell,
                Integer.toString(model.getHighCell()));
         
        OutputStream os = null;
        File file = new File(fileName);
         
        try {
            os = new FileOutputStream(file);
            properties.store(os, "2048 High Score");
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        try {
            if (os != null) {
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }

}