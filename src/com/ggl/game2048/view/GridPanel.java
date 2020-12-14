package com.ggl.game2048.view;
 
import java.awt.Graphics;
 
import javax.swing.JPanel;
 
import com.ggl.game2048.model.Game2048Model;
 
public class GridPanel extends JPanel {
 
    private static final long   serialVersionUID    =
            4019841629547494495L;
     
    private Game2048Model model;
     
    private GameOverImage image;
     
    public GridPanel(Game2048Model model) {
        this.model = model;
        this.setPreferredSize(model.getPreferredSize());
        this.image = new GameOverImage(model);
        this.image.run();
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        model.draw(g);
         
        if (model.isGameOver()) {
            g.drawImage(image.getImage(), 0, 0, null);
        }
    }
}
