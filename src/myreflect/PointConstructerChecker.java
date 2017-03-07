package myreflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
// import java.lang.reflect.Parameter; 1.8

public class PointConstructerChecker extends ConstructerChecker{

    public PointConstructerChecker(){
        super(1);
    }

    /*
     * @Override 1.8
     */
    /*
     * protected void paramChecker(Constructor<?> constructer){
     *     Parameter[] params = constructer.getParameters();

     *     for (Parameter param : params) {
     *         System.out.println("parameter: " + param.getName());
     *     }

     * }
     */

    /*
     * @Override
     */
    protected boolean instanceChecker(Constructor<?> constructer) throws InstantiationException,IllegalAccessException,InvocationTargetException,NoSuchMethodException{
        boolean correct = false;
        Object obj;
        StringBuilder sb = new StringBuilder();
        Class<?> clazz;
        String constructerType = constructer.toString();
        PointFieldChecker pointFieldChecker = new PointFieldChecker();

        if(constructerType.equals("kadai3.Point(double,double)")){
            correct = true;
            obj = constructer.newInstance(200,200);
            System.out.print("instance generate (200,200) check fields: ");
            clazz = obj.getClass();
            sb.append("[");
            for (Field field : clazz.getDeclaredFields()) {
                sb.append(pointFieldChecker.toString(field,obj));
                sb.append(", ");
            }
            sb.append("]");
            System.out.println(sb.toString());
        }

        return correct;
    }
}
