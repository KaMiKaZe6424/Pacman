package pacman.obj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import pacman.main.Game;

public class PacMan extends Collidable {
	
	Image img;
	ImageObserver imgObs;
	
	private boolean isActive = true;
	
	public PacMan() {
		super(5, 5);
		setSpeed(1);
		Game.addObjectToRegistry(this);
		String path = String.valueOf(Game.getConfiguration().get("pacmanImage", "user.home:\\.pacman\\data\\images\\pacman\\PacmanDefault.png"));
		if (path.contains(":")) {
			path = System.getProperty(path.split(":")[0]) + path.split(":")[1];
		}
		System.out.println(path);
		img = (new ImageIcon(path).getImage());
	}

	private static final long serialVersionUID = 4342964723168355959L;
	
	@Override
	public void update() {
		setPosition();
	}
	
	public void toggle() {
		isActive = !isActive;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0,  0, getWidth(), getHeight());
		g.drawImage(img, 0, 0, 20, 20, imgObs);
	}
	
}
