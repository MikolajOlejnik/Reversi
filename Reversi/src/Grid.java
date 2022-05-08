import java.util.Scanner;

/**
 * @author Mikolaj Olejnik
 * Class is used to display the grid on which the game is played on and perform check regarding the grid
 */
public class Grid 
{
	
	/** Method used to set the initial layout of the grid 
	 * @param board - current board
	 * @return board - The grid with the starting discs */
	public char [][] initialiseGrid(char [][] board)
	{
		//Loops for each value in the columns
		for (int x = 0; x < 8; x++)
		{
			//Loops for each value in the rows
			for (int y = 0; y < 8; y++)
			{
				
				//Next few lines of code check for specific places on the board and sets them to starting values
				if ((x == 3 && y == 3) || (x == 4 && y == 4))
				{
					board [x] [y] = 'X';
				}
				
				else if ((x == 3 && y == 4) || (x == 4 && y == 3))
				{
					board [x] [y] = 'O';
				}
				
				//Sets all other positions to empty squares
				else
				{
					board [x] [y]= '.';
				}
			}
		}
		return board;
	}
	
	/** Method used to show the position of all discs on the board 
	 * @param board - The grid */
	public void displayGrid(char[][] board)
	{
		//Formatting for the grid
		System.out.print("  ");
		
		//Prints top row of numbers
		for (int x = 0; x < 8; x++)
		{
			System.out.print(x + 1 + " ");
		}
		
		//Formatting, makes it so the rows start from the next line not the same one
		System.out.println();
		
		for (int x = 0; x < 8; x++)
		{
			//Prints side column of numbers
			System.out.print(x + 1 + " ");
			
			//Prints the disc at each position
			for (int y = 0; y < 8; y++)
			{
				System.out.print(board[x][y] + " ");
			}
			
		//Takes a new line after each row
		System.out.println();
		
		}
	}
	
	/** Determines which row the user wants to place their disc */
	public int row()
	{
        int row; //Initialising
        
        do 
        {
        	Scanner s1 = new Scanner(System.in);
            System.out.print("Enter which row you'd like to play your disc in: ");
            row = s1.nextInt(); // Sets row to user input

            //Subtracts one for valid input (since user input of "1" would result in the second row being selected due to arrays starting at 0)
            if (row > 0 && row < 9)
            {
                row--;
                return row; //Ends early for valid input
            }
            
            else
            {
                System.out.println("Invalid input, enter a number between 1 and 8: ");
            }
            
        }
        while (row < 1 || row > 8); //Loops until a valid row is given
        
        return row;
    }
	
	/** Determines which column the user wants to place their disc */
	public int col()
	{
        int col; //Initialising
        
        do
        {
        	Scanner s2 = new Scanner(System.in);
            System.out.print("Enter which column you'd like to play your disc in: ");
            col = s2.nextInt(); // Sets column to user input

            if (col > 0 && col < 9) //Subtracts one for valid input (same logic as row)
            {
                col--;
                return col;
            }
            
            else
            {
                System.out.println("Invalid input, enter a number between 1 and 8: ");
            }
            
        }
        while (col < 1 || col > 8); //Loops until a valid input is given
        
        return col;
    }
	
	/** Used to find whether there are empty spaces on the board
	 * @param board - The playing grid
	 * @return true -  if there are no free spaces, otherwise false */
    public boolean boardSpace(char[][] board)
    {
        int counter = 0; //Initialising

        for (int i = 0; i < 8; i++) //Loops for each row
        {
            for (int j = 0; j < 8; j++) //Loops for each column
            {

                if (board[i][j] == '.') //Checks whether there is an empty space represented by "."
                {
                    counter++; //If so, increments counter
                }
            }
        }
 
        if (counter == 0)
        {
            return true;
        }
        
        else
        {
            return false;
        }
    }
    
    /** Checks whether the square is empty
     * @param board - The grid
     * @param row - Row input by user
     * @param col - Column input by user
     * @return true - if position is taken, else false */
    public boolean emptyPos(char[][] board, int row, int col)
    {
    	//Checks whether there is a "X" or "O"
        if (board[row][col] == 'X' || board[row][col] == 'O')
        {
            System.out.println("Invalid move, position taken");
            return true; //If yes, returns true
        }
        
    return false;
    }
}