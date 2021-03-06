//import us.hebi.matlab.mat.format.Mat5;
//import us.hebi.matlab.mat.format.Mat5Writer;
//import us.hebi.matlab.mat.types.*;

import java.util.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static final Random random = new Random();
    public static List<Day> days = new ArrayList<>();
    public static List<Person> people = new ArrayList<>();
    public static List<Club> clubs = new ArrayList<>();

    private Main(){
        //private constructor so no instances can be made.
    }

    public static void main(String[] args) {
        initialize();
        LogPeopleToTerminal();

        for (Day day : days) {
            day.simulate();
        }
    }

    private static void initialize() {
        initializeClubs();
        initializePeople(Variables.amountOfPersons);
        initializeDays();

        // Let all people enter the club.
        //todo create 'enter' state for people, so they can add themselves to the club
        Club club = clubs.get(0);
        for (Person p : people) {
            p.enterClub(club);
        }
    }

    public static float getFirstOpen() {
        float min = Float.MAX_VALUE;
        for (Club club : clubs) {
            if (club.openTime < min)
                min = club.openTime;
        }
        return min;
    }

    public static float getLastClose() {
        float max = Float.MIN_VALUE;
        for (Club club : clubs) {
            if (club.closeTime > max)
                max = club.closeTime;
        }
        return max;
    }

    private static void initializePeople(int amount){
        for (int i = 0; i < amount; i++) {
            int index =  people.size();
            Gender gender;
            String name;
            if(random.nextBoolean()) {
                gender = Gender.MALE;
                name = Names.male[random.nextInt(Names.male.length)];
            } else {
                gender = Gender.FEMALE;
                name = Names.female[random.nextInt(Names.female.length)];
            }

            //int id, String name, Gender gender, int alcoholTolerance, int danceAffinity, int money, int initalLikenessToDrink, int initialHappiness, float initialEnergy
            float arrivalTime = clubs.get(0).openTime + random.nextFloat() * (clubs.get(0).closeTime - clubs.get(0).openTime- 2f);//arrive somewhere between opening and 2 hours before close
            System.out.println("arrivaltime "+arrivalTime);
             int danceAffinity = random.nextInt(20);
             int initialLikenessToDrink= (70); // value between 0 and 100
             int initialHappiness = (50 + random.nextInt(26)); // value between 50 and 75
             float energy = 1f;
            Person person = new Person(index, name, gender, Variables.initialAlcoholTolerance ,danceAffinity, Variables.initialMoney, initialLikenessToDrink, initialHappiness, energy, Variables.sipsPerHour, arrivalTime);
            people.add(person);
        }
    }

    private static void initializeClubs(){
        clubs.add(new Club());
        //when 0 patrons drink a quarter slower, when 1 patrons drink a quarter faster
        clubs.get(0).setMusicVolume(Variables.musicVolume);
    }

    private static void initializeDays(){
        days.add(new Day());
    }

    private static void initializationDebug(Club club){
        //[DEBUG] print the total money of the entire starting crowd
        printSpendableMoney(people);
    }

    static void printSpendableMoney(List<Person> crowd){
        int totalMoney = 0;
        for (Person person: crowd){
            totalMoney+=person.getMoney();
        }
        System.out.println("--------------------------------------");
        System.out.println("Total patron money: " + totalMoney);
        System.out.println("--------------------------------------");
    }

    public static void LogPeopleToTerminal () {
        for (Person person : people) {
            System.out.println("--------------------------------------");
            System.out.println("(" + person.getId() + ") " + person.getName());
            System.out.print("Gender: ");
            if (person.getGender() == Gender.MALE)
                System.out.println("Male");
            else
                System.out.println("Female");
            System.out.println("Position: [" + person.getCurrentState().getPosition().getX() + "," + person.getCurrentState().getPosition().getY() + "]");
            System.out.println("Money: " + person.getCurrentState().getSpendableMoney());
            System.out.println("Energy: " + person.getCurrentState().getEnergy());
            System.out.println("Drinks consumed: " + person.getCurrentState().getDrinksConsumed());
        }
    }

    public static void LogInfo (Club club) {
        LogPeopleToTerminal();
        // TODO prepare data for MATLAB
        //for position
        if(Variables.makePositiontxt) {
            try {
                FileWriter writer = new FileWriter("Position.txt", false);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                float x = 0;
                float y = 0;
                for (int k = 0; k < people.size(); k++) {
                    bufferedWriter.write('X' + Integer.toString(k));
                    bufferedWriter.write(" ");
                    bufferedWriter.write('Y' + Integer.toString(k));
                    bufferedWriter.write(" ");
                }

                for (int i = 0; i < people.get(0).getStates().size(); i++) { //go trough all states
                    bufferedWriter.newLine();
                    for (int k = 0; k < people.size(); k++) {//go trough all people in those states

                        x = people.get(k).getStates().get(i).getPosition().getX();
                        y = people.get(k).getStates().get(i).getPosition().getY();
                        bufferedWriter.write(Float.toString(x));
                        bufferedWriter.write(" ");
                        bufferedWriter.write(Float.toString(y));
                        bufferedWriter.write(" ");
                    }
                }

                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            } //end position
        }
        //for club
        if(Variables.makeClubtxt) {
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
                for (int i = 0; i < people.get(0).getStates().size(); i++) {
                    put = club.getTotalMoneySpend(i);
                    bufferedWriter.newLine();
                    bufferedWriter.write(Integer.toString(put));

                    put = club.getNumberOfPeople(i);
                    bufferedWriter.write(" ");
                    bufferedWriter.write(Integer.toString(put));

                    put = club.getNumberOfPeopleDancing(i);
                    bufferedWriter.write(" ");
                    bufferedWriter.write(Integer.toString(put));
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            } // end club
        }



        //for happiness
        if(Variables.makeHappinesstxt) {
            try {
                FileWriter writer = new FileWriter("Happiness.txt", false);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                float put = 0;
                for (int k = 0; k < people.size(); k++) {
                    bufferedWriter.write("Happiness" + k);
                    bufferedWriter.write(" ");
                }
                for (int i = 0; i < people.get(0).getStates().size(); i++) { //go trough all states
                    bufferedWriter.newLine();
                    for (int k = 0; k < people.size(); k++) { //go trough all people
                        put = people.get(k).getStates().get(i).getHappiness();
                        bufferedWriter.write(Float.toString(put));
                        bufferedWriter.write(" ");
                    }
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            } //end happiness
        }

        //for energy
        if(Variables.makeEnergytxt) {
            try {
                FileWriter writer = new FileWriter("energy.txt", false);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                float put = 0;
                for (int k = 0; k < people.size(); k++) {//go trough all people
                    bufferedWriter.write("energy" + Integer.toString(k));
                    bufferedWriter.write(" ");
                }
                for (int i = 0; i < people.get(0).getStates().size(); i++) {//go trough all states
                    bufferedWriter.newLine();
                    for (int k = 0; k < people.size(); k++) {//go trough all people
                        put = people.get(k).getStates().get(i).getEnergy();
                        bufferedWriter.write(Float.toString(put));
                        bufferedWriter.write(" ");
                    }
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            } //end energy
        }

        //for drinksConsumed
        if(Variables.makeDrinksConsumedtxt) {
            try {
                FileWriter writer = new FileWriter("drinksConsumed.txt", false);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                int put = 0;
                for (int k = 0; k < people.size(); k++) {
                    bufferedWriter.write("drinksConsumed" + Integer.toString(k));
                    bufferedWriter.write(" ");
                }
                for (int i = 0; i < people.get(0).getStates().size(); i++) {
                    bufferedWriter.newLine();
                    for (int k = 0; k < people.size(); k++) {
                        put = people.get(k).getStates().get(i).getDrinksConsumed();
                        bufferedWriter.write(Integer.toString(put));
                        bufferedWriter.write(" ");
                    }
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            } //end drinksConsumed
        }

        //for drinksConsumed
        if(Variables.makeTotalDrinksConsumedtxt) {
            try {
                FileWriter writer = new FileWriter("totalDrinksConsumed.txt", false);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                int put = 0;
                bufferedWriter.write("totalDrinksConsumed");
                for (int i = 0; i < people.get(0).getStates().size(); i++) {
                    bufferedWriter.newLine();
                    put = clubs.get(0).getState(i).getTotalDrinksConsumed();
                    bufferedWriter.write(Integer.toString(put));
                    bufferedWriter.write(" ");
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            } //end drinksConsumed
        }
    }
}