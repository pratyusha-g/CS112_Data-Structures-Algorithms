package ps4;
import java.io.*;   // allows us to read from a file
import java.util.*;

public class Sudoku1 {
	// The current contents of the cells of the puzzle. 
    private int[][] grid;
    /*
     * Indicates whether the value in a given cell is fixed 
     * (i.e., part of the initial configuration).
     * valIsFixed[r][c] is true if the value in the cell 
     * at row r, column c is fixed, and false otherwise.
     */
    private boolean[][] valIsFixed;
    
    /*
     * This 3-D array allows us to determine if a given subgrid 
     * (i.e., a given 3x3 region of the puzzle) already contains a given value.
     * We use 2 indices to identify a given subgrid:
     *
     *    (0,0)   (0,1)   (0,2)
     *
     *    (1,0)   (1,1)   (1,2)
     * 
     *    (2,0)   (2,1)   (2,2)
     * 
     * For example, subgridHasVal[0][2][5] will be true if the subgrid in the 
     * upper right-hand corner already has a 5 in it, and false otherwise.
     */
    private boolean[][][] subgridHasVal;
	
    private int index;
	private int row;
	private int col;
    
    /*** ADD YOUR ADDITIONAL FIELDS HERE. ***/
    

    
    
    /* 
     * Constructs a new Puzzle object, which initially
     * has all empty cells.
     */
    public Sudoku1() {
        this.grid = new int[9][9];
        this.valIsFixed = new boolean[9][9];     
        
        /* 
         * Note that the third dimension of the following array is 10,
         * because we need to be able to use the possible values 
         * (1 through 9) as indices.
         */
        this.subgridHasVal = new boolean[3][3][10];        
        
        //keep track of cell number (to ensure the code stops once index reaches 81)
        this.index = 1;
        this.row = 0;
        this.col = 0;
        
        /*** INITIALIZE YOUR ADDITIONAL FIELDS HERE. ***/
        
        
    }
    
    /*
     * Place the specified value in the cell with the specified coordinates, 
     * and update the state of the puzzle accordingly.
     */
    public void placeVal(int val, int row, int col) {
        this.grid[row][col] = val;
        this.subgridHasVal[row/3][col/3][val] = true;
        
        /*** UPDATE YOUR ADDITIONAL FIELDS HERE. ***/
    }
        
    /*
     * remove the specified value from the cell with the specified coordinates, 
     * and update the state of the puzzle accordingly.
     */
    public void removeVal(int val, int row, int col) {
        this.grid[row][col] = 0;
        this.subgridHasVal[row/3][col/3][val] = false;
        
        
        /*** UPDATE YOUR ADDITIONAL FIELDS HERE. ***/
    }  
        
