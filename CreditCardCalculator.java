import java.util.Scanner;

/**
 * HW-06 -- Help a user determine their repayment options for potential credit card balances.
 * <p>
 *
 * @author YourNameHere, YourLabSectionHere
 * @version 2024-02-15
 */
public class CreditCardCalculator {
    public static final String WELCOME_MESSAGE = "Welcome to the Credit Card Calculator!";
    public static final String MENU = "Menu";
    public static final String QUIT_OPTION = "0. Quit";
    public static final String ADD_OPTION = "1. Add a card";
    public static final String RATE_PROMPT = "Enter the annual interest rate:";
    public static final String BALANCE_PROMPT = "Enter the balance:";
    public static final String PAYMENT_PROMPT = "Enter the monthly payment:";
    public static final String MODIFY_OPTION = "1. Modify card";
    public static final String PAYOFF_OPTION = "2. Calculate Payoff";
    public static final String CALCULATION_OPTION = "Would you like to print the payoff calculations?";
    public static final String CALCULATION_DECISION = "1. Yes\n2. No";
    public static final String CALCULATION_RESULT = "Months until payoff: %d";
    public static final String ERROR_MESSAGE = "Error! Invalid input.";
    public static final String GOODBYE_MESSAGE = "Thank you!";

    public static void main(String[] strings) {
        Card card = null;
        Scanner scan = new Scanner(System.in);
        String input;

        System.out.println(WELCOME_MESSAGE);
        System.out.println(MENU);
        System.out.println(QUIT_OPTION);
        System.out.println(ADD_OPTION);
        input = scan.nextLine();
        try {
            int temp = Integer.parseInt(input);
            if (temp == 0) {
                System.out.println(GOODBYE_MESSAGE);
            } else if (temp == 1) {
                System.out.println(RATE_PROMPT);
                input = scan.nextLine();
                double rate = Double.parseDouble(input);
                System.out.println(BALANCE_PROMPT);
                input = scan.nextLine();
                double balance = Double.parseDouble(input);
                System.out.println(PAYMENT_PROMPT);
                input = scan.nextLine();
                double monthlyPayment = Double.parseDouble(input);
                card = new Card(rate, balance, monthlyPayment);
                System.out.println(card);

                while (true) {
                    System.out.println(MENU);
                    System.out.println(QUIT_OPTION);
                    System.out.println(MODIFY_OPTION);
                    System.out.println(PAYOFF_OPTION);
                    input = scan.nextLine();
                    int t = Integer.parseInt(input);
                    if (t == 0) {
                        System.out.println(GOODBYE_MESSAGE);
                        break;
                    } else if (t == 1) {
                        System.out.println(RATE_PROMPT);
                        input = scan.nextLine();
                        rate = Double.parseDouble(input);
                        card.setRate(rate);
                        System.out.println(BALANCE_PROMPT);
                        input = scan.nextLine();
                        balance = Double.parseDouble(input);
                        card.setBalance(balance);
                        System.out.println(PAYMENT_PROMPT);
                        input = scan.nextLine();
                        monthlyPayment = Double.parseDouble(input);
                        card.setMonthlyPayment(monthlyPayment);
                        System.out.println(card);
                    } else if (t == 2) {
                        System.out.println(CALCULATION_OPTION);
                        System.out.println(CALCULATION_DECISION);
                        input = scan.nextLine();
                        int tt = Integer.parseInt(input);
                        int result;
                        if (tt == 1) {
                            result = card.calculatePayoff(true);
                        } else if (tt == 2) {
                            result = card.calculatePayoff(false);
                        } else {
                            System.out.println(ERROR_MESSAGE);
                            continue;
                        }
                        if (result > -1) {
                            System.out.printf(CALCULATION_RESULT, result);
                            System.out.println();
                        }
                    } else {
                        System.out.println(ERROR_MESSAGE);
                    }
                }
            } else {
                System.out.println(ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            System.out.println(ERROR_MESSAGE);
        }
    } // End main
}