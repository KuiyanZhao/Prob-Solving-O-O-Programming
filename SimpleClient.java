import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * HW11 -- Walkhrouhgh
 * <p>
 * Socket
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author YourNameHere, YourLabSectionHere
 * @version 2024-03-21
 */
public class SimpleClient {
    public static void main(String[] args)
            throws UnknownHostException, IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);

        Socket socket = new Socket("localhost", 4242);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println("What do you want to send to the server?");
        String response = scan.nextLine();
        writer.write(response);
        writer.println();
        writer.flush();  //ensure data is sent to the server
        System.out.printf("Sent to server:\n%s\n", response);

        String s1 = reader.readLine();
        System.out.printf("Received from server:\n%s\n", s1);

        writer.close();
        reader.close();
    }
}
