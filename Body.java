import java.awt.Color;
import java.util.ArrayList;

//this class is in charge of each body and its characters
public class Body {

    private String name;
    private double mass;
    private int size;
    private Pair location;
    private Pair velocity;
    private Color c;
    ArrayList<Pair> vector = new ArrayList<Pair>();
    private int t = 0;

    public Body(String name, double m, double x_coordinate, double y_coordinate, double x_velocity, double y_velocity, int size) {
        this.name = name;
        this.mass = m;
        this.size = size;
        this.location = new Pair(x_coordinate, y_coordinate);
        this.velocity = new Pair(x_velocity ,y_velocity);
        // randomly assign a color to body
        this.c = new Color((int)(Math.random()*0x1000000));
    }

    public String toString(){
        return "Name: " + this.name +
                "Mass: " + this.mass +
                "x_coordinate: " + this.location.getX() +
                "y_coordinate: " + this.location.getY() +
                "x_velocity: " + this.velocity.getX() +
                "y_velocity: " + this.velocity.getY() +
                "Size: " + this.size;
    }

    public Color getColor() { return c; }
    public Pair getLocation() {
        return location;
    }
    public int getSize() {
        return size;
    }
    public Pair getVelocity() { return velocity; }
    public double getMass() { return mass; }

    public void addToVector(Pair p){
        vector.add(new Pair(p.getX(), p.getY()));
    }
    public void clearVector(){
        vector.clear();
    }
    public Pair vectorSummation(){
        Pair sum = new Pair(0.0, 0.0);
        for (Pair p: vector) {
            sum.sum(p);
        }
        return sum;
    }
    //the purpose is updating the location of body based on all the forces exerted on it
    public void update_location(Pair force){
        Pair acceleration = new Pair(0.0, 0.0);
        //calculating ax and ay
        acceleration.setX(force.getX()/this.mass);
        acceleration.setY(force.getY()/this.mass);
        //calculating vx and vy
        velocity.setX(this.velocity.getX()+acceleration.getX());
        velocity.setY(this.velocity.getY()+acceleration.getY());
        //updating the location
        location.setX(location.getX()+velocity.getX());
        location.setY(location.getY()+velocity.getY());
    }
}
