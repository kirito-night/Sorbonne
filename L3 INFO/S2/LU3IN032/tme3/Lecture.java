import java.lang.reflect.*;

public class Lecture{
    public static void main(String args[]){
        Class c=null;
        Field[] champs=null;
        Method[] methodes=null;
        try{
            c=Class.forName(args[0]);
            champs = c.getDeclaredFields();
            methodes = c.getMethods();
        }
        catch(ClassNotFoundException e){//...;
            System.exit(0);
        }
        catch(SecurityException e){//PBd’autorisation
            System.exit(0);
        }
        for(int i=0;i<champs.length;i++){
            Field uc=champs[i];
            System.out.println("champs"+i+":"+uc);
        }
        for(int i=0;i<methodes.length;i++){
            Method um=methodes[i];
            System.out.println("methodes"+i+":"+um);
        }
    }
}
