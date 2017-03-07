package myreflect;
import java.lang.reflect.Modifier;

enum TYPE {
    INT,CHAR,STRING,FLOAT,DOUBLE,ARRAY,OBJECT,VOID
}

class Modifier_Type_Checker{

    protected void printModifier(int modifier){
        StringBuilder sb = new StringBuilder();

        if(Modifier.isPrivate(modifier)){
            sb.append("this modifier is private \n");
        }
        if(Modifier.isStatic(modifier)){
            sb.append("this modifier is static \n");
        }
        if(Modifier.isFinal(modifier)){
            sb.append("this modifier is final \n");
        }
        if(Modifier.isProtected(modifier)){
            sb.append("this modifier is protected \n");
        }
        if(Modifier.isPublic(modifier)){
            sb.append("this modifier is public \n");
        }
        System.out.print(sb.toString());
    }


    protected TYPE isType(Class<?> type){
        // String typeName = type.getTypeName(); 1.8
        String typeName = String.valueOf(type);
        
        if(typeName.contains("int")){
            return TYPE.INT;
        }else if(typeName.contains("char")){
            return TYPE.CHAR;
        }else if(typeName.contains("float")){
            return TYPE.FLOAT;
        }else if(typeName.contains("double")){
            return TYPE.DOUBLE;
        }else if(typeName.contains("void")){
            return TYPE.VOID;
        }else if(typeName.contains("java.lang.String")){
            return TYPE.STRING;
        }else if(type.isArray()){
            return TYPE.ARRAY;
        }else{
            return TYPE.OBJECT;
        }
    }
}
