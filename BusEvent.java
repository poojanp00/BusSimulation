// Jake Buchanan: 5450753
// Poojan Patel: 5453845

public class BusEvent implements Event{
  //BusEvent implements the Event run interface
    private int curStop;
    private Bus bus;
    private static double avgCap;
    private static int iter =0;

    //BusEvennt constructor
    public BusEvent(int stop, Bus b) {
        curStop = stop;
        bus = b;
    }

    public void run() {
        //First we deboard all passengers on bus that want to get off at the stop
        Rider[] deboard = bus.removeRidersAtStop(curStop);
        if(deboard.length != 0)  {
            BusEvent be = new BusEvent(curStop, bus);
            Agenda.deboardTime.add(2*deboard.length);
            Agenda.a.add(be,2*deboard.length);      //Each rider takes 2 seconds
        }

        //Boarding passengers at depending on if they are going east or west, determined by the stop they are standing at.
        //if the bus is going east, the bus is filled until no one is left in line or the bus is full.
        //prints average capacity and PMPG
        else if(!bus.isFull() && curStop < 15 && Agenda.east[curStop].length() > 0) {
            int boarded = 0;
            while (!bus.isFull() && Agenda.east[curStop].length() > 0 && curStop < 15) {
                bus.addRider(Agenda.east[curStop].remove());
                boarded += 3;                       //Each rider takes 3 seconds
            }
            Agenda.boardTime.add(boarded);

            avgCap = (avgCap+bus.size())/2;         //Calculate average capacity every time we finish boarding the bus

            System.out.println("Average Capacity at iteration " + iter + " is " + avgCap);
            System.out.println("PMPG: " + ((bus.getBusSize()-100)/-10)*avgCap);

            Agenda.a.add(new BusEvent(curStop, bus), boarded);
        }

        //if the bus is going west, the bus is filled until no one is left in line or the bus is full.
        //prints average capacity and PMPG
        else if( !bus.isFull() && curStop >= 15 && Agenda.west[curStop - 15].length() > 0 && curStop >= 15){
            int boarded = 0;
            while (!bus.isFull() && Agenda.west[curStop - 15].length() > 0) {
                bus.addRider(Agenda.west[curStop - 15].remove());
                boarded += 3;
            }
            Agenda.boardTime.add(boarded);

            avgCap = (avgCap+bus.size())/2;

            System.out.println("Average Capacity at iteration " + iter + " is " + avgCap);
            System.out.println("PMPG: " + ((bus.getBusSize()-100)/-10)*avgCap);     //4.3 MPG at 50 seats

            Agenda.a.add(new BusEvent(curStop, bus), boarded);
        }
        //only one of the previous else if is reached.


        //Moves the bus to the next stop depending on what kind of bus it is. If it is a normal bus, it goes from stop to stop w/o changes.
        //If it is an express bus, it stops at all popular stops and every stop divisible by 4.
        else {
            int travelTime = 240;
            if(bus.isExpress()){
              if(curStop == 0 || curStop == 14 || curStop == 15 || curStop == 28){
                curStop = curStop + 1;
              }
              else if(curStop == 1){
                curStop = curStop + 3;
                travelTime = travelTime*3;
              }
              else if(curStop == 12){
                curStop = curStop + 2;
                travelTime = travelTime*2;
              }
              else if(curStop == 29){
                curStop = 0;
              }
              else{
                curStop = curStop + 4;
                travelTime = travelTime*4;
              }
            }
            else{
              if(curStop == 29){
                curStop = 0;
              }
              else{
                curStop = curStop + 1;
              }
            }
            Agenda.travTime.add(travelTime);
            iter ++;

            Agenda.a.add(new BusEvent(curStop, bus), travelTime);
        }
    }
}//BusEvent
