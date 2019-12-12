
public class Main {

	public static void main(String[] args) {
		TicTacToe ticcy = new TicTacToe();
		Board myBoard = new Board(ticcy);
		ticcy.print();
		ticcy.move(1, 1);
		ticcy.print();
		ticcy.move(0, 1);
		ticcy.print();
		ticcy.undo();
		ticcy.print();
	}

}
