package kadai3;
public class Main_LineSegment{

    public static void main(String[] args){

	Point[] p = null;
	LineSegment[] line = new LineSegment[10];

	double a = 0;
	double b = 0;
	double c = 2;
	double d = 2;

;
	line[0] = new LineSegment(a, b, c, d);
	p = line[0].linearTransfer();
	ResultShow(p);

	for (int i=1; i<10; i++){
	    line[i] = new LineSegment(p[0].getX1(), p[0].getY1(), p[1].getX1(), p[1].getY1());
	    p = line[i].linearTransfer();
	    ResultShow(p);
	}
    }

  static void ResultShow(Point[] p){
	System.out.println("Input LineSegment (" + p[0].getX() + "," + p[0].getY() + ")->(" + p[1].getX() + "," + p[1].getY() + ")");

	System.out.println("=>Line Segment after liner Transfer: (" + p[0].getX1() + "," + p[0].getY1() + ")->("+ p[1].getX1() + "," + p[1].getY1() + ")\n");
    }
}
