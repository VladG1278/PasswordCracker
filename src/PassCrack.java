public class PassCrack {
    public static void main(String[] args) {
        String string = args [2];
        if (args [0].equals ("-BF")) {
            if (args [1].equals ("-M")){

            } else if (args [1].equals ("-S")){

            } else if (args [1].equals ("-B")){

            }



        } else if (args [0].equals ("-D")) {
            Dictionary d;
            if (args [1].equals ("-M")){
                d = new Dictionary ("-M",string);
            } else if (args [1].equals ("-S")){
                d = new Dictionary ("-S",string);
            } else if (args [1].equals ("-B")){
                d = new Dictionary ("-B",string);
            }

        } else if (args [0].equals ("-H")) {
            if (args [1].equals ("-M")){
                MD5 m = new MD5();
                System.out.println (m.MD5 (string));
            } else if (args [1].equals ("-S")){
                SHA256 s = new SHA256();
                System.out.println (s.SHA256Encode(string));
            } else if (args [1].equals ("-B")){
                BCryptHash b = new BCryptHash();
                System.out.println (b.BCryptEncode(string));
            }
        } else {
            System.out.println ("Incorrect Arguments. Please Try Again. :( ");
        }
    }


}
