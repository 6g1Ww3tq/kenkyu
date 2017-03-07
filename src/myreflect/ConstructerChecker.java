package myreflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

abstract class ConstructerChecker extends Modifier_Type_Checker{
    int allCollectAnswers;
    int numberOfCorrects;

    public ConstructerChecker(int allCollectAnswers){
        this.allCollectAnswers= allCollectAnswers;
        this.numberOfCorrects = 0;
    }

    public void analyze(Object obj){
        Class<?> clazz = obj.getClass();
        Constructor<?>[] constructers = clazz.getDeclaredConstructors();
        int length = constructers.length;

        System.out.println("---ConstructorsSearch---");
        System.out.println("NumberOfConstructors:" + length +"\n");

        for (Constructor<?> constructer : constructers) {
            constructer.setAccessible(true);
            System.out.println("---"+constructer+"---");
            printModifier(constructer.getModifiers());
            System.out.println("ConstructorName:"+constructer.getName());
            // paramChecker(constructer); 1.8
            try {
                if(instanceChecker(constructer)){
                    System.out.println("this constructer is correct from the definition");
                    this.numberOfCorrects++;
                }else{
                    System.out.println("this constructer is different from the definition");
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            System.out.println();
        }

        displayResult();
    }


    protected void displayResult(){
        StringBuilder sb = new StringBuilder();
        int index = 0;

        sb.append("--ConstructerResult--\n");
        for (; index < this.numberOfCorrects; index++) {
            sb.append("*****");
        }
        for (; index < this.allCollectAnswers; index++) {
            sb.append("=====");
        }
        sb.append(":"+(double)this.numberOfCorrects / this.allCollectAnswers * 100  + "%" + "\n");

        System.out.println(sb.toString());
    }

    // abstract protected void paramChecker(Constructor<?> constructer); 1.8

    abstract protected boolean instanceChecker(Constructor<?> constructer) throws InstantiationException,IllegalAccessException,InvocationTargetException,NoSuchMethodException;
}
