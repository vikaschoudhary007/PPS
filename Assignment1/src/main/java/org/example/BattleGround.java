package org.example;

/**
 * The {@code BattleGround} class represents the grid where the game is played. It is responsible for
 * managing positions on the grid and providing methods to retrieve and update elements at specific
 * positions.
 */
public class BattleGround {

    /**
     * The number of rows in the grid.
     */
    private static final int numRows = 8;

    /**
     * The number of columns in the grid.
     */
    private static final int numCols = 8;

    /**
     * A 2D array representing the grid, where each element is a Position object.
     */
    private Position[][] grid;

    /**
     * Constructs a new BattleGround instance with the specified number of rows and columns.
     * Initializes the grid and sets default positions for each element in the grid.
     */
    public BattleGround() {
        this.grid = new Position[numRows][numCols];
        initializeGrid();
    }

    /**
     * Initializes the grid with default positions for each element.
     */
    private void initializeGrid() {

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                grid[i][j] = new Position("_",null, false);
            }
        }
    }

    /**
     * Retrieves the Position object at the specified row and column.
     *
     * @param row The row index.
     * @param col The column index.
     * @return The Position object at the specified row and column.
     */
    public Position getElement(int row, int col){
        return this.grid[row][col];
    }


    /**
     * Sets the Position object at the specified row and column.
     *
     * @param row      The row index.
     * @param col      The column index.
     * @param position The Position object to set at the specified row and column.
     */
    public void setElement(int row, int col, Position position){
        this.grid[row][col] = position;
    }

}
