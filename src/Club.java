import java.util.List;
import java.util.ArrayList;

public class Club {
    int steps;

    // Bar objects (0-3 are position and size, 4 is collision object: 0=not, 1=collision
    public static int[] bar = new int[] {20,220,60,160, 1};
    public static int[][] barStools = {{90, 270, 10, 10, 0},{90, 300, 10, 10, 0}, {90, 330, 10, 10, 0}};
    public static int[] danceFloor = {150, 100, 200, 150, 0};
    public static int[][] barObjects = {bar,danceFloor, barStools[0],barStools[1],barStools[2]};

    int[] totalMoneySpend;
    int[] numberOfPeople;
    int[] numberOfPeopleDancing;
    int[] musicVolume;
    List<Person>[] crowd;

    // Necessary to bring OnLocation.steps over here: determines size of all arrays
    private Main main;
    public Club (Main x) {
        main = x;

        steps = main.location.steps;
        totalMoneySpend = new int[steps];
        numberOfPeople = new int[steps];
        numberOfPeopleDancing = new int[steps];
        musicVolume  = new int[steps];
        crowd = new ArrayList[steps];
        for (int i = 0; i < steps; i++) { crowd[i] = new ArrayList<>(); }
    }

    void setTotalMoneySpend(int x, int i){ totalMoneySpend[i] = x; }
    void updateTotalMoneySpend(int x, int i){ totalMoneySpend[i] += x; }
    void setNumberOfPeople(int x, int i){ numberOfPeople[i] = x; }
    void updateNumberOfPeople(int x, int i){ numberOfPeople[i]+=x; }
    void setNumberOfPeopleDancing(int x, int i){ numberOfPeopleDancing[i] = x; }
    void updateNumberOfPeopleDancing(int x, int i){ numberOfPeopleDancing[i] += x; }
    void setMusicVolume(int x, int i){ musicVolume[i] = x; }
    void updateMusicVolume(int x, int i){ musicVolume[i] += x; }
}