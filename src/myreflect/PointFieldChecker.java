package myreflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class PointFieldChecker extends FieldChecker{


    public PointFieldChecker(){
        super(2);
    }

    /*
     * @Override
     */
    protected boolean name_Type_Checker(Field field,String fieldName){
        boolean correct = false;
        int modifier = field.getModifiers();
        TYPE fieldType = isType(field.getType());
        StringBuilder sb = new StringBuilder();

        if(fieldName.equals("x")){
            sb.append("field:x ");

            if(fieldType == TYPE.DOUBLE){
                sb.append("double ");
                if(modifier == Modifier.PRIVATE){
                    sb.append("private ");
                    correct = true;
                }
            }

            sb.append("...found");
        }else if(fieldName.equals("y")){
            sb.append("field:y ");

            if(fieldType == TYPE.DOUBLE){
                sb.append("double ");
                if(modifier == Modifier.PRIVATE){
                    sb.append("private ");
                    correct = true;
                }
            }

            sb.append("...found");
        }

        System.out.println(sb.toString());

        return correct;
    }
}
