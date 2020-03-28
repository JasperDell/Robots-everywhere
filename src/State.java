public abstract class State {

    //functions any State needs to implement;
    public abstract void updatePerson();
    public abstract void takeAction();
    public abstract void moveNextState();

    //functions every State will need
    /*protected float[] nextPersonLocation (Person p, float[] target){
        float speed = 1.5f;

        //calculate movement vector

        float x = p.getPosition()[0] - target[0];
        float y = p.getPosition()[1] - target[1];

        //make vector only as big as speed allows //is that what this does?
        double distance = Math.sqrt(Math.abs(Math.pow(x,2)) + Math.abs(Math.pow(y,2)));
        if (distance > speed) {
            x = Math.round(x / distance) * speed;
            y = Math.round(y / distance) * speed;
        }

        //adjust target position to max speed + Outer wall collision check
        target[0] = Math.min(Math.max(p.getPosition()[0] + x, 40), 360);
        target[1] = Math.min(Math.max(p.getPosition()[1] + y, 40), 360);

        //Collision check , wait do we just not move if we colide with a barobject?
        for (int i = 0; i < Club.barObjects.length; i++) {
            if (Club.barObjects[i][4] == 1 && target[0] > Club.barObjects[i][0] - 10 && target[0] < Club.barObjects[i][0] + Club.barObjects[i][2] + 10 &&
                    target[1] > Club.barObjects[i][1] - 10 && target[1] < Club.barObjects[i][1] + Club.barObjects[i][3] + 10) {
                target[0] = p.getPosition()[0];
                target[1]= p.getPosition()[1];
            }
        }
        return new float[] {target[0],target[1]};
    }*/

    /*protected float[] determineTarget(int[] barobject){
        //todo implement;

    }

    //use default range when not specified
    //note that even if true, then we still need to set the position.
    protected boolean isAtTarget(Person person, float[] target){
        return isAtTarget(person, target, 10);
    }

    protected boolean isAtTarget(Person person, float[] target, int range) {
        float x= person.getPosition()[0];
        float y = person.getPosition()[1];
        if (x > target[0] - range && x < target[0] + range &&
                y > target[1] - range && y < target[1] + range) {
            return true;
        }
        return false;
    }*/
}
