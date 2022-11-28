import java.util.ArrayList;
import java.io.*;

public class BruteForce {

    private File tenwords;
    private ArrayList<String> arr;
    private String characters;
    FileWriter rainbow;
    SHA256 sha256;
    MD5 md;

    public BruteForce() {
        try {
            rainbow = new FileWriter("RainbowPasswordList.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tenwords = new File("10k Password.txt");
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
                    rainbow.write(arr.toString() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println (arr.toString());
                RainbowTableArr(k + 1, stop);
            }
        }
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