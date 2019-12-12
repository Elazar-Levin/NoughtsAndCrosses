
public class Piece {
	int row, col;
	char symbol;
	public Piece(int row, int col, char symbol) {
		super();
		this.row = row;
		this.col = col;
		this.symbol = symbol;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		result = prime * result + symbol;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		if (symbol != other.symbol)
			return false;
		return true;
	}
	
	
}
