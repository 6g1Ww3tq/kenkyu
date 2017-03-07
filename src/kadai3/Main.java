package kadai3;

import myreflect.*;

public class Main {
    public static void main(String args[]) {
        ClassChecker classChecker = new ClassChecker();
        PointFieldChecker pointFieldChecker = new PointFieldChecker();
        PointMethodChecker pointMethodChecker = new PointMethodChecker();
        PointConstructerChecker pointConstructerChecker = new PointConstructerChecker();
        LineSegmentFieldChecker lineSegmentFieldChecker = new LineSegmentFieldChecker();
        LineSegmentMethodChecker lineSegmentMethodChecker = new LineSegmentMethodChecker();
        LineSegmentConstructerChecker lineSegmentConstructerChecker = new LineSegmentConstructerChecker();
        ThickLineSegmentFieldChecker thickLineSegmentFieldChecker = new ThickLineSegmentFieldChecker();
        ThickLineSegmentMethodChecker thickLineSegmentMethodChecker = new ThickLineSegmentMethodChecker();
        ThickLineSegmentConstructerChecker thickLineSegmentConstructerChecker = new ThickLineSegmentConstructerChecker();

        Point point = new Point(1,3);
        System.out.println("~~~~~PointClass~~~~~");
        classChecker.analyze(point);
        pointFieldChecker.analyze(point);
        pointMethodChecker.analyze(point);
        pointConstructerChecker.analyze(point);

        LineSegment lineSegment = new LineSegment(1,2,3,4);
        System.out.println("~~~~~LineSegmentClass~~~~~");
        classChecker.analyze(lineSegment);
        lineSegmentFieldChecker.analyze(lineSegment);
        lineSegmentMethodChecker.analyze(lineSegment);
        lineSegmentConstructerChecker.analyze(lineSegment);

        ThickLineSegment thickLineSegment = new ThickLineSegment(5,6,7,8);
        System.out.println("~~~~~ThickLineSegmentClass~~~~~");
        thickLineSegmentFieldChecker.analyze(thickLineSegment);
        thickLineSegmentMethodChecker.analyze(thickLineSegment);
        thickLineSegmentConstructerChecker.analyze(thickLineSegment);
    }
}
