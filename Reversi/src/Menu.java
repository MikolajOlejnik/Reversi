//Importing Java elements
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Mikolaj Olejnik
 * Class displaying the menu and playing Reversi
 */
public class Menu {
	
	//Initialising variables
	public char grid [][];
	
	public Grid board;
	public Player user;
	
	/**
	 * @param args - main method
	 */
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.inputChoice();
	}

	/** Constructor assigning instances of the classes to the previously declared references
	 * Creates a grid
	 *  */
	public Menu()
	{
		grid = new char ['i']['j'];
		board = new Grid();
		user = new Player();
	}
	
	/** Prints the menu */
	public static void displayMenu()
	{
        System.out.println("Select from the following options");
        System.out.println("0. Exit");
        System.out.println("1. New game");
        System.out.println("2. Save current game");
        System.out.println("3. Load a saved game");
	}
	
	/** Processes user choice from the menu, runs corresponding methods */
	public void inputChoice()
	{
		boolean keepGoing = true; //Used to determine whether to end game or continue
		
		do
		{
			int choice; //Asks the user to choose a menu option
			displayMenu();
			Scanner s3 = new Scanner(System.in);
			choice = s3.nextInt();
		
			//Switch used to determine which methods will run
			switch (choice)
			{
				case 1:
					newGame();
					mainGame();
					break;
					
				case 2:
					saveGame();
					break;
					
				case 3:
					loadGame();
					mainGame();
					break;
					
				case 0:
					System.exit(0);
			}
		
		}
		while(keepGoing); // Loop until keepGoing is changed to false
	}
	
	/** Creates a new board */
	public void newGame()
	{
		board.initialiseGrid(grid);
	}
	
	/** Handles the game play of Reversi */
	public void mainGame()
	{
		boolean keepGoing = true; //Used to determine when to stop running the game
		int counter = 0; //Used to determine which player's turn it is
		char player = 'i'; //Initialising, placeholder
		
        while (keepGoing)
        {
        	//Shows how many discs each player has, each turn
            System.out.println("O has " + user.countO(grid) + " discs on the grid");
            System.out.println("X has " + user.countX(grid) + " discs on the grid");
            
            //Stops the game if there no empty spaces on the grid
            if (board.boardSpace(grid))
            {
            	System.out.println("No spaces left on the grid\n");
            	user.results(user.countX(grid), user.countO(grid));
            	keepGoing = false;
            }
            
            //This runs when there are empty spaces
            else
            {
            	//For each odd turn, the player is X
            	if (counter % 2 == 1)
            	{
            		player = 'X';
            	}
            	
            	//For each even turn, the player is O
            	else
            	{
            		player = 'O';
            	}
            	
            	board.displayGrid(grid);
            	
            	//These prompt the user to enter a row and column in which they'd like to place their disc
            	int posX = board.row();
            	int posY = board.col();
            	
            	//When it's empty, it sets the users disc to that position
            	if (!board.emptyPos(grid, posX, posY))
            	{
            		grid[posX][posY] = player;
            		counter += 1;
            	}
            }
            
            //Asks if user wants to open menu
            System.out.println("Would you like to open the menu? Enter Y or N");
            Scanner s4 = new Scanner(System.in);
            String input = s4.nextLine();
            
            //If yes, sets keepGoing to false meaning it leaves the main game loop
            if (input.equals("Y"))
            {
                keepGoing = false;
            }
            
            //Otherwise continues playing the game
            else if (input.equals("N"))
            {
            	keepGoing = true;
            }
        }
	}
	
	/** Writes the current board into a file */
	public void saveGame()
	{
		PrintWriter printWriter = null; //Initialising
	 
	    try //Try in case of errors
	    {
	        Scanner s5 = new Scanner(System.in);
	        System.out.print("What would you like to save the game as: ");
	        //Creates a file to write to with a .txt extension
	        printWriter = new PrintWriter(new FileOutputStream(s5.nextLine() + ".txt"));
	    }
	        
	    catch (IOException e) //Displays error when file is not found
	    {
	    	System.out.println("Error: " + e);
	    }
	        
	    finally
	    {
	    	for (int i = 0; i < 8; i++) //Loops for each row
	    	{
	    		for (int j = 0; j < 8; j++) //Loops for each column
	    		{
	    			printWriter.print(grid[i][j] + "\n"); //Writes each item in the first row going along the columns, prints a new line after them
	    		}
	    	}
	    	printWriter.print(board);
	    	printWriter.close();
	    }
	}
	 
	/** Reads from a file and creates a board out of it 
	 * @return grid - 2D array of the board
	 * */
	public char[][] loadGame()
	{
		//Initialising
		BufferedReader bufferedReader = null;
		String nextLine = null;

		try //Try in case of errors
		{
			System.out.print("Enter the file name: ");
	 
			Scanner s6 = new Scanner(System.in);
			bufferedReader = new BufferedReader(new FileReader(s6.nextLine())); //Opens file given
			nextLine = bufferedReader.readLine(); //Read line in file
	 
			//Loops for grid size
			for (int i = 0; i < 8; i++)
			{
				//While there is a next line to read
				if (nextLine != null)
				{
					for (int j = 0; j < 8; j++)
					{
						grid[i][j] = nextLine.charAt(0); //Assigns the read character to the corresponding position on the board
						nextLine = bufferedReader.readLine(); //Reads next line
					}
				}
			}
		}
		
		//When filename is not found, prints error
		catch (IOException e)
		{
			System.out.println("Error " + e);
		}
	        
		return grid;
	}
}
