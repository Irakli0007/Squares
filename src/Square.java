import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import java.util.Random;


public class Square extends Rectangle {

	private static final long serialVersionUID = 1L;
	private int size;
	private boolean filled;
	private int speed;
	private boolean spinning;
	private Point p1;
	private Point p2;
	private Point p3;
	private Point p4;
	private Point center;
	private int color1 = 255;
	private int color2 = 0;
	private int color3 = 0;
	private int addColor = 5;
	private double angle = 3;
	
	public Square(int randSize, boolean randFill, int randXLoc, int randSpeed, int spinning) {
		if(randSize == 1) {
			this.size = 20; 
		} else if(randSize == 2) {
			this.size = 20;
		} else {
			this.size = 40;
		}
		if(spinning == 0 || spinning == 1) {
			this.spinning = true;
		} else {
			this.spinning = false;
		}
		this.filled = randFill;
		this.x = randXLoc;
		this.y = 0;
		this.speed = randSpeed;
		p1 = new Point(randXLoc, 0);
		p2 = new Point(randXLoc + size, 0);
		p3 = new Point(randXLoc + size, size);
		p4 = new Point(randXLoc, size);
		center = new Point(randXLoc + size/2, size/2);
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(color1, color2, color3));
        int[] xArr = {(int) p1.getX(), (int) p2.getX(), (int)p3.getX(), (int) p4.getX()};
        int[] yArr = {(int) p1.getY(), (int) p2.getY(), (int)p3.getY(), (int) p4.getY()};
		Polygon p = new Polygon(xArr, yArr, xArr.length);
		if(filled) {
			g2d.fill(p);   
		} else {
			g2d.draw(p);   
		}
			     
	}

	public void fall() {
		p1.y += speed;
		p2.y += speed;
		p3.y += speed;
		p4.y += speed;
		center.y += speed;
		if(p1.y >= Screen.HEIGHT + size) {
			p1.y = -size;
			p2.y = -size;
			p3.y = 0;
			p4.y = 0;
			p1.x = new Random().nextInt(Screen.WIDTH);
			p2.x = p1.x + size;
			p3.x = p1.x + size;
			p4.x = p1.x;
			center.x = p1.x + size/2;
			center.y = p1.y + size/2;
			color1 = 255;
			color2 = 0;
			color3 = 0;
		}
	}

	public void changeColors() {
		if(p1.y >= 0 && p1.y < 120) {
			if(color2 < 70) {
				color2 += addColor;
			}
		}
		else if(p1.y >= 120 && p1.y < 240) {
			if(color2 < 255) {
				color2 += addColor;
			}
		}
		else if(p1.y >= 240 && p1.y < 360) {
			if(color1 > 0) {
				color1 -= addColor;
			}
			if(color2 > 128) {
				color2 -= addColor;
			}
		}
		else if(p1.y >= 360 && p1.y < 480) {
			if(color2 > 0) {
				color2 -= addColor;
			}
			if(color3 < 255) {
				color3 += addColor;
			}
		}
		else if(p1.y >= 480 && p1.y < 600) {
			if(color3 > 128) {
				color3 -= addColor;
			}
			if(color1 < 75) {
				color1 += addColor;
			}
		}
		
	}


	private Point[] getOriginalPoints() {
		Point[] originalPoints = new Point[5];
		originalPoints[0] = p1;
		originalPoints[1] = p2;
		originalPoints[2] = p3;
		originalPoints[3] = p4;
		originalPoints[4] = center;
		
		return originalPoints;
	}
	
	public void rotate() {
		Point[] points1 = getOriginalPoints();
		rotatePointMatrix(getOriginalPoints(), angle, points1);
	}

	private void rotatePointMatrix(Point[] originalPoints, double angle2, Point[] points1) {
		AffineTransform.getRotateInstance(Math.toRadians(angle2), center.x, center.y).transform(originalPoints, 0, points1, 0, 5);
	}

	public boolean getSpinning() {
		return spinning;
	}

	
}
