import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class TicTacToe {
    static char[] board;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cont;
        do {
            playGame();

            System.out.println("Enter 1 to play again or enter 0 to end game :)");
            cont = input.nextInt();
        } while (cont == 1);


        input.close();
        System.out.println("Thanks for playing!");
    }


    public static void playGame() {
        // creating a new board that consists of 9 chars
        board = new char[9];

        // each "spot" will be numbered from 1 to 9 for user's convenience and aesthetic
        for (int i = 0; i < 9; i ++) {
            board[i] = (char) ((i+1) + '0');
        }


        System.out.println("Welcome to TicTacToe. Select where you want to place your mark!");

        // while there is no winner or tie, the game will keep going
        while (checkGameState() == null) {
            buildBoard();
            Scanner input = new Scanner(System.in);

            // check if input is an int; if not loop resets
            if (!input.hasNextInt()) {
                System.out.println("Invalid input. Try again");
                continue;
            }

            int userMove = input.nextInt();


            // check is user's move is valid; if not loop resets
            if (!validMove(userMove)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            // user's move is accepted and placed on the board
            board[userMove - 1] = 'X';

            // computer's move next
            bestMove();
        }

        // once game state is decided (winner or tie), determine winner
        String winner = checkGameState();
        // reveal board to show the final state of the game
        buildBoard();
        if (winner.equals("tie")) {
            System.out.println("Tie game!");
            return;
        }
        System.out.println(winner + " wins!");


    }

    // build board in terminal
    public static void buildBoard() {
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("------------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("------------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8]);


    }

    // check if move is valid
    public static boolean validMove(int move) {
        // if it is within the bounds of the game
        if (move < 1 || move > 9) {
            return false;
        }

        // check if move is not on an already occupied spot
        return (board[move - 1] != 'X' && board[move - 1] != 'O');

    }

    // determine game state: returns the winner ("X" or "O"), "tie", or null if game has not concluded
    public static String checkGameState() {
        // horizontal win
        for (int i = 0; i <= 6; i += 3) {
            if (board[i] == 'O' && board[i+1] == 'O' && board[i + 2] == 'O') {
                return "O";
            }
            if (board[i] == 'X' && board[i+1] == 'X' && board[i + 2] == 'X') {
                return "X";
            }
        }

        // vertical win
        for (int i = 0; i <= 2; i++) {
            if (board[i] == 'O' && board[i+3] == 'O' && board[i + 6] == 'O') {
                return "O";
            }
            if (board[i] == 'X' && board[i+3] == 'X' && board[i + 6] == 'X') {
                return "X";
            }
        }

        // diagonal win
        if (board[0] == 'O' && board[4] == 'O' && board[8] == 'O') {
            return "O";
        }

        if (board[0] == 'X' && board[4] == 'X' && board[8] == 'X') {
            return "X";
        }

        if (board[2] == 'O' && board[4] == 'O' && board[6] == 'O') {
            return "O";
        }

        if (board[2] == 'X' && board[4] == 'X' && board[6] == 'X') {
            return "X";
        }

        // tied game
        if (isTie()) {
            return "tie";
        }

        return null;
    }

    // determine if game has concluded with no winner
    public static boolean isTie() {
        // check if board[] has any available spots. if it does, return false
        for (int i = 0; i < 9; i++) {
            if (board[i] != 'X' && board[i] != 'O') {
                return false;
            }
        }
        // game has no available spots, therefore a tie
        return true;
    }

    // determine the best move for the computer
    public static void bestMove() {
        int bestScore = Integer.MIN_VALUE; // lowest score possible
        int bestMove = 0;
        int score;

        // iterate over all possible moves
        for (int i = 0; i < 9; i++) {
            // check if move is valid
            if (validMove(i + 1)) {
                board[i] = 'O'; // temporarily make move
                score = minimax(0, false); // determine score using minimax
                board[i] = (char) ((i+1) + '0'); // reverse move to original state
                if (score > bestScore) { // determine if the new score is better than best one so far
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        board[bestMove] = 'O'; // make the best move
    }

    // minimax algorithm to evaluate best move for current player
    public static int minimax(int depth, boolean isMax) {
        String result = checkGameState(); // check if game has concluded
        int bestScore;
        int score;
        if (result != null) {  // if the game is over, return the score based on result
            if (result.equals("X")) {
                return -1;  // loss for 'O'
            }
            if (result.equals("O")) {
                return 1; // win for 'O'
            }
            if (result.equals("tie")) {
                return 0; // tie game
            }
        }

        if (isMax) {
            bestScore = Integer.MIN_VALUE; // lowest score possible
            for (int i = 0; i < 9; i++) {
                if (validMove(i + 1)) {
                    board[i] = 'O';
                    // recursively evaluate the move.
                    // isMax = false as next move is now 'humans' thus computer wants to minimize their score.
                    score = minimax(depth+1, false);
                    board[i] = (char) ((i+1) + '0');
                    bestScore = max(score, bestScore); // choose max score
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE; // highest possible score
            for (int i = 0; i < 9; i++) {
                if (validMove(i + 1)) {
                    board[i] = 'X';
                    // isMax = true as next move is now 'computer' thus computer wants to maximize their score.
                    score = minimax(depth + 1, true);
                    board[i] = (char) ((i + 1) + '0');
                    bestScore = min(score, bestScore); // choose min score
                }
            }
        }
        return bestScore;


    }

}