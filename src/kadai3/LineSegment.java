package kadai3;
public class LineSegment{

    Point p[] = new Point[2];


    public LineSegment(){
    }


    public LineSegment(double x1, double y1, double x2, double y2){
	this.p[0] = new Point(x1, y1);
	this.p[1] = new Point(x2, y2);
    }

    Point[] linearTransfer(){
	this.p[0].linearTransfer();
	this.p[1].linearTransfer();

	return this.p;
    }
}

