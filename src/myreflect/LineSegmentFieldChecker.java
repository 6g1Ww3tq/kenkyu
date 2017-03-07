package myreflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class LineSegmentFieldChecker extends FieldChecker{

    public LineSegmentFieldChecker(){
        super(3);
    }

    /*
     * @Override
     */
    protected boolean name_Type_Checker(Field field,String fieldName){
        boolean correct = false;
        int modifier = field.getModifiers();
        TYPE fieldType = isType(field.getType());
        StringBuilder sb = new StringBuilder();

        if(fieldName.equals("start")){
            sb.append("field:start ");

            if(fieldType == TYPE.OBJECT){
                sb.append("Object ");
                if(modifier == Modifier.PRIVATE){
                    sb.append("private ");
                    correct = true;
                }
            }

            sb.append("...found");
        }else if(fieldName.equals("end")){
            sb.append("field:end ");

            if(fieldType == TYPE.OBJECT){
                sb.append("Object ");
                if(modifier == Modifier.PRIVATE){
                    sb.append("private ");
                    correct = true;
                }
            }

            sb.append("...found");
        }else if(fieldName.equals("length")){
            sb.append("field:length ");

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
