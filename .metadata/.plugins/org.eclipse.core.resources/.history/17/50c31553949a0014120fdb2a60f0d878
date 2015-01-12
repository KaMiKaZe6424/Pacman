package pacman.main;

import java.util.ArrayList;
import java.util.List;

import pacman.obj.Collidable;

public class Game {
	
	private List<Collidable> objReg;
	private long maxFps;
	
	private static Configuration config;
	
	public Game() {
		config = new Configuration();
		maxFps = (long) config.get("maxFps", 30);
		objReg = new ArrayList<Collidable>();
	}
	
	public Configuration getConfiguration() {
		return config;
	}
	
	public void addObjectToRegistry(Collidable c) {
		objReg.add(c);
	}
	
	public void removeObjectFromRegistry(Collidable c) {
		objReg.remove(c);
	}
	
	
	
	Thread update = new Thread(new Runnable() {
		
		@SuppressWarnings("static-access")
		@Override
		public void run() {
			for (Collidable c : objReg) {
				c.update();
			}
			
			try {
				update.sleep(1000 / maxFps);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	});
	
}
