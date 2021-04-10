// Jake Buchanan: 5450753
// Poojan Patel: 5453845

import java.util.Random;

public class RiderEvent implements Event{
    private int stop;

    //creating an array to hold the model for arrival of riders at each stop
    private double[] arrivalPercents = {.75,.75,.5,.5,.5,.2,.2,.2,.2,0,0,-.2,-.2,-.2,-.2,-.5,-.5,-.5,-.75,-.75};

    //constructor
    public RiderEvent(int stop){
        this.stop = stop;
    }

    //Getter method returns stop
    public int getStop(){
        return stop;
    }

    //Creates a new rider everytime program is run(). We place the rider in the appropriate queue based on the way they are travelling.
    //If the stop is located in downtown the load is 50% because riders arrive twice as frequently.
    public void run() {
        Rider r = new Rider(stop);
        if(r.getPickupStop() <= 14){
            Agenda.east[stop].add(r);
        }
        else{
            Agenda.west[stop-15].add(r);
        }
        RiderEvent riderE = new RiderEvent(stop);
        Random rand = new Random();
        int rate = rand.nextInt(20);
        double load = Agenda.interArrivalRate;
        load = load + arrivalPercents[rate]*load;
        if(r.getPickupStop() == 0 || r.getPickupStop() == 1 || r.getPickupStop() == 29 || r.getPickupStop() == 14 || r.getPickupStop() == 15 || r.getPickupStop() == 16){
          load = load*.5;
        }

        Agenda.a.add(riderE, load);
    }//run method

}//RiderEvent class
