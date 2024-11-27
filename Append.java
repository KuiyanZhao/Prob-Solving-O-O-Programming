import java.io.*;
import java.util.Scanner;

/**
 * HW-08 -- Debugging
 * <p>
 * A class that debugging the program.
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author YourNameHere, YourLabSectionHere
 * @version 2024-02-29
 */
public class Append {

    public void appendText(String path, String toAppend) throws PathException {

        File input = new File(path);
        if (!input.exists() || input.isDirectory()) {
            throw new PathException();
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(input, true));
            bw.write(toAppend);
            bw.newLine();
            bw.flush();

            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in); 
        
        System.out.println("Enter the path to the file:");
        String path = scan.nextLine();
        
        System.out.println("Enter the line to append:");
        String toAppend = scan.nextLine(); 
        
        Append a = new Append();
        try {
            a.appendText(path, toAppend);
        } catch (PathException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}