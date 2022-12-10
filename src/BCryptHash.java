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

    public BCryptHash(String type) {
        if (type.equals ("-D")) {
            Dictionary = new File ("Dictionary_10k Password.txt");
        } else {
            Dictionary = new File ("D:\\RainbowPasswordList.txt");
        }


        salt = "";
        costfactor = "";
        version = "";
        hash = "";

    }

    //Hashes a string using bcrypt and a random generated salt
    public String BCryptHash(String b) {
        return BCrypt.hashpw(b, BCrypt.gensalt());
    }

    // this will check if the plain text from the dictionary file or the generated text file is equal to a bcrypt hash
    //This implents mindrot's checkpw method
    public String findPasswordRainbowTable(String hash) {
        Scanner sc = null;
        String password;

        try {
            sc = new Scanner (Dictionary).useDelimiter("\\s*\n\\s");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            password = sc.nextLine ();
            if (BCrypt.checkpw (password, hash)) {
                return password;
            }
        }

        return "Not Found";
    }

    //Finds the Salt, Version, and Cost Factor of A Bcrypt Hash
    //Currently not Implemented in the Program
    public String findSalt(String hashIn){
        String hash = hashIn;
        version = hash.substring(1,3);
        costfactor = hash.substring(4,6);
        hash = hash.substring(hash.lastIndexOf("$")+1);
        return  hash.substring(0,23);
    }




}