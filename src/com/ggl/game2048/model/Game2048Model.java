package com.ggl.game2048.model;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
 
public class Game2048Model {
     
    private static final boolean DEBUG = false;
     
    private static final int FRAME_THICKNESS = 16;
    private static final int GRID_WIDTH = 4;
     
    private boolean arrowActive;
     
    private int highScore;
    private int highCell;
    private int currentScore;
    private int currentCell;
     
    private Cell[][] grid;
     
    private Random random;
     
    public Game2048Model() {
        this.grid = new Cell[GRID_WIDTH][GRID_WIDTH];
        this.random = new Random();
        this.highScore = 0;
        this.highCell = 0;
        this.currentScore = 0;
        this.currentCell = 0;
        this.arrowActive = false;
        initializeGrid();
    }
     
    public void initializeGrid() {
        int xx = FRAME_THICKNESS;
        for (int x = 0; x < GRID_WIDTH; x++) {
            int yy = FRAME_THICKNESS;
            for (int y = 0; y < GRID_WIDTH; y++) {
                Cell cell = new Cell(0);
                cell.setCellLocation(xx, yy);
                grid[x][y] = cell;
                yy += FRAME_THICKNESS + Cell.getCellWidth();
            }
            xx += FRAME_THICKNESS + Cell.getCellWidth();
        }
    }
     
    public void setHighScores() {
        highScore = (currentScore > highScore) ?
                currentScore : highScore;
        highCell = (currentCell > highCell) ?
                currentCell : highCell;
        currentScore = 0;
        currentCell = 0;
    }
     
    public boolean isGameOver() {
        return isGridFull() && !isMovePossible();
    }
     
