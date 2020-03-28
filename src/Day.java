public class Day {

    private PersonBehaviour pBehaviour = new PersonBehaviour();
    public Day() {
    }

    private static float simulationTime = 2;
    public static float time;
    private static float openTime = Club.openTime;
    private static float closeTime = Club.closeTime;
    private static float timeIncrement = Club.timeIncrement;
    private static int steps = (int)Math.ceil((closeTime - openTime) / timeIncrement) + 1; // + 1 to have data of both start and end
    private static int curStep = 1;
    private static int lastStep = 0;

    // Initialize the bar environment, its employees
    public void InitDay () {
        time = openTime;
        //also initialize all people position[0] here etc;
    }

    public int UpdateDay () {
        // Necessary to ignore one step: start of the day has already been defined
        time += timeIncrement;
        //TEST VISUALISATION
        Frame.Start();

        // Continue the simulation until closing time
        while (time <= closeTime) {

            //TODO: this is the main body of the simulation: any individual or inter-agent logic should be here
            Club.crowd[Day.curStep] = pBehaviour.updatePersons(Club.crowd[curStep - 1]);

            // Visualisation
            Frame.Update();
            curStep++;
            lastStep++;
            System.out.println(time);
            time += timeIncrement;

            // Sleep for a short while
            try { Thread.sleep((int)(simulationTime * 1000 / steps)); }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        return 0;
    }

    public void RoundUpDay () {
        System.out.println("[TODO] end of simulation!");
        Main.LogInfo();
    }

    public static int getSteps() {
        return steps;
    }
    public static int getCurStep() {
        return curStep;
    }
    public static int getLastStep() {
        return lastStep;
    }
}