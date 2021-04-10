// Jake Buchanan: 5450753
// Poojan Patel: 5453845

import java.util.Random;

public class Rider {
    private int pickupStop;
    private int dropoffStop;
    //Integer arrays for each of the east and west stops. Some stops are included twice to show that they are twice as popular
    private static int[] eastStop = {1,1,2,3,4,5,6,7,8,9,10,11,12,13,14,14,15,15};
    private static int[] westStop = {16,16,17,18,19,20,21,22,23,24,25,26,27,28,29,29,0,0};

    //Getter for pick up and drop off stops.
    public int getPickupStop(){
        return pickupStop;
    }

    public int getDropoffStop(){
        return dropoffStop;
    }

    //Creates riders and randomly choses their pickup and dropoff stops. Uses else if statement for special cases.
    public Rider(int pickupStop){
        this.pickupStop = pickupStop;
        Random rand = new Random();
        if(pickupStop < 15){
          dropoffStop = eastStop[rand.nextInt(18)];
          while(pickupStop >= dropoffStop){
            dropoffStop = eastStop[rand.nextInt(18)];
          }
        }
        else if(pickupStop == 29){
          dropoffStop = 0;
        }
        else{
          dropoffStop = westStop[rand.nextInt(18)];
          while(pickupStop >= dropoffStop){
            dropoffStop = westStop[rand.nextInt(18)];
          }
        }
    }//rider method

}//Rider Class
