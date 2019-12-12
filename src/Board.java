import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Board {
	private ArrayList<Piece> pieces;
	private TicTacToe ticTacToe;
	private Canvas canvas;
	private BufferedImage bf;
	private BufferedImage cross;
	private BufferedImage nought;
	
	private int startX, startY;
	
	private boolean stop;
	public Board(TicTacToe ticTacToe)
	{
		stop = false;
		startX = 50;
		startY = 50;
		BufferedImage img = null;
		try {
			cross = ImageIO.read(new File("assets/cross.png"));
		    nought = ImageIO.read(new File("assets/nought.png"));
		} catch (IOException e) {
		}
		
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
		if(e.getX() >= 90 && e.getX() <= 160 && e.getY() >= 215 && e.getY() <= 235)
		{
			if(pieces.size() > 0)
			{
				ticTacToe.undo();
				canvas.repaint();
				return;
			}
		}
		
		
		int boardX = (e.getX() - startX)/50; // find the x coordinate of the block we clicked in
		int boardY = (e.getY() - startY)/50; // find the y coordinate of the block we clicked in
		
		if(boardX >=0 && boardX < 3 && boardY >=0 && boardY < 3 && ticTacToe.getPiece(boardY, boardX) == null)
		{
			ticTacToe.move(boardY, boardX);
			canvas.repaint();
		}
		
				
	}
	public void refresh()
	{
		canvas.repaint();
	}
	public void stop()
	{
		stop = true;
	}
	public void start()
	{
		stop = false;
	}
	
	private void paintComponent(Graphics g)
	{
		if(!stop)
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
				//TODO: draw images later
				if(p.symbol == 'x')
					g.drawImage(cross, startX + p.col*50 + 5, startY + p.row*50 + 5, null);
				else
					g.drawImage(nought, startX + p.col*50 + 5, startY + p.row*50 + 5, null);
							
			}
			//undo button
			g.setColor(Color.WHITE);
			g.fillRect(90, 215, 70, 20);
			g.setColor(Color.BLUE);
			g.drawRect(90, 215, 70, 20);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Dialog", Font.PLAIN, 15));
			g.drawString("UNDO", 106, 231);
		}
	}
	
}
