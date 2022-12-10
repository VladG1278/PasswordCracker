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
        /*String hash = "$2a$10$1BHTSuFNQukWXrj1Gk0H2e/0Qa/vvOrDj8WXOAa5Kzmn9Z7ADPKza";
        String [] sepHash = hash.split("$", 4);
        for(int i = 0; i < sepHash.length; i++){
            System.out.println(sepHash[i]);
        }

         */
        BCryptHash b = new BCryptHash();
        String a = b.BCryptHash("test");
        System.out.println (a);
        System.out.println (BCrypt.checkpw("test",a));
    }


    }

