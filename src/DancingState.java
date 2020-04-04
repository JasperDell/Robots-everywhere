public class DancingState extends State {
    /////////
    //singleton stuff
    ////////
    private static DancingState instance;
    private DancingState(){};
    public static DancingState getInstance() {
        if (instance == null)
            instance = new DancingState();
        return instance;
    }

    ///////
    //actual implementation
    ///////
    @Override
    public void takeAction(PersonState ps) {
        setGoalPosition(ps);
        moveToGoalPosition(ps);
        //spend 20 percent of energy per hour, aka be able to stay dancing for 5 hours
        //however stop dancing at 30 percent energy so effectively dance for 5*.7 = 3.5 hours
        ps.addToEnergy(-1* (20f/100f * Day.timeIncrementInHours));

        //once on the dancefloor and dancing you are always at the goalposition
        //as youre not targeting a spot thats further away than you can walk in a timestep
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

        if (ps.getEnergy()<0.30){
            ps.setState(TalkingState.getInstance());
        } else {
            float likelinessToGoGrabADrink = 0;//out of 100
            //if has no alcohol and has money, then create a chance to go get a drink
            if (!ps.hasAlcohol() && ps.getSpendableMoney() > 0) {
                //if has no alcohol average of 85 percent chance per hour to go get drinks, least 70, high 100
                likelinessToGoGrabADrink += 70;
                likelinessToGoGrabADrink += Main.random.nextInt(30);
                //kansrekening werkt niet zo 2* 0.5 kans is niet hetzelfde als 1* 1 kans, gemiddeld wel though
                //we doen hier dus x* (1/x) * y kans, als die x heel groot is, dan is het allemaal ongeveer hetzelfde,
                //dus hoe kleiner te timestep hoe nauwkeuriger? dat is vast hoe het werkt
                likelinessToGoGrabADrink *= Day.timeIncrementInHours; //multiply by the amount of hours in this step
            }
            //note if likeliness is 0, then chance is also 0, if likeliness is 100 then chance is also 100
            if (likelinessToGoGrabADrink > Main.random.nextInt(100)) {
                //set Nextstate to be drinking!!!
                ps.setState(GoToBarState.getInstance());
            } else {
                //set Nextstate to be dancing !!!
                ps.setState(this);
            }
        }
    }

    @Override
    public void setGoalPosition(PersonState ps) {
        //TODO: make dancing not depend on simuluation time
        //float secondsPerTick = Day.timeIncrementInHours * 3600;
        //float secondsPerDanceMove = 2f;
        //float tickPerMove = secondsPerDanceMove / secondsPerTick;

        if (isAtTargetBarObject(ps.getGoalPosition(), 0)){
            if(ps.getGoalPosition().equals(ps.getPosition())){
                //were dancing so move a little
                Position targetPosition = ps.getPosition().clone();
                //1.08 km/h == 30cm per second
                float speed =  (0.54f * 1000f)/0.03f * Day.timeIncrementInHours;

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
                //if not wanting to move outside of bar
                if (isAtTargetBarObject(targetPosition, 0)) {
                    ps.setGoalPosition(targetPosition);
                }
            } //else just keep going to that goal
        //if we dont have a goal on the dancefloor, set a new goal on the dancefloor
        } else {
            //not yet intending to go to dancefloor and not on dancefloor (to not keep changing the position at the dancefloor
            float x = Main.clubs.get(0).getBarObjects()[1][0] + Main.random.nextInt(Main.clubs.get(0).getBarObjects()[1][2]);
            float y = Main.clubs.get(0).getBarObjects()[1][1] + Main.random.nextInt(Main.clubs.get(0).getBarObjects()[1][3]);
            ps.setGoalPosition(new Position(x, y));
        }
    }

    @Override
    public int[] getTargetBarObject() {
        return Main.clubs.get(0).danceFloor;
    }
}