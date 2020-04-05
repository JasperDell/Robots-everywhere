import java.util.*;
public class Variables {
    public static final Random random = new Random();
   public static int amountOfPersons = 100;
    // Set person starting values
    //alcoholtolerance in units alcohol -> base this on research
    public static int initialAlcoholTolerance = (6+random.nextInt(10));
    public static int danceAffinity = random.nextInt(20);
    public static int initialMoney = (random.nextInt(20)); //are there really people with no money?
    public static int initialLikenessToDrink= (70); // value between 0 and 100
    public static int initialHappiness = (50 + random.nextInt(26)); // value between 50 and 75
    public static float energy = 1f;
    public static int sipsPerHour = random.nextInt(53) +7;//at least seven sips per hour, max a sip per minute

    public static float musicVolume = 0.5f;

}
