public class Person {
    String name;
    int index; // All persons have different indexes in order to differentiate them
    int gender; // 0>male, 1>female

    int steps;

    float[][] position;
    int[] moneyToSpend;
    float[] hasAlcohol;
    int[] drinksConsumed;
    int[] alcoholTolerance;
    int[] happiness;
    int[] danceAffinity;
    float[] energy;

    // Calculation variables
    float[] goalPosition;
    int prevGoal;

    // Necessary to bring OnLocation.steps over here: determines size of all arrays
    private Main main;
    public Person (Main x) {
        main = x;

        steps = main.location.steps;
        position = new float[steps][2];
        moneyToSpend = new int[steps];
        hasAlcohol = new float[steps];
        drinksConsumed = new int[steps];
        alcoholTolerance = new int[steps];
        happiness = new int[steps];
        danceAffinity = new int[steps];
        energy = new float[steps];

        goalPosition = new float[2];
        prevGoal = -1;
    }


    void setName (String x) {name = x; }
    String getName () { return name; }
    void setIndex (int x) {index = x; }
    int getIndex () {return index; }
    void setGender(int x) { gender = x; }
    int getGender() {return gender; }

    int arrayPointer = 0;
    void setArrayPointer(int i){
        arrayPointer = i;
    }

    public void setPosition(float[] x) { position[arrayPointer] = x; }
    public float[] getPosition(int i){return position[i]; }
    public float[] getPosition(){return position[arrayPointer]; }
    public void setMoneyToSpend(int x){ moneyToSpend[arrayPointer] = x; }
    int getMoneyToSpend(){ return moneyToSpend[arrayPointer]; }
    int getMoneyToSpend(int x){ return moneyToSpend[x]; }
    void updateMoneyToSpend(int x){ moneyToSpend[arrayPointer]+=x; }
    void setHasAlcohol(float x){ hasAlcohol[arrayPointer] = x; }
    float getHasAlcohol(){ return hasAlcohol[arrayPointer]; }
    float getHasAlcohol(int i){ return hasAlcohol[i]; }
    void setDrinksConsumed(int x){ drinksConsumed[arrayPointer] +=x; }
    int getDrinksConsumed(){ return drinksConsumed[arrayPointer]; }
    void setAlcoholTolerance(int x){ alcoholTolerance[arrayPointer] = x; }
    void setHappiness(int x){ happiness[arrayPointer] = x; }
    void setDanceAffinity(int x){ danceAffinity[arrayPointer] = x; }
    void setEnergy(float x){ energy[arrayPointer] = x; }
    float getEnergy(int i) { return energy[i]; }
    float getEnergy() { return energy[arrayPointer]; }

    public void setGoalPosition(float[] x) { goalPosition = x; }
    public float[] getGoalPosition(){return goalPosition; }
    public void setPrevGoal(int x) { prevGoal = x; }
    public int getPrevGoal() { return prevGoal; }


}