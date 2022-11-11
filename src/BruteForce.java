import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.security.*;
import java.io.*;
import java.util.stream.Stream;

public class BruteForce {
    File tenwords;

    public BruteForce() {
        tenwords = new File("10k Password.txt");

    }

    public String md5(String hash) {
        MD5 md = new MD5();
        return md.find10KPassword(hash);
    }

    public String sha256(String hash) {
        SHA256 sha256 = new SHA256();
        return sha256.find10KPassword(hash);
    }

    public String sha1(String hash) {
        SHA1 sha1 = new SHA1();
        return sha1.find10KPassword(hash);
    }
}