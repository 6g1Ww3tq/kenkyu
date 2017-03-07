package myreflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
// import java.lang.reflect.Parameter; 1.8

public class PointMethodChecker extends MethodChecker{

    public PointMethodChecker(){
        super(4);
    }

    /*
     * @Override
     */
    protected boolean nameChecker(Method method,String methodName){
        boolean correct = false;
        int modifier = method.getModifiers();
        StringBuilder sb = new StringBuilder();

        if(methodName.equals("getX")){
            sb.append("methodName:getX ");
            if(modifier == Modifier.PUBLIC){
                sb.append("public ");
                correct = true;
            }
            sb.append("...found");

        }else if(methodName.equals("getY")){
            sb.append("methodName:getY ");
            if(modifier == Modifier.PUBLIC){
                sb.append("public ");
                correct = true;
            }
            sb.append("...found");

        }else if(methodName.equals("setX")){
            sb.append("methodName:setX ");
            if(modifier == Modifier.PUBLIC){
                sb.append("public ");
                correct = true;
            }
            sb.append("...found");

        }else if(methodName.equals("setY")){
            sb.append("methodName:setY ");
            if(modifier == Modifier.PUBLIC){
                sb.append("public ");
                correct = true;
            }
            sb.append("...found");

        }

        System.out.println(sb.toString());

        return correct;
    }

    /*
     * @Override 1.8
     */
    /*
     * protected void paramChecker(Method method){
     *     Parameter[] params = method.getParameters();

     *     for (Parameter param : params) {
     *         System.out.println("parameter: " + param.getName());
     *     }
     * }
     */

    /*
     * @Override
     */
    protected boolean invokeChecker(Method method,Object obj) throws IllegalAccessException,InvocationTargetException{
        boolean correct = false;
        Class<?> clazz = obj.getClass();
        String methodName = method.getName();
        Object retPointValue;
        PointFieldChecker fieldChecker = new PointFieldChecker();

        if(methodName.equals("getX")){
            retPointValue = method.invoke(obj);
            System.out.println("Execute getX Method ReturnValue: " + retPointValue);
            correct = true;
        }else if(methodName.equals("getY")){
            retPointValue = method.invoke(obj);
            System.out.println("Execute getY Method ReturnValue: " + retPointValue);
            correct = true;
        }else if(methodName.equals("setX")){
            retPointValue = method.invoke(obj,100);
            System.out.println("Execute setX Method ReturnValue: " + fieldChecker.getValue(clazz,"x",obj));
            correct = true;
        }else if(methodName.equals("setY")){
            retPointValue = method.invoke(obj,100);
            System.out.println("Execute setY Method ReturnValue: " + fieldChecker.getValue(clazz,"y",obj));
            correct = true;
        }

        return correct;
    }

}
