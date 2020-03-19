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
            int energy = 160 + random.nextInt(241);
            person.setEnergy(energy); // value between 160 and 400
            person.setHappiness(50 + random.nextInt(26)); // value between 50 and 75
            person.setLikenessToDrink(70); // value between 0 and 100
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
        //float[] x = new float[location.steps];
        //float[] y = new float[location.steps];
        //int k = club.crowd[0].size();
        //int[] test = club.totalMoneySpend;
        //System.out.println(Integer.toString(test[1200]));

        //for position
        try {

            FileWriter writer = new FileWriter("Position.txt", false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            float x = 0;
            float y = 0;
            for(int k = 0; k<club.crowd[0].size(); k++) {
                bufferedWriter.write('X' + Integer.toString(k));
                bufferedWriter.write(" ");
                bufferedWriter.write('Y' + Integer.toString(k));
                bufferedWriter.write(" ");
            }

            for(int i = 0; i < location.steps; i++) {
                bufferedWriter.newLine();
                for (int k = 0; k<club.crowd[0].size(); k++) {
                    //System.out.println(club.crowd[i].get(0).getEnergy(i));
                    x = club.crowd[0].get(k).getPosition(i)[0];
                    y = club.crowd[0].get(k).getPosition(i)[1];
                    bufferedWriter.write(Float.toString(x));
                    bufferedWriter.write(" ");
                    bufferedWriter.write(Float.toString(y));
                    bufferedWriter.write(" ");

                    //x[i] = club.crowd[0].get(k).getPosition(i)[0];
                    //y[i] = club.crowd[0].get(k).getPosition(i)[1];
                }
/*
                for (int j = 0; j < x.length; j++) {
                    b = x[j];
                    bufferedWriter.write(Float.toString(b));
                    //if (i < (a.length - 1)) {
                    bufferedWriter.write(" ");
                    //}
                    b = y[j];
                    bufferedWriter.write(Float.toString(b));
                    bufferedWriter.newLine();
                    //bufferedWriter.write("See You Again!");
                }*/
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } //end position

        //for club
        try {

            FileWriter writer = new FileWriter("Club.txt", false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            int put = 2;
            //for(int k = 0; k<club.crowd[0].size(); k++) {
            bufferedWriter.write("Money_spent"); //totalMoneySpend
            bufferedWriter.write(" ");

            bufferedWriter.write("Amount_people"); //numberOfPeople
            bufferedWriter.write(" ");

            bufferedWriter.write("People_dancing"); //numberOfPeopleDancing
            bufferedWriter.write(" ");
            //}
            for(int i = 0; i < location.steps; i++) {
                put = club.totalMoneySpend[i];
                bufferedWriter.newLine();
                bufferedWriter.write(Integer.toString(put));

                put = club.numberOfPeople[i];
                bufferedWriter.write(" ");
                bufferedWriter.write(Integer.toString(put));

                put = club.numberOfPeopleDancing[i];
                bufferedWriter.write(" ");
                bufferedWriter.write(Integer.toString(put));
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } // end club

        //for happiness
        try {
            FileWriter writer = new FileWriter("Happiness.txt", false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            int put = 0;
            for(int k = 0; k<club.crowd[0].size(); k++) {
                bufferedWriter.write("Happiness" + Integer.toString(k));
                bufferedWriter.write(" ");
            }
            for(int i = 0; i < location.steps; i++) {
                bufferedWriter.newLine();
                for (int k = 0; k<club.crowd[0].size(); k++) {
                    put = club.crowd[0].get(k).happiness[i];
                    bufferedWriter.write(Integer.toString(put));
                    bufferedWriter.write(" ");
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } //end happiness

        //for energy
        try {
            FileWriter writer = new FileWriter("energy.txt", false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            float put = 0;
            for(int k = 0; k<club.crowd[0].size(); k++) {
                bufferedWriter.write("energy" + Integer.toString(k));
                bufferedWriter.write(" ");
            }
            for(int i = 0; i < location.steps; i++) {
                bufferedWriter.newLine();
                for (int k = 0; k<club.crowd[0].size(); k++) {
                    put = club.crowd[0].get(k).energy[i];
                    bufferedWriter.write(Float.toString(put));
                    bufferedWriter.write(" ");
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } //end energy

        //for drinksConsumed
        try {
            FileWriter writer = new FileWriter("drinksConsumed.txt", false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            int put = 0;
            for(int k = 0; k<club.crowd[0].size(); k++) {
                bufferedWriter.write("drinksConsumed" + Integer.toString(k));
                bufferedWriter.write(" ");
            }
            for(int i = 0; i < location.steps; i++) {
                bufferedWriter.newLine();
                for (int k = 0; k<club.crowd[0].size(); k++) {
                    put = club.crowd[0].get(k).drinksConsumed[i];
                    bufferedWriter.write(Integer.toString(put));
                    bufferedWriter.write(" ");
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } //end drinksConsumed
    }


    public static void main(String[] args) {
        (new Main()).main();
    }
}