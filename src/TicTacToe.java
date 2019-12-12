import java.util.ArrayList;
import java.util.Stack;

public class TicTacToe {
	private ArrayList<Piece> pieces;
	private char currPlayer;
	private Stack<Position> moves;
	
	public TicTacToe()
	{
		pieces = new ArrayList<>();
		currPlayer = 'x';
		moves = new Stack<>();
		
	}
	public char getCurrPlayer()
	{
		return currPlayer;
	}
	public Piece getPiece(int row, int col)
	{
		return getPiece(new Position(row, col));
		
	}
	private Piece getPiece(Position pos)
	{
		for(int i = 0; i < pieces.size(); i++)
		{
			if(pieces.get(i).row == pos.row && pieces.get(i).col == pos.col)
				return pieces.get(i);
		}
		return null;
	
	}
	
	public boolean move(int row, int col)
	{
		return move(new Position(row, col));
	}
	
	public boolean move(Position pos)
	{
		if(pos.row < 0 || pos.row >= 3 || pos.col < 0 || pos.col >= 3)
			return false;
		if(getPiece(pos) != null)
			return false;
		Piece newPiece = new Piece(pos.row, pos.col, currPlayer);
		pieces.add(newPiece);
		currPlayer = currPlayer == 'x' ? 'o' : 'x';
		moves.push(pos);
		return true;
		
	}
	public void undo()
	{
		Piece myPiece = getPiece(moves.pop());
		pieces.remove(myPiece);
		currPlayer = currPlayer == 'x' ? 'o' : 'x';
	}
	
	public ArrayList<Position> getPossibleMoves()
	{
		
		ArrayList<Position> positions = new ArrayList<>();
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(getPiece(new Position(i,j)) == null)
					positions.add(new Position(i,j));
			}
		}
		return positions;
	}
	public ArrayList<Piece> getPieces()
	{
		return pieces;
	}
	
	public void print()
	{
		char[][] grid = new char[3][3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				grid[i][j] = ' ';
		for(int i = 0; i < pieces.size(); i++)
			grid[pieces.get(i).row][pieces.get(i).col] = pieces.get(i).symbol;
		System.out.println("+-+-+-+");
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				System.out.print("|");
				System.out.print(grid[i][j]);
			}
			System.out.println("|");
			System.out.println("+-+-+-+");
		}
		
	
	}
	
}
