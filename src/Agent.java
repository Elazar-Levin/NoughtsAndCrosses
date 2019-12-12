import java.util.ArrayList;
import java.util.Random;

public class Agent {
	private static TicTacToe ticTacToe;
	public static String method;
	private static Random myRandom;
	
	
	
	
	
	public Agent(TicTacToe ticTacToe, String method) {
		super();
		this.ticTacToe = ticTacToe;
		Agent.method = method;
		myRandom = new Random();
	}
	
	public Position move()
	{
		switch(method)
		{
			case "random":
				return random();
			case "minimax":
				return miniMaxRoot();
			default:
				return random();
		}
	}
	private static Position random()
	{
		ArrayList<Position> moves = ticTacToe.getPossibleMoves();
		if(moves.size() > 0)
		{
			//System.out.println(moves.size());
			int i = myRandom.nextInt(moves.size());
			return moves.get(i);
		}
		return null;
	}
	private static Position miniMaxRoot()
	{
		//TODO: Implement alpha beta pruning
		ArrayList<Position> moves = ticTacToe.getPossibleMoves();
		if(moves.size() > 0)
		{
			boolean isMax = ticTacToe.getCurrPlayer() == 'x';
			int best = 0;
			Position bestMove = null;
			if(!isMax)
				best = 10000;
			else 
				best = -10000;
			for(int i = 0; i < moves.size(); i++)
			{
				ticTacToe.move(moves.get(i));
				int value = miniMax(4, !isMax);
				if(isMax)
				{
					if(value > best)
					{
						best = value;
						bestMove = moves.get(i);
					}
				}
				else
				{
					if(value < best)
					{
						best = value;
						bestMove = moves.get(i);
					}
				}
				
				ticTacToe.undo();
				
			}
			return bestMove;
		}
		return null;
	}
	private static int miniMax(int depth, boolean isMax)
	{
		if(depth == 0 || calculateScore()!=0 || full())
			return calculateScore();
		ArrayList<Position> moves = ticTacToe.getPossibleMoves();
		if(isMax)
		{
			int best = - 10000;
			for(int i =0; i < moves.size(); i++)
			{
				ticTacToe.move(moves.get(i));
				int value = miniMax(depth - 1, !isMax);
				if(value > best)
				{
					best = value;
				}
				ticTacToe.undo();
			}
			return best;
		}
		else
		{
			int best = 10000;
			for(int i =0; i < moves.size(); i++)
			{
				ticTacToe.move(moves.get(i));
				int value = miniMax(depth - 1, !isMax);
				if(value < best)
				{
					best = value;
				}
				ticTacToe.undo();
			}
			return best;
		}
	}
	private static boolean fullLine(int startRow, int startCol, Piece[][] grid, int incRow, int incCol, char symbol)
	{
		for(int i = 0; i < 3; i++)
		{
			int row = startRow + incRow * i;
			int col = startCol + incCol * i;
			if(grid[row][col] == null || grid[row][col].symbol != symbol)
				return false;
		}
		return true;
	}
	
	private static boolean line(char symbol)
	{
		Piece[][] grid = new Piece[3][3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				grid[i][j] = null;
		for(int i = 0; i < ticTacToe.getPieces().size(); i++)
			grid[ticTacToe.getPieces().get(i).row][ticTacToe.getPieces().get(i).col] = ticTacToe.getPieces().get(i);
		//row
		for(int i = 0; i < 3; i++)
			if(fullLine(0, i, grid, 1, 0, symbol))
				return true;
		//columns
		for(int i = 0; i < 3; i++)
			if(fullLine(i, 0, grid, 0, 1, symbol))
				return true;
		//diagonals
		if(fullLine(0, 0, grid, 1, 1, symbol))
			return true;
		if(fullLine(2, 0, grid, -1, 1, symbol))
			return true;
		
		return false;
	}
	public static boolean full()
	{
		return ticTacToe.getPieces().size() == 9;
	}
	private static int calculateScore()// x is positive, o is negative
	{
		
	
		if(line('x'))
			return 100;
		else if(line('o'))
			return -100;
		return 0;
		
	}
	
}