    /*
     * read in the initial configuration of the puzzle from the specified 
     * Scanner, and use that config to initialize the state of the puzzle.  
     * The configuration should consist of one line for each row, with the
     * values in the row specified as integers separated by spaces.
     * A value of 0 should be used to indicate an empty cell.
     * 
     * You should not change this method.
     */
    public void readConfig(Scanner input) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int val = input.nextInt();
                this.placeVal(val, r, c);
                if (val != 0) {
                    this.valIsFixed[r][c] = true;
                }
            }
            input.nextLine();
        }
    }
                
    /*
     * Displays the current state of the puzzle.
     * You should not change this method.
     */        
    public void printGrid() {
        for (int r = 0; r < 9; r++) {
            this.printRowSeparator();
            for (int c = 0; c < 9; c++) {
                System.out.print("|");
                if (this.grid[r][c] == 0)
                    System.out.print("   ");
                else
                    System.out.print(" " + this.grid[r][c] + " ");
            }
            System.out.println("|");
        }
        this.printRowSeparator();
    }
        
    // A private helper method used by display() 
    // to print a line separating two rows of the puzzle.
    private static void printRowSeparator() {
        for (int i = 0; i < 9; i++)
            System.out.print("----");
        System.out.println("-");
    }
    
    /*** ADD ANY ADDITIONAL METHODS HERE. ***/

    //condition 1: check if number is in the row
    private boolean rowHasVal(int[][] board, int row, int num) {
    	for (int i = 0; i < 9; i++) {
    		if (board[row][i]==num) {
    			return true;
    		}
    	}
    	return false;
    }
    
    //condition 2: check of number is in the column
    private boolean colHasVal(int[][] board, int col, int num) {
    	for (int i = 0; i < 9; i++) {
    		if(board[i][col] == num) {
    			return true;
    		}
    	}
    	return false;
    }
    
    //condition 3: check if number is in the subgrid
    //returns true if the subgrid has the value and false otherwise
    private boolean insideSubgrid( int row, int col, int num) {
    	//to get the range of rows and cols that form the subgrid of interest
    	
    	int r = row%3;
    	int c = col%3;
    	return subgridHasVal[r][c][num];
    	
    }
    
    //method to check if all 3 conditions are met
    private boolean isValid(int[][] board, int row, int col, int num) {
    	//all 3 conditions must be met
    	if (!rowHasVal(board, row, num) && !colHasVal(board, col, num) && !insideSubgrid( row, col, num)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    
    /*
     * This is the key recursive-backtracking method.
     * Returns true if a solution has already been found, and false otherwise.
     * 
     * There are different ways to use parameters in this method, 
     * and you will need to decide how many parameters you want to use 
     * and what they should mean.
     */
    
    
    
    public void nextCell() {
    	//updates, row, col and index
    	if (this.col < 8) {
    	this.col += 1;
    	} else {
    		this.row += 1;
    		this.col = 0;
    	}
    	this.index += 1;
    }
    public void previousCell() {
    	if (this.col == 0) {
    		this.row -=1;
    		this.col = 8;
    	}
    	else this.col -= 1;
    	this.index -= 1;
    }

    
    private boolean solveRB(int[][] board, int index) {
    	
    	if (this.index == 81) {
    		return true;
    	}
    	//go through each row
    	for(int r = 0; r < 9; r++) {
    		//each column
    		for (int c = 0; c < 9; c++) {
    			//if the value is fixed then go to next cell 
    			if (valIsFixed[r][c]) {
    				this.index+=1;
    				continue;
    			}
    			for (int num = 1; num <= 9; num++) {
    				if(isValid(board, r, c, num)) {
    					this.placeVal(num,r,c);
    					this.index+=1;
    					if (solveRB(board, this.index)) {
    						return true;
    					} else {
    						this.index -=1;
    						removeVal(num, r, c);
    					}
    					
    				}
    				
    			}
    			return false;
    		}
    	}
    	return true;
    }
    
    
    
    
    
    
    
    
    /*
    private boolean solveRB( int[][] board) {     // ADD IN YOUR PARAMETERS!
    	
    	if(this.index > 81) {
			return true;
		}
    	//using backtracking
    	for (int i = 0; i<9; i++) {
    		for (int j = 0; j<9; j++) {
    			//check if cell has fixed value
    			//if it does, skip this cell
    			if(valIsFixed[i][j]) {
    				//move to next cell
    				this.nextCell();
    				solveRB(board);
    			} else {
    			//for nonfixed cells
    			//check 1-9
    			for (int num = 1; num <= 9; num++) {
    				//check if num is valid at that cell
    				if(isValid(board, i, j, num)) {
    					//if yes, place number, go to next cell
    					placeVal(num, i, j);
    					this.nextCell();
    					//if subsequent recursion is true, return true
    					if (solveRB(board)) 
    						return true; 
    					
    					}
    				this.previousCell();
        			removeVal(board[i][j], i, j);
    					}
    			
    			
    				}
    			
    			
    			}
    	
    		}
    	return false; 
    }
    
    /*
     * public "wrapper" method for solveRB().
     * Makes the initial call to solveRB, and returns whatever it returns.
     */
    
    
    public boolean solve() { 

        boolean foundSol = this.solveRB(this.grid, 1); // ADD THE INITIAL PARAMETER VALS!
        return foundSol;
    }
    
    
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Sudoku1 puzzle = new Sudoku1();
        
        System.out.print("Enter the name of the puzzle file: ");
        String filename = scan.nextLine();

        
        try {
            Scanner input = new Scanner(new File(filename));
            puzzle.readConfig(input);
        } catch (IOException e) {
            System.out.println("error accessing file " + filename);
            System.out.println(e);
            System.exit(1);
        }

        
        System.out.println();
        System.out.println("Here is the initial puzzle: ");
        puzzle.printGrid();
        System.out.println();
        
        /*
        //test whether constraint checking works!
        //it works!
        
        System.out.println(puzzle.isValid(puzzle.grid, 0,0, 9));
        System.out.println(puzzle.isValid(puzzle.grid, 0, 0, 4));
        System.out.println(puzzle.isValid(puzzle.grid, 0, 0, 5));
        
        //test if nextCell works
        //it works
        System.out.println(puzzle.row);
        System.out.println(puzzle.col);
        System.out.println(puzzle.index);
        
        puzzle.nextCell();
        
        System.out.println(puzzle.row);
        System.out.println(puzzle.col);
        System.out.println(puzzle.index);
        
        //test if prefiousCell works
        //it works
        puzzle.previousCell();
        
        System.out.println(puzzle.row);
        System.out.println(puzzle.col);
        System.out.println(puzzle.index);
       */
        
        
        if (puzzle.solve()) {
            System.out.println("Here is the solution: ");
        } else {
            System.out.println("No solution could be found.");
            System.out.println("Here is the current state of the puzzle:");
        }
        puzzle.printGrid();  
        
    }    
}
