package com.ggl.game2048.model;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
 
public class Cell {
     
    private static final int CELL_WIDTH = 120;
     
    private int value;
     
    private Point cellLocation;
     
    public Cell(int value) {
        setValue(value);
    }
 
    public static int getCellWidth() {
        return CELL_WIDTH;
    }
     
    public int getValue() {
        return value;
    }
 
    public void setValue(int value) {
        this.value = value;
    }
     
    public boolean isZeroValue() {
        return (value == 0);
    }
     
    public void setCellLocation(int x, int y) {
        setCellLocation(new Point(x, y));
    }
 
    public void setCellLocation(Point cellLocation) {
        this.cellLocation = cellLocation;
    }
 
    public void draw(Graphics g) {
        if (value == 0) {
            g.setColor(Color.GRAY);
            g.fillRect(cellLocation.x, cellLocation.y,
                    CELL_WIDTH, CELL_WIDTH);
        } else {       
            Font font = g.getFont();
            FontRenderContext frc =
                    new FontRenderContext(null, true, true);
     
            String s = Integer.toString(value);
            BufferedImage image =
                    createImage(font, frc, CELL_WIDTH, s);
             
            g.drawImage(image, cellLocation.x, cellLocation.y, null);
        }
    }
     
    private BufferedImage createImage(Font font, FontRenderContext frc,
            int width, String s) {
 
        Font largeFont = font.deriveFont((float) (width / 4));
        Rectangle2D r = largeFont.getStringBounds(s, frc);
        int rWidth = (int) Math.round(r.getWidth());
        int rHeight = (int) Math.round(r.getHeight());
        int rX = (int) Math.round(r.getX());
        int rY = (int) Math.round(r.getY());
 
        BufferedImage image = new BufferedImage(width, width,
                BufferedImage.TYPE_INT_RGB);
         
        Graphics gg = image.getGraphics();
        gg.setColor(getTileColor());
        gg.fillRect(0, 0, image.getWidth(), image.getHeight());
 
        int x = (width / 2) - (rWidth / 2) - rX;
        int y = (width / 2) - (rHeight / 2) - rY;
         
        gg.setFont(largeFont);
        gg.setColor(getTextColor());
        gg.drawString(s, x, y);
        gg.dispose();
        return image;
    }
     
    private Color getTileColor() {
        Color color = Color.WHITE;
         
        switch (value) {
            case 2:     color = Color.WHITE;
                        break;
            case 4:     color = Color.WHITE;
                        break;
            case 8:     color = new Color(255, 255, 170);
                        break;
            case 16:    color = new Color(255, 255, 128);
                        break;
            case 32:    color = new Color(255, 255, 85);
                        break;
            case 64:    color = new Color(255, 255, 43);
                        break;
            case 128:   color = new Color(255, 255, 0);
                        break;
            case 256:   color = new Color(213, 213, 0);
                        break;
            case 512:   color = new Color(170, 170, 0);
                        break;
            case 1024:  color = new Color(128, 128, 0);
                        break;
            case 2048:  color = new Color(85, 85, 0);
                        break;
            default:    color = new Color(43, 43, 0);
                        break;
        }
         
        return color;
    }
     
    private Color getTextColor() {
        return (value >= 256) ? Color.WHITE : Color.BLACK;
    }
}