import java.util.ArrayList;
import java.util.List;

//see PersonState for how all of this works
//same idea but then for clubs
public class ClubState {
    private final Club club;
    private final float time;

    private int musicVolume;
    private List<Person> crowd;

    public ClubState(Club club, float time){
        this.club = club;
        this.time = time;
        this.crowd = new ArrayList<>();
    }

    public int getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(int musicVolume) {
        this.musicVolume = musicVolume;
    }

    public int getMoneySpend() {
        int total = 0;
        for(Person person : this.crowd) {
            total += person.getStateForTime(time).getMoneySpend();
        }
        return total;
    }

    public int getNumberOfPeople() {
        return this.crowd.size();
    }

    public int getNumberOfPeopleDancing() {
        int total = 0;
        for(Person person : this.crowd) {
            if(person.getStateForTime(time).isDancing())
                total++;
        }
        return total;
    }

    public void enter(Person person) {
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
