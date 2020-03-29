public class DancingState extends State {
    private static DancingState instance;
    private DancingState(){};

    public static DancingState getInstance() {
        if (instance == null)
            instance = new DancingState();
        return instance;
    }

    @Override
    public void takeAction(PersonState ps) {
        setGoalPosition(ps);
        moveToGoalPosition(ps);
        //ps.addToEnergy(-1);//every two minutes of dancing take 1 energy

        //if we have alcohol and are at the dancefloor
        if (ps.hasAlcohol() && ps.getPosition().equals(ps.getGoalPosition())) {
            ps.takeSip();
        }
    }

    @Override
    public void moveNextState(PersonState ps) {
        int likelinessToGoGrabADrink = 0;//out of 100
        //if has no alcohol and has money, then create a chance to go get a drink
        if (!ps.hasAlcohol() && ps.getSpendableMoney() > 0) {
            //if has no alcohol average of 70 percent chance to go get drinks
            likelinessToGoGrabADrink += 50;
            likelinessToGoGrabADrink += Main.random.nextInt(40);

            //if low on energy also make that a reason  to get a drink
            if (ps.getEnergy() < Main.random.nextInt(10)) { //if energy at least lower than 10
                likelinessToGoGrabADrink += 10 - ps.getEnergy();
            }
        }

        //note if likeliness is 0, then chance is also 0, if likeliness is 100 then chance is also 100
        if(likelinessToGoGrabADrink > Main.random.nextInt(100)){
            //set Nextstate to be drinking!!!
            ps.setState(GetDrinksState.getInstance());
        } else {
            //set Nextstate to be dancing !!!
            ps.setState(this);
        }

    }

    @Override
    public void setGoalPosition(PersonState ps) {
        //if already has a goal on the dancefloor
        if (isAtTargetBarObject(ps.getGoalPosition(), 0)){
            //if at that goal
            if(ps.getGoalPosition().equals(ps.getPosition())){
                //were dancing so move a little
                Position targetPosition = ps.getPosition().clone();
                if(Main.random.nextBoolean()){//move up
                    targetPosition.addToX(1);
                } else{ //or down
                    targetPosition.addToX(-1);
                }

                if(Main.random.nextBoolean()){//move up
                    targetPosition.addToY(1);
                } else{ //or down
                    targetPosition.addToY(-1);
                }

                if(isAtTargetBarObject(targetPosition, 0)){
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