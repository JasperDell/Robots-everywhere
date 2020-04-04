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
        //spend 10 percent of energy per hour, aka be able to stay talking for 10 hours
        //however when entering this state were at 30 percent, so effectively 3 hours
        ps.addToEnergy(-1* (10f/100f * Day.timeIncrementInHours));
        //if we have alcohol and are at the talking state
        boolean notWalking = ps.getPosition().equals(ps.getGoalPosition());
        if (notWalking) {
            ps.takeSip();
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
            return;
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
                return;
            }
        }
        ps.setState(this);
    }

    @Override
    public void setGoalPosition(PersonState ps) {
        if (isAtTargetBarObject(ps.getGoalPosition(), 0)) {
            if (ps.getGoalPosition().equals(ps.getPosition())) {
                //were not standing completely still while talking
                Position targetPosition = ps.getPosition().clone();
                //0.27 km/h == 7cm per second
                float speed =  (0.27f * 1000f)/0.03f * Day.timeIncrementInHours;

                //select one cardinal direction to move in
                if (Main.random.nextBoolean()) {
                    if (Main.random.nextBoolean()) {
                        targetPosition.addToX(speed);
                    } else { //or down
                        targetPosition.addToX(-1 * speed);
                    }
                } else {
                    if (Main.random.nextBoolean()) {
                        targetPosition.addToY(speed);
                    } else {
                        targetPosition.addToY(-1 * speed);
                    }
                }

                if (isAtTargetBarObject(targetPosition, 0)) {
                    ps.setGoalPosition(targetPosition);
                }
            }
        }  else {
            float x = getTargetBarObject()[0] + Main.random.nextInt(getTargetBarObject()[2]);
            float y = getTargetBarObject()[1] + Main.random.nextInt(getTargetBarObject()[3]);
            ps.setGoalPosition(new Position(x, y));
        }
    }

    @Override
    public int[] getTargetBarObject() {
        return Main.clubs.get(0).standingPlaces[2];
    }
}
