import java.rmi.MarshalException;
import java.util.*;

public class PersonState {
    //!!!
    //anything added to this should also be added to clone()!!
    //if it isn't in clone than it doesn't get remembered by the person in the next state !!
    //!!!
    private final Person person; //associated (unchanging) person
    private final float time; //the timestamp of this state
    private Position position; //one unit is 3cm
    private Position goalPosition;
    private int moneySpend;
    private int amountOfAlcohol; //sips of alcohol in hand
    private int drinksConsumed; //units alcohol finished
    private float energy; //abstract representation energy -> between 1 and 0;
    private State state; //the state this person is in.
    private float lastSipTime; //last timestamp this person took a sip
    private float drinkPouringTimeLeft; //time this person has to wait for their drink


    public PersonState(Person person, float time) {
         this.person = person;
         this.time = time;
         //make enter state, put person in enter state per default
         this.state = OutsideState.getInstance();
     }

    public PersonState clone(float time){
        PersonState ps = new PersonState(this.person, time);
        ps.position = this.position.clone();
        ps.goalPosition = this.goalPosition.clone();
        ps.moneySpend = this.moneySpend;
        ps.amountOfAlcohol = this.amountOfAlcohol;
        ps.drinksConsumed = this.drinksConsumed;
        ps.energy = this.energy;
        ps.state = this.state;
        ps.lastSipTime = this.lastSipTime;
        ps.drinkPouringTimeLeft = this.drinkPouringTimeLeft;
        return ps;
    }

    ////////////
    /////some less intuitive getters and setters
    /////////////
    public void buyNewDrink(int drinkCost, int sipsOfAlcohol){
        this.amountOfAlcohol += sipsOfAlcohol;
        addMoneySpend(drinkCost);
    }

    public void takeSip() {
        this.amountOfAlcohol--;
        if(!this.hasAlcohol())
            drinksConsumed++;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        if(energy >1){
            this.energy = 1;
        } else if (energy < 0){
            this.energy = 0;
        } else {
            this.energy = energy;
        }
    }

    public void addToEnergy(float energy) {
        this.energy += energy;
        if(this.energy >1){
            this.energy = 1;
        } else if (this.energy < 0){
            this.energy = 0;
        }
    }

    ////////////
    /////from here on just normal getters, setters and addTos
    /////////////
    public Person getPerson() {
        return person;
    }

    public float getTime() {
        return time;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }

    public int getMoneySpend() {
        return moneySpend;
    }

    private void addMoneySpend(int moneySpend) {
        this.moneySpend += moneySpend;
    }

    public int getDrinksConsumed() {
        return drinksConsumed;
    }

    public boolean hasAlcohol() {
         return amountOfAlcohol > 0;
    }

    public int getSpendableMoney() {
        return this.person.getMoney() - this.moneySpend;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public float getLastSipTime() {
        return lastSipTime;
    }

    public void setLastSipTime(float lastSipTime) {
        this.lastSipTime = lastSipTime;
    }

    public float getDrinkPouringTimeLeft() {
        return drinkPouringTimeLeft;
    }

    public void addToDrinkPouringTimeLeft(float drinkPouringTimeLeft) {
        this.drinkPouringTimeLeft += drinkPouringTimeLeft;
    }

    public void setDrinkPouringTimeLeft(float drinkPouringTimeLeft) {
        this.drinkPouringTimeLeft = drinkPouringTimeLeft;
    }
}
