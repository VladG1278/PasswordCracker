import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class BCryptHash {
    File BcryptHash;
    File Dictionary;
    public BCryptHash() {
        Dictionary = new File ("Dictionary_10k Password.txt");


    }


    public String BCryptEncode(String b) {
        return BCrypt.hashpw(b, BCrypt.gensalt());
    }

    public String findPasswordRainbowTable(String hash) {
       Scanner sc = null;
       String password;
        try {
            sc = new Scanner (Dictionary).useDelimiter("\\s*\n\\s");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
         //   password =  File.readString(Dictionary);
        }

        return "";
    }




}