package kadai3;
public class Point{
    double x,y;
    double x1,y1;

    Point(double x , double y){
	this.x = x;
	this.y = y;
    }

    void linearTransfer(){
	this.x1 = 6 * this.x + 4 * this.y;
	this.y1 = -2 * this.x + this.y;
    }
    final double getX(){
	return this.x;
    }

    final double getY(){
	return this.y;
    }

    final double getX1(){
	return this.x1;
    }

    final double getY1(){
	return this.y1;
    }
}

