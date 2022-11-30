import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class BCryptHash {
    File BcryptHash;
    File Dictionary;
    public BCryptHash() {
        BcryptHash = new File("BCrypt.txt");
        Dictionary = new File ("Dictionary_10k Password.txt");
        BCryptHashDictionary();

    }


    public String BCryptEncode(String b) {
        return BCrypt.hashpw(b, BCrypt.gensalt());
    }

    public String findPasswordRainbowTable(String hash) {
        Scanner sc = null;
        try {
            sc = new Scanner(BcryptHash).useDelimiter("\\s*\n\\s");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int line = -1;
        String tempHash;
        String password = "Not Found";
        while (sc.hasNextLine()) {
            line++;
            tempHash = sc.nextLine();
            if (hash.equals(tempHash)) {
                try (Stream<String> lines = Files.lines(Paths.get("Dictionary_10k Password.txt"))) {
                    password = lines.skip(line).findFirst().get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return password;
    }


    public void BCryptHashDictionary() {
        Scanner SC1 = null;
        try {
            SC1 = new Scanner(Dictionary).useDelimiter("\\s*\n\\s*");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileWriter tenwordshash = null;
        try {
            tenwordshash = new FileWriter(BcryptHash, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (SC1.hasNextLine()) {
            String BC = SC1.nextLine();
            BC = (BCryptEncode(BC));
            try {
                tenwordshash.write(BC + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}