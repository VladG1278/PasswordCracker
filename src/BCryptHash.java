import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;



public class BCryptHash {
    File BcryptHash;
    File Dictionary;

    private String salt;
    private String costfactor;
    private String version;
    private String hash;

    public BCryptHash() {
        Dictionary = new File ("Dictionary_10k Password.txt");

        salt = "";
        costfactor = "";
        version = "";
        hash = "";

    }


    public String BCryptHash(String b) {

        if(salt.length() == 0 ){
            return BCrypt.hashpw(b, BCrypt.gensalt());
        }
        else{
            return BCrypt.hashpw(b, salt);
        }
    }

    // this will find the password in the large dictionary 10k file
    public String findPasswordRainbowTable(String hash) {
       Scanner sc = null;
       String password = "";
       salt = findSalt(hash);
        try {
            sc = new Scanner (Dictionary).useDelimiter("\\s*\n\\s");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /* while (sc.hasNextLine()) {
           password = sc.nextLine ();
          //if(BCryptHash(password).equals(hash);
            //find salt and then hash plain
            if (BCrypt.checkpw (password, hash)) {
                return password;
            }
            //System.out.println(a.checkpw("testing", a.hashpw("test",a.gensalt())));

        }

         */

        return "Not Found";
    }

    public String findSalt(String hashIn){
        String hash = hashIn;
        version = hash.substring(1,3);
        costfactor = hash.substring(4,6);
        hash = hash.substring(hash.lastIndexOf("$")+1);
        return  hash.substring(0,23);
    }




}