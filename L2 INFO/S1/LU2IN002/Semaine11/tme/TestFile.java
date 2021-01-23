package tme;

import java.io.File;
import java.io.IOException;
public class TestFile {
    public static void main(String[] args) {
        try {
            File f = new File (args[0]);
            f.delete();
            System.out.println("le fichier existe : " + (f.exists()?"oui" :"non"));
            f.createNewFile();
            System.out.println("le fichier existe : " + (f.exists()?"oui" :"non"));
            System.out.println(f.getAbsolutePath());
            System.out.println(f.getPath());

            
        }catch(IOException e){
            System.out.println(e);
        }
    }
}

