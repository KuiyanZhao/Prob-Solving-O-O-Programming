import java.io.*;
import java.net.*;

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
public class SimpleServer {
    public static void main(String[] args)
            throws UnknownHostException, IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(4242);

        System.out.println("Waiting for the client to connect...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected!");

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        String message = reader.readLine();

        System.out.printf("Received from client:\n%s\n", message);
        String response = message.replaceAll(" ", ",");
        writer.write(response);
        writer.println();
        writer.flush();//Ensure data is sent to the client.
        System.out.printf("Sent to client:\n%s\n", response);

        writer.close();
        reader.close();
    }
}
