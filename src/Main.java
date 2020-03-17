//import us.hebi.matlab.mat.format.Mat5;
//import us.hebi.matlab.mat.format.Mat5Writer;
//import us.hebi.matlab.mat.types.*;

import java.io.PrintWriter;
import java.util.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    Random random = new Random();

    public Club club;
    public OnLocation location;
    public Frame frame;
    //public Visualiser vis;
    private Names names;

    private int startCrowd = 10;

    void main (){
        // Initialize the club and the location
        location = new OnLocation(this);
        location.InitDay();
        club = new Club(this);
        frame = new Frame();
        //vis = new Visualiser();
        names = new Names();

        // Initialize x persons to start the day with
        initializeCrowd(startCrowd);

        //[DEBUG] print the total money of the entire starting crowd
        printSpendableMoney(club.crowd[0]);

        // Start the simulation: time will progress over the day
        int dummy = location.UpdateDay();

        // End the simulation and collect the results
        location.RoundUpDay();
    }

    void initializeCrowd(int startCrowd){
        // For now, no groups: init all patrons separately
        for (int i = 0; i < startCrowd; i++) {
            Person person = new Person(this);

            // Index, gender and name
            int index = club.crowd[0].size();
            person.setIndex(index);
            int gender = random.nextInt(2);
            person.setGender(gender); // Only allow 0>male or 1>female
            person.setName(names.names[gender][random.nextInt(names.names[gender].length)]);

            // Set person starting values
            person.setMoneyToSpend(random.nextInt(20));
            int energy = 180 + random.nextInt(60);
            person.setEnergy(energy);

            person.setLikenessToDrink(70);
            person.setHasAlcohol(0);
            person.setDrinksConsumed(0);

            //person.setPosition(new int[] {random.nextInt(360)  + 40, random.nextInt(360) + 40});
            person.setPosition(new float[] {190 + random.nextInt(20), 360});

            // Finally, add person to the club crowd
            club.crowd[0].add(person);
        }

        // Visualisation
        LogCrowdTerminal();
    }

    void printSpendableMoney(List<Person> crowd){
        int totalMoney = 0;
        for (Person person: crowd){
            person.setArrayPointer(0);
            totalMoney+=person.getMoneyToSpend();
        }
        System.out.println("--------------------------------------");
        System.out.println("Total patron money: " + totalMoney);
        System.out.println("--------------------------------------");
    }

    public void LogCrowdTerminal () {
        for (Person person : club.crowd[location.curStep - 1]) {
            person.setArrayPointer(location.curStep-1);
            System.out.println("--------------------------------------");
            if (location.curStep == 1) { // First time this person is inside the club
                System.out.println("(" + person.getIndex() + ") " + person.getName() + " joined the club!");
            } else if (location.curStep == location.steps) { // Club closed or person leaves the club
                System.out.println("(" + person.index + ") " + person.getName() + " left the club!");
            } else { // Person remains inside club
                System.out.println("(" + person.index + ") " + person.getName());
            }
            System.out.print("Gender: ");
            if (person.getGender() == 0) {System.out.println("Male"); } else {System.out.println("Female"); }
            System.out.println("Position: [" + person.getPosition()[0] + "," + person.getPosition()[1] + "]");
            System.out.println("Money: " + person.getMoneyToSpend());
            System.out.println("Energy: " + person.getEnergy(location.curStep-1));
            System.out.println("Drinks consumed: " + person.getDrinksConsumed());
        }
    }

    public void LogInfo () {
        LogCrowdTerminal();
        // TODO prepare data for MATLAB
        float[] x = new float[location.steps];
        float[] y = new float[location.steps];

        for (int i = 0; i < location.steps; i++) {
            //System.out.println(club.crowd[i].get(0).getEnergy(i));
            x[i] = club.crowd[0].get(0).getPosition(i)[0];
            y[i] = club.crowd[0].get(0).getPosition(i)[1];
        }

        try {


            FileWriter writer = new FileWriter("output2.txt", false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            float b = 2;
            var i = 0;
            bufferedWriter.write('x');
            bufferedWriter.write(" ");
            bufferedWriter.write('y');
            bufferedWriter.newLine();
            for (i = 0; i < x.length; i++) {
                b = x[i];
                bufferedWriter.write(Float.toString(b));
                //if (i < (a.length - 1)) {
                bufferedWriter.write(" ");
                //}
                b = y[i];
                bufferedWriter.write(Float.toString(b));
                bufferedWriter.newLine();
                //bufferedWriter.write("See You Again!");
            }
            //bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        (new Main()).main();
    }
}