// Jake Buchanan: 5450753
// Poojan Patel: 5453845

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Agenda{

    private static int numBuses;
    private static int expressBuses;
    private static int normalBuses;
    private static int[] expressStops = {0,1,14,15,16,29};
    private static int[] normalStops = new int[30];

    public static double interArrivalRate = 120;

    // Note that this is static. This way, it can be accessed anywhere.
    public static PQ a = new PQ();

    // 2 Arrays of queues to store Riders for each stop, one array of queue for east stops and one for west stops
    public static Q1Gen<Rider>[] east = new Q1Gen[15];
    public static Q1Gen<Rider>[] west = new Q1Gen[15];

    //Arraylist to hold duration of each action in the BusEvent, used to calculate average time between stops for buses
    public static ArrayList<Integer> deboardTime = new ArrayList<>();
    public static ArrayList<Integer> boardTime = new ArrayList<>();
    public static ArrayList<Integer> travTime = new ArrayList<>();

    public static void main(String[] args) {
        for(int m =0; m < 15; m++) {
            east[m] = new Q1Gen();
            west[m] = new Q1Gen();
        }
        for(int k = 0; k < 30; k++){
          normalStops[k] = k;
        }

        // add RiderEvents to a
        a.add(new RiderEvent(0),0);
        a.add(new RiderEvent(1),0);
        a.add(new RiderEvent(2),0);
        a.add(new RiderEvent(3),0);
        a.add(new RiderEvent(4),0);
        a.add(new RiderEvent(5),0);
        a.add(new RiderEvent(6),0);
        a.add(new RiderEvent(7),0);
        a.add(new RiderEvent(8),0);
        a.add(new RiderEvent(9),0);
        a.add(new RiderEvent(10),0);
        a.add(new RiderEvent(11),0);
        a.add(new RiderEvent(12),0);
        a.add(new RiderEvent(13),0);
        a.add(new RiderEvent(14),0);
        a.add(new RiderEvent(15),0);
        a.add(new RiderEvent(16),0);
        a.add(new RiderEvent(17),0);
        a.add(new RiderEvent(18),0);
        a.add(new RiderEvent(19),0);
        a.add(new RiderEvent(20),0);
        a.add(new RiderEvent(21),0);
        a.add(new RiderEvent(22),0);
        a.add(new RiderEvent(23),0);
        a.add(new RiderEvent(24),0);
        a.add(new RiderEvent(25),0);
        a.add(new RiderEvent(26),0);
        a.add(new RiderEvent(27),0);
        a.add(new RiderEvent(28),0);
        a.add(new RiderEvent(29),0);

        Random rand = new Random();

        boolean express = true;
        boolean regular = false;
        Scanner s = new Scanner(System.in);
        System.out.println("How many buses on the route? Max 14");
        int userBuses = s.nextInt();
        while(userBuses > 14 || userBuses < 0){
          System.out.println("Must be between 1 and 14 buses, inclusive.");
          System.out.println("How many buses on the route? Max 14");
          userBuses = s.nextInt();
        }
        numBuses = userBuses;
        System.out.println("How many of those buses are regular buses?");
        int regBuses = s.nextInt();
        System.out.println("How many of those buses are express buses?");
        int expBuses = s.nextInt();
        while(regBuses + expBuses != userBuses || regBuses < 1){
          System.out.println("Regular and express buses do not equal total number of buses and/or need a minimum of one regular bus");
          System.out.println("How many of those buses are regular buses?");
          regBuses = s.nextInt();
          System.out.println("How many of those buses are express buses?");
          expBuses = s.nextInt();
        }
        normalBuses = regBuses;
        expressBuses = expBuses;

        System.out.println("What is the interArrivalRate you want?");
        double interRate = s.nextDouble();
        while(interRate < 0){
          System.out.println("Must be postive number");
          System.out.println("What is the interArrivalRate you want?");
          interRate = s.nextDouble();
        }
        interArrivalRate = interRate;

        // add BusEvent to agenda
        for(int i = 0; i < expressBuses; i++) {
            int stop = expressStops[rand.nextInt(6)];
            a.add(new BusEvent(stop, new Bus(express)), 0);
        }
        for(int j = 0; j < normalBuses; j++) {
            int stop = normalStops[rand.nextInt(30)];
            a.add(new BusEvent(stop, new Bus(regular)), 0);
        }
        // loops through agenda and runs the next Event
        // will throw an exception until events are added to the agenda
        while(a.getCurrentTime() <= 10000) {
            a.remove().run();
            //System.out.print(a.getCurrentTime());
        }

        System.out.println("Ramp B |" + " Number of Riders in line: " + east[0].length());
        System.out.println("4th & Nicollet going East |" + " Number of Riders in line: " + east[1].length());
        System.out.println("Anderson Hall going East |" + " Number of Riders in line: " + east[2].length());
        System.out.println("Jones Hall going East |" + " Number of Riders in line: " + east[3].length());
        System.out.println("Kasota Circle going East |" + " Number of Riders in line: " + east[4].length());
        System.out.println("Como & Eustis going East |" + " Number of Riders in line: " + east[5].length());
        System.out.println("Como & Cleveland going East |" + " Number of Riders in line: " + east[6].length());
        System.out.println("Como & Snelling going East |" + " Number of Riders in line: " + east[7].length());
        System.out.println("Como and Hamline going East |" + " Number of Riders in line: " + east[8].length());
        System.out.println("Maryland and Dale going East |" + " Number of Riders in line: " + east[9].length());
        System.out.println("Maryland and Ride going East |" + " Number of Riders in line: " + east[10].length());
        System.out.println("Front and Lexington going East |" + " Number of Riders in line: " + east[11].length());
        System.out.println("Front and Dale going East |" + " Number of Riders in line: " + east[12].length());
        System.out.println("Capitol going East |" + " Number of Riders in line: " + east[13].length());
        System.out.println("Cedar going East |" + " Number of Riders in line: " + east[14].length());
        System.out.println("Union Depot |" + " Number of Riders in line: " + west[0].length());
        System.out.println("4th & Nicollet going West |" + " Number of Riders in line: " + west[1].length());
        System.out.println("Anderson Hall going West |" + " Number of Riders in line: " + west[2].length());
        System.out.println("Jones Hall going West |" + " Number of Riders in line: " + west[3].length());
        System.out.println("Kasota Circle going West |" + " Number of Riders in line: " + west[4].length());
        System.out.println("Como & Eustis going West |" + " Number of Riders in line: " + west[5].length());
        System.out.println("Como & Cleveland going West |" + " Number of Riders in line: " + west[6].length());
        System.out.println("Como & Snelling going West |" + " Number of Riders in line: " + west[7].length());
        System.out.println("Como and Hamline going West |" + " Number of Riders in line: " + west[8].length());
        System.out.println("Maryland and Dale going West |" + " Number of Riders in line: " + west[9].length());
        System.out.println("Maryland and Ride going West |" + " Number of Riders in line: " + west[10].length());
        System.out.println("Front and Lexington going West |" + " Number of Riders in line: " + west[11].length());
        System.out.println("Front and Dale going West |" + " Number of Riders in line: " + west[12].length());
        System.out.println("Capitol going West |" + " Number of Riders in line: " + west[13].length());
        System.out.println("Cedar going West |" + " Number of Riders in line: " + west[14].length());

        double totalDeboardTime=0, totalBoardTime=0, totalTravTime=0, sum=0;
        for(int i = 0; i<deboardTime.size(); i++)
            totalDeboardTime += deboardTime.get(i);
        for(int i = 0; i<boardTime.size(); i++)
            totalBoardTime += boardTime.get(i);
        for(int i = 0; i<travTime.size(); i++)
            totalTravTime += travTime.get(i);

        sum=totalDeboardTime/deboardTime.size()+totalBoardTime/boardTime.size()+totalTravTime/travTime.size();

        double avgTime = sum;

        int riderSum = 0;
        for(int i = 0; i < 15; i++){
          riderSum += east[i].length();
        }
        for(int i = 0; i < 15; i++){
          riderSum += west[i].length();
        }
        int avgRiders = riderSum / 30;
        System.out.println("Average riders in line: " + avgRiders);
        System.out.println("Time: " + avgTime);
    }

}
