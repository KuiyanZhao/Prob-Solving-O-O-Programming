import java.util.Arrays;
import java.util.Scanner;

/**
 * PJ-01 -- Handout
 * <p>
 * This program will calculate a player's chess Elo under different conditions.
 *
 * @author Kuiyan Zhao, YourLabSectionHere
 * @version 2024-02-17
 */

public class ChessElo {

    public static final String WELCOME_MESSAGE = "Welcome to the Chess Elo Calculator!";
    public static final String MAIN_MENU = "Please Select an Operation\n" + "1 - Single Match\n" +
            "2 - Tournament Results\n" + "3 - Field Prediction\n" + "4 - Exit";

    public static final String SINGLE_MATCH = "Please Enter Player 1's Elo followed by a hyphen '-' " +
            "then the Outcome followed by Player 2's Elo.";
    public static final String TOURNAMENT_RESULTS = "Please Enter the Tournament String to be Calculated.";
    public static final String FIELD_PREDICTION = "Please Enter a String of Elo Ratings.";
    public static final String MATCH_OUTCOME = "The Result of the Single Match is ";
    public static final String TOURNAMENT_OUTCOME = "The Final Tournament Results are ";
    public static final String PREDICTION_OUTCOME = "The Chess Elo Calculator Predicts ";
    public static final String CONFIRMATION = "Are You Sure You Want to Exit?";
    public static final String INVALID_INPUT = "Invalid Input! Try again";
    public static final String THANK_YOU = "Thank You For Using the Chess Elo Calculator!";

    public static final int K = 25;

    public static void main(String[] args) {
        boolean isFinish = false;

        Scanner scan = new Scanner(System.in);

        System.out.println(WELCOME_MESSAGE);

        String input;
        while (!isFinish) {
            System.out.println(MAIN_MENU);

            input = scan.nextLine();

            try {
                int temp = Integer.parseInt(input);
                if (temp == 1) {
                    System.out.println(SINGLE_MATCH);
                    input = scan.nextLine();
                    int hyphenIndex = input.indexOf("-");
                    int player1Elo = Integer.parseInt(input.substring(0, hyphenIndex));
                    String isWin = input.substring(hyphenIndex + 1, hyphenIndex + 2);
                    int player2Elo = Integer.parseInt(input.substring(hyphenIndex + 2));
                    int[] result = calculate(player1Elo, player2Elo, isWin);
                    System.out.println(MATCH_OUTCOME + result[0] + "-" + result[1]);
                } else if (temp == 2) {
                    System.out.println(TOURNAMENT_RESULTS);
                    input = scan.nextLine();
                    boolean isEnd = false;
                    String result2 = "";
                    int hyphenIndex = input.indexOf("-");
                    int player1Elo = Integer.parseInt(input.substring(0, hyphenIndex));
                    int player2Elo;
                    while (!isEnd) {
                        String isWin = input.substring(hyphenIndex + 1, hyphenIndex + 2);
                        int hyphenIndex2 = input.indexOf("-", hyphenIndex + 1);
                        if (hyphenIndex2 == -1) {
                            isEnd = true;
                            player2Elo = Integer.parseInt(input.substring(hyphenIndex + 2));
                        } else {
                            player2Elo = Integer.parseInt(input.substring(hyphenIndex + 2, hyphenIndex2));
                        }
                        int[] result = calculate(player1Elo, player2Elo, isWin);
                        player1Elo = result[0];
                        result2 = result2 + "-" + result[1];
                        hyphenIndex = hyphenIndex2;
                    }
                    result2 = player1Elo + result2;
                    System.out.println(TOURNAMENT_OUTCOME + result2);
                } else if (temp == 3) {
                    // 600-1200-400-800-1350
                    System.out.println(FIELD_PREDICTION);
                    input = scan.nextLine();
                    int number = input.length() - input.replaceAll("-", "").length() + 1;
                    int[] elos = new int[number];
                    int hyphenIndex = input.indexOf("-");
                    int player1Elo = Integer.parseInt(input.substring(0, hyphenIndex));
                    elos[0] = player1Elo;
                    int player2Elo;
                    for (int i = 1; i < number; i++) {
                        int hyphenIndex2 = input.indexOf("-", hyphenIndex + 1);
                        if (hyphenIndex2 == -1) {
                            player2Elo = Integer.parseInt(input.substring(hyphenIndex + 1));
                        } else {
                            player2Elo = Integer.parseInt(input.substring(hyphenIndex + 1, hyphenIndex2));
                        }
                        elos[i] = player2Elo;
                        hyphenIndex = hyphenIndex2;
                    }
                    Arrays.sort(elos);
                    int[] lowWinHigh = calculate(elos[0], elos[number - 1], "W");
                    int[] lowLoss = calculate(elos[0], elos[1], "L");
                    int[] highWin = calculate(elos[number - 1], elos[number - 2], "W");
                    System.out.printf("%s%d (+%d/-%d) %d (+%d/-%d)\n",
                            PREDICTION_OUTCOME, elos[0], lowWinHigh[0] - elos[0], elos[0] - lowLoss[0],
                            elos[number - 1], highWin[0] - elos[number - 1], elos[number - 1] - lowWinHigh[1]);
                } else if (temp == 4) {
                    while (true) {
                        System.out.println(CONFIRMATION);
                        input = scan.nextLine();
                        if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
                            isFinish = true;
                            System.out.println(THANK_YOU);
                            break;
                        } else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
                            break;
                        } else {
                            System.out.println(INVALID_INPUT);
                        }
                    }
                } else {
                    System.out.println(INVALID_INPUT);
                }
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT);
            }
        }

        scan.close();
    } // End main

    public static int[] calculate(int player1Elo, int player2Elo, String player1Win) {
        int[] newElo = new int[2];
        double r1 = Math.pow(10, (double) player1Elo / 400);
        double r2 = Math.pow(10, (double) player2Elo / 400);
        double e1 = r1 / (r1 + r2);
        double e2 = r2 / (r1 + r2);
        // Regardless of the draw.
        if (player1Win.equalsIgnoreCase("W")) {
            newElo[0] = (int) (player1Elo + K * (1 - e1));
            newElo[1] = (int) (player2Elo + K * (0 - e2));
        } else {
            newElo[0] = (int) (player1Elo + K * (0 - e1));
            newElo[1] = (int) (player2Elo + K * (1 - e2));
        }
        return newElo;
    }
} // End class
