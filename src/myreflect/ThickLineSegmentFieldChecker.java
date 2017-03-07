package myreflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ThickLineSegmentFieldChecker extends FieldChecker{

    public ThickLineSegmentFieldChecker(){
        super(1);
    }

    /*
     * @Override
     */
    protected boolean name_Type_Checker(Field field,String fieldName){
        boolean correct = false;
        int modifier = field.getModifiers();
        TYPE fieldType = isType(field.getType());
        StringBuilder sb = new StringBuilder();

        if(fieldName.equals("width")){
            sb.append("field:width ");

            if(fieldType == TYPE.INT){
                sb.append("int ");
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
