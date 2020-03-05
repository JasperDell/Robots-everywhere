import java.util.List;

public class PersonBehaviour {

    List<Person> updatePerson (List<Person> crowd, int curStep) {
        for(Person p : crowd) {
            //System.out.println(curStep + ": " + p.getEnergy(curStep));
            p.setEnergy(p.getEnergy(curStep) - 1, curStep + 1);
        }
        return crowd;
    }
}
