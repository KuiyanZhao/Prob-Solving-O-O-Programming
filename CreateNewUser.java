import java.util.Locale;
import java.util.Scanner;

/**
 * HW-03 -- CreateNewUser
 * <p>
 * This program takes in input from System.in
 * performs string manipulation and returns a user String
 *
 * @author YourNameHere, YourLabSectionHere
 * @version Jan 9, 2024
 */

public class CreateNewUser {

    //List of public static final strings provided to prevent typos

    public static final String PROMPT_ONE = "Enter Customer First Name:";
    public static final String PROMPT_TWO = "Enter Customer Last Name:";
    public static final String PROMPT_THREE = "Enter Customer Age:";
    public static final String PROMPT_FOUR = "Enter Customer Street Address:";
    public static final String PROMPT_FIVE = "Enter Customer City:";
    public static final String PROMPT_SIX = "Enter Customer State:";
    public static final String PROMPT_SEVEN = "Enter Customer Zip Code:";
    public static final String PROMPT_EIGHT = "Enter Customer Phone Number:";
    public static final String OUTPUT = "The Assigned User String is ";

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String theGenerateResult = "";
        // 1
        System.out.println(PROMPT_ONE);
        String firstName = scan.nextLine();
        // Take the frst character of the input and convert them to lower case
        theGenerateResult = firstName.substring(0, 1).toLowerCase();
        // Take the last character of the input and convert them to lower case
        theGenerateResult = theGenerateResult
                + firstName.substring(firstName.length() - 1).toLowerCase();

        // 2
        System.out.println(PROMPT_TWO);
        String lastName = scan.nextLine();
        // Take the frst two characters of the lastname and convert them to upper case.
        theGenerateResult = lastName.substring(0, 2).toUpperCase() + theGenerateResult;

        // 3
        System.out.println(PROMPT_THREE);
        String age = scan.nextLine();
        theGenerateResult = age.substring(0, 1) + theGenerateResult;
        theGenerateResult += toBinaryString(Integer.parseInt(age.substring(1)), 4);

        // 4
        System.out.println(PROMPT_FOUR);
        String streetAddress = scan.nextLine();
        // Remove all spaces, covert to upper
        theGenerateResult += streetAddress.replaceAll(" ", "").toUpperCase();

        // 5
        System.out.println(PROMPT_FIVE);
        String city = scan.nextLine();
        theGenerateResult = theGenerateResult + " " + city.substring(0, 2).toUpperCase();

        // 6
        System.out.println(PROMPT_SIX);
        String state = scan.nextLine();
        state = state.toUpperCase();
        theGenerateResult += (int) (state.charAt(0)) + (int) (state.charAt(1));

        // 7
        System.out.println(PROMPT_SEVEN);
        String zipCode = scan.nextLine();
        theGenerateResult = theGenerateResult
                + (Integer.parseInt(zipCode.substring(0, 1))
                + Integer.parseInt(zipCode.substring(2, 3)));

        // 8
        System.out.println(PROMPT_EIGHT);
        String phoneNumber = scan.nextLine();
        theGenerateResult = theGenerateResult
                + phoneNumber.substring(phoneNumber.length() - 4);


        // 9
        int len = firstName.length()
                + lastName.length()
                + age.length()
                + streetAddress.length()
                + city.length()
                + state.length()
                + zipCode.length()
                + phoneNumber.length();
        theGenerateResult = len + theGenerateResult;

        // 10
        // remove all instance of capitol o's 'O'
        theGenerateResult = theGenerateResult.replaceAll("O", "");
        // remove all instance of capitol i's 'I'
        theGenerateResult = theGenerateResult.replaceAll("I", "");

        // 11
        System.out.println(OUTPUT + theGenerateResult);

    } //End Main method

    private static String toBinaryString(int num, int digits) {
        int value = 1 << digits | num;
        String bs = Integer.toBinaryString(value);
        return bs.substring(1);
    }


} //End CreateNewUser.class
