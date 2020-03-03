public class OnLocation {

    private Main main;
    public OnLocation (Main x) {
        main = x;
    }

    private float time;
    private float openTime = 18;
    private float closeTime = 2;
    private float timeIncrement = 0.5f;

    // Initialize the bar environment, its employees
    public void InitDay () {
        time = openTime;
    }

    public int UpdateDay () {
        while (time < 24 + closeTime) {

            //TODO: this is the main body of the simulation: any individual or inter-agent logic should be here



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
