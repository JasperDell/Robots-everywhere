public class Person {
    String name;
    int index; // All persons have different indexes in order to differentiate them

    int moneyToSpend;
    Boolean hasAlcohol = false;
    int drinksConsumed = 0;
    int alcoholTolerance;
    int happiness;
    int danceAffinity;
    float energy;

    int gender; // 0>male, 1>female

    void setName (String x) {name = x; }
    String getName () { return name; }
    void setIndex (int x) {index = x; }
    int getIndex () {return index; }
    void setGender(int x) { gender = x; }
    int getGender() {return gender; }

    public void setMoneyToSpend(int x){
        moneyToSpend = x;
    }
    int getMoneyToSpend(){
        return moneyToSpend;
    }
    void updateMoneyToSpend(int x){
        moneyToSpend+=x;
    }
    void setHasAlcohol(Boolean x){
        hasAlcohol = x;
    }
    void updateDrinksConsumed(int x){
        drinksConsumed+=x;
    }
    int getDrinksConsumed(){ return drinksConsumed;}
    void setAlcoholTolerance(int x){
        alcoholTolerance = x;
    }
    void setHappiness(int x){
        happiness = x;
    }
    void setDanceAffinity(int x){
        danceAffinity = x;
    }
    void setEnergy(int x){
        energy = x;
    }
    float getEnergy() {return energy; }


}