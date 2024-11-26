import java.util.Scanner;

/**
 * HW-04 -- MusicRecommender
 * <p>
 * This program takes in input from System.in
 * helps the users find a song that fits their mood
 *
 * @author YourNameHere, YourLabSectionHere
 * @version 2024-02-03
 */
public class MusicRecommender {

    public static final String WELCOME_MESSAGE = "Welcome to the Music Recommender!";
    public static final String INITIAL_SONG = "Do you want to listen to a song?";
    public static final String HAPPY = "Are you feeling happy?";
    public static final String CLAP_ALONG = "Do you want to clap along?";
    public static final String SING_OUT = "Do you want to sing out?";
    public static final String DANCE = "Do you want to dance?";
    public static final String SAD = "Are you feeling sad?";
    public static final String LYRICS = "Do you want lyrics?";
    public static final String WORRIED = "Are you feeling worried?";
    public static final String CALM = "Are you feeling calm?";
    public static final String COMPLICATED = "Are your feelings more complicated" +
        " than this program can handle?";
    public static final String CONTINUE_WORRIED = "Do you want to keep " +
        "feeling worried?";
    public static final String GOODBYE_MESSAGE = "Thank you for using" +
        " the Music Recommender!";


    public static final String SONG_ONE = "Play \"Happy\" by Pharrell Williams";
    public static final String SONG_TWO = "Play \"If you want to Sing Out, Sing Out\" by Cat Stevens";
    public static final String SONG_THREE = "Play \"Uptown Funk\" by Mark Ronson featuring Bruno Mars";
    public static final String SONG_FOUR = "Play \"La finta giardiniera, K. 196: Ouverture. Allegro molto\" by Mozart";
    public static final String SONG_FIVE = "Play \"Hurt\" by Trent Reznor, as performed by Johnny Cash";
    public static final String SONG_SIX = "Play Theme from Schindler's List";
    public static final String SONG_SEVEN = "Play \"Aben bog\" by Bremer/McCoy";
    public static final String SONG_EIGHT = "Play \"Complicated\" by Avril Lavigne";
    public static final String SONG_NINE = "Play \"A Witch Stole Sam\" by Mark Korven" +
        " from The Witch Original Soundtrack";
    public static final String SONG_TEN = "Play \"Don't worry. Be Happy\" by Bobby McFerrin";

    // ------------------------- DO NOT MODIFY ABOVE -------------------------

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // which is used to save user input
        String input = "No";
        System.out.println(WELCOME_MESSAGE);

        // Do you want to listen to a song?
        System.out.println(INITIAL_SONG);
        input = scan.nextLine();
        if ("yes".equalsIgnoreCase(input)) {
            // Are you feeling happy?
            System.out.println(HAPPY);
            input = scan.nextLine();
            if ("no".equalsIgnoreCase(input)) {
                // Are you feeling sad?
                System.out.println(SAD);
                input = scan.nextLine();
                if ("no".equalsIgnoreCase(input)) {
                    // Are you feeling worried?
                    System.out.println(WORRIED);
                    input = scan.nextLine();
                    if ("no".equalsIgnoreCase(input)) {
                        // Are you feeling calm?
                        System.out.println(CALM);
                        input = scan.nextLine();
                        if ("no".equalsIgnoreCase(input)) {
                            // COMPLICATED
                            System.out.println(COMPLICATED);
                            input = scan.nextLine();
                            if ("yes".equalsIgnoreCase(input)) {
                                System.out.println(SONG_EIGHT);
                            }  // end COMPLICATED
                        } else if ("yes".equalsIgnoreCase(input)) {
                            System.out.println(SONG_SEVEN);
                        }  // end CALM
                    } else if ("yes".equalsIgnoreCase(input)) {
                        // CONTINUE_WORRIED
                        System.out.println(CONTINUE_WORRIED);
                        input = scan.nextLine();
                        if ("no".equalsIgnoreCase(input)) {
                            System.out.println(SONG_TEN);
                        } else if ("yes".equalsIgnoreCase(input)) {
                            System.out.println(SONG_NINE);
                        }  // end CONTINUE_WORRIED
                    }  // end WORRIED
                } else if ("yes".equalsIgnoreCase(input)) {
                    // Do you want lyrics?
                    System.out.println(LYRICS);
                    input = scan.nextLine();
                    if ("no".equalsIgnoreCase(input)) {
                        System.out.println(SONG_SIX);
                    } else if ("yes".equalsIgnoreCase(input)) {
                        System.out.println(SONG_FIVE);
                    }  // end LYRICS
                }  // end SAD
            } else if ("yes".equalsIgnoreCase(input)) {
                // Do you want to clap along?
                System.out.println(CLAP_ALONG);
                input = scan.nextLine();
                if ("no".equalsIgnoreCase(input)) {
                    // Do you want to sing out?
                    System.out.println(SING_OUT);
                    input = scan.nextLine();
                    if ("no".equalsIgnoreCase(input)) {
                        // Do you want to dance?
                        System.out.println(DANCE);
                        input = scan.nextLine();
                        if ("no".equalsIgnoreCase(input)) {
                            System.out.println(SONG_FOUR);
                        } else if ("yes".equalsIgnoreCase(input)) {
                            System.out.println(SONG_THREE);
                        } // end DANCE
                    } else if ("yes".equalsIgnoreCase(input)) {
                        System.out.println(SONG_TWO);
                    } // end SING_OUT
                } else if ("yes".equalsIgnoreCase(input)) {
                    System.out.println(SONG_ONE);
                } // end CLAP_ALONG
            } // end HAPPY
        }  // end INITIAL_SONG

        System.out.println(GOODBYE_MESSAGE);
        scan.close();
    }
}
