/**
 * PJ-2 - Handout
 *
 * <p>Purdue University -- CS18000 -- Summer 2022</p>
 *
 * @author Purdue CS
 * @version Mar 2, 2024
 */
public class Cube {
    private Face[] mesh = new Face[6];

    public Cube(Face one, Face two, Face three, Face four, Face five, Face six) {
        this.mesh[0] = one;
        this.mesh[0] = two;
        this.mesh[0] = three;
        this.mesh[0] = four;
        this.mesh[0] = five;
        this.mesh[0] = six;
    }

    public Face[] getMesh() {
        return mesh;
    }

    @Override
    public String toString() {
        return String.format("|C%s%s%s%s%s%s|",
                this.mesh[0], this.mesh[1], this.mesh[2],
                this.mesh[3], this.mesh[4], this.mesh[5]);
    }
}
