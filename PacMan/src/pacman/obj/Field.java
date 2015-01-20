package pacman.obj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import pacman.main.Game;
import pacman.src.ReferencedActionListener;

public class Field extends JFrame {
	
	private static final long serialVersionUID = -1865614660647747232L;
	
	int lines;
	int columns;
	
	private PacMan pm;
	
	private JPanel fld;
	
	private static Square[] sqrs;
	
	private static List<Collidable> objectsToRender = new ArrayList<Collidable>();
	
	private static List<JButton> btns = new ArrayList<JButton>();
	
	public Field(int c, int l) {
		lines = l;
		columns = c;
		objectsToRender.clear();
		createGrid();
		
		pm = new PacMan();
		
		JPanel bg = new JPanel();
		this.getContentPane().add(bg);
		bg.setLayout(null);
		{
			JMenuBar bar = new JMenuBar();
			this.getRootPane().setJMenuBar(bar);
			{
				JMenu game = new JMenu("Game");
				bar.add(game);
				{
					{
						JMenuItem exit = new JMenuItem("Exit");
						exit.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent evt) {System.exit(0);}});
						game.add(exit);
					}
					{
						JMenuItem newmap = new JMenuItem("New Map");
						newmap.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent evt) {System.exit(0);}});
						game.add(newmap);
					}
				}
			}
			fld = new JPanel();
			bg.add(fld);
			fld.setBackground(Color.LIGHT_GRAY);
			fld.setBounds(10, 10, columns*30+20, lines*30+20);
		}
		addKeyListener(kl);
		
		
		
		setupDefinitionButtons();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize((int) Game.getConfiguration().get("windowX", 400), (int) Game.getConfiguration().get("windowY", 400));
	}
	
	
	public static void addObjectToRender(Collidable collidable) {
		objectsToRender.add(collidable);
	}
	
	public void update() {
		try {
			for (Square s : sqrs) {
				s.paintField();
			}
		} catch (NullPointerException e) {}
		for (Collidable c : objectsToRender) {
			if (c.getDirection() == "l" && c.getPosX() <= 15) {
				c.setPosX(15);
				c.setSpeed(0);
			} else if (c.getDirection() == "r" && c.getPosX() >= columns*30-15) {
				c.setPosX(columns*30-15);
				c.setSpeed(0);
			} else if (c.getDirection() == "d" && c.getPosY() >= lines*30-15) {
				c.setPosY(lines*30-15);
				c.setSpeed(0);
			} else if (c.getDirection() == "u" && c.getPosY() <= 15) {
				c.setPosY(15);
				c.setSpeed(0);
			} else {
				c.setSpeed(2);
			}
			c.draw();
		}
		objectsToRender.clear();
	}

	public Graphics getFieldGraphics() {
		return fld.getGraphics();
	}
	
	public void createGrid() {
		sqrs = new Square[lines * columns];
		for (int i1 = 1; i1 <= columns ; i1++) {
			for (int i2 = 1; i2 <= lines ; i2++) {
				System.out.println("Creating square: " + i1 + ", " + i2 + " at sqrs[" + ((i1-1)*columns + i2 - 1) + "]");
				sqrs[i1 + i2 - 2] = new Square(i1, i2, this);
			}
		}
	}
	
	public void setupDefinitionButtons() {
		for (int y = 0; y <= lines; y++) {
			for (int x = 0; x <= columns; x++) {
				JButton b = new JButton();
				b.setLocation(30*x+5, 30*y-5);
				b.setSize(20, 10);
				JButton b1 = new JButton();
				b1.setLocation(30*x-5, 30*y+5);
				b1.setSize(10, 20);
				b.setBackground(Color.LIGHT_GRAY);
				b1.setBackground(Color.LIGHT_GRAY);
				b.addActionListener(new ReferencedActionListener(b));
				b1.addActionListener(new ReferencedActionListener(b1));
				btns.add(b);
				btns.add(b1);
			}
			JButton b3 = new JButton();
			b3.setBackground(Color.LIGHT_GRAY);
			b3.setLocation(columns*30+25, y*30+5);
			b3.setSize(10, 20);
			b3.addActionListener(new ReferencedActionListener(b3));
			btns.add(b3);
		}
		for (int x = 0; x <= columns; x++) {
			JButton b2 = new JButton();
			b2.setLocation(x*30+5, lines*30+25);
			b2.setSize(20, 10);
			b2.setBackground(Color.LIGHT_GRAY);
			b2.addActionListener(new ReferencedActionListener(b2));
			btns.add(b2);
		}
	}
	
	private KeyListener kl = new KeyListener() {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				pm.scheduleDirectionChange("d");
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				pm.scheduleDirectionChange("u");
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				pm.scheduleDirectionChange("l");
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				pm.scheduleDirectionChange("r");
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
		
	};
	
}
