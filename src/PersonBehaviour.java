public class PersonBehaviour {
    public static void updatePersons() {
        for (Person p : Main.people) {
            State state = p.getCurrentState().getState();
            state.updatePerson(p.getCurrentState());
        }
    }
}