package kadai3;
public class Main_ThickLineSegment {

    public static void main(String[] args ){

	double e = 10;
	double f = 10;
	double g = 20;
	double h = 30;
	ThickLineSegment tline = new ThickLineSegment(e, f, g, h, 2);
	StringBuilder sb = new StringBuilder();
	Point p[] = tline.linearTransfer();
		sb.append("Input LineSegment (");
		sb.append(p[0].getX());
		sb.append(",");
		sb.append(p[0].getY());
		sb.append(")");
		sb.append(tline.makeForm(e, f, g, h));
		sb.append("(");
		sb.append(p[1].getX());
		sb.append(",");
		sb.append(p[1].getY());
		sb.append(")");
		sb.append("\n");
		sb.append("=>Line Segment after liner Trasfer:(");
		sb.append(p[0].getX1());
		sb.append(",");
		sb.append(p[0].getY1());
		sb.append(")");
		sb.append(tline.makeForm(p[0].getX1(), p[0].getY1(), p[1].getX1(), p[1].getY1()));
		sb.append("(");
		sb.append(p[1].getX1());
		sb.append(",");
		sb.append(p[1].getY1());
		sb.append(")");
		System.out.println(sb.toString());

    }
}