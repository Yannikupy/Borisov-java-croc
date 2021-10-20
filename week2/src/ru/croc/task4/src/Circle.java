public class Circle extends Figure{
    private Point cord_of_centre;
    private double Radius;

    @Override
    public String toString() {
        return "C " + cord_of_centre + ", " + Radius;
    }
}
