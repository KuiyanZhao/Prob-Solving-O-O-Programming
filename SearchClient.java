import javax.swing.*;
import java.io.*;
import java.net.*;

/**
 * HW11 -- Challenge
 * <p>
 * Socket
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author YourNameHere, YourLabSectionHere
 * @version 2024-03-21
 */
public class SearchClient {
    public static void main(String[] args)
            throws UnknownHostException, IOException, ClassNotFoundException {

        String hostname = "";
        int port = 0;
        String key;

        showWelcomeMessageDialog();
        hostname = showHostnameInputDialog();
        port = showPortInputDialog();

        Socket socket = new Socket(hostname, port);
//        Socket socket = new Socket("localhost", 4242);
        showConnectionEstablishMessageDialog();

        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        while (true) {
            key = showKeyInputDialog();

            writer.write(key);
            writer.println();
            writer.flush();
//        System.out.printf("Sent to server:\n%s\n", key);

            String s1 = reader.readLine();
            System.out.printf("Received from server:\n%s\n", s1);
            if (s1.isEmpty() || s1 == null) {
                JOptionPane.showMessageDialog(null, "Search result is empty!", "Search Form",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String university;
                String[] split = s1.split(";");
                do {
                    university = (String) JOptionPane.showInputDialog(null, "Select your text",
                            "Search Form", JOptionPane.QUESTION_MESSAGE, null, split,
                            split[0]);
                    if (university == null) {
                        JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "Search Form",
                                JOptionPane.ERROR_MESSAGE);
                    } //end if
                } while (university == null);

                int index = 0;
                for (int i = 0; i < split.length; i++) {
                    if (university.equals(split[i])) {
                        index = i;
                        break;
                    }
                }
                writer.write(String.valueOf(index));
                writer.println();
                writer.flush();

                s1 = reader.readLine();
                JOptionPane.showMessageDialog(null, s1,
                        "Search Form", JOptionPane.INFORMATION_MESSAGE);
            }

            int ret = JOptionPane.showConfirmDialog(null, "Would you like to search again?", "Search Form", JOptionPane.YES_NO_OPTION);
            if (ret == 1) {
                writer.write("no");
                writer.println();
                writer.flush();
                break;
            } else {
                writer.write("yes");
                writer.println();
                writer.flush();
            }
        }

        showFarewellMessageDialog();

        writer.close();
        reader.close();
    }

    /**
     * Displays a Message Dialog with the following text: "Welcome to University Card generator"
     */
    public static void showWelcomeMessageDialog() {
        JOptionPane.showMessageDialog(null, "Welcome to search application",
                "Search Form", JOptionPane.INFORMATION_MESSAGE);
    } //showWelcomeMessageDialog

    public static String showHostnameInputDialog() {
        String name;
        do {
            name = JOptionPane.showInputDialog(null, "Please enter a host name:",
                    "Search Form", JOptionPane.QUESTION_MESSAGE);
            if ((name == null) || (name.isEmpty())) {
                JOptionPane.showMessageDialog(null, "Host name cannot be empty!", "Search Form",
                        JOptionPane.ERROR_MESSAGE);

            } //end if
        } while ((name == null) || (name.isEmpty()));

        return name;
    } //showHostnameInputDialog

    public static int showPortInputDialog() {
        String temp;
        do {
            temp = JOptionPane.showInputDialog(null, "Please enter a port:",
                    "Search Form", JOptionPane.QUESTION_MESSAGE);
            if ((temp == null) || (temp.isEmpty()) || !temp.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number!", "Search Form",
                        JOptionPane.ERROR_MESSAGE);

            } //end if

        } while ((temp == null) || (temp.isEmpty()) || !temp.matches("[0-9]+"));

        return Integer.parseInt(temp);
    } //showPortInputDialog

    public static void showConnectionEstablishMessageDialog() {
        JOptionPane.showMessageDialog(null, "connection established",
                "Search Form", JOptionPane.INFORMATION_MESSAGE);
    } //showWelcomeMessageDialog

    public static String showKeyInputDialog() {
        String name;
        do {
            name = JOptionPane.showInputDialog(null, "Please enter search text:",
                    "Search Form", JOptionPane.QUESTION_MESSAGE);
            if ((name == null) || (name.isEmpty())) {
                JOptionPane.showMessageDialog(null, "Search text cannot be empty!", "Search Form",
                        JOptionPane.ERROR_MESSAGE);

            } //end if
        } while ((name == null) || (name.isEmpty()));

        return name;
    } //showKeyInputDialog

    public static void showFarewellMessageDialog() {
        JOptionPane.showMessageDialog(null, "Goodbye",
                "Search Form", JOptionPane.INFORMATION_MESSAGE);
    } //showFarewellMessageDialog
}
