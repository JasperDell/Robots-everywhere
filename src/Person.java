public class Person {
    final String name;
    final int index; // All persons have different indexes in order to differentiate them
    final int gender; // 0>male, 1>female
    final int steps;

    int likenessToDrink;

    float[][] position;
    int[] moneyToSpend;
    int[] hasAlcohol;
    int[] drinksConsumed;
    int[] alcoholTolerance;
    int[] happiness;
    int[] danceAffinity;
    float[] energy;

    // Calculation variables
    float[] goalPosition;
    int prevGoal;

    public Person (int index, int gender, String name) {
        this.index = index;
        this.name = name;
        this.gender = gender;

        steps = Day.getSteps();
        position = new float[steps][2];
        moneyToSpend = new int[steps];
        hasAlcohol = new int[steps];
        drinksConsumed = new int[steps];
        alcoholTolerance = new int[steps];
        happiness = new int[steps];
        danceAffinity = new int[steps];
        energy = new float[steps];
        goalPosition = new float[2];
        prevGoal = -1;
    }

    String getName () { return name; }
    int getIndex () {return index; }
    int getGender() {return gender; }
    void setLikenessToDrink(int x) {likenessToDrink = x;}
    int arrayPointer = 0;

    public void setNewPosition(float[] x) { position[Day.getCurStep()] = x; }
    public float[] getLastPosition(){return position[Day.getLastStep()]; }
    public float[] getPosition(int i){return position[i]; }

    public void setNewMoneyToSpend(int x){ moneyToSpend[Day.getCurStep()] = x; }
    int getLastMoneyToSpend(){ return moneyToSpend[Day.getLastStep()]; }
    void addToNewMoneyToSpend(int x){ moneyToSpend[Day.getCurStep()]+=x; }

    int getLikenessToDrink(){return likenessToDrink;}

    void setNewHasAlcohol(int x){ hasAlcohol[Day.getCurStep()] = x; }
    int getLastHasAlcohol(){ return hasAlcohol[Day.getLastStep()]; }

    void addToNewDrinksConsumed(int x){ drinksConsumed[Day.getCurStep()] +=x; }
    void setNewDrinksConsumed(int x){drinksConsumed[Day.getCurStep()] = x;}
    int getLastDrinksConsumed(){ return drinksConsumed[Day.getLastStep()]; }

    void setNewAlcoholTolerance(int x){ alcoholTolerance[Day.getCurStep()] = x; }

    void setNewHappiness(int x){ happiness[Day.getCurStep()] = x; }
    int getLastHappiness(){return happiness[Day.getLastStep()];}


    void setDanceAffinity(int x){ danceAffinity[Day.getCurStep()] = x; }

    void setNewEnergy(float x){ energy[Day.getCurStep()] = x; }
    float getLastEnergy() { return energy[Day.getLastStep()]; }

    public void setGoalPosition(float[] x) { goalPosition = x; }
    public float[] getGoalPosition(){return goalPosition; }
    public void setPrevGoal(int x) { prevGoal = x; }
    public int getPrevGoal() { return prevGoal; }
}