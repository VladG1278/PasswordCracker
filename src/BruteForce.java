import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

//BCryptHash Start (total 480k)  ::     8:05 am
//27000 finished at              ::     8:48 am

public class BruteForce {

    private File tenwords;


    private ArrayList<String> arr;
    private String characters;
    FileWriter rainbow;

    //Objects to run methods in Class
    SHA256 sha256;
    MD5 md;
    BCryptHash bcrypt = new BCryptHash();

    //RainbowPasswordHashesBF
    private File rainbowList;
    private File rainbowListM;
    private File rainbowListS;
    private File rainbowListB;

    //Inputs
    private String type;
    private String input;

    public BruteForce(String type, String string) {
       this.type = type;
        input = string;
        rainbowList = new File ("RainbowPasswordList.txt");
        try {
            rainbow = new FileWriter(rainbowList, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tenwords = new File("Dictionary_10k Password.txt");
        md = new MD5();
        sha256 = new SHA256();
        arr = new ArrayList <String>();
        characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVXYZ1!2@3#4$5%6^7&8*9(0)-_=+,<.>/?;:'\\\"{}[]~`";

        //generates all the possibilities using for k characters using the characters above ex. k= 1 [a,a],[b,a],[c,a] etc.
        for (int k =0; k < 8; k++) {
           arr.add("'");
           RainbowTableArr(0, k);
       }
    }

    //recursion to generate a whole bunch of text
    public void RainbowTableArr (int k, int stop) {
        if (k == stop) {
        } else {
            for (int i = 0; i < characters.length(); i++) {
                arr.set(k, characters.substring(i, i + 1));
                try {
                    rainbow.write(toString() + " ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println (arr.toString());
                RainbowTableArr(k + 1, stop);
            }
        }
    }

    public String BruteForceRunner () {
        Scanner sc = null;
        try {
            sc = new Scanner (rainbowList).useDelimiter("\\s* \\s");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (type.equals ("-M")) {
            rainbowListM = new File ("RainbowBFListMD5.txt");
            md = new MD5 ();
           String answer= md.BruteForce(input);
        }


        while (sc.hasNext()) {

        }



        return "Not Found";
    }







    //Converts ArrayList to a one word String
    public String toString () {
        String word = "";
        for (int i =0; i < arr.size(); i ++) {
            word += arr.get (i);
        }
        return word;
    }
}