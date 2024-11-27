/**
 * PJ-2 - Handout
 *
 * <p>Purdue University -- CS18000 -- Summer 2022</p>
 *
 * @author Purdue CS
 * @version Mar 2, 2024
 */
public class UnitVector {
    private double i;
    private double j;
    private double k;

    public UnitVector() {
        this.i = 0.000;
        this.j = 0.000;
        this.k = 0.000;
    }

    public UnitVector(double i, double j, double k) {
        this.i = i;
        this.j = j;
        this.k = k;

        double magnitude = Math.sqrt(i * i + j * j + k * k);

        if (Point.isEqual(magnitude, 0.000)) {
            this.i = 0.000;
            this.j = 0.000;
            this.k = 0.000;
        } else if (!Point.isEqual(magnitude, 1.000)) {
            this.i = i / magnitude;
            this.j = j / magnitude;
            this.k = k / magnitude;
        }
    }

    public UnitVector(Point start, Point end) {
        this.i = end.getX() - start.getX();
        this.j = end.getY() - start.getY();
        this.k = end.getZ() - start.getZ();

        double magnitude = Math.sqrt(i * i + j * j + k * k);

        if (Point.isEqual(magnitude, 0.000)) {
            this.i = 0.000;
            this.j = 0.000;
            this.k = 0.000;
        } else if (!Point.isEqual(magnitude, 1.000)) {
            this.i = i / magnitude;
            this.j = j / magnitude;
            this.k = k / magnitude;
        }
    }

    public double getI() {
        return i;
    }

    public double getJ() {
        return j;
    }

    public double getK() {
        return k;
    }

    public void fipVector() {
        this.i = -this.i;
        this.j = -this.j;
        this.k = -this.k;
    }

    public boolean compareTo(UnitVector vector) {
        return Point.isEqual(this.i, vector.getI())
                && Point.isEqual(this.j, vector.getJ())
                && Point.isEqual(this.k, vector.getK());
    }

    public UnitVector crossProduct(UnitVector b) {
        double i = this.j * b.getK() - this.k * b.getJ();
        double j = this.k * b.getI() - this.i * b.getK();
        double k = this.i * b.getJ() - this.j * b.getI();

        double magnitude = Math.sqrt(i * i + j * j + k * k);

        if (Point.isEqual(magnitude, 0.000)) {
            i = 0.000;
            j = 0.000;
            k = 0.000;
        } else if (!Point.isEqual(magnitude, 1.000)) {
            i = i / magnitude;
            j = j / magnitude;
            k = k / magnitude;
        }

        UnitVector newUnitVector = new UnitVector(i , j, k);
        return newUnitVector;
    }

    @Override
    public String toString() {
        if (Point.isEqual(this.i, 0.000)
                && Point.isEqual(this.j, 0.000)
                && Point.isEqual(this.k, 0.000)) {
            return "<InvalidUnitVector>";
        }

        return String.format("<%.03fi, %.03fj, %.03fk>", this.i, this.j, this.k);
    }
}
