import java.util.Scanner;

/**
 * HW-05 -- Challenge
 * <p>
 * This program will allow the user to select one of two options which will
 * then perform different calculations utilizing loops.
 *
 * @author YourNameHere, YourLabSectionHere
 * @version 2024-02-10
 */

public class MyMathHelper {

    public static final String WELCOME_MESSAGE = "Welcome to My Math Helper!";
    public static final String MAIN_MENU_ONE = "Please Select an Operation";
    public static final String MAIN_MENU_TWO = "1 - Calculate Greatest Common Denominator";
    public static final String MAIN_MENU_THREE = "2 - Perform Prime Factorization";
    public static final String MAIN_MENU_FOUR = "3 - Exit";
    public static final String GCD_NOTIFICATION = "Ready to Calculate Greatest Common Denominator";
    public static final String PF_NOTIFICATION = "Ready to Perform Prime Factorization";
    public static final String INPUT_ONE = "Please Enter an Integer";
    public static final String INPUT_TWO = "Please Enter a Second Integer";
    public static final String GCD_OUTPUT = "The Greatest Common Denominator is ";
    public static final String PF_OUTPUT = "The Prime Factorization is ";
    public static final String EXIT_MESSAGE = "Thank you for using My Math Helper!";
    public static final String INVALID_MENU_SELECTION = "Invalid selection!";
    public static final String INVALID_INPUT = "Invalid Input - Returning to Main Menu";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println(WELCOME_MESSAGE);

        String input;
        while (true) {
            System.out.println(MAIN_MENU_ONE);
            System.out.println(MAIN_MENU_TWO);
            System.out.println(MAIN_MENU_THREE);
            System.out.println(MAIN_MENU_FOUR);

            input = scan.nextLine();

            try {
                int temp = Integer.parseInt(input);
                if (temp == 1) {
                    System.out.println(GCD_NOTIFICATION);
                    System.out.println(INPUT_ONE);
                    input = scan.nextLine();
                    try {
                        int a = Integer.parseInt(input);
                        if (a > 0) {
                            System.out.println(INPUT_TWO);
                            input = scan.nextLine();
                            try {
                                int b = Integer.parseInt(input);
                                if (b > 0) {
                                    System.out.println(GCD_OUTPUT + gcd(a, b));
                                } else {
                                    System.out.println(INVALID_INPUT);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(INVALID_INPUT);
                            }
                        } else {
                            System.out.println(INVALID_INPUT);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(INVALID_INPUT);
                    }
                } else if (temp == 2) {
                    System.out.println(PF_NOTIFICATION);
                    System.out.println(INPUT_ONE);
                    input = scan.nextLine();
                    try {
                        int a = Integer.parseInt(input);
                        if (a < 2) {
                            System.out.println(INVALID_INPUT);
                        } else {
                            System.out.print(PF_OUTPUT);
                            primeFactorization(a);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(INVALID_INPUT);
                    }
                } else if (temp == 3) {
                    scan.close();
                    break;
                } else {
                    System.out.println(INVALID_MENU_SELECTION);
                }
            } catch (NumberFormatException e) {
                System.out.println(INVALID_MENU_SELECTION);
            }
        }
        System.out.println(EXIT_MESSAGE);
    } // End main

    /**
     * Calculate the greatest common denominator of two integers.
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        int c = a % b;
        while (c != 0) {
            a = b;
            b = c;
            c = a % b;
        }
        return b;
    }

    /**
     * prime factorization of a given integer
     *
     * @param a
     * @return
     */
    public static void primeFactorization(int a) {
        for (int i = 2; i <= a; i++) {
            while (a != i) {
                if (a % i == 0) {
                    System.out.print(i + " x ");
                    a = a / i;
                } else
                    break;
            }
        }
        System.out.println(a);
    }
} // End class
