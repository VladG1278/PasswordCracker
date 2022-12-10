import java.util.Arrays;
import java.util.Dictionary;

public class Runner {
    public static void main(String[] args) {
        //MD5 m = new MD5();
        //BruteForce b = new BruteForce ("-M", m.MD5("a8"));
        //BCryptHash n = new BCryptHash()
        //BCryptHash a = new BCryptHash();
        //System.out.println(a.hashpw("test", a.gensalt()));
        //System.out.println(a.checkpw("testing", a.hashpw("test",a.gensalt())));
        String hash = "$2a$10$1BHTSuFNQukWXrj1Gk0H2e/0Qa/vvOrDj8WXOAa5Kzmn9Z7ADPKza";
        //String [] sepHash = hash.split("$", 4);
        String temp = hash;
        String version = "";
        String  costfactor = "";
        String salt = "";
        String pass = "";

        version = hash.substring(1,3);
        costfactor = hash.substring(4,6);
        hash = hash.substring(hash.lastIndexOf("$")+1);
        salt = hash.substring(0,23);
        hash = hash.substring(23);



    }


    }

