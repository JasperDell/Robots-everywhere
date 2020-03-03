import java.util.*;

public class Main {
    Random random = new Random();

    private Club club;
    private OnLocation location;

    private int startCrowd = 10;

    void main(){
        // Initialize the club and the location
        club = new Club();
        club.Init();
        location = new OnLocation();
        location.InitDay();

        // Initialize x persons to start the day with
        initializeCrowd(startCrowd);

        //[DEBUG] print the total money of the entire starting crowd
        printSpendableMoney(club.crowd);

        // Start the simulation: time will progress over the day
        int dummy = location.UpdateDay();

        // End the simulation and collect the results
        System.out.println("[TODO] end of simulation!");
    }

    void initializeCrowd(int startCrowd){
        // For now, no groups: init all patrons separately
        for (int i = 0; i < startCrowd; i++) {
            Person person = new Person();
            // Set person starting values
            person.setMoneyToSpend(random.nextInt(20));
            System.out.println(person.getMoneyToSpend());
            //TODO add more person initialisations
            //TODO visualise (for now in console)

            // Finally, add person to the club crowd
            club.crowd.add(person);
        }
    }

    void printSpendableMoney(List<Person> crowd){
        int totalMoney = 0;
        for (Person person: crowd){
            totalMoney+=person.getMoneyToSpend();
        }
        System.out.println(totalMoney);
    }

    public static void main(String[] args) {
        (new Main()).main();
    }
}