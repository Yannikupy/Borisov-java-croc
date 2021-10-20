public class Circle extends Figure implements Movable{
    private Point cord_of_centre;
    private double Radius;

    @Override
    public String toString() {
        return "C " + cord_of_centre + ", " + Radius;
    }

    @Override
    public void move(int dx, int dy) {
        cord_of_centre.cordX += dx;
        cord_of_centre.cordY += dy;
    }
}
