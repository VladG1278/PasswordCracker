public class PassCrack {

    //Runs the program by taking in some arguments
    public static void main(String[] args) {
        if (args.length != 3) {
            man();
        } else {

            String string = args[2];
            if (args[0].equals("-BF")) {
                if (args[1].equals("-M")) {
                    BruteForce b = new BruteForce ("-M", string);
                } else if (args[1].equals("-S")) {
                    BruteForce b = new BruteForce ("-S", string);
                } else if (args[1].equals("-B")) {
                    BruteForce b = new BruteForce ("-B", string);
                }

            } else if (args[0].equals("-D")) {
                Dictionary d;
                if (args[1].equals("-M")) {
                    d = new Dictionary("-M", string);
                } else if (args[1].equals("-S")) {
                    d = new Dictionary("-S", string);
                } else if (args[1].equals("-B")) {
                    d = new Dictionary("-B", string);
                }

            } else if (args[0].equals("-H")) {
                if (args[1].equals("-M")) {
                    MD5 m = new MD5();
                    System.out.println(m.MD5(string));
                } else if (args[1].equals("-S")) {
                    SHA256 s = new SHA256();
                    System.out.println(s.SHA256Hash(string));
                } else if (args[1].equals("-B")) {
                    BCryptHash b = new BCryptHash("");
                    System.out.println(b.BCryptHash(string));
                }
            } else {
                System.out.println("Incorrect Arguments. Please Try Again. :( ");
            }
        }
    }
    //Prints out a message if something is wrong
    public static void man () {
        System.out.println ("Something you entered was not correct.\nPlease check the README for more information.");
    }


}
