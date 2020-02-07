

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            new RandomIMAGE();
        } catch (NullPointerException e) {
            System.err.println("Please connect to the internet.");

        }

    }
}
