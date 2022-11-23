import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.security.*;
import java.io.*;
import java.util.stream.Stream;
public class BCrypt {
        File BcryptHash;

        public BCrypt() {
            BcryptHash = new File("BCrypt.txt");
            System.out.println(BCryptEncode("test"));
        }

        //
        public String BCryptEncode(String b) {
            try {
                java.security.MessageDigest B = java.security.MessageDigest.getInstance("B-Crypt");
                byte[] array = B.digest(b.getBytes());
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
                sc = new Scanner(BcryptHash).useDelimiter("\\s*\n\\s");
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


