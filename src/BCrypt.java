import java.io.*;

public class BCrypt {
    File BcryptHash;

    public BCrypt() {
        BcryptHash = new File("BCrypt.txt");
        System.out.println(BCryptEncode("test"));
    }

    //
    public String BCryptEncode(String b) {
        return "";
    }

}


