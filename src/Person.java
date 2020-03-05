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

    public void setMoneyToSpend(int x, int i){ moneyToSpend[i] = x; }
    int getMoneyToSpend(int i){ return moneyToSpend[i]; }
    void updateMoneyToSpend(int x, int i){ moneyToSpend[i]+=x; }
    void setHasAlcohol(Boolean x, int i){ hasAlcohol[i] = x; }
    void setDrinksConsumed(int x, int i){ drinksConsumed[i] +=x; }
    void setAlcoholTolerance(int x, int i){ alcoholTolerance[i] = x; }
    void setHappiness(int x, int i){ happiness[i] = x; }
    void setDanceAffinity(int x, int i){ danceAffinity[i] = x; }
    void setEnergy(float x, int i){ energy[i] = x; }
    float getEnergy(int i) { return energy[i]; }


}