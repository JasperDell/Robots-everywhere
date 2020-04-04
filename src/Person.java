import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//a person does not change over time,
//instead it can set the initial values of its state
//and it keeps track of how that state changes over time
public class Person {
    private final int id;
    private final String name;
    private final Gender gender;
    private final int alcoholTolerance;
    private final int danceAffinity;
    private final int money;
    private final int initialLikenessToDrink;
    private final int initialHappiness;
    private final float initialEnergy;
    private final float sipsPerHour;
    private final float arrivalTime;

    private List<PersonState> states;
    private PersonState currentState;

    public Person(int id, String name, Gender gender, int alcoholTolerance, int danceAffinity, int money, int initalLikenessToDrink, int initialHappiness, float initialEnergy, float sipsPerHour, float arrivalTime) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.alcoholTolerance = alcoholTolerance;
        this.danceAffinity = danceAffinity;
        this.money = money;
        this.states = new ArrayList<>();
        this.initialLikenessToDrink = initalLikenessToDrink;
        this.initialHappiness = initialHappiness;
        this.initialEnergy = initialEnergy;
        this.sipsPerHour = sipsPerHour;
        this.arrivalTime = arrivalTime;
        this.newState(0);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAlcoholTolerance() {
        return alcoholTolerance;
    }

    public int getDanceAffinity() {
        return danceAffinity;
    }

    public int getMoney() {
        return money;
    }

    public PersonState getCurrentState() {
        return currentState;
    }

    public PersonState newState(float time) {
        PersonState state;
        if(this.currentState == null) {
            //Initialize new state based on initial unchanging values
            state = new PersonState(this, time);
            state.setPosition(new Position());
            state.setGoalPosition(new Position());
            //private int moneySpend => default 0
            //private int amountOfAlcohol => default 0
            //private int drinksConsumed => default 0
            state.setEnergy(initialEnergy);
            //private boolean isDancing=> default 0
        }
        else {
            state = currentState.clone(time);
        }
        this.states.add(state);
        this.currentState = state;
        return state;
    }

    public PersonState getStateForTime(float time) {
        for (PersonState state : states) {
            if(state.getTime() == time)
                return state;
        }
        return null;
    }

    public void enterClub(Club club) {
        club.getCurrentState().enter(this);
        //TODO: make sure position gets set to entrance club
    }

    public void leaveClub(Club club) {
        club.getCurrentState().leave(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        // self check
        if (this == obj)
            return true;
        // null check
        if (obj == null)
            return false;
        // type check and cast
        if (getClass() != obj.getClass())
            return false;
        Person person = (Person) obj;
        return this.id == person.id;
    }

    public List<PersonState> getStates(){
        return states;
    }

    public float getSipsPerHour() {
        float musicModified = Main.clubs.get(0).getMusicVolumeBasedDrinkSpeedModifier() * sipsPerHour;
        if (musicModified > 60){
            return 60;//cap at a sip per minute;
        }
        return musicModified;
    }

    public float getArrivalTime() {
        return arrivalTime;
    }
}