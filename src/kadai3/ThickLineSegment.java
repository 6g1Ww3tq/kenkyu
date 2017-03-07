package kadai3;


public class ThickLineSegment extends LineSegment {

	private int width;

	private static final String WIDTH_1 = "-";
	private static final String WIDTH_2 = "=";
	private static final String WIDTH_3OVER = "#";


	public ThickLineSegment(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2);
		width = 1;
	}

	public ThickLineSegment(double x1, double y1, double x2, double y2, int width) {
		super(x1, y1, x2, y2);
		this.width = width;
	}

	public ThickLineSegment(Point p1, Point p2, int width) {
		super.p[0] = p1;
		super.p[1] = p2;
		this.width = width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	private int length(double x1, double y1, double x2, double y2) {
		return (int) Math.ceil(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
	}

	public String makeForm(double x1, double y1, double x2, double y2) {

		StringBuilder sb = new StringBuilder();
		if (this.width == 1) {
			for (int i = 0; i < length(x1, y1, x2, y2); i++) {
				sb.append(WIDTH_1);
			}
		} else if (this.width == 2) {
			for (int i = 0; i < length(x1, y1, x2, y2); i++) {
				sb.append(WIDTH_2);
			}
		} else if (this.width >= 3) {
			for (int i = 0; i < length(x1, y1, x2, y2); i++) {
				sb.append(WIDTH_3OVER);
			}
		}
		return sb.toString();
	}
}
