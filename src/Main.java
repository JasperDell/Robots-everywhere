import java.util.*;

public class Main {
    Random random = new Random();

    private Club club;
    private OnLocation location;
    private Names names;

    private int startCrowd = 10;

    void main(){
        // Initialize the club and the location
        club = new Club();
        location = new OnLocation(this);
        location.InitDay();
        names = new Names();

        // Initialize x persons to start the day with
        initializeCrowd(startCrowd);

        //[DEBUG] print the total money of the entire starting crowd
        printSpendableMoney(club.crowd);

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
            int index = club.crowd.size();
            person.setIndex(index);
            int gender = random.nextInt(2);
            person.setGender(gender); // Only allow 0>male or 1>female
            person.setName(names.names[gender][random.nextInt(names.names[gender].length)]);

            // Set person starting values
            person.setMoneyToSpend(random.nextInt(20));
            person.setEnergy(80 + random.nextInt(40));

            // Visualisation
            System.out.println("--------------------------------------");
            System.out.println("(" + index + ") " + person.getName() + " joined the club!");
            System.out.print("Gender: ");
            if (gender == 0) {System.out.println("Male"); } else {System.out.println("Female"); }
            System.out.println("Energy: " + person.getEnergy());
            System.out.println("Money: " + person.getMoneyToSpend());

            // Finally, add person to the club crowd
            club.crowd.add(person);
        }
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

    public void LogInfo () {

    }

    public static void main(String[] args) {
        (new Main()).main();
    }
}