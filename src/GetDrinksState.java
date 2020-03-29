public class GetDrinksState extends State {
    /////////
    //singleton stuff
    ////////
    private static GetDrinksState instance;
    private GetDrinksState(){};
    public static GetDrinksState getInstance() {
        if (instance == null)
            instance = new GetDrinksState();
        return instance;
    }

    ///////
    //actual implementation
    ///////
    @Override
    public void takeAction(PersonState ps) {
        setGoalPosition(ps);
        moveToGoalPosition(ps);

        //being at the bar with no alcohol and with money allows one to buy alcohol
        if(isAtTargetBarObject(ps.getPosition(), 3) && !ps.hasAlcohol() && ps.getSpendableMoney() >0){
            System.out.println(ps.getPerson().getName()+ " buying a drink");
            ps.buyNewDrink(1, 7);
            System.out.println(ps.getPerson().getName()+ " has "+ps.getSpendableMoney()+ " money");
        }
    }

    @Override
    public void moveNextState(PersonState ps) {
        if (ps.hasAlcohol() || ps.getSpendableMoney() <= 0){
            ps.setState(DancingState.getInstance());
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
            target.setX(bar[0] + Main.random.nextInt(bar[2]));
            target.setY(bar[1] + Main.random.nextInt(bar[3]));
            ps.setGoalPosition(target);
        }
    }

    @Override
    public int[] getTargetBarObject() {
        return Main.clubs.get(0).bar;
    }
}
