public class GoToBarState extends State {
    /////////
    //singleton stuff
    ////////
    private static GoToBarState instance;
    private GoToBarState(){};
    public static GoToBarState getInstance() {
        if (instance == null)
            instance = new GoToBarState();
        return instance;
    }

    ///////
    //actual implementation
    ///////
    @Override
    public void takeAction(PersonState ps) {
        setGoalPosition(ps);
        moveToGoalPosition(ps);
    }

    @Override
    public void moveNextState(PersonState ps) {
        //being at the bar with no alcohol and with money allows one to buy alcohol
        if(isAtTargetBarObject(ps.getPosition(), 3) && !ps.hasAlcohol() && ps.getSpendableMoney() >0){
            float minutesOfPouring = Main.random.nextInt(3)+1;
            ps.setDrinkPouringTimeLeft(minutesOfPouring / 60f);//time is in hours
            ps.setState(WaitForDrinkState.getInstance());
        //still having alcohol or having no money means you shouldn't go to bar
        } else if (ps.hasAlcohol() || ps.getSpendableMoney() <= 0) {
            if (ps.getEnergy() < 0.3) {
                ps.setState(TalkingState.getInstance());
            } else {
                ps.setState(DancingState.getInstance());
            }
        } else {
            ps.setState(this);
        }
    }

    @Override
    public void setGoalPosition(PersonState ps) {
        //goal position is not at bar
        if(!isAtTargetBarObject(ps.getGoalPosition(), 1)){
            int[] bar = getTargetBarObject();
            Position target = new Position();
            if (Main.random.nextBoolean()) {
                target.setX(bar[0] + Main.random.nextInt(bar[2]));
                target.setY(bar[1]);
            } else {
                target.setX(bar[0] + bar[2]);
                target.setY(bar[1] + Main.random.nextInt(bar[3]));
            }
            ps.setGoalPosition(target);
        }
    }

    @Override
    public int[] getTargetBarObject() {
        return Main.clubs.get(0).bar;
    }
}
