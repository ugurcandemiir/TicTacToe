 package lab10;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class TicTacToe extends JFrame {
	Line[] lines;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TicTacToe() {
		super("Tic-Tac-Toe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new JPanel(new GridLayout(3,3,5,5)));
		// The board consists of a grid of 9 buttons
		// Define all 8 sets of winning lines
		lines=new Line[8];
		for(int l=0;l<8;l++) lines[l]=new Line(this);
		for(int r=0;r<3;r++) {
			for(int c=0;c<3;c++) {
				Square sq = new Square(this);
				getContentPane().add(sq);
				lines[r].add(sq);
				lines[c+3].add(sq);
				if (r==c) lines[6].add(sq);
				if ((r==0 && c==2) || (r==1 && c==1) || (r==2 && c==0) ) lines[7].add(sq);
			}
		}
	}
	
	public boolean checkWinner() {
		for(Line l : lines) if (l.isWinner()) return true;	
		return false;
	}
	
	public void disableAllSquares() {
		for(int i=0;i<3;i++) lines[i].disableAllSquares();
	}
	
	public void computerTurn() {
		
		for(Line line : lines) {
		    Square sq=line.allButOne("O");
		    if (sq != null) {
		      sq.choose("O");
		      checkWinner();
		      return;
		    }
		  }
	    
		for(Line line : lines) {
		    Square sq=line.allButOne("X");
		    if (sq != null) {
		      sq.choose("O");
		      checkWinner();
		      return;
		    }
		  }
	    
		for(Line line : lines) {
			 Square sq=line.avail();
			 if (sq != null) {
				 sq.choose("O");
			     checkWinner();
			     return;
			    }
			  }
			  
			  
// If you get here, there are no squares open!
			}
	
	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		TicTacToe game = new TicTacToe();
        javax.swing.SwingUtilities.invokeLater(()->{
        	//Display the window.
            game.pack();
            game.setVisible(true);
        });

	}
}
