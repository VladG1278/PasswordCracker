import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.security.*;
import java.io.*;
import java.util.stream.Stream;

public class SHA256 {
    File SHA256Hash;

    public SHA256() {
        SHA256Hash = new File("D:\\SHA256Hashes.txt");
    }

    public String SHA256Hash(String SHA256) {
        try {
            java.security.MessageDigest sha = java.security.MessageDigest.getInstance("SHA-256");
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


        public String findPasswordRainbowTable (String hash){
            Scanner sc = null;
            try {
                sc = new Scanner(SHA256Hash).useDelimiter("\\s* \\s");
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
    }

