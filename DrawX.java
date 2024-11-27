import java.util.Arrays;

/**
 * HW-07 -- Walkthrough
 * <p>
 * A class that generates a 2-D char array representing an 'X'
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author YourNameHere, YourLabSectionHere
 * @version 2024-02-22
 */
public class DrawX {

    private int size;

    public DrawX(int size) {
        this.size = size;
    }

    public char[][] generateArray() {

        char[][] xArray = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j || i + j == size - 1) {
                    xArray[i][j] = '*';
                } else {
                    xArray[i][j] = ' ';
                }
            }
        }

        return xArray;
    }

    public static void main(String[] args) {
        DrawX sample = new DrawX(10);
        char[][] testArray = sample.generateArray();
        for (int i = 0; i < testArray.length; i++) {
            System.out.println(Arrays.toString(testArray[i]));
        }
    }
}
