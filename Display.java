import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.util.Scanner;
import java.lang.String;
import java.io.File;
import java.io.FileNotFoundException;

//this class is in charge of the ui of the application
public class Display extends JPanel implements ActionListener {

    //width and height of the display
    final int WIDTH = 768;
    final int HEIGHT = 768;

    static NBody nbody;
    Timer tm = new Timer(5, this);

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        nbody.drawBodies(g);
        tm.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nbody.update();
        // if a body is off a screen is going to be removed from the body collection
        nbody.removeOffScreen(WIDTH, HEIGHT);
        repaint();
    }

    public static void main(String[] args) {

        //reading the text file
        try {
            File f = new File("nbody_input.txt");
            Scanner s = new Scanner(f);

            // reading data structure
            String ds = s.nextLine();
            // reading scale
            String scale_str = s.nextLine();
            double scale = Double.parseDouble(scale_str);

            //setting the data structure in the program
            if(ds.equals("ArrayList") || ds.equals("LinkedList")){
                nbody = new NBody(ds, scale);
             } else {
                System.out.println("Invalid Data Structure!");
                System.exit(-1);
            }

            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] data = line.split(",");
                //save the inputs into body object
                Body b = new Body(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2]),
                        Double.parseDouble(data[3]), Double.parseDouble(data[4]), Double.parseDouble(data[5]), Integer.parseInt(data[6]));
                nbody.addBody(b);
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //setting up the display
        Display display = new Display();
        JFrame jf = new JFrame();
        jf.setTitle("NBody");
        jf.setSize(display.WIDTH, display.HEIGHT);
        jf.add(display);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
    }

}
