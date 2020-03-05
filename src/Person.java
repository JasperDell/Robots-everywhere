public class Person {
    String name;
    int index; // All persons have different indexes in order to differentiate them
    int gender; // 0>male, 1>female

    int steps;

    int[] moneyToSpend;
    Boolean[] hasAlcohol;
    int[] drinksConsumed;
    int[] alcoholTolerance;
    int[] happiness;
    int[] danceAffinity;
    float[] energy;

    // Necessary to bring OnLocation.steps over here: determines size of all arrays
    private Main main;
    public Person (Main x) {
        main = x;

        steps = main.location.steps;
        moneyToSpend = new int[steps];
        hasAlcohol = new Boolean[steps];
        drinksConsumed = new int[steps];
        alcoholTolerance = new int[steps];
        happiness = new int[steps];
        danceAffinity = new int[steps];
        energy = new float[steps];
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

    public void setMoneyToSpend(int x){ moneyToSpend[arrayPointer] = x; }
    int getMoneyToSpend(){ return moneyToSpend[arrayPointer]; }
    void updateMoneyToSpend(int x){ moneyToSpend[arrayPointer]+=x; }
    void setHasAlcohol(Boolean x){ hasAlcohol[arrayPointer] = x; }
    void setDrinksConsumed(int x){ drinksConsumed[arrayPointer] +=x; }
    void setAlcoholTolerance(int x){ alcoholTolerance[arrayPointer] = x; }
    void setHappiness(int x){ happiness[arrayPointer] = x; }
    void setDanceAffinity(int x){ danceAffinity[arrayPointer] = x; }
    void setEnergy(float x){ energy[arrayPointer] = x; }
    float getEnergy(int i) { return energy[i]; }


}