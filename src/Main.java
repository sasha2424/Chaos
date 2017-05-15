
import java.util.ArrayList;

import javax.swing.JOptionPane;

import processing.core.PApplet;

public class Main extends PApplet {

	public static final double HEIGHT = 600; // height is a float copy
	public static final double WIDTH = 600; // width is a float copy

	public static float SCALE = 1;

	public ArrayList<ArrayList<Point>> points;

	public int currentSet = 0;

	public double p = 0;

	public int T = 0; // 0 editing // 1 help // 2 drawing

	public Point M;

	public boolean tempDraw = false;

	public void settings() {
		size((int) WIDTH, (int) HEIGHT);
		fullScreen();
	}

	public void setup() {
		strokeWeight(5);
		background(255);

		points = new ArrayList<ArrayList<Point>>();
		points.add(new ArrayList<Point>());
		currentSet = points.size() - 1;

		M = new Point((float) (Math.random() * width), (float) (Math.random() * height), 1);
	}

	public void draw() {

		if (T == 0) {
			background(255);
			fill(0);
			text("" + T, 50, 50);
			text("" + (currentSet + 1), 50, 70);
			text("editing", 100, 100);

			for (int i = 0; i < points.size(); i++) {
				for (Point p : points.get(i)) {
					draw(p, i + 1);
				}
			}

			if (tempDraw) {
				for (int i = 0; i < points.get(currentSet).size(); i++) {
					Point p = points.get(currentSet).get(i);
					text("" + (i + 1), p.getX(), p.getY());
				}
			}

		}
		if (T == 1) {
			text("" + T, 50, 50);
			text("" + currentSet, 50, 70);

			text("Controlls:", 300, 300);
			text("TAB for different menus", 300, 320);
			text("n to make a new set of points", 300, 340);
			text("a and d to switch between sets", 300, 360);
			text("p to change in set point probabilities", 300, 380);
			text("o to change in set swap probability", 300, 400);
			text("s to change point movment scale", 300, 420);
			text("w to change point weight", 300, 440);
			text("r to delete current set", 300, 460);

		}
		if (T == 2 && points.size() > 0 && points.get(currentSet).size() > 0) {
			text("" + T, 50, 50);
			text("" + currentSet, 50, 70);
			draw(M, 0);
			M.shift(RP(), SCALE);
		}
	}

	public void mousePressed() {
		if (T == 0 && points.size() > 0)
			points.get(currentSet).add(new Point(mouseX, mouseY, 1));
	}

	public void keyPressed() {
		if (keyCode == TAB) {
			T++;
			T = T % 3;
			background(255);
			delay(100);
		}
		if (key == 'n') { // new Set
			points.add(new ArrayList<Point>());
			currentSet = points.size() - 1;
			delay(100);
		}
		if (key == 'a') {
			currentSet--;
			currentSet %= points.size();
			delay(100);
		}
		if (key == 'd') {
			currentSet++;
			currentSet %= points.size();
			delay(100);
		}
		if (key == 'p') {
			if (points.size() > 0) {

				if (tempDraw) {

					for (int i = 0; i < points.get(currentSet).size(); i++) {
						Point p = points.get(currentSet).get(i);
						int k = Integer.parseInt(JOptionPane
								.showInputDialog("Enter an int representing the weight of point " + (i + 1)));
						p.setWeight(k);
					}
					tempDraw = false;
				} else {
					tempDraw = true;
				}
			}

			delay(100);
		}
		if (key == 'o') {
			p = Double.parseDouble(JOptionPane.showInputDialog("Enter set switch probability as a double (0 to 1)"));
		}
		if (key == 's') {
			SCALE = Float.parseFloat(JOptionPane.showInputDialog("Enter scale as any positive value."));
		}
		if (key == 'w') {
			strokeWeight(Float.parseFloat(JOptionPane.showInputDialog("Enter positive value for point size")));
		}
		if (key == 'r') {
			points.remove(currentSet);
			if (points.size() == 0) {
				currentSet = 0;
			} else {
				currentSet %= points.size();
			}
		}
	}

	public static void main(String[] args) {
		PApplet.main("Main");
	}

	private Point RP() {
		if (Math.random() < p) {
			currentSet = (int) (Math.random() * points.size());
		}
		int total = 0;
		for (Point p : points.get(currentSet)) {
			total += p.getWeight();
		}

		int w = (int) (Math.random() * total);
		for (Point p : points.get(currentSet)) {
			w -= p.getWeight();
			if (w < 0) {
				return p;
			}
		}
		return points.get(currentSet).get(points.get(currentSet).size() - 1);
	}

	private void draw(Point p, int i) {
		stroke((i * 80) % 256, (i * 100) % 256, (i * 13489) % 256);
		line(p.x, p.y, p.x, p.y);
	}

}