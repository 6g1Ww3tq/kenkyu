package myreflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ThickLineSegmentMethodChecker extends MethodChecker{

    public ThickLineSegmentMethodChecker(){
        super(2);
    }

    /*
     * @Override
     */
    protected boolean nameChecker(Method method,String methodName){
        int modifier = method.getModifiers();
        boolean correct = false;
        StringBuilder sb = new StringBuilder();

        if(methodName.equals("makeForm")){
            sb.append("methodName:makeForm ");
            if(modifier == Modifier.PUBLIC){
                sb.append("public ");
                correct = true;
            }

            sb.append("...found");
        }else if(methodName.equals("toString")){
            sb.append("methodName:toString ");
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
     * @Override
     */
    protected boolean invokeChecker(Method method,Object obj) throws IllegalAccessException,InvocationTargetException,IllegalArgumentException{
        boolean correct = false;
        String methodName = method.getName();
        Object retThickLineSegmentValue;

        if(methodName.equals("makeForm")){
            correct = true;
            retThickLineSegmentValue = method.invoke(obj);
            System.out.println("Execute makeForm Method ReturnValue: " + retThickLineSegmentValue);
        }else if(methodName.equals("toString")){
            correct = true;
            retThickLineSegmentValue = method.invoke(obj);
            System.out.println("Execute toString Method ReturnValue: " + retThickLineSegmentValue);
        }

        return correct;
    }

}
