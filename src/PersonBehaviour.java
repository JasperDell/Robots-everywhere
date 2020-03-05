import java.util.List;

public class PersonBehaviour {

    List<Person> updatePerson (List<Person> crowd) {
        for(Person p : crowd) {
            p.energy--;
        }
        return crowd;
    }
}
