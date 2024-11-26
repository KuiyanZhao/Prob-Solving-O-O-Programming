/**
 * HW-06 -- Help a user determine their repayment options for potential credit card balances.
 * <p>
 *
 * @author Kuiyan Zhao, YourLabSectionHere
 * @version 2024-02-15
 */
public class Card {
    /**
     * The annual percentage interest of the credit card.
     */
    private double rate;

    /**
     * The total balance of the credit card.
     */
    private double balance;

    /**
     * The monthly payment of the credit card.
     */
    private double monthlyPayment;

    /**
     * Instantiate the class fields to their associated parameters.
     *
     * @param rate
     * @param balance
     * @param monthlyPayment
     */
    public Card(double rate, double balance, double monthlyPayment) {
        this.rate = rate;
        this.balance = balance;
        this.monthlyPayment = monthlyPayment;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public int calculatePayoff(boolean output) {
        double totalInterestPaid = 0;
        int number = 0;

        // 1. Identify the current balance and verify it is greater than zero.
        while (balance > 0) {
            // 2. Calculate the monthly interest rate.
            double monthlyInterestRate = rate / 12;
            // 3. Calculate the monthly interest charge and monthly principal.
            double interest = balance * monthlyInterestRate;
            if (monthlyPayment < interest) {
                System.out.println("Error: Impossible to payoff balance under these conditions");
                return -1;
            }

            double principal;
            // 4. Subtract the monthly principal from the balance, the result is the new balance.
            if (monthlyPayment - interest >= balance) {
                principal = balance;
                balance = 0;
            } else {
                principal = monthlyPayment - interest;
                balance = balance - principal;
            }

            // 5. Increase the total interest charge by the calculated monthly interest charge.
            totalInterestPaid += interest;
            // 6. Increment the number of months by one.
            number++;

            if (output) {
                System.out.printf("Payment: %d - Principal: %.2f - Interest: %.2f - Remaining Balance: %.2f\n",
                        number, principal, interest, balance);
            }
        }

        if (output) {
            System.out.printf("Total Interest Paid: %.2f\n", totalInterestPaid);
        }

        return number;
    }

    @Override
    public String toString() {
        return String.format("Rate: %.2f - Balance: %.2f - Payment: %.2f", rate, balance, monthlyPayment);
    }
}
