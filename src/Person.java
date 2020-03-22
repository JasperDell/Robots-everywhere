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

    // Necessary to bring OnLocation.steps over here: determines size of all arrays
    private Main main;
    public Person (Main x, int index, int gender, String name) {
        main = x;
        this.index = index;
        this.name = name;
        this.gender = gender;

        steps = main.location.steps;
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

    int getLikenessToDrink(){return likenessToDrink;}

    void setHasAlcohol(int x){ hasAlcohol[arrayPointer] = x; }
    int getHasAlcohol(){ return hasAlcohol[arrayPointer]; }
    int getHasAlcohol(int i){ return hasAlcohol[i]; }

    void updateDrinksConsumed(int x){ drinksConsumed[arrayPointer] +=x; }
    void setDrinksConsumed(int x){drinksConsumed[arrayPointer] = x;}
    int getDrinksConsumed(){ return drinksConsumed[arrayPointer]; }
    int getDrinksConsumed(int i){ return drinksConsumed[i]; }

    void setAlcoholTolerance(int x){ alcoholTolerance[arrayPointer] = x; }

    void setHappiness(int x){ happiness[arrayPointer] = x; }
    int getHappiness(int x){return happiness[x];}


    void setDanceAffinity(int x){ danceAffinity[arrayPointer] = x; }

    void setEnergy(float x){ energy[arrayPointer] = x; }
    float getEnergy(int i) { return energy[i]; }
    float getEnergy() { return energy[arrayPointer]; }

    public void setGoalPosition(float[] x) { goalPosition = x; }
    public float[] getGoalPosition(){return goalPosition; }
    public void setPrevGoal(int x) { prevGoal = x; }
    public int getPrevGoal() { return prevGoal; }
}