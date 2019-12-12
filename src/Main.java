
public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		TicTacToe ticcy = new TicTacToe();
		Board myBoard = new Board(ticcy);
		Agent james = new Agent(ticcy, "minimax");
		while(true)
		{
			System.out.println("a");
			if(ticcy.getCurrPlayer() == 'x')
			{
				myBoard.stop();
				ticcy.move(james.move());
				myBoard.start();
				myBoard.refresh();
				if(james.full())
					break;
			}
		}
	}

}
