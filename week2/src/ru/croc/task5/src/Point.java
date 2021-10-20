public class Point {
    protected int cordX;
    protected int cordY;

    public Point(){
        cordX = 0;
        cordY = 0;
    }

    public Point(int x, int y) {
        cordX = x;
        cordY = y;
    }

    @Override
    public String toString() {
        return '(' + cordX + ", " + cordY + ')';
    }
}
