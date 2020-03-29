public class GetDrinksState extends State {
    private static GetDrinksState instance;
    private GetDrinksState(){};

    public static GetDrinksState getInstance() {
        if (instance == null)
            instance = new GetDrinksState();
        return instance;
    }

    @Override
    public void takeAction(PersonState ps) {
        setGoalPosition(ps);
        moveToGoalPosition(ps);

        //being at the bar with no alcohol allows one to buy alcohol
        if(isAtTargetBarObject(ps.getPosition(), 3) && !ps.hasAlcohol()){
            System.out.println(ps.getPerson().getName()+ "buying a drink" );
            ps.buyNewDrink(1, 7);
        }
    }

    @Override
    public void moveNextState(PersonState ps) {
        if (ps.hasAlcohol()){
            ps.setState(DancingState.getInstance());
        } else {
            ps.setState(this);
        }

    }

    @Override
    public void setGoalPosition(PersonState ps) {
        //goal position is not at bar
        //possibly this will make people walk into the bar, in that case check for position at targetPosition
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
