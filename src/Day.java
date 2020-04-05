import java.time.temporal.ValueRange;

//Day keeps track of the time and runs the simulation
public class Day {
    //make sure to use timeIncrement when change personState,
    //so whatever happens depends on the 'simulation time' instead of actual time
    public static final float timeIncrementInHours = 1/(60*60f);//every second
    private final float startTime;
    private final float endTime;
    private final int sleepTime;

    private static final boolean useVisualisation = Variables.useVisualisation;

    private PersonBehaviour pBehaviour = new PersonBehaviour();
    public float currentTime;

    public Day() {
        startTime = Main.getFirstOpen();
        endTime = Main.getLastClose();
        this.currentTime = startTime;
        //0 means as fast as it can go!!
        int simulationDuration =0;//10 * 60 * 1000;//10 minutes
        this.sleepTime = (int) ((simulationDuration * timeIncrementInHours) / (endTime - startTime));//2 * 1000  / ((int)Math.ceil((endTime - startTime) / timeIncrement) + 1);
    }

    public void simulate() {
        if (useVisualisation) {
            // Initialize visualisation.
            // TODO: If implementing multiple clubs, the club should hold the frame.
            Frame.Start(this);
            while (this.currentTime <= this.endTime) {
                long startCalculationTime = System.currentTimeMillis();
                // Create new states for people and clubs.
                //Persons and clubs keep track of all the states they have been in
                //in the form of a 'person/club state' class
                this.createNewStates();

                // Simulate the current time.
                // TODO: Update clubs
                PersonBehaviour.updatePersons();

                // Update visualisation.
                Frame.Update(this);


                long endCalculationTime = System.currentTimeMillis();
                // Sleep for a short while before beginning again.
                try {
                    int calculationDuration = (int) (endCalculationTime - startCalculationTime);
                    Thread.sleep(Math.max(0, sleepTime - calculationDuration));
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                // Set next time for simulation.
                this.currentTime = this.currentTime + timeIncrementInHours;
            }
        } else {
            while (this.currentTime <= this.endTime) {
                this.createNewStates();
                PersonBehaviour.updatePersons();
                this.currentTime = this.currentTime + timeIncrementInHours;
            }
        }
        // Ended simulation of day.
        System.out.println("money spent: ");
        System.out.println(Main.clubs.get(0).getTotalMoneySpend(Main.people.get(0).getStates().size() -1));
        System.out.println("[TODO] end of simulation!");
        Main.LogInfo(Main.clubs.get(0));
    }

    private void createNewStates(){
        for (Person p : Main.people) {
            p.newState(this.currentTime);
        }

        for (Club c : Main.clubs) {
            c.newState(this.currentTime);
        }
    }
}