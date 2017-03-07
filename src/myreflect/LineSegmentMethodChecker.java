package myreflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import kadai3.Point;

public class LineSegmentMethodChecker extends MethodChecker{

    public LineSegmentMethodChecker(){
        super(1);
    }

    protected boolean nameChecker(Method method,String methodName){
        boolean correct = false;
        int modifier = method.getModifiers();
        StringBuilder sb = new StringBuilder();

        if(methodName.equals("linearTransfer")){
            sb.append("methodName:linearTransfer ");
            if(modifier == Modifier.PUBLIC){
                sb.append("public ");
                correct = true;
            }

            sb.append("...found");
        }

        System.out.println(sb.toString());
        return correct;
    }


    protected boolean invokeChecker(Method method,Object obj) throws IllegalAccessException,InvocationTargetException{
        boolean correct = false;
        String methodName = method.getName();
        Class<Point> pointClass = Point.class;
        Object retLineSegmentValue = null;

        if(methodName.equals("linearTransfer")){
            retLineSegmentValue = method.invoke(obj);
            System.out.println("Execute LinearTransfer Method ReturnValue: ");
            for (Field field : pointClass.getDeclaredFields()) {
                field.setAccessible(true);
                System.out.println(field + ":" + retLineSegmentValue);
            }

            System.out.println();
            correct = true;
        }

        return correct;
    }

}
