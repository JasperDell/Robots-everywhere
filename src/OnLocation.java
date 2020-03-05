public class OnLocation {

    private PersonBehaviour pBehaviour = new PersonBehaviour();
    private Main main;
    public OnLocation (Main x) {
        main = x;
    }

    private float time;
    private float openTime = 18;
    private float closeTime = 26; // continue counting after 24
    private float timeIncrement = 0.5f;
    public int steps = (int)((closeTime - openTime) / timeIncrement) + 1; // + 1 to have data of both start and end
    public int curStep = 1;

    // Initialize the bar environment, its employees
    public void InitDay () {
        time = openTime;
    }

    public int UpdateDay () {
        // Necessary to ignore one step: start of the day has already been defined
        time += timeIncrement;
        // Continue the simulation until closing time
        while (time <= closeTime) {

            //TODO: this is the main body of the simulation: any individual or inter-agent logic should be here
            main.club.crowd[curStep] = pBehaviour.updatePerson(main.club.crowd[curStep - 1]);

            curStep++;
            System.out.println(time);
            time += timeIncrement;
        }
        return 0;
    }

    public void RoundUpDay () {
        System.out.println("[TODO] end of simulation!");
        // Finish the day (send everyone home, prepare final values)
        main.LogInfo();
    }
}