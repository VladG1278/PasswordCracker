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


    public String BCryptHash(String b) {
        return BCrypt.hashpw(b, BCrypt.gensalt());
    }

    // this will find the password in the large dictionary 10k file
    public String findPasswordRainbowTable(String hash) {
       Scanner sc = null;
       String password;
       String salt;
       String costfactor;
       String [] sepHash = hash.split("$", 4);

        try {
            sc = new Scanner (Dictionary).useDelimiter("\\s*\n\\s");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
          //password =  File.readLine(Dictionary);
            password = sc.nextLine ();
            //find salt and then hash plain
            if (BCrypt.checkpw (password, hash)) {
                return password;
            }
            //System.out.println(a.checkpw("testing", a.hashpw("test",a.gensalt())));

        }

        return "Not Found";
    }




}