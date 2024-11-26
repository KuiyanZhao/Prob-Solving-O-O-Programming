import java.io.*;
import java.net.*;
import java.util.ArrayList;

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
public class SearchServer {
    public static void main(String[] args)
            throws UnknownHostException, IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(4242);
        ArrayList<String> contents = new ArrayList<>();
        // load a database
        try (BufferedReader reader = new BufferedReader(new FileReader("searchDatabase.txt"))) {
            String in = "";
            while ((in = reader.readLine()) != null) {
                contents.add(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println("Waiting for the client to connect...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            while (true) {
                String message = reader.readLine();
                System.out.printf("Received from client:\n%s\n", message);
                StringBuilder sb = new StringBuilder();
                ArrayList<String> descriptions = new ArrayList<String>();
                for (int i = 0; i < contents.size(); i++) {
                    String[] datas = contents.get(i).split(";");
                    if (datas[1].contains(message) || datas[2].contains(message)) {
                        sb.append(datas[1]);
                        sb.append(";");
                        descriptions.add(datas[2]);
                    }
                }

                String response;
                if (sb.length() > 0) {
                    response = sb.substring(0, sb.length() - 1);
                } else {
                    response = "";
                }
                writer.write(response);
                writer.println();
                writer.flush();
                System.out.printf("Sent to client:\n%s\n", response);

                if (response.length() > 0) {
                    message = reader.readLine();
                    String s = descriptions.get(Integer.parseInt(message));
                    writer.write(s);
                    writer.println();
                    writer.flush();
                }

                message = reader.readLine();
                if (!"yes".equals(message)) {
                    break;
                }
            }

            writer.close();
            reader.close();
        }
    }
}
