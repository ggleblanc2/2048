package com.ggl.game2048.controller;
 
import java.awt.event.ActionEvent;
 
import javax.swing.AbstractAction;
 
import com.ggl.game2048.model.Game2048Model;
import com.ggl.game2048.view.Game2048Frame;
 
public class LeftArrowAction extends AbstractAction {
 
    private static final long serialVersionUID = 863330348471372562L;
 
    private Game2048Frame frame;
     
    private Game2048Model model;
 
    public LeftArrowAction(Game2048Frame frame, Game2048Model model) {
        this.frame = frame;
        this.model = model;
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.isArrowActive()) {
            if (model.moveCellsLeft()) {
                if (model.isGameOver()) {
                    model.setArrowActive(false);
                } else {
                    model.addNewCell();
                     
                    frame.repaintGridPanel();
                    frame.updateScorePanel();
                }
            }
        }
    }
 
}
