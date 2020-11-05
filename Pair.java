import java.lang.Math;

//this class is in charge of storing x and y of vector objects
public class Pair {

    double x;
    double y;

    public Pair(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getX() {
        return this.x;
    }
    public double getY() { return this.y; }
    // add value to current x and y
    public void sum(Pair p) {
        this.x += p.getX();
        this.y += p.getY();
    }
    // gets distance of two pairs
    public double get_distance(Pair p, double scale){
        double x = this.x*scale - p.getX()*scale;
        double y = this.y*scale - p.getY()*scale;
        return Math.sqrt(x * x + y * y);
    }
    public Pair difference(Pair p, double scale){
        return new Pair(this.getX()*scale - p.getX()*scale, this.getY()*scale - p.getY()*scale);
    }

}
