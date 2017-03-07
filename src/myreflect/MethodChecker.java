package myreflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract class MethodChecker extends Modifier_Type_Checker{
    int allCollectAnswers;
    int numberOfCorrects;

    public MethodChecker(int allCollectAnswers){
        this.allCollectAnswers= allCollectAnswers;
        this.numberOfCorrects = 0;
    }

    public void analyze(Object obj){
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        int length = methods.length;

        System.out.println("---MethodsSearch---");
        System.out.println("NumberOfMethods:" + length +"\n");

        for (Method method : methods) {
            method.setAccessible(true);
            System.out.println("---"+method+"---");
            printModifier(method.getModifiers());
            printReturnType(method.getReturnType());
            System.out.println("methodName:"+method.getName());
            // paramChecker(method); 1.8
            try {
                if(invokeChecker(method,obj) && nameChecker(method,method.getName())){
                    System.out.println("this method is correct from the definition");
                    this.numberOfCorrects++;
                }else{
                    System.out.println("this method is different from the definition");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                System.out.println("wrong number of arguments");
            }
            System.out.println();
        }

        displayResult();
    }

    protected void printReturnType(Class<?> returnType){
        // String typeName = returnType.getTypeName(); 1.8
        String typeName = String.valueOf(returnType);

        if(typeName.equals("int")){
            System.out.println("this return type is int");
        }else if(typeName.equals("char")){
            System.out.println("this reutrn type is char");
        }else if(typeName.equals("String")){
            System.out.println("this reutrn type is String");
        }else if(typeName.equals("float")){
            System.out.println("this reutrn type is float");
        }else if(typeName.equals("double")){
            System.out.println("this reutrn type is double");
        }else if(typeName.equals("void")){
            System.out.println("this reutrn type is void");
        }else{
            System.out.println("this reutrn type is Object");
        }

    }

    protected void displayResult(){
        StringBuilder sb = new StringBuilder();
        int index = 0;

        sb.append("--MethodResult--\n");
        for (; index < this.numberOfCorrects; index++) {
            sb.append("*****");
        }
        for (; index < this.allCollectAnswers; index++) {
            sb.append("=====");
        }
        sb.append(":"+(double)this.numberOfCorrects / this.allCollectAnswers * 100 + "%" + "\n");

        System.out.println(sb.toString());
    }

    abstract protected boolean nameChecker(Method method,String methodName);

    // abstract protected void paramChecker(Method method); 1.8

    abstract protected boolean invokeChecker(Method method,Object obj) throws IllegalAccessException,InvocationTargetException,IllegalArgumentException;

}
