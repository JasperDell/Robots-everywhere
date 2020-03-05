import java.util.List;
import java.util.*;

public class PersonBehaviour {
    Random random = new Random();
    List<Person> updatePerson (List<Person> crowd) {
        for(Person p : crowd) {
            p.energy--;
            int money = p.getMoneyToSpend();
            if (money>=1){
                if (random.nextBoolean()){
                    p.updateDrinksConsumed(1);
                    p.updateMoneyToSpend(-1);
                }
            }
        }
        return crowd;
    }
}
