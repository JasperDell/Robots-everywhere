public class PersonState {
    private final Person person;
    private final float time;

    private Position position;
    private Position goalPosition;
    private int moneySpend;
    private int likenessToDrink;
    private int amountOfAlcohol;
    private int drinksConsumed;
    private int happiness;
    private float energy;
    private boolean isDancing;
    private boolean hasJoinedClubThisState;
    private boolean hasLeftClubThisState;
    private State state;

    public PersonState(Person person, float time) {
         this.person = person;
         this.time = time;
         //make enterstate
         this.state = DancingState.getInstance();
     }

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

    public int getLikenessToDrink() {
        return likenessToDrink;
    }

    public void setLikenessToDrink(int likenessToDrink) {
        this.likenessToDrink = likenessToDrink;
    }

    public int getAmountOfAlcohol() {
        return amountOfAlcohol;
    }

    public void buyNewDrink(int drinkCost, int sipsOfAlcohol){
        this.amountOfAlcohol += sipsOfAlcohol;
        addMoneySpend(drinkCost);
    }

    public int getDrinksConsumed() {
        return drinksConsumed;
    }
    public void incrementDrinksConsumed() {
        drinksConsumed++;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public void addToEnergy(float energy) {
        this.energy += energy;
    }

    public boolean isDancing() {
        return isDancing;
    }

    public void setDancing(boolean dancing) {
        isDancing = dancing;
    }

    public boolean hasAlcohol() {
         return amountOfAlcohol > 0;
    }

    public void takeSip() {
         this.amountOfAlcohol--;
         if(!this.hasAlcohol())
             drinksConsumed++;
    }

    public int getSpendableMoney() {
        return this.person.getMoney() - this.moneySpend;
    }

    public PersonState clone(float time){
         PersonState ps = new PersonState(this.person, time);
         ps.position = this.position.clone();
         ps.goalPosition = this.goalPosition.clone();
         ps.moneySpend = this.moneySpend;
         ps.likenessToDrink = this.likenessToDrink;
         ps.amountOfAlcohol = this.amountOfAlcohol;
         ps.drinksConsumed = this.drinksConsumed;
         ps.happiness = this.happiness;
         ps.energy = this.energy;
         ps.isDancing = this.isDancing;
         ps.state = this.state;
         return ps;
    }

    public boolean isHasJoinedClubThisState() {
        return hasJoinedClubThisState;
    }

    public void setHasJoinedClubThisState(boolean hasJoinedClubThisState) {
        this.hasJoinedClubThisState = hasJoinedClubThisState;
    }

    public boolean isHasLeftClubThisState() {
        return hasLeftClubThisState;
    }

    public void setHasLeftClubThisState(boolean hasLeftClubThisState) {
        this.hasLeftClubThisState = hasLeftClubThisState;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
