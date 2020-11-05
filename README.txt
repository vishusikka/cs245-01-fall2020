Assignment 1 - N Body Problem
Vishu Sikka
Wednesday, 4th November, 2020
CS 245-02

This project contains four java files: Display.java, NBody.java, Body.java, and Pair.java
The program reads in a text file and sets the parameters accordingly. The movement of the 
bodies are slow, but for testing purposes, we can increase the movement of the bodies by 
replacing the update_location method code in the Body.java file with the following code 
(added the t variable)


    public void update_location(Pair force){
        t++;
        Pair acceleration = new Pair(0.0, 0.0);
        //calculating ax and ay
        acceleration.setX(force.getX()/this.mass);
        acceleration.setY(force.getY()/this.mass);
        //calculating vx and vy
        velocity.setX(this.velocity.getX()+acceleration.getX()*t);
        velocity.setY(this.velocity.getY()+acceleration.getY()*t);
        //updating the location
        location.setX(location.getX()+velocity.getX()*t);
        location.setY(location.getY()+velocity.getY()*t);
    }

    