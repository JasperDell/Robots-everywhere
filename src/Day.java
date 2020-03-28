import java.util.ArrayList;
import java.util.List;

public class Day {
    private static final float timeIncrement = 1/120f;//1/120: every half minute one frame
    private final float startTime;
    private final float endTime;
    private final int sleepTime;

    private PersonBehaviour pBehaviour = new PersonBehaviour();
    private float currentTime;

    public Day() {
        startTime = Main.getFirstOpen();
        endTime = Main.getLastClose();
        this.currentTime = startTime;
        this.sleepTime = 2 * 1000 / ((int)Math.ceil((endTime - startTime) / timeIncrement) + 1);
    }

    public void simulate() {
        // Initialize visualisation.
        // TODO: Move to club constructor in case of multiple clubs.
        Frame.Start();
        while(this.currentTime <= this.endTime) {
            // Create new states for people and clubs.
            this.createNewStates();

            // Simulate the current time.
            // TODO: Update club and people.
            PersonBehaviour.updatePersons();

            // Update visualisation.
            Frame.Update();

            // Sleep for a short while before beginning again.
            try {
                Thread.sleep(sleepTime);
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            // Set next time for simulation.
            this.currentTime = this.currentTime + timeIncrement;
        }
        // Ended simulation of day.
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