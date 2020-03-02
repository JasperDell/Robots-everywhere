public class Person {
int moneyToSpend;
Boolean hasAlcohol = false;
int drinksConsumed = 0;
int alcoholTolerance;
int happiness;
int danceAffinity;
int energy;

void setMoneyToSpend(int x){
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
}
