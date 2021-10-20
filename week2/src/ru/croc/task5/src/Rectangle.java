public class Rectangle extends Figure implements Movable{
    private Point coordinates_left_bottom;
    private Point coordinates_right_top;
    @Override
    public String toString() {
        return "R " + coordinates_left_bottom + ", " + coordinates_right_top;
    }

    @Override
    public void move(int dx, int dy) {
        coordinates_left_bottom.cordX += dx;
        coordinates_left_bottom.cordY += dy;
        coordinates_right_top.cordX += dx;
        coordinates_right_top.cordY += dy;
    }
}
