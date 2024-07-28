import java.util.Random;
import java.util.Scanner;

public class guessTheNumber {
    public static void main(String[] args) {

 
        Random random = new Random();
        int secretenum = random.nextInt(100) + 1;
        System.out.println("guess a number between 0 to 100 , you have 10 tries to guess correct number");
        Scanner sc = new Scanner(System.in);

        int tryOfGuessings = 0;
        boolean correctlyGuessed = false;
        int limitOfTries = 10;

        while (!correctlyGuessed) {
            int number = sc.nextInt();
            tryOfGuessings++;
            if (tryOfGuessings == limitOfTries) {
                System.out.println(
                        "failed ! you consumed all the " + tryOfGuessings + " tries left to guess the correct number.");
                return;
            } else if (number < secretenum) {
                System.out.println("Too low");
            } else if (number > secretenum) {
                System.out.println("Too  high");
            } else {
                correctlyGuessed = true;
                System.out.println("congretulation you guessed the coreect number in " + tryOfGuessings + " tries");
                sc.close();
            }
        }
    }
}
