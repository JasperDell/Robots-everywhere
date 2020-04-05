import java.util.*;
public class Variables {
    public static boolean makePositiontxt = true;
    public static boolean makeClubtxt = true;
    public static boolean makeTotalDrinksConsumedtxt = true;
    public static boolean makeDrinksConsumedtxt =  true;
    public static boolean makeEnergytxt = true;
    public static boolean makeHappinesstxt = true;

    public static final Random random = new Random();
    public static int amountOfPersons = 100;
    // Set person starting values
    //alcoholtolerance in units alcohol -> base this on research
    public static int initialAlcoholTolerance = (6+random.nextInt(10));
    public static int initialMoney = 5 + (random.nextInt(20)); //are there really people with no money?
    public static int sipsPerHour = random.nextInt(53) +7;//at least seven sips per hour, max a sip per minute
    public static float musicVolume = 0.5f; //between 0 and 1

    public static final boolean useVisualisation = true;
    //First bar, original one

    //0,1 is position 2,3 is sizex, sizey
    //1 unit is 3cm
    public static final int[] bar = new int[] { 20,220,60,160, 1 };
    public static final int[][] barStools = { {90, 270, 10, 10, 0}, {90, 300, 10, 10, 0}, {90, 330, 10, 10, 0} };
    public static final int[] danceFloor = { 150, 100, 200, 150, 0 };
    public static final int[] entrance = {20,60,20,20,0};
    //upper left next to dancefloor, at the bar, under the dancefloor
    //only last one gets used
    public static final int[][] standingPlaces = { {30, 30, 70, 150, 0},{90, 230, 40, 140, 0},{150, 270, 180, 90, 0} };


    //Bar bar: orignal bar but, bar 40 by 40 units bigger
    /*
    //0,1 is position 2,3 is sizex, sizey
    //1 unit is 3cm
    public static final int[] bar = new int[] { 20,180,100,200, 1 };
    public static final int[][] barStools = { {90, 270, 10, 10, 0}, {90, 300, 10, 10, 0}, {90, 330, 10, 10, 0} };
    public static final int[] danceFloor = { 150, 100, 200, 150, 0 };
    public static final int[] entrance = {20,60,20,20,0};
    //upper left next to dancefloor, at the bar, under the dancefloor
    //only last one gets used
    public static final int[][] standingPlaces = { {30, 30, 70, 150, 0},{90, 230, 40, 140, 0},{150, 270, 180, 90, 0} };
    */

    /*
    //Dance bar: original bar but dancefloor 50 by 50 units bigger
    //0,1 is position 2,3 is sizex, sizey
    //1 unit is 3cm
    public static final int[] bar = new int[] { 20,220,60,160, 1 };
    public static final int[][] barStools = { {90, 270, 10, 10, 0}, {90, 300, 10, 10, 0}, {90, 330, 10, 10, 0} };
    public static final int[] danceFloor = { 100, 100, 250, 200, 0 };
    public static final int[] entrance = {20,60,20,20,0};
    //upper left next to dancefloor, at the bar, under the dancefloor
    //only last one gets used
    public static final int[][] standingPlaces = { {30, 30, 70, 150, 0},{90, 230, 40, 140, 0},{150, 270, 180, 90, 0} };
    */

}
