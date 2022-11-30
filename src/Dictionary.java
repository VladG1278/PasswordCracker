import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class Dictionary {
    String hash;
    MD5 m;
    SHA256 s;
    BCryptHash b;

    public Dictionary(String hashType, String hash) {
        this.hash = hash;
        String password = "";
        if (hashType.equals("-M")) {
            m = new MD5();
            password = m.findPasswordRainbowTable(hash);
            if (password.equals("Not Found")) {
                System.out.println ("The password was not found. Please use the Brute Force attack for better results.");
            } else {
                System.out.println ("The password that matches the hash is: " + password);
            }
        } else if (hashType.equals("-S")) {
            s= new SHA256();
            password = s.findPasswordRainbowTable(hash);
            if (password.equals("Not Found")) {
                System.out.println ("The password was not found. Please use the Brute Force attack for better results.");
            } else {
                System.out.println ("The password that matches the hash is: " + password);
            }
        } else if (hashType.equals("-B")) {
           b = new BCryptHash();
          password = b.findPasswordRainbowTable(hash);
            if (password.equals("Not Found")) {
                System.out.println ("The password was not found. Please use the Brute Force attack for better results.");
            } else {
                System.out.println ("The password that matches the hash is: " + password);
            }
        }
    }
}



