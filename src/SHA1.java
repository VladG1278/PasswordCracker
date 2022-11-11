import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class SHA1 {
        File SHA1Hash;

        public SHA1() {
            SHA1Hash = new File("SHA-1 Hash.txt");
            System.out.println (SHA1Encode("test"));
        }

        public String SHA1Encode(String SHA256) {
            try {
                java.security.MessageDigest sha = java.security.MessageDigest.getInstance("SHA-1");
                byte[] array = sha.digest(SHA256.getBytes());
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < array.length; i++) {
                    sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
                }
                return sb.toString();
            } catch (java.security.NoSuchAlgorithmException e) {
            }
            return null;


        }


        public String find10KPassword (String hash){
            Scanner sc = null;
            try {
                sc = new Scanner(SHA1Hash).useDelimiter("\\s*\n\\s");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int line = -1;
            String tempHash;
            String password = "Not Found";
            while (sc.hasNextLine()) {
                line++;
                //This used to be next which broke my code. Took Two days to figure this out
                tempHash = sc.nextLine();
                if (hash.equals(tempHash)) {
                    try (Stream<String> lines = Files.lines(Paths.get("10k Password.txt"))) {
                        password = lines.skip(line).findFirst().get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
            return password;
        }
    }
