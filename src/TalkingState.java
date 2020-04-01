import java.util.*;

public class TalkingState extends State {
    Random random = new Random();
    /////////
    //singleton stuff
    ////////
    private static TalkingState instance;
    private TalkingState(){};
    public static TalkingState getInstance() {
        if (instance == null)
            instance = new TalkingState();
        return instance;
    }

    @Override
    public void takeAction(PersonState ps) {
        setGoalPosition(ps);
        moveToGoalPosition(ps);
        ps.addToEnergy(2*((Main.getLastClose()-Main.getFirstOpen())*Day.timeIncrementInHours)/100);
        //if we have alcohol and are at the talking state
        float leastTimeToTakeNextSip = ps.getLastSipTime() + 2f/(ps.getPerson().getSipsPerHour()); //every half minute
        boolean canTakeNextSip = ps.getTime() >= leastTimeToTakeNextSip;
        boolean notWalking = ps.getPosition().equals(ps.getGoalPosition());
        if (canTakeNextSip && ps.hasAlcohol() && notWalking) {
            ps.takeSip();
            ps.setLastSipTime(ps.getTime());
        }
    }

    @Override
    public void moveNextState(PersonState ps) {
        if (!ps.isWantingToBeAtClub()){
            ps.setState(OutsideState.getInstance());
            return;
        }
        if (ps.getEnergy()>0.60){
            ps.setState(DancingState.getInstance());
        } else {
            int likelinessToGoGrabADrink = 0;//out of 100
            //if has no alcohol and has money, then create a chance to go get a drink
            if (!ps.hasAlcohol() && ps.getSpendableMoney() > 0) {
                //if has no alcohol average of 75 percent chance to go get drinks
                likelinessToGoGrabADrink += 60;
                likelinessToGoGrabADrink += random.nextInt(30);
            }

            //note if likeliness is 0, then chance is also 0, if likeliness is 100 then chance is also 100
            if (likelinessToGoGrabADrink > random.nextInt(100)) {
                //set Nextstate to be drinking!!!
                ps.setState(GoToBarState.getInstance());
            } else {
                //set Nextstate to be
                ps.setState(this);
            }
        }
    }

    @Override
    public void setGoalPosition(PersonState ps) {
        if (isAtTargetBarObject(ps.getGoalPosition(), 0)) {
            //if at that goal
            if (ps.getGoalPosition().equals(ps.getPosition())) {
                //were dancing so move a little
                Position targetPosition = ps.getPosition().clone();
                if (Main.random.nextBoolean()) {//move up
                    targetPosition.addToX(1);
                } else { //or down
                    targetPosition.addToX(-1);
                }

                if (Main.random.nextBoolean()) {//move up
                    targetPosition.addToY(1);
                } else { //or down
                    targetPosition.addToY(-1);
                }

                if (isAtTargetBarObject(targetPosition, 0)) {
                    ps.setGoalPosition(targetPosition);
                }

            } //else just keep going to that goal
            //if we dont have a goal on the dancefloor, set a new goal on the dancefloor
        }  else {
            float x = Main.clubs.get(0).getBarObjects()[7][0] + Main.random.nextInt(Main.clubs.get(0).getBarObjects()[7][2]);
            float y = Main.clubs.get(0).getBarObjects()[7][1] + Main.random.nextInt(Main.clubs.get(0).getBarObjects()[7][3]);
            ps.setGoalPosition(new Position(x, y));
        }
        }

    @Override
    public int[] getTargetBarObject() {
        return Main.clubs.get(0).standingPlaces[2];
    }
}
