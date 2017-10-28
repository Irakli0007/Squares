import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;


public class Screen extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	static final int WIDTH = 1920;
	static final int HEIGHT = 1080;

	private boolean running = false;
	private ArrayList<Square> list;
	private Random rand;
	private int count = 0;
	
	public Screen() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		list = new ArrayList<Square>();
		rand = new Random();
		init();
	}
	
	private void init() {
		Thread thread = new Thread(this);
		running = true;
		thread.start();
	}
	
	
	@Override
	public void run() {
		while(running) {
			if(count == 10) {
				addSquare();
				count = 0;
			}
			count++;
			tick();
			changeColors();
			try {
				Thread.sleep(20);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
			repaint();
		}
	}

	private void changeColors() {
		for(Square s : list) {
			s.changeColors();
		}
	}

	private void addSquare() {
		if(list.size() < 50) {
			Square s = new Square(rand.nextInt(3), rand.nextBoolean(), rand.nextInt(WIDTH - 10), (rand.nextInt(4) + 1), rand.nextInt(3));
			list.add(s);
		}
	}

	private void tick() {
		for(Square s : list) {
			s.fall();
			if(s.getSpinning()) {
				s.rotate();
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for(Square s : list) {
			s.draw(g);
		}
	}
	
	
	
}
