import java.util.*;

public class Main {
    Random random = new Random();

    public Club club;
    public OnLocation location;
    private Names names;

    private int startCrowd = 10;

    void main(){
        // Initialize the club and the location
        location = new OnLocation(this);
        location.InitDay();
        club = new Club(this);
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
            Person person = new Person();

            // Index, gender and name
            int index = club.crowd[0].size();
            person.setIndex(index);
            int gender = random.nextInt(2);
            person.setGender(gender); // Only allow 0>male or 1>female
            person.setName(names.names[gender][random.nextInt(names.names[gender].length)]);

            // Set person starting values
            person.setMoneyToSpend(random.nextInt(20));
            person.setEnergy(80 + random.nextInt(40));

            // Finally, add person to the club crowd
            club.crowd[0].add(person);
        }

        // Visualisation
        LogCrowdTerminal();
    }

    void printSpendableMoney(List<Person> crowd){
        int totalMoney = 0;
        for (Person person: crowd){
            totalMoney+=person.getMoneyToSpend();
        }
        System.out.println("--------------------------------------");
        System.out.println("Total patron money: " + totalMoney);
        System.out.println("--------------------------------------");
    }

    public void LogCrowdTerminal () {
        for (Person person : club.crowd[location.curStep - 1]) {
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
            System.out.println("Energy: " + person.getEnergy());
            System.out.println("Money: " + person.getMoneyToSpend());
        }
    }

    public void LogInfo () {
        LogCrowdTerminal();
        // TODO perpare data for MATLAB
        for (int i = 0; i < location.steps; i++) {
            //System.out.println(club.crowd[i].get(0).getEnergy());
        }
    }

    public static void main(String[] args) {
        (new Main()).main();
    }
}