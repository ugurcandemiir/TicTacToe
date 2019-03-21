package lab10;

import java.awt.Color;

import javax.swing.Timer;

public class Line {
	Square[] mem;
	int num;
	TicTacToe game;
	boolean blinker;

	/**
	 * 
	 */
	public Line(TicTacToe game) {
		this.game=game;
		mem=new Square[3];
		num=0;
	}
	
	public void add(Square ns) {
		mem[num]=ns;
		num++;
	}
	
	public boolean isWinner() {
		int i;
		String v;
		v=mem[0].getText();
		if (v.equals(" ")) { return false; }
		for (i=1;i<num;i++) {
			if (!mem[i].getText().equals(v)) return false;
		}
		game.disableAllSquares();
		for(i=0;i<num;i++) mem[i].setOpaque(true);
		blinker=false;
		Timer blinkTimer = new Timer(300, e->{
			for(int j=0;j<num;j++)  mem[j].setBackground( blinker ? Color.GREEN : null);
			blinker=!blinker;
		});
		blinkTimer.start();
		return true;
	}
	
	Square allButOne(String check) {
		int cnt=0;
		Square res=null;
		for(int i=0;i<num;i++) {
			if (mem[i].getText().equals(check)) cnt++;
			else if (!mem[i].getText().equals(" ")) return null;
			else res=mem[i];
		}
		if (cnt!=2) return null;
		return res;
	}
	
	Square avail() {
		for (int i=0;i<num;i++) {
			if (mem[i].getText().equals(" ")) return mem[i];
		}
		return null;
	}
	
	void disableAllSquares() {
		for (int i=0;i<num;i++) {
			mem[i].setEnabled(false);
		}
	}
}
