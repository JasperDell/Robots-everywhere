import java.util.ArrayList;
import java.util.List;

//see PersonState for how all of this works
//same idea but then for clubs
public class ClubState {
    private final Club club;
    private final float time;

    private float musicVolume;
    private List<Person> crowd;

    public ClubState(Club club, float time){
        this.club = club;
        this.time = time;
        this.crowd = new ArrayList<>();
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        if (!(musicVolume > 1 || musicVolume < 0)) {
            this.musicVolume = musicVolume;
        }
    }

    public int getMoneySpend() {
        int total = 0;
        for(Person person : Main.people) {
            //get the amount of money spend until this time
            total += person.getStateForTime(time).getMoneySpend();
        }
        return total;
    }

    public int getTotalDrinksConsumed() {
        int total = 0;
        for(Person person : Main.people) {
            //get the amount of money spend until this time
            total += person.getStateForTime(time).getDrinksConsumed();
        }
        return total;
    }

    public int getNumberOfPeople() {
        return this.crowd.size();
    }

    public int getNumberOfPeopleDancing() {
        int total = 0;
        for(Person person : this.crowd) {
            if(person.getStateForTime(time).getState() instanceof DancingState)
                total++;
        }
        return total;
    }

    public void enter(Person person) {
        person.getCurrentState().setPosition(new Position(club.entrance[0], club.entrance[1]));
        this.crowd.add(person);
    }

    public void leave(Person person) {
        this.crowd.remove(person);
    }

    public ClubState clone(float time){
        ClubState cs = new ClubState(this.club, time);
        cs.musicVolume = this.musicVolume;
        cs.crowd = new ArrayList<>(this.crowd);
        return cs;
    }

    public List<Person> getCrowd(){
        return crowd;
    }
}
