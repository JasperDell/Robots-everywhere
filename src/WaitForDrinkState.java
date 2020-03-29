public class WaitForDrinkState extends State {
    /////////
    //singleton stuff
    ////////
    private static WaitForDrinkState instance;
    private WaitForDrinkState(){};
    public static WaitForDrinkState getInstance() {
        if (instance == null)
            instance = new WaitForDrinkState();
        return instance;
    }

    @Override
    public void takeAction(PersonState ps) {
        if(ps.getDrinkPouringTimeLeft()>0){
            ps.addToDrinkPouringTimeLeft(-1 * Day.timeIncrementInHours);
        } else {
            ps.buyNewDrink(1, 7);
        }
    }

    @Override
    public void moveNextState(PersonState ps) {
        if(ps.hasAlcohol()){
            ps.setState(DancingState.getInstance());
        } else {
            ps.setState(this);
        }

    }

    @Override
    public void setGoalPosition(PersonState ps) {

    }

    @Override
    public int[] getTargetBarObject() {
        return new int[0];
    }
}
