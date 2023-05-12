package bankingApp.utility;

import java.util.Random;

public class AccountNumberGenerator {
    private static Random rand = new Random();
    private static String numberAsString;


    public static String generateNumber() {
        numberAsString = "";
        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(10);
            numberAsString = numberAsString + n;
        }

        return numberAsString;
    }

}

