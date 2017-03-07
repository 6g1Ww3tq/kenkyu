package myreflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import kadai3.Point;

public class ThickLineSegmentConstructerChecker extends ConstructerChecker{

    public ThickLineSegmentConstructerChecker(){
        super(3);
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
        ThickLineSegmentFieldChecker thickLineSegmentFieldChecker = new ThickLineSegmentFieldChecker();

        if(constructerType.equals("public kadai3.ThickLineSegment(kadai3.Point,kadai3.Point,int)")){
            correct = true;
            pointClassConstructer = pointClass.getDeclaredConstructor(double.class,double.class);
            pointClassConstructer.setAccessible(true);
            obj = constructer.newInstance(pointClassConstructer.newInstance(100,100),pointClassConstructer.newInstance(200,200),300);
            System.out.print("instance generate Point(100,100),Point(200,200),300 check fields: ");
            clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                sb.append(thickLineSegmentFieldChecker.toString(field,obj));
            }
        }else if(constructerType.equals("public kadai3.ThickLineSegment(double,double,double,double)")){
            correct = true;
            obj = constructer.newInstance(100,200,300,400);
            System.out.print("instance generate (100,200,300,400,1) check fields: ");
            clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                sb.append(thickLineSegmentFieldChecker.toString(field,obj));
            }
        }else if(constructerType.equals("public kadai3.ThickLineSegment(double,double,double,double,int)")){
            correct = true;
            obj = constructer.newInstance(100,200,300,400,500);
            System.out.print("instance generate (100,200,300,400,500) check fields: ");
            clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                sb.append(thickLineSegmentFieldChecker.toString(field,obj));
            }
        }

        System.out.println(sb.toString());

        return correct;
    }

}
