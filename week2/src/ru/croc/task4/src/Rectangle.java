public class Rectangle extends Figure{
    private Point coordinates_left_bottom;
    private Point coordinates_right_top;
    @Override
    public String toString() {
        return "R " + coordinates_left_bottom + ", " + coordinates_right_top;
    }
}
