/**
 * PJ-2 - Handout
 *
 * <p>Purdue University -- CS18000 -- Summer 2022</p>
 *
 * @author Purdue CS
 * @version Mar 2, 2024
 */
public class Face {
    private Triangle[] mesh = new Triangle[2];
    private UnitVector surfaceNormal;

    public Face(Triangle one, Triangle two) {
        if (!one.getSurfaceNormal().compareTo(two.getSurfaceNormal())) {
            this.mesh[0] = new Triangle();
            this.mesh[1] = new Triangle();
            this.surfaceNormal = new UnitVector();
        }

        this.mesh[0] = one;
        this.mesh[1] = two;
        this.surfaceNormal = new UnitVector(one.getSurfaceNormal().getI(),
                one.getSurfaceNormal().getJ(),
                one.getSurfaceNormal().getK());
    }

    public Face() {
        this.mesh[0] = new Triangle();
        this.mesh[1] = new Triangle();
        this.surfaceNormal = new UnitVector();
    }

    public Triangle[] getMesh() {
        return mesh;
    }

    public UnitVector getSurfaceNormal() {
        return surfaceNormal;
    }

    public void fipSurfaceNormal() {
        this.mesh[0].fipSurfaceNormal();
        this.mesh[1].fipSurfaceNormal();
        this.surfaceNormal.fipVector();
    }

    public boolean compareTo(Face face) {
        return this.mesh[0].compareTo(face.getMesh()[0])
                && this.mesh[1].compareTo(face.getMesh()[1])
                && this.surfaceNormal.compareTo(face.getSurfaceNormal());
    }

    public boolean isSimilar(Face face) {
        if (this.surfaceNormal.compareTo(face.getSurfaceNormal())) {
            return true;
        }

        if (this.mesh[0].isSimilar(face.getMesh()[0])
                && this.mesh[1].isSimilar(face.getMesh()[1])) {
            return true;
        }

        if (this.mesh[0].isSimilar(face.getMesh()[1])
                && this.mesh[1].isSimilar(face.getMesh()[0])) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        if ("<InvalidUnitVector>".equals(this.surfaceNormal.toString())) {
            return "[InvalidFace]";
        }

        if ("InvalidTriangle".equals(this.mesh[0].toString())) {
            return "[InvalidFace]";
        }

        if ("InvalidTriangle".equals(this.mesh[1].toString())) {
            return "[InvalidFace]";
        }

        return String.format("{F[A%s; B%s; C%s][A%s; B%s; C%s]N%s}",
                this.mesh[0].getVertexA(), this.mesh[0].getVertexB(), this.mesh[0].getVertexC(),
                this.mesh[1].getVertexA(), this.mesh[1].getVertexB(), this.mesh[1].getVertexC(),
                this.surfaceNormal);
    }
}
