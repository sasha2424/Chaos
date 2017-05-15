
public class Point {

	public float x;
	public float y;
	public int weight;

	public Point(float x, float y, int W) {
		super();
		this.x = x;
		this.y = y;
		weight = W;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void shift(Point p, float weight) {
		this.x = (p.x * (weight) + this.x) / (1 + weight);
		this.y = (p.y * (weight) + this.y) / (1 + weight);
	}
}
