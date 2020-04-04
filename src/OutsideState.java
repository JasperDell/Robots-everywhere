public class OutsideState extends State {

    /////////
    //singleton stuff
    ////////
    private static OutsideState instance;
    private OutsideState(){};
    public static OutsideState getInstance() {
        if (instance == null)
            instance = new OutsideState();
        return instance;
    }

    @Override
    public void takeAction(PersonState ps) {
        //when in state the person could not yet be outside, so move them there
        setGoalPosition(ps);
        if (!ps.getGoalPosition().equals(ps.getPosition())){
            moveToGoalPosition(ps);
        }
    }

    @Override
    public void moveNextState(PersonState ps) {
        //if time is big enough to enter the club, enter the club

        if(ps.isWantingToBeAtClub() && ps.getPerson().getArrivalTime() <= Main.days.get(0).currentTime){
            ps.setPosition(new Position (getTargetBarObject()[0], getTargetBarObject()[1])); //put em at the entrance
            ps.getPerson().enterClub(Main.clubs.get(0));
            ps.setState(DancingState.getInstance());
            return;
        } else {
            ps.setState(this);
            return;
        }
    }

    @Override
    public void setGoalPosition(PersonState ps) {
        //goal is exit, exit is next to entrance but lower
        ps.setGoalPosition(new Position (getTargetBarObject()[0], getTargetBarObject()[1] + 40));
    }

    @Override
    public int[] getTargetBarObject() {
        //when entering club, position gets automatically set to the club entrance
        return Main.clubs.get(0).entrance;
    }
}
