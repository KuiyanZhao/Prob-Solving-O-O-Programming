import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * HW10 -- Challenge
 * <p>
 * Concurrency
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author YourNameHere, YourLabSectionHere
 * @version 2024-03-21
 */
public class StockGame extends Thread {
    private static double stockPrice;
    private static int availableShares;
    private static int tradeCount = 0;

    private String name;
    private int numShares;
    private String fileName;

    public StockGame(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
        this.numShares = 0;

        if (tradeCount == 0) {
            stockPrice = 100.00;
            availableShares = 100;
        }
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            String in = "";
            while ((in = reader.readLine()) != null) {
                String[] datas = in.split(",");
                if (datas.length != 2) {
                    System.out.println("Error, invalid input!");
                } else {
                    try {
                        int num = Integer.parseInt(datas[1]);
                        if (num > 0) {
                            if ("BUY".equals(datas[0])) {
                                synchronized (StockGame.class) {
                                    if (num <= availableShares) {
                                        System.out.println("----------");
                                        System.out.println(String.format("Stock Price: %.2f", stockPrice));
                                        System.out.println(String.format("Available Shares: %d", availableShares));
                                        System.out.println(String.format("Trade number: %d", ++tradeCount));
                                        System.out.println(String.format("Name: %s", name));
                                        System.out.println(String.format("Purchasing %d shares at %.2f...",
                                                num, stockPrice));
                                        numShares += num;
                                        availableShares -= num;
                                        stockPrice += 1.5 * num;
                                    } else {
                                        System.out.println("Insufficient shares available, cancelling order...");
                                    }
                                }
                            } else if ("SELL".equals(datas[0])) {
                                synchronized (StockGame.class) {
                                    if (num <= numShares) {
                                        System.out.println("----------");
                                        System.out.println(String.format("Stock Price: %.2f", stockPrice));
                                        System.out.println(String.format("Available Shares: %d", availableShares));
                                        System.out.println(String.format("Trade number: %d", ++tradeCount));
                                        System.out.println(String.format("Name: %s", name));
                                        System.out.println(String.format("Selling %d shares at %.2f...",
                                                num, stockPrice));
                                        numShares -= num;
                                        availableShares += num;
                                        stockPrice -= 2.0 * num;
                                    } else {
                                        System.out.println("Insufficient owned shares, cancelling order...");
                                    }
                                }
                            } else {
                                System.out.println("Error, invalid input!");
                            }
                        } else {
                            System.out.println("Error, invalid input!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error, invalid input!");
                    }
                }
            } // end while
        } catch (IOException e) {
            System.out.println("Error, invalid input!");
        }
    }
}

