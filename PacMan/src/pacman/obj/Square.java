package pacman.obj;

import java.awt.Color;
import java.awt.Graphics;

public class Square {
	
	public int x;
	public int y;
	
	private int size;
	
	private Field f;
	
	private boolean top;
	private boolean left;
	private boolean bottom;
	private boolean right;
	
	public Square(int x, int y, Field f) {
		this.f = f;
		this.x = x;
		this.y = y;
	}
	
	public void paintField() {
		Graphics g = f.getGraphics();
		g.setColor(Color.BLACK);
		if (top) {
			g.drawRect(x*size, y*size, 30, 5);
		}
		if (left) {
			g.drawRect(x*size, y*size, 5, 30);
		}
		if (bottom) {
			g.drawRect(x*size, y*size + 25, 30, 5);
		}
		if (right) {
			g.drawRect(x*size + 25, y*size, 5, 30);
		}
	}
	
	public int getAbsoluteX() {
		return (x * size) + size / 2;
	}
	
	public int getAbsoluteY() {
		return (y * size) + size / 2;
	}
	
}
