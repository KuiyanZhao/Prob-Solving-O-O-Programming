import javax.swing.*;

/**
 * HW11 -- Debugging
 * <p>
 * Socket
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author YourNameHere, YourLabSectionHere
 * @version 2024-03-21
 */
public class OrderFormGUI {
    public static void main(String[] args) {
        /** DO NOT CHANGE VALUES BELOW **/
        boolean hoodieInStock = true;
        boolean tshirtInStock = false;
        boolean longsleeveInStock = true;
        String item = "";
        int quantity = 0;
        String name = "";
        /** DO NOT CHANGE VALUES ABOVE **/

        while (true) {

            // display error GUI if item selected is out of stock
            String[] options = {"Hoodie", "T-shirt", "Long sleeve"};
            item = (String) JOptionPane.showInputDialog(null, "Select item style ", "Order Form",
                    JOptionPane.PLAIN_MESSAGE, null, options, null);
            if ("T-shirt".equals(item)) {
                JOptionPane.showMessageDialog(null, "Out of Stock", "Order Form",
                        JOptionPane.ERROR_MESSAGE);
            }

            // display error GUI if input is not an int or if input is less than 1
            try {
                quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter quantity", "Order Form",
                        JOptionPane.QUESTION_MESSAGE));

                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(null, "Enter a number greater than 0", "Order Form",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter an integer", "Order Form",
                        JOptionPane.ERROR_MESSAGE);
            }


            // display error GUI if input does not include a space
            name = JOptionPane.showInputDialog(null, "Enter your Name", "Order Form",
                    JOptionPane.QUESTION_MESSAGE);
            if (!name.contains(" ")) {
                JOptionPane.showMessageDialog(null, "Enter first and last name", "Order Form",
                        JOptionPane.ERROR_MESSAGE);
            }


            /** Order Confirmation Message **/
            String resultMessage = "Name: " + name + "\nItem: " + item + "\nQuantity: " + quantity;
            JOptionPane.showMessageDialog(null, resultMessage, "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);

            // loop through order form again if YES
            int ret = JOptionPane.showConfirmDialog(null, "Would you like to place another order?", "Order Form", JOptionPane.YES_NO_OPTION);
            if (ret == 1) {
                break;
            }
        }
    }
}
