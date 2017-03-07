package myreflect;

public class ClassChecker extends Modifier_Type_Checker{
    public void analyze(Object obj){
        Class<?> clazz = obj.getClass();
        StringBuilder sb = new StringBuilder();

        // sb.append("---InterfacesSearch---\n");
        // sb.append(toStringInterfaces(clazz));
        // sb.append("---ClassesSearch---\n");
        // sb.append(toStringClasses(clazz));
        sb.append("---SuperClasses---\n");
        sb.append(toStringSuperClasses(clazz));
        sb.append("\n");

        System.out.println(sb.toString());
    }

    protected String toStringInterfaces(Class<?> clazz){
        StringBuilder sb = new StringBuilder();
        if(clazz != null){
            for (Class<?> c : clazz.getInterfaces()) {
                sb.append(c);
                sb.append(" >> ");
                sb.append(toStringInterfaces(c));
            }
        }
        return sb.toString(); 
    }

    protected String toStringClasses(Class<?> clazz){
        StringBuilder sb = new StringBuilder();
        if(clazz != null){
            for (Class<?> c : clazz.getClasses()) {
                sb.append(c);
                sb.append(" >> ");
                sb.append(toStringClasses(c));
            }
        }

        return sb.toString(); 
    }

    protected String toStringSuperClasses(Class<?> clazz){
        StringBuilder sb = new StringBuilder();
        if(clazz != null){
            sb.append(clazz);
            sb.append(" >> ");
            sb.append(toStringSuperClasses(clazz.getSuperclass()));
        }

        return sb.toString(); 
    }
}
