import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;

public class BruteForce {

    private File tenwords;


    private ArrayList<String> arr;
    private String characters;
    private String[] charactersEZ;

    //FileWriter
    FileWriter rainbow;
    FileWriter rainbowMD5;
    FileWriter rainbow256;
    FileWriter checkFinal;


    //Objects to run methods in Class
    SHA256 sha256;
    MD5 md;
    BCryptHash bcrypt;

    //RainbowPasswordHashesBF
    private File rainbowList;
    private File rainbowListHashesmd;
    private File rainbowListHashes256;
    private File rainbowListHashes;

    //Inputs
    private String type;
    private String input;

    //Checks
    Boolean finished = true;
    Boolean Bcrypt = false;


    //This is the constructor that initializes the variables such as files, filewriters, etc.
    public BruteForce(String type1, String string) {
        type = type1;
        input = string;

        //Rainbow Password List
        rainbowList = new File("D:\\RainbowPasswordList.txt");

        //File Writers

        try {
            rainbow = new FileWriter(rainbowList, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Files
        tenwords = new File("Dictionary_10k Password.txt");
        rainbowListHashesmd = new File ("D:\\MD5Hashes.txt");
        rainbowListHashes256 = new File ("D:\\SHA256Hashes.txt");


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
        BruteForceRunner ();
    }

    //Mini Runner that controls the logic for checking files before generating new passwords
    public void BruteForceRunner() {

        if (type.equals("-B")) {
            bcrypt = new BCryptHash("-B");
            String a = bcrypt.findPasswordRainbowTable(input);
            if (a.equals ("Not Found")) {
                Bcrypt = true;
                RainbowTableArrRunner();
            } else {
                System.out.println ("\nYour password is: " + a);
            }
        } else {
            if (type.equals("-M")) {
                rainbowListHashes = new File("D:\\MD5Hashes.txt");
                String a = findPasswordRainbowTable(input);
                if (a.equals("Not Found")) {
                    RainbowTableArrRunner();
                } else {
                    System.out.println ("\nYour password is: " + a);
                }
            } else if (type.equals("-S")) {
                rainbowListHashes = new File("D:\\SHA256Hashes.txt");
                String a = findPasswordRainbowTable(input);
                if (a.equals("Not Found")) {
                    RainbowTableArrRunner();
                } else {
                    System.out.println ("\nYour password is: " + a);
                }
            }

        }
    }

    //Converts ArrayList to a one word String
    public String toString() {
        String word = "";
        for (int i = 0; i < arr.size(); i++) {
            word += arr.get(i);
        }
        return word;
    }

    //This checks if the password hash is found in the rainbowtable
    //It returns the password or "Not Found"
    public String findPasswordRainbowTable(String hash) {
        Scanner sc = null;
        try {
            sc = new Scanner(rainbowListHashes).useDelimiter("\\s*\n\\s");
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
                try (Stream<String> lines = Files.lines(Paths.get("D:\\RainbowPasswordList.txt"))) {
                    password = lines.skip(line).findFirst().get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return password;
    }

    //recursion to generate a whole bunch of text and write the hashes to file (except for BCrypt) ex. a b ... aa ab... aaa ... aab
    //k is the index where you start in the array that will contain the generated text
    //I is the index of the last character in the saved password so that we can restart from where we left off
    //Stop is just the number of indexes in arr. When it matches k it adds a new index
    //This will run until it finds your match
    public void RainbowTableArr(int k, int stop, int start) {
        if (k == stop) {
        } else {
            int i = 0;
            if (start != 0) {
                i = start;
            }
            for (i = 0; i < charactersEZ.length; i++) {
                if (finished) {
                    arr.set(k, charactersEZ[i]);

                    try {
                        rainbow.write(toString() + "\n");
                        rainbowMD5.write(md.MD5(toString()) + "\n");
                        rainbow256.write(sha256.SHA256Hash(toString()) + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (Bcrypt) {
                        if (BCrypt.checkpw (toString(), input)) {
                            try {
                                checkFinal.write(toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                rainbow.close();
                                rainbowMD5.close();
                                rainbow256.close();
                                checkFinal.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            finished = false;
                            break;
                        }

                    }else if (check(md.MD5(toString()), sha256.SHA256Hash(toString()))) {
                        try {
                            checkFinal.write(toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            rainbow.close();
                            rainbowMD5.close();
                            rainbow256.close();
                            checkFinal.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        finished = false;
                        break;
                    }
                    RainbowTableArr(k + 1, stop, 0);
                }
            }
        }
    }

    //This runs the RainbowTableArr method and takes the input from the file FinalCheck to start the recursive generator
    public void RainbowTableArrRunner() {
        File finalC = new File ("D:\\FinalCheck.txt");
        boolean start = false;
        Scanner sc = null;
        try {
            sc = new Scanner (finalC);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String lastPassword = "";
        //set stuff from last text file to string
        if (sc.hasNextLine()) {
            lastPassword = sc.nextLine();
            start = true;
            if (lastPassword.length() > 0) {
                for (int i = 0; i < lastPassword.length(); i++) {
                    if (i == lastPassword.length() - 1) {
                        arr.add(i, lastPassword.substring(i));
                    } else {
                        arr.add(i, lastPassword.substring(i, i + 1));
                    }
                }
            }
        }
        try {
            rainbow256 = new FileWriter(rainbowListHashes256, true);
            rainbowMD5 = new FileWriter(rainbowListHashesmd,true);
            checkFinal = new FileWriter ("D:\\FinalCheck.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i =0;
        while (finished) {
            i++;
            arr.add("");
            //Stop is how many characters total you want the password to be
            if (i== 1 && start == true) {
                RainbowTableArr(0, i, characters.indexOf(lastPassword.substring(lastPassword.length() - 1)));
            } else {
                RainbowTableArr(0,i,0);
            }
        }
            System.out.println("\nYour Password is : " + toString());

    }

    //Checks if an inputted MD5 hash (md5) or a SHA256 hash (sha) is equal to the inputted hash
    public Boolean check (String md5, String sha) {

        if (input.equals (md5)) {
            return true;
        } else if (input.equals (sha)) {
            return true;
        }

        return false;
    }
}
