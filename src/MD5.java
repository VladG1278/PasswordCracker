import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class MD5 {
    File tenwordshash;

    public MD5() {
        tenwordshash = new File("D:\\MD5Hashes.txt");
    }

    //Converts a string to its md5 hash
    public String MD5(String md5) {
        // https://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash-in-java
        // dac2009
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    //Check a md5 hash against the 10k password list
    public String findPasswordRainbowTable(String hash) {
        Scanner sc = null;
        try {
            sc = new Scanner(tenwordshash).useDelimiter("\\s* \\s");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int line = -1;
        String tempHash;
        String password = "Not Found";
        while (sc.hasNextLine()) {
            line++;
            tempHash = sc.nextLine();
            //Stream Stuff From: https://www.educative.io/answers/reading-the-nth-line-from-a-file-in-java
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

























































/*
    //Converted a 10k password list to 10k hashes
    // MD5 get 128 bit, and produce a 32 digit hash
    public void textToMD5 ()  {

        Scanner SC1 = null;
        try {
            SC1 = new Scanner(tenwords).useDelimiter("\\s*\n\\s*");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileWriter tenwordshash = null;
        try {
            tenwordshash = new FileWriter("10k Password Hash.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count = 0;
        while (SC1.hasNextLine()) {
            String md = SC1.next();
            md = MD5(md);
            try {
                tenwordshash.write(md + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
    */
