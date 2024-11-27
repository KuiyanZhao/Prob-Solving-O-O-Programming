/**
 * HW-07 -- Challenge
 * <p>
 * A class that calculate the score and winner.
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author YourNameHere, YourLabSectionHere
 * @version 2024-02-22
 */
public class YahtzeePlayer {
    /*
     * The roll results for the player.
     */
    private int[][] rollResults;

    /*
     * The total number of points scored by the player in the game.
     */
    private int total;

    /*
     * The integer value corresponding to the points the player earned from scoring a Four of a Kind.
     */
    private int fourOfAKindTotal;

    /*
     * The boolean value corresponding to whether or not the player rolled a Full House in the game.
     */
    private boolean fullHouse;

    /*
     * The boolean value corresponding to whether or not the player rolled a Large Straight in the game.
     */
    private boolean largeStraight;

    /*
     * The boolean value corresponding to whether or not the player rolled a Yahtzee in the game.
     */
    private boolean yahtzee;

    public YahtzeePlayer(int[][] rollResults) {
        this.rollResults = rollResults;
        this.total = 0;
        this.fourOfAKindTotal = 0;
        this.fullHouse = false;
        this.largeStraight = false;
        this.yahtzee = false;
    }

    /**
     * Returns the player's Four of aKind total.
     *
     * @return
     */
    public int getFourOfAKindTotal() {
        return fourOfAKindTotal;
    }

    /**
     * Returns whether or not the player has a Full House.
     *
     * @return
     */
    public boolean hasFullHouse() {
        return fullHouse;
    }

    /**
     * Returns whether or not the player has a Large Straight.
     *
     * @return
     */
    public boolean hasLargeStraight() {
        return largeStraight;
    }

    /**
     * Returns whether or not the player has a Yahtzee.
     *
     * @return
     */
    public boolean hasYahtzee() {
        return yahtzee;
    }

    public int getTotal() {
        return total;
    }

    public void checkFourOfAKind() {
        int[] temp = new int[6];
        int points;
        for (int i = 0; i < rollResults.length; i++) {
            for (int ii = 0; ii < 6; ii++) {
                temp[ii] = 0;
            }
            points = 0;

            for (int j = 0; j < rollResults[0].length; j++) {
                temp[rollResults[i][j] - 1]++;
                points += rollResults[i][j];
            }

            if (temp[0] == 4 || temp[1] == 4 || temp[2] == 4
                    || temp[3] == 4 || temp[4] == 4 || temp[5] == 4) {
                fourOfAKindTotal = points;
                break;
            }
        }
    }

    public void checkFullHouse() {
        int[] temp = new int[6];
        for (int i = 0; i < rollResults.length; i++) {
            for (int ii = 0; ii < 6; ii++) {
                temp[ii] = 0;
            }

            for (int j = 0; j < rollResults[0].length; j++) {
                temp[rollResults[i][j] - 1]++;
            }

            boolean threeTimes = false;
            boolean twiceTimes = false;
            for (int ii = 0; ii < 6; ii++) {
                if (temp[ii] == 3) {
                    threeTimes = true;
                }
                if (temp[ii] == 2) {
                    twiceTimes = true;
                }
            }

            if (threeTimes && twiceTimes) {
                fullHouse = true;
                break;
            }
        }
    }

    public void checkLargeStraight() {
        boolean result;
        for (int i = 0; i < rollResults.length; i++) {
            result = true;
            for (int j = 1; j < rollResults[0].length; j++) {
                if (rollResults[i][j] - rollResults[i][j - 1] != 1) {
                    result = false;
                    break;
                }
            }

            if (result) {
                largeStraight = true;
                break;
            }
        }
    }

    public void checkYahtzee() {
        int[] temp = new int[6];

        for (int i = 0; i < rollResults.length; i++) {
            for (int ii = 0; ii < 6; ii++) {
                temp[ii] = 0;
            }

            for (int j = 0; j < rollResults[0].length; j++) {
                temp[rollResults[i][j] - 1]++;
            }

            if (temp[0] == rollResults[0].length || temp[1] == rollResults[0].length
                    || temp[2] == rollResults[0].length
                    || temp[3] == rollResults[0].length
                    || temp[4] == rollResults[0].length
                    || temp[5] == rollResults[0].length) {
                yahtzee = true;
                break;
            }
        }
    }

    public void calculateTotal() {
        total += fourOfAKindTotal;

        if (fullHouse) {
            total += 25;
        }

        if (largeStraight) {
            total += 40;
        }

        if (yahtzee) {
            total += 50;
        }
    }
}
