package lab10;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;

public class Square extends JButton {

	private static final long serialVersionUID = 1L;
	TicTacToe game;
	
	public Square(TicTacToe game) {
		this.game = game;
		setFont(getFont().deriveFont((float) 40.0));
		setPreferredSize(new Dimension(70,70));
		setMargin(new Insets(-30,-30,-30,-30));
		setText(" ");
		addActionListener(event-> { 
			choose("X");
			if (game.checkWinner()) return;
			game.computerTurn();
		});
	}
	
	public void choose(String val) {
		setText(val);
		setEnabled(false);
	}
	
}
