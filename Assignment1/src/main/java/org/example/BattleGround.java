package org.example;

public class BattleGround {
    private static final int numRows = 8;
    private static final int numCols = 8;
    private Position[][] grid;
    public BattleGround() {
        this.grid = new Position[numRows][numCols];
        initializeGrid();
    }

    private void initializeGrid() {

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                grid[i][j] = new Position("_",null, false);
            }
        }
    }
    public Position getElement(int row, int col){
        return this.grid[row][col];
    }

    public void setElement(int row, int col, Position position){
        this.grid[row][col] = position;
    }

}
