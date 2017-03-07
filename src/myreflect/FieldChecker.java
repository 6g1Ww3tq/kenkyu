package myreflect;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

abstract class FieldChecker extends Modifier_Type_Checker{
    int allCollectAnswers;
    int numberOfCorrects;

    public FieldChecker(int allCollectAnswers){
        this.allCollectAnswers = allCollectAnswers;
        this.numberOfCorrects = 0;
    }

    public void analyze(Object obj){
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        int length = fields.length;

        System.out.println("---FieldsSearch---");
        System.out.println("NumberOfFields:" + length +"\n");

        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println("---"+field+"---");
            printModifier(field.getModifiers());
            printType(field.getType());
            System.out.println("fieldName:"+field.getName());
            if(name_Type_Checker(field , field.getName())){
                System.out.println("this field is correct from the definition");
                this.numberOfCorrects++;
            }else{
                System.out.println("this field is different from the definition");
            }
            System.out.println("this field value is " + toString(field,obj));

            System.out.println();
        }

        displayResult();
    }

    protected String getValue(Class<?> clazz,String fieldName,Object obj){
        String fieldValue = null;
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            fieldValue = String.valueOf(field.get(obj));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return fieldValue;
    }

    protected String toString(Field field,Object obj){
        StringBuilder sb = new StringBuilder();
        field.setAccessible(true);

        try{
            Class<?> clazz;
            Field[] fields;
            Object fieldValue = field.get(obj);
            Object indexObject;
            int length;
            TYPE fieldType = isType(field.getType());

            if(fieldType != TYPE.ARRAY && fieldType != TYPE.OBJECT){
                sb.append(fieldValue);
            }else if(fieldType == TYPE.ARRAY ){

                length = Array.getLength(fieldValue);
                for (int index = 0; index < length; index++) {
                    sb.append("[");

                    if (String.valueOf(fieldValue).contains(";") ) {
                        /*
                         * 未完成
                         */

                        indexObject = Array.get(fieldValue,index);
                        // clazz = fieldValue.getClass();
                        clazz = indexObject.getClass();
                        // System.out.println("indexObjectTYPE:"+indexObject);
                        // System.out.println("clazz:"+clazz);
                        fields = clazz.getDeclaredFields();
                        sb.append(toStringFieldsBuilder(fields,indexObject));
                    }else{
                        sb.append(Array.get(fieldValue,index));
                        sb.append(", ");
                    }

                    sb.append("]");
                }


            }else if (fieldType == TYPE.OBJECT) {
                clazz = fieldValue.getClass();
                System.out.println("typeobject:"+fieldType);
                fields = clazz.getDeclaredFields();

                sb.append("[");
                sb.append(toStringFieldsBuilder(fields,fieldValue));
                sb.append("]");
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    protected String toStringFieldsBuilder(Field[] fields,Object obj){
        StringBuilder sb = new StringBuilder();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                sb.append(f.get(obj));
                sb.append(", ");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    protected void printType(Class<?> type){

        switch (isType(type)) {
            case INT:
                System.out.println("this type is int");
                break;
            case CHAR:
                System.out.println("this type is char");
                break;
            case STRING:
                System.out.println("this type is String");
                break;
            case FLOAT:
                System.out.println("this type is float");
                break;
            case DOUBLE:
                System.out.println("this type is double");
                break;
            case ARRAY:
                System.out.println("this type is array");
                break;
            case OBJECT:
                System.out.println("this type is Object");
                break;
            default:
                ;
        }
    }

    protected void displayResult(){
        StringBuilder sb = new StringBuilder();
        int index = 0;

        sb.append("--FieldResult--\n");
        for (; index < this.numberOfCorrects; index++) {
            sb.append("*****");
        }
        for (; index < this.allCollectAnswers; index++) {
            sb.append("=====");
        }
        sb.append(":"+(double)this.numberOfCorrects / this.allCollectAnswers * 100 + "%"  + "\n");

        System.out.println(sb.toString());
    }

    abstract protected boolean name_Type_Checker(Field field,String fieldName);

}
