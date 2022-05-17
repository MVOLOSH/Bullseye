import javax.swing.*;

/*
A game to guess a random generated 4 digit number with infinite number of tries.
*/

public class Main {
    public static void main(String[] args) {
        int playAgain;
        String playerValue;
        boolean inputOk;
        boolean guessResult = false;
        do {
            JOptionPane.showMessageDialog(null, "Think of a 4 digit number.\n Click OK when you are ready...",
                    "Let's play a game", JOptionPane.INFORMATION_MESSAGE);
            GameLogic gameLogic = new GameLogic(); //generating a new random number to guess.
            do {
                playerValue = JOptionPane.showInputDialog("Please enter your guess!");
                if (playerValue == null) {
                    break;
                } else {
                    inputOk = GameLogic.inputGuessIsOkay(playerValue); //checking if the input guess number of the player is correct
                    if (inputOk) {
                        guessResult = gameLogic.hitOrBullseye(playerValue); //showing the game result.
                    }
                }
            } while (!inputOk || !guessResult);
            if (guessResult) {
                JOptionPane.showMessageDialog(null, gameLogic.getNumOfGuess(), "Winner", JOptionPane.INFORMATION_MESSAGE);
            }
            if (playerValue == null) {
                break;
            } else {
                playAgain = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Question", JOptionPane.YES_NO_OPTION);
            }
        } while (playAgain != 1);
    }
}


