import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

//this class holds all the body and manages the body's appearing on the screen at the right location
public class NBody {

    private List<Body> arrNBody;
    private static final double G = 6.674*Math.pow(10,-11);
    private double scale;

    public NBody(String s, double scale) {
        if(s.equals("ArrayList")){
            this.arrNBody = new ArrayList<Body>();
        }else{
            this.arrNBody = new LinkedList<Body>();
        }
        this.scale = scale;
    }

    public void addBody(Body b) {
        this.arrNBody.add(b);
    }

    // removes the body if it is off the screen
    public void removeOffScreen(int width, int height) {
        for (int i = 0; i < arrNBody.size(); i++) {
            if ((arrNBody.get(i).getLocation().getX() < 0 || arrNBody.get(i).getLocation().getX() > width) ||
                    (arrNBody.get(i).getLocation().getY() < 0 || arrNBody.get(i).getLocation().getY() > height)){
                this.arrNBody.remove(arrNBody.get(i));
            }
        }
    }

    //draw the body on the display
    public void drawBodies(Graphics g) {
        for (int i = 0; i < arrNBody.size(); i++) {
            g.setColor(arrNBody.get(i).getColor());
            g.fillOval((int)arrNBody.get(i).getLocation().getX(),
                       (int)arrNBody.get(i).getLocation().getY(),
                       arrNBody.get(i).getSize(),
                       arrNBody.get(i).getSize());
        }
    }

    // calculates all the forces on each body and update the location
    public void update(){
        for (int i = 0; i < arrNBody.size(); i++) {
            // clearing the vector before calculating all the forces for the body
            arrNBody.get(i).clearVector();
            for (int j = 0; j < arrNBody.size(); j++) {

                if(i!= j){ // we don't want the force of a body on itself
                    arrNBody.get(i).addToVector(getForce(arrNBody.get(i), arrNBody.get(j)));
                }
            }
            //perform a summation on all the force vectors of the body
            Pair sum = arrNBody.get(i).vectorSummation();
            //update the location
            arrNBody.get(i).update_location(sum);
        }
    }

    //returns the force exerted on target
    public Pair getForce(Body target, Body neighbor) {
        // calculate distance
        double distance = target.getLocation().get_distance(neighbor.getLocation(), scale);

        // calculate gravitational force
        double GForce = (G*target.getMass()*neighbor.getMass())/(distance * distance);

        // calculate Fx and Fy
        Pair xydifference = target.getLocation().difference(neighbor.getLocation(), scale);
        Pair force_vector = new Pair(GForce * (xydifference.getX()/distance), GForce * (xydifference.getY()/distance));
        force_vector.setX(force_vector.getX()*-1);
        force_vector.setY(force_vector.getY()*-1);

        // return Fx and Fy
        return force_vector;
    }

}