    private boolean isGridFull() {
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = 0; y < GRID_WIDTH; y++) {
                if (grid[x][y].isZeroValue()) {
                    return false;
                }
            }
        }
        return true;
    }
     
    private boolean isMovePossible() {
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = 0; y < (GRID_WIDTH - 1); y++) {
                int yy = y + 1;
                if (grid[x][y].getValue() == grid[x][yy].getValue()) {
                    return true;
                }
            }
        }
         
        for (int y = 0; y < GRID_WIDTH; y++) {
            for (int x = 0; x < (GRID_WIDTH - 1); x++) {
                int xx = x + 1;
                if (grid[x][y].getValue() == grid[xx][y].getValue()) {
                    return true;
                }
            }
        }
         
        return false;
    }
     
    public void addNewCell() {
        int value = (random.nextInt(10) < 9) ?  2 : 4;
         
        boolean locationFound = false;
        while(!locationFound) {
            int x = random.nextInt(GRID_WIDTH);
            int y = random.nextInt(GRID_WIDTH);
            if (grid[x][y].isZeroValue()) {
                grid[x][y].setValue(value);
                locationFound = true;
                if (DEBUG) {
                    System.out.println(displayAddCell(x, y));
                }
            }
        }
         
        updateScore(0, value);
    }
     
    private String displayAddCell(int x, int y) {
        StringBuilder builder = new StringBuilder();
        builder.append("Cell added at [");
        builder.append(x);
        builder.append("][");
        builder.append(y);
        builder.append("].");
         
        return builder.toString();
    }
     
    public boolean moveCellsUp() {
        boolean dirty = false;
         
        if (moveCellsUpLoop())  dirty = true;
         
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = 0; y < (GRID_WIDTH - 1); y++) {
                int yy = y + 1;
                dirty = combineCells(x, yy, x, y, dirty);
            }
        }
         
        if (moveCellsUpLoop())  dirty = true;
         
        return dirty;
    }
     
    private boolean moveCellsUpLoop() {
        boolean dirty = false;
         
        for (int x = 0; x < GRID_WIDTH; x++) {
            boolean columnDirty = false;
            do {
                columnDirty = false;
                for (int y = 0; y < (GRID_WIDTH - 1); y++) {
                    int yy = y + 1;
                    boolean cellDirty = moveCell(x, yy, x, y);
                    if (cellDirty) {
                        columnDirty = true;
                        dirty = true;
                    }
                }
            } while (columnDirty);     
        }
         
        return dirty;
    }
     
    public boolean moveCellsDown() {
        boolean dirty = false;
         
        if (moveCellsDownLoop())    dirty = true;
         
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = GRID_WIDTH - 1; y > 0; y--) {
                int yy = y - 1;
                dirty = combineCells(x, yy, x, y, dirty);
            }
        }
         
        if (moveCellsDownLoop())    dirty = true;
         
        return dirty;
    }
     
    private boolean moveCellsDownLoop() {
        boolean dirty = false;
         
        for (int x = 0; x < GRID_WIDTH; x++) {
            boolean columnDirty = false;
            do {
                columnDirty = false;
                for (int y = GRID_WIDTH - 1; y > 0; y--) {
                    int yy = y - 1;
                    boolean cellDirty = moveCell(x, yy, x, y);
                    if (cellDirty) {
                        columnDirty = true;
                        dirty = true;
                    }
                }
            } while (columnDirty);     
        }
         
        return dirty;
    }
     
    public boolean moveCellsLeft() {
        boolean dirty = false;
         
        if (moveCellsLeftLoop())    dirty = true;
         
        for (int y = 0; y < GRID_WIDTH; y++) {
            for (int x = 0; x < (GRID_WIDTH - 1); x++) {
                int xx = x + 1;
                dirty = combineCells(xx, y, x, y, dirty);
            }
        }
         
        if (moveCellsLeftLoop())    dirty = true;
         
        return dirty;
    }
     
    private boolean moveCellsLeftLoop() {
        boolean dirty = false;
         
        for (int y = 0; y < GRID_WIDTH; y++) {
            boolean rowDirty = false;
            do {
                rowDirty = false;
                for (int x = 0; x < (GRID_WIDTH - 1); x++) {
                    int xx = x + 1;
                    boolean cellDirty = moveCell(xx, y, x, y);
                    if (cellDirty) {
                        rowDirty = true;
                        dirty = true;
                    }
                }
            } while (rowDirty);    
        }
         
        return dirty;
    }
     
    public boolean moveCellsRight() {
        boolean dirty = false;
         
        if (moveCellsRightLoop())   dirty = true;
         
        for (int y = 0; y < GRID_WIDTH; y++) {
            for (int x = (GRID_WIDTH - 1); x > 0; x--) {
                int xx = x - 1;
                dirty = combineCells(xx, y, x, y, dirty);
            }
        }
         
        if (moveCellsRightLoop())   dirty = true;
         
        return dirty;
    }
 
    private boolean moveCellsRightLoop() {
        boolean dirty = false;
         
        for (int y = 0; y < GRID_WIDTH; y++) {
            boolean rowDirty = false;
            do {
                rowDirty = false;
                for (int x = (GRID_WIDTH - 1); x > 0; x--) {
                    int xx = x - 1;
                    boolean cellDirty = moveCell(xx, y, x, y);
                    if (cellDirty) {
                        rowDirty = true;
                        dirty = true;
                    }
                }
            } while (rowDirty);    
        }
         
        return dirty;
    }
     
    private boolean combineCells(int x1, int y1, int x2, int y2,
            boolean dirty) {
        if (!grid[x1][y1].isZeroValue()) {
            int value = grid[x1][y1].getValue();
            if (grid[x2][y2].getValue() == value) {
                int newValue = value + value;
                grid[x2][y2].setValue(newValue);
                grid[x1][y1].setValue(0);
                updateScore(newValue, newValue);
                dirty = true;
            }
        }
        return dirty;
    }
     
    private boolean moveCell(int x1, int y1, int x2, int y2) {
        boolean dirty = false;
        if (!grid[x1][y1].isZeroValue()
                && (grid[x2][y2].isZeroValue())) {
            if (DEBUG) {
                System.out.println(displayMoveCell(x1, y1, x2, y2));
            }
            int value = grid[x1][y1].getValue();
            grid[x2][y2].setValue(value);
            grid[x1][y1].setValue(0);
            dirty = true;
        }
        return dirty;
    }
     
    private String displayMoveCell(int x1, int y1, int x2, int y2) {
        StringBuilder builder = new StringBuilder();
        builder.append("Moving cell [");
        builder.append(x1);
        builder.append("][");
        builder.append(y1);
        builder.append("] to [");
        builder.append(x2);
        builder.append("][");
        builder.append(y2);
        builder.append("].");
         
        return builder.toString();
    }
     
    private void updateScore(int value, int cellValue) {
        currentScore += value;
        currentCell = (cellValue > currentCell) ?
                cellValue : currentCell;
    }
     
    public Cell getCell(int x, int y) {
        return grid[x][y];
    }
     
    public int getHighScore() {
        return highScore;
    }
 
    public int getHighCell() {
        return highCell;
    }
 
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
 
    public void setHighCell(int highCell) {
        this.highCell = highCell;
    }
 
    public int getCurrentScore() {
        return currentScore;
    }
 
    public int getCurrentCell() {
        return currentCell;
    }
 
    public boolean isArrowActive() {
        return arrowActive;
    }
 
    public void setArrowActive(boolean arrowActive) {
        this.arrowActive = arrowActive;
    }
 
    public Dimension getPreferredSize() {
        int width = GRID_WIDTH * Cell.getCellWidth() +
                FRAME_THICKNESS * 5;
        return new Dimension(width, width);
    }
     
    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        Dimension d = getPreferredSize();
        g.fillRect(0, 0, d.width, d.height);
         
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = 0; y < GRID_WIDTH; y++) {
                grid[x][y].draw(g);
            }
        }
    }
 
}
