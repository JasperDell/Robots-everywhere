import java.util.List;

public class PersonBehaviour {

    List<Person> updatePerson (List<Person> crowd, int curStep) {
        for(Person p : crowd) {
            p.setArrayPointer(curStep+1);
            //System.out.println(curStep + ": " + p.getEnergy(curStep));
            p.setEnergy(p.getEnergy(curStep) - 1);
        }
        return crowd;
    }
}
