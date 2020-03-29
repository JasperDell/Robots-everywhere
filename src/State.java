//any behavioural state should implement this State
//and be made into a singleton so we don't have an enormous amount of instances of every state
//make sure to use Day.timeIncrementInHours when implementing anything time sensitive
public abstract class State {
    //this gets called every time step on every person
    public void updatePerson(PersonState ps){
        takeAction(ps);
        moveNextState(ps);
    };
    ////////////////////
    //functions any State needs to implement:
    ///////////////////
    //put here all that person does in the state (move, set vars)
    public abstract void takeAction(PersonState ps);

    //put here logic of state transitions
    public abstract void moveNextState(PersonState ps);

    ////////////////////////
    //needed for the implemented helpers:
    ////////////////////////////
    //use ps.setgoalposition so you can use moveToGoalPosition in 'takeAction'
    public abstract void setGoalPosition(PersonState ps);

    //return the associated barobject so "isAtTargetBarObject" works
    public abstract int[] getTargetBarObject();

    ////////////////////
    //the implemented helpers:
    ////////////////////
    protected void moveToGoalPosition(PersonState ps){
        float speed = 2160 * Day.timeIncrementInHours;
        moveToGoalPosition(ps, speed);
    }

    protected void moveToGoalPosition (PersonState ps, float speed){
        Position target = ps.getGoalPosition().clone();

        //calculate movement vector
        float x = target.getX() - ps.getPosition().getX();
        float y = target.getY() - ps.getPosition().getY();

        // Normalize if vector too big
        double sqrt = Math.sqrt(Math.abs(Math.pow(x,2)) + Math.abs(Math.pow(y,2)));
        if (sqrt > speed) {
            x = Math.round(x / sqrt) * speed;
            y = Math.round(y / sqrt) * speed;
        }

        // Outer wall collision check + set actual position vector
        x = Math.min(Math.max(ps.getPosition().getX() + x, 40), 360);
        y = Math.min(Math.max(ps.getPosition().getY() + y, 40), 360);

        //Collision check, however no pathfinding so we cannot deal with collisions anyways
        /*for (int i = 0; i < Main.clubs.get(0).getBarObjects().length; i++) {
            if (Main.clubs.get(0).getBarObjects()[i][4] == 1 && x > Main.clubs.get(0).getBarObjects()[i][0] - 10 && x < Main.clubs.get(0).getBarObjects()[i][0] + Main.clubs.get(0).getBarObjects()[i][2] + 10 &&
                    y > Main.clubs.get(0).getBarObjects()[i][1] - 10 && y < Main.clubs.get(0).getBarObjects()[i][1] + Main.clubs.get(0).getBarObjects()[i][3] + 10) {
                x = ps.getPosition().getX();
                y = ps.getPosition().getY();
            }
        }*/
        ps.setPosition(new Position(x,y));
    }

    //use default range when not specified
    protected boolean isAtTarget(PersonState ps){
        return ps.getPosition().equals(ps.getGoalPosition());
    }

    protected boolean isAtTargetBarObject(Position p, int range){
        float x = p.getX();
        float y = p.getY();
        int[] barObject = getTargetBarObject();

        return (x > barObject[0] - range && x < barObject[0] + barObject[2] + range &&
                y > barObject[1] - range && y < barObject[1] + barObject[3] + range);
    }
}
