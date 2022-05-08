import java.util.Scanner;

/**
 * @author Mikolaj Olejnik
 * Used to determine the amount of discs each player has and the winner
 */
public class Player {
	
	/** Finds the amount of X discs on the grid 
	 * @param grid - The grid on which the game is played on
	 * @return countX - Amount of X discs
	 * */
	public int countX(char[][] grid)
	{
        int countX = 0; //Sets counter to 0
 
        //Loops for each item in the grid, increments counter if it's an x
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (grid[i][j] == 'X')
                {
                    countX++;
                }
            }
        }
        return countX;
    }
	
	/** Finds the amount of O discs on the grid 
	 * @param grid - The grid on which the game is played on
	 * @return countO - Amount of O discs
	 * */
	public int countO(char[][] grid)
	{
        int countO = 0; //Sets counter to 0
        
        //Loops for each item in the grid, increments counter if it's an x
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
 
                if (grid[i][j] == 'O')
                {
                    countO++;
                }
            }
        }
        return countO;
    }
	
	/** Method to find out who wins the game 
	 * @param countX - Amount of X discs
	 * @param countO - Amount of O discs
	 * */
	public void results(int countX, int countO) {
		 
        if (countX > countO) 
        {
            System.out.println("X wins with " + countX + " discs" + "O has " + countO + " discs");
        }
        
        else if (countO > countX)
        {
            System.out.println("O wins with " + countO + " discs" + "X has " + countX + " discs");
        }
        
        else //In this case countX and countO are equal, meaning it's a draw
        {
            System.out.println("The game ended in a draw, each player has " + countX + " discs");
        }
    }
}