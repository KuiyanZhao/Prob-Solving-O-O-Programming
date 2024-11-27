import java.util.Arrays;

/**
 * PJ-2 - Handout
 *
 * <p>Purdue University -- CS18000 -- Summer 2022</p>
 *
 * @author Purdue CS
 * @version Mar 2, 2024
 */
public class Triangle {
    private Point vertexA;
    private Point vertexB;
    private Point vertexC;
    private UnitVector surfaceNormal;

    public Triangle() {
        this.vertexA = new Point();
        this.vertexB = new Point();
        this.vertexC = new Point();
        this.surfaceNormal = new UnitVector();
    }

    public Triangle(Point a, Point b, Point c) {
        this.vertexA = a;
        this.vertexB = b;
        this.vertexC = c;

        this.surfaceNormal = new UnitVector(a, b).crossProduct(new UnitVector(a, c));
    }

    public Point getVertexA() {
        return vertexA;
    }

    public Point getVertexB() {
        return vertexB;
    }

    public Point getVertexC() {
        return vertexC;
    }

    public UnitVector getSurfaceNormal() {
        return surfaceNormal;
    }

    public Point[] getVertices() {
        return new Point[]{this.vertexA, this.vertexB, this.vertexC};
    }

    public void fipSurfaceNormal() {
        this.surfaceNormal.fipVector();
    }

    public boolean compareTo(Triangle triangle) {
        return this.vertexA.compareTo(triangle.getVertexA())
                && this.vertexB.compareTo(triangle.getVertexB())
                && this.vertexC.compareTo(triangle.getVertexC())
                && this.surfaceNormal.compareTo(triangle.getSurfaceNormal());
    }

    public boolean isSimilar(Triangle triangle) {
        if (this.surfaceNormal == triangle.getSurfaceNormal()) {
            return true;
        }

        if (this.vertexA == triangle.getVertexA()
                && this.vertexB == triangle.getVertexB()
                && this.vertexC == triangle.getVertexC()) {
            return true;
        }

        if (this.vertexA == triangle.getVertexA()
                && this.vertexB == triangle.getVertexC()
                && this.vertexC == triangle.getVertexB()) {
            return true;
        }

        if (this.vertexB == triangle.getVertexA()
                && this.vertexA == triangle.getVertexB()
                && this.vertexC == triangle.getVertexC()) {
            return true;
        }

        if (this.vertexB == triangle.getVertexA()
                && this.vertexA == triangle.getVertexC()
                && this.vertexC == triangle.getVertexB()) {
            return true;
        }

        if (this.vertexC == triangle.getVertexA()
                && this.vertexA == triangle.getVertexB()
                && this.vertexB == triangle.getVertexC()) {
            return true;
        }

        if (this.vertexC == triangle.getVertexA()
                && this.vertexB == triangle.getVertexC()
                && this.vertexA == triangle.getVertexB()) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        if (this.vertexA.compareTo(this.vertexB)
                || this.vertexA.compareTo(this.vertexC)
                || this.vertexB.compareTo(this.vertexC)) {
            return "[InvalidTriangle]";
        }

        if ("<InvalidUnitVector>".equals(this.surfaceNormal.toString())) {
            return "[InvalidTriangle]";
        }

        return String.format("[A%s; B%s; C%s; N%s]", vertexA, vertexB, vertexB, surfaceNormal);
    }
}
