public class Figure extends Point {
    private Point[] coordinates_of_points;

    public boolean contains(int x, int y){
        Point p = new Point(x, y);
        for (int i = 0; i < coordinates_of_points.length; i++){
            if(coordinates_of_points[i] == p){
                return true;
            }
        }
        return false;
    }
}
