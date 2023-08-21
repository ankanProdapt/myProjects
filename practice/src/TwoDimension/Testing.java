package TwoDimension;

public class Testing {
	
	public static void main(String[] args) {
		Point origin = new Point(0, 0);
		
		Line line1 = new Line(1, 5);
		Line line2 = new Line(2, 10);
		
		Point intersection = line1.intersection(line2);
		
		System.out.println(origin);
		System.out.println(line1);
		System.out.println(line2);
		System.out.println(line1.intersection(line2));
		System.out.println(origin.distanceFrom(intersection));
	}
}
