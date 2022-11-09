// here's a change
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.security.*;
import java.io.*;
import java.util.stream.Stream;

public class BruteForce {

    File tenwords;
    File tenwordshash;
    public BruteForce() {
        tenwords = new File ("10k Password.txt");
        tenwordshash = new File ("10k Password Hash.txt");
        //textToMD5();
        System.out.println(find10KPassword("e10adc3949ba59abbe56e057f20f883e"));

    }

    //Converts a string to its md5 hash
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }


    //Converted a 10k password list to 10k hashes
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

    //Check a md5 hash against the 10k password list
    public String find10KPassword (String hash) {
        Scanner sc = null;
        try {
            sc = new Scanner (tenwordshash).useDelimiter("\\s*\n\\s");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int line = -1;
        String tempHash;
        String password = "Not Found";
        while (sc.hasNextLine ()) {
            line ++;
            tempHash = sc.next();
            if (tempHash.equals (tempHash)) {
                System.out.println(tempHash.getClass ());
                try (Stream<String> lines = Files.lines(Paths.get ("10k Password.txt"))) {
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
