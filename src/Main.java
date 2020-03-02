import java.util.*;

public class Main {
    Random random = new Random();

    public void main(){
        int groupSize = 10;
        Person[] group = new Person[groupSize];
        initializeGroup(group);
        printSpendableMoney(group);
    }

    void initializeGroup(Person[] group){
        for (Person person: group){
            int x = random.nextInt(20);
            person.setMoneyToSpend(x);
        }
    }
    void printSpendableMoney(Person[] group){
        int totalMoney = 0;
        for (Person person: group){
            totalMoney+=person.getMoneyToSpend();
        }
        System.out.println(totalMoney);
    }

    public static void main(String[] args) {
        (new Main()).main();
    }
}