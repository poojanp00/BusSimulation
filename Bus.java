// Jake Buchanan: 5450753
// Poojan Patel: 5453845

import java.util.ArrayList;

public class Bus {
    private ArrayList<Rider> riders = new ArrayList<>();
    private boolean express; //true if express
    private int busSize = 50;

    //Bus constructor
    public Bus(boolean express) {
        this.express = express;
    }

    public int getBusSize(){
      return busSize;
    }

    public boolean isExpress(){
      return express;
    }

    //adds rider to ArrayList if bus isn't full
    public boolean addRider(Rider r) {
        if (riders.size() < busSize+1) {
            riders.add(r);
            return true;
        }
        return false;
    }

    //Function checks all riders on the bus who need to get off at the stop the bus is at.
    //Those riders are removed from the bus.
    public Rider[] removeRidersAtStop(int stop){
        int count = 0;
        for(int i = 0; i < riders.size(); i ++) {
            if(riders.get(i).getDropoffStop() == stop)
                count ++;
        }
        Rider[] removed = new Rider[count];
        int index = 0;
        for(int i = 0; i < riders.size(); i ++) {
            if (riders.get(i).getDropoffStop() == stop) {
                removed[index] = riders.get(i);
                riders.remove(i);
                i--;
                index++;
            }
        }
        return removed;
    }

    //The bus isn't full if ArrayList size is less than designated bus size
    public boolean isFull() {
        if(riders.size() >= busSize)
            return true;
        return false;
    }


    //returns number of riders on a given bus
    public int size() {
        return riders.size();
    }

}//bus
