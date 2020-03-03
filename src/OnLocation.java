public class OnLocation {

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

            time += timeIncrement;
        }
        return 0;
    }

    public void RoundUpDay () {
        // Might not be necessary when
    }
}
