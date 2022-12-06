import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.Arrays;

public class BruteForce {

    private File tenwords;


    private ArrayList<String> arr;
    private String characters;
    private String[] charactersEZ;

    //FileWriter
    FileWriter rainbow;
    FileWriter rainbowMD5;
    FileWriter rainbow256;


    //Objects to run methods in Class
    SHA256 sha256;
    MD5 md;
    BCryptHash bcrypt = new BCryptHash();

    //RainbowPasswordHashesBF
    private File rainbowList;
    private File rainbowListHashes;


    //Inputs
    private String type;
    private String input;

    public BruteForce(String type, String string) {
        this.type = type;
        input = string;

        //Rainbow Password List
        rainbowList = new File("D:\\RainbowPasswordList.txt");

        //File Writers

        try {
            rainbow = new FileWriter(rainbowList, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Dictionary File
        tenwords = new File("Dictionary_10k Password.txt");

        //type of Hashing Objects
        md = new MD5();
        sha256 = new SHA256();
        arr = new ArrayList<String>();

        //Total Characters in Password
        characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVXYZ1!2@3#4$5%6^7&8*9(0)-_=+,<.>/?;:'\\\"{}[]~`";
        charactersEZ = new String[characters.length()];
        for (int i = 0; i < characters.length(); i++) {
            if (i == characters.length() - 1) {
                charactersEZ[i] = characters.substring(i);
            }
            charactersEZ[i] = characters.substring(i, i + 1);
        }


        //Testing
        RainbowTableArrRunner();


    }
// i when originally calling is the last character on the characters list that is in the txt file


    // this method will use brute force to find the correct password for the hash
    public String BruteForceRunner() {
        Scanner sc = null;
        try {
            sc = new Scanner(rainbowList).useDelimiter("\\s* \\s");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (type.equals("-B")) {
            bcrypt = new BCryptHash();
        } else {
            if (type.equals("-M")) {
                rainbowListHashes = new File("D:\\MD5Hashes.txt");
                if (findPasswordRainbowTable(input).equals("Not Found")) {
                    RainbowTableArrRunner();
                }
            } else if (type.equals("-S")) {
                rainbowListHashes = new File("D:\\SHA256Hashes.txt");
                if (findPasswordRainbowTable(input).equals("Not Found")) {
                    RainbowTableArrRunner();
                }
            }

        }


        return "Not Found";
    }

    //Converts ArrayList to a one word String
    public String toString() {
        String word = "";
        for (int i = 0; i < arr.size(); i++) {
            word += arr.get(i);
        }
        return word;
    }

    // program would go here to see if the password's hash is already in this list before creating new passwords
    public String findPasswordRainbowTable(String hash) {
        Scanner sc = null;
        try {
            sc = new Scanner(rainbowListHashes).useDelimiter("\\s* \\s");
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
                try (Stream<String> lines = Files.lines(Paths.get("rainbowList.txt"))) {
                    password = lines.skip(line).findFirst().get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return password;
    }

    //recursion to generate a whole bunch of text
    public void RainbowTableArr(int k, int stop) {

        if (k == stop) {
        } else {
            for (int i = 0; i < characters.length(); i++) {
                arr.set(k, characters.substring(i, i + 1));
               try {
                    rainbow.write(toString() + " ");
                    rainbowMD5.write (md.MD5(toString()) + " ");
                    rainbow256.write (sha256.SHA256Encode(toString()) + " ");
                } catch (IOException e) {
                   e.printStackTrace();
               }
                System.out.println(arr.toString());
                RainbowTableArr(k + 1, stop);
            }
        }
    }

    public void RainbowTableArrRunner() {
        try {
            rainbow256 = new FileWriter(rainbowListHashes, true);
            rainbowMD5 = new FileWriter(rainbowListHashes, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i =0; i < 4; i++) {
            arr.add ("");
        }
        //Stop is how many characters total you want the password to be
        RainbowTableArr (0, 32);

    }
}
