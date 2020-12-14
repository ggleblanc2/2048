package com.ggl.game2048.view;
 
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;
 
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
import com.ggl.game2048.model.Game2048Model;
 
public class ScorePanel {
     
    private static final Insets regularInsets   =
            new Insets(10, 10, 0, 10);
    private static final Insets spaceInsets =
            new Insets(10, 10, 10, 10);
     
    private static final NumberFormat nf =
            NumberFormat.getInstance();
     
    private Game2048Model model;
     
    private JPanel panel;
     
    private JTextField highScoreField;
    private JTextField highCellField;
    private JTextField currentScoreField;
    private JTextField currentCellField;
 
    public ScorePanel(Game2048Model model) {
        this.model = model;
        createPartControl();
        updatePartControl();
    }
     
    private void createPartControl() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
 
        int gridy = 0;
         
        JLabel highScoreLabel = new JLabel("High Score:");
        addComponent(panel, highScoreLabel, 0, gridy, 1, 1,
                regularInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);
         
        highScoreField = new JTextField(6);
        highScoreField.setEditable(false);
        highScoreField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(panel, highScoreField, 1, gridy++, 1, 1,
                regularInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);
         
        JLabel highCellLabel = new JLabel("High Cell:");
        addComponent(panel, highCellLabel, 0, gridy, 1, 1,
                spaceInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);
         
        highCellField = new JTextField(6);
        highCellField.setEditable(false);
        highCellField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(panel, highCellField, 1, gridy++, 1, 1,
                spaceInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);
         
        JLabel currentScoreLabel = new JLabel("Current Score:");
        addComponent(panel, currentScoreLabel, 0, gridy, 1, 1,
                regularInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);
         
        currentScoreField = new JTextField(6);
        currentScoreField.setEditable(false);
        currentScoreField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(panel, currentScoreField, 1, gridy++, 1, 1,
                regularInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);
         
        JLabel currentCellLabel = new JLabel("Current High Cell:");
        addComponent(panel, currentCellLabel, 0, gridy, 1, 1,
                regularInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);
         
        currentCellField = new JTextField(6);
        currentCellField.setEditable(false);
        currentCellField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(panel, currentCellField, 1, gridy++, 1, 1,
                regularInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);
    }
 
    private void addComponent(Container container, Component component,
            int gridx, int gridy, int gridwidth, int gridheight,
            Insets insets, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
                gridwidth, gridheight, 1.0D, 1.0D, anchor, fill,
                insets, 0, 0);
        container.add(component, gbc);
    }
     
    public void updatePartControl() {
        highScoreField.setText(nf.format(model.getHighScore()));
        highCellField.setText(nf.format(model.getHighCell()));
        currentScoreField.setText(nf.format(model.getCurrentScore()));
        currentCellField.setText(nf.format(model.getCurrentCell()));
    }
 
    public JPanel getPanel() {
        return panel;
    }
    
}
