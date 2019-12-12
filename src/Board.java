import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Board {
	private ArrayList<Piece> pieces;
	private TicTacToe ticTacToe;
	private Canvas canvas;
	private BufferedImage bf;
	
	private int startX, startY;
	
	public Board(TicTacToe ticTacToe)
	{
		startX = 50;
		startY = 50;
		
		bf = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
		this.ticTacToe = ticTacToe;
		this.pieces = ticTacToe.getPieces();
		JFrame frame = new JFrame("TicTacToe");
		
		canvas = new Canvas(){
				private static final long serialVersionUID = 1L;
	
				@Override
				public void paint(Graphics g)
				{
					paintComponent(bf.getGraphics());
					
					g.drawImage(bf, 0, 0, null);
				}
				@Override 
				public void update(Graphics g)
				{
					paint(g);
				}
		};
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				{
					mouseDown(e);
				}
				//Graphics g = canvas.getGraphics();
				//paintComponent(g);
				//g.dispose();
			}
		});	
		canvas.setSize(250, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void mouseDown(MouseEvent e)
	{
		int boardX = (e.getX() - startX)/50; // find the x coordinate of the block we clicked in
		int boardY = (e.getY() - startY)/50; // find the y coordinate of the block we clicked in
		
		if(boardX >=0 && boardX < 3 && boardY >=0 && boardY < 3 && ticTacToe.getPiece(boardY, boardX) == null)
		{
			System.out.println(boardY + " " + boardX);
			ticTacToe.move(boardY, boardX);
			canvas.repaint();
		}
		
				
	}
	
	
	private void paintComponent(Graphics g)
	{
		//draw grid
		g.setColor(Color.WHITE);
		g.fillRect(startX, startY,  150, 150);
		g.setColor(Color.BLACK);
		g.drawLine(startX, startY + 50, startX + 150, startY + 50);
		g.drawLine(startX, startY + 100, startX + 150, startY + 100);
		g.drawLine(startX + 50, startY, startX + 50, startY + 150);
		g.drawLine(startX + 100, startY, startX + 100, startY + 150);
		//draw pieces
		for(int i = 0; i < pieces.size(); i++)
		{
			Piece p = pieces.get(i);
			//draw images later
			g.drawString(String.valueOf(p.symbol), startX + p.col*50 + 24, startY + p.row*50 + 29);			
		}
		
	}
	
}
