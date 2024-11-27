import java.util.concurrent.atomic.AtomicInteger;

/**
 * HW10 -- Walkthrough
 * <p>
 * Concurrency
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author YourNameHere, YourLabSectionHere
 * @version 2024-03-21
 */
public class Player {
    public static Object obj = new Object();

    private int x;    //x position of the player
    //	private int y;	//y position of the player
    private AtomicInteger y;    //y position of the player
    private int hp;        //health point of the player

    public Player(int x, int y, int hp) {
        this.x = x;
//		this.y = y;
        this.y = new AtomicInteger(y);
        this.hp = hp;
    }

    public void printPlayer() {
        System.out.printf("x position:\t%d\ny position:\t%d\nhealth point:\t%d\n", x, y.get(), hp);
    }

    public synchronized void moveLeft() {
        x--;
    }

    public synchronized void moveRight() {
        x++;
    }

    public void moveUp() {
//		y --;
        y.decrementAndGet();
    }

    public void moveDown() {
//		y ++;
        y.incrementAndGet();
    }

    public void loseHealth() {
        synchronized (obj) {
            hp--;
        }
    }

    public void gainHealth() {
        synchronized (obj) {
            hp++;
        }
    }

}
