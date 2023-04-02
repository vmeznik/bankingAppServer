package bankingApp.utility;

import java.util.Random;

public class AccountNumberGenerator {
   private static Random rand = new Random();
    public static int accNumber;
    private static String numberAsString ="";


   public static int generateNumber()
    {
        for (int i = 0; i < 9; i++) {
            int n = rand.nextInt(10);
            numberAsString = numberAsString+n;
        }
        accNumber = Integer.parseInt(numberAsString);
        return accNumber;
    }
}

