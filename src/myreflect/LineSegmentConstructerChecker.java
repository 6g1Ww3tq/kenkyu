package myreflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import kadai3.Point;

public class LineSegmentConstructerChecker extends ConstructerChecker{

    public LineSegmentConstructerChecker(){
        super(2);
    }

    /*
     * @Override
     */
    protected boolean instanceChecker(Constructor<?> constructer) throws InstantiationException,IllegalAccessException,InvocationTargetException,NoSuchMethodException{
        boolean correct = false;
        Object obj;
        StringBuilder sb = new StringBuilder();
        Class<?> clazz = null;
        Class<Point> pointClass = Point.class;
        Constructor<Point> pointClassConstructer = null;
        String constructerType = constructer.toString();
        LineSegmentFieldChecker lineSegmentFieldChecker = new LineSegmentFieldChecker();

        if(constructerType.equals("public kadai3.LineSegment(kadai3.Point,kadai3.Point)")){
            correct = true;
            pointClassConstructer = pointClass.getDeclaredConstructor(double.class,double.class);
            obj = constructer.newInstance(pointClassConstructer.newInstance(100,100),pointClassConstructer.newInstance(200,200));
            System.out.print("instance generate Point(100,100),Point(200,200) check: ");
            clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                sb.append(lineSegmentFieldChecker.toString(field,obj));
            }
        }else if(constructerType.equals("public kadai3.LineSegment(double,double,double,double)")){
            correct = true;
            obj = constructer.newInstance(100,200,300,400);
            System.out.print("instance generate (100,200,300,400) check fields: ");
            clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                sb.append(lineSegmentFieldChecker.toString(field,obj));
            }
        }
        System.out.println(sb.toString());

        return correct;
    }
}
