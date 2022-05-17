import javax.swing.*;
import java.util.ArrayList;

//The logic behind the game.
public class GameLogic {
    private int numberToGuess;
    private String newNumberToGuess;
    private static final int inputBound = 4;
    private final ArrayList<String> listOfGuessesAndResults;

    GameLogic() {
        this.numberToGuess = (int) ((Math.random() * (9999))); //generating a number between 0 - 9999.
        newNumberToGuess = String.format("%04d", numberToGuess); // formatting the int to a string with leading 0.
        while (!noRepetitiveNumbers((newNumberToGuess), true)) { //checking if the generated number is okay.
            this.numberToGuess = (int) ((Math.random() * (9999)));
            newNumberToGuess = String.format("%04d", numberToGuess);
        }
        this.listOfGuessesAndResults = new ArrayList<String>();
    }

    public String getNewNumberToGuess() {
        return this.newNumberToGuess; //the new random number that was generated.
    }

    public int getNumberToGuess() {
        return this.numberToGuess;  //the random number that was generated.
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String line : listOfGuessesAndResults) {
            str.append(line);
        }
        return str.toString();
    }

    String getNumOfGuess() {
        return "You won!!!\nNumber of guesses to win was: " + this.listOfGuessesAndResults.size(); //number of guesses it took to win.
    }

    boolean hitOrBullseye(String str) { //checking if there is a bullseye or a hit while comparing the random generated number and the input value of the player.
        int hit = 0, bullseye = 0, miss = 0;
        for (int i = 0; i < inputBound; i++) {
            for (int j = 0; j < inputBound; j++) {
                if (i == j) {
                    if (str.charAt(i) == String.valueOf(newNumberToGuess).charAt(j)) {
                        bullseye++;
                    }

                } else {
                    if (str.charAt(i) == String.valueOf(newNumberToGuess).charAt(j)) {
                        hit++;
                    }
                }
            }
        }
        listOfGuessesAndResults.add("Player guess is : " + str + ", Hit = " + hit + " and Bullseye = " + bullseye + "\n");
        String newArrayListString = listOfGuessesAndResults.toString().replace("]", "").replace("[", ""); //removing the arraylist brackets while printing.
        JOptionPane.showMessageDialog(null, newArrayListString, "Info about guesses", JOptionPane.INFORMATION_MESSAGE);

        return bullseye == inputBound; //if bullseye = 4 the player found the generated number.
    }

    static boolean inputGuessIsOkay(String str) {
        return ((correctInputBound(str)) && (correctTypeInput(str)) && (noRepetitiveNumbers(str, false))); //input error checking.
    }

     private static boolean noRepetitiveNumbers(String str, boolean isObject) { //checking if there is repetitive numbers.
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                if ((i != j) && (str.charAt(i) == str.charAt(j))) {
                    if (!isObject) {
                        JOptionPane.showMessageDialog(null, "Invalid. Please enter a none repetitive number", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean correctTypeInput(String str) { //checking if the string is only numbers.
        for (int i = 0; i < inputBound; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                JOptionPane.showMessageDialog(null, "Invalid. Please enter a number", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private static boolean correctInputBound(String str) { //checking if the string was 4 digit number.
        if (str != null && str.length() == inputBound) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid. Please enter a 4 digit number", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
