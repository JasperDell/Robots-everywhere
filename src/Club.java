import java.util.ArrayList;
import java.util.List;
//see Person, for how this all works
//same idea just for the club
//you can currently just get to the club with Main.clubs.get(0)
//that should be changed if we for some reason decide to implement multiple clubs

public class Club {
    public final float openTime = 18;
    public final float closeTime = 28; // continue counting after 24


    // Bar objects (0-3 are position and size, 4 is collision object: 0=not, 1=collision
    //note to do this properly this needs getters as the values of the array can be changed
    //0,1 is position 2,3 is sizex, sizey
    //1 unit is 3cm
    public final int[] bar = new int[] { 20,220,60,160, 1 };
    public final int[][] barStools = { {90, 270, 10, 10, 0}, {90, 300, 10, 10, 0}, {90, 330, 10, 10, 0} };
    public final int[] danceFloor = { 150, 100, 200, 150, 0 };
    public final int[] entrance = {20,60,20,20,0};
    //upper left next to dancefloor, at the bar, under the dancefloor
    public final int[][] standingPlaces = { {30, 30, 70, 150, 0},{90, 230, 40, 140, 0},{150, 270, 180, 90, 0} };
    public final int[][] barObjects = { bar, danceFloor, barStools[0], barStools[1], barStools[2], standingPlaces[0], standingPlaces[1], standingPlaces[2] };

    private List<ClubState> states;
    private ClubState currentState;

    public Club() {
        this.states = new ArrayList<>();
        this.newState(0);
    }

    public ClubState newState(float time) {
        ClubState state;
        if(this.currentState == null)
            state = new ClubState(this, time);
        else
            state = currentState.clone(time);
        this.states.add(state);
        this.currentState = state;
        return state;
    }

    public int getMusicVolume() {
        return this.currentState.getMusicVolume();
    }

    public void setMusicVolume(int musicVolume) {
        this.currentState.setMusicVolume(musicVolume);
    }

    public int getMoneySpendCurrentDay() {
        return this.currentState.getMoneySpend();
    }

    public int getNumberOfPeople() {
        return this.currentState.getNumberOfPeople();
    }
    public int getNumberOfPeople(int i) {return this.states.get(i).getNumberOfPeople();}

    public int getNumberOfPeopleDancing() {
        return this.currentState.getNumberOfPeopleDancing();
    }
    public int getNumberOfPeopleDancing(int i) {return this.states.get(i).getNumberOfPeopleDancing();}

    public int getTotalMoneySpend(){
        return this.currentState.getMoneySpend();
    }

    public int getTotalMoneySpend(int uptillhere){
        return states.get(uptillhere).getMoneySpend();
    }

    ClubState getCurrentState() {
        return this.currentState;
    }

    public int[] getBar() {
        return bar;
    }

    public int[][] getBarObjects() {
        return barObjects;
    }
}