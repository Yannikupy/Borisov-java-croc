public class Annotation extends Figure {
    String Label;
    Figure figure;
    @Override
    public String toString() {
        return figure.toString() + ": " + Label;
    }
}
