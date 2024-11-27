/**
 * PJ-2 - Handout
 *
 * <p>Purdue University -- CS18000 -- Summer 2022</p>
 *
 * @author Purdue CS
 * @version Mar 2, 2024
 */
public class Point {
    /**
     * The x coordinate in Cartesian space.
     */
    private double x;

    /**
     * The y coordinate in Cartesian space.
     */
    private double y;

    /**
     * The z coordinate in Cartesian space.
     */
    private double z;

    /**
     * Construct a newly allocated Point object and
     * instantiate all fields set to 0.0.
     */
    public Point() {
       this.x = 0.0;
       this.y = 0.0;
       this.z = 0.0;
    }

    /**
     * Construct a newly allocated Point object and
     * instantiate the fields to their respective parameters.
     * @param x
     * @param y
     * @param z
     */
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean compareTo(Point point) {
        return isEqual(this.x, point.getX())
                && isEqual(this.y, point.getY())
                && isEqual(this.z, point.getZ());
    }

    @Override
    public String toString() {
        return String.format("(x%.03f, y%.03f, z%.03f)", x, y, z);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public static boolean isEqual(double a, double b) {
        if (a - b > 0.0001) {
            return false;
        } else if (a - b < -0.0001) {
            return false;
        } else {
            return true;
        }
    }
}
