package com.ggl.game2048;

import javax.swing.SwingUtilities;

import com.ggl.game2048.model.Game2048Model;
import com.ggl.game2048.view.Game2048Frame;

public class Game2048 implements Runnable {

   @Override
   public void run() {
       new Game2048Frame(new Game2048Model());
   }
    
   public static void main(String[] args) {
       SwingUtilities.invokeLater(new Game2048());
   }

}
