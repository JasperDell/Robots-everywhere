import java.util.List;
import java.util.Random;


public class PersonBehaviour {

    Random random = new Random();

    List<Person> updatePersons(List<Person> crowd) {
        int lastStep = Day.getLastStep();
        int curStep = Day.getCurStep();
        for(Person p : crowd) {
            TakeAction (p, lastStep);

            if (p.getLastHasAlcohol()>0) {
                p.setNewHasAlcohol(p.getLastHasAlcohol() - 1);
                if (p.getLastHasAlcohol() == 1){
                    p.setNewDrinksConsumed(p.getLastDrinksConsumed()+1);
                } else{
                    p.setNewDrinksConsumed(p.getLastDrinksConsumed());
                }
            } else{
                p.setNewDrinksConsumed(p.getLastDrinksConsumed());
                p.setNewHasAlcohol(0);
            }

            p.setNewHappiness(p.getLastHappiness());

            p.setNewMoneyToSpend(p.getLastMoneyToSpend());
            if (lastStep%4 == 0){
                p.setNewEnergy(Math.max(p.getLastEnergy() - 1, 0));
            } else {
                p.setNewEnergy(Math.max(p.getLastEnergy(), 0));
            }
            int drinkChance = random.nextInt(101);
            int[] bar = Club.barObjects[0];
            float x = p.getLastPosition()[0]; float y = p.getLastPosition()[1];
            if(p.getLastHasAlcohol() == 0 && p.getLastMoneyToSpend()>0 &&(drinkChance<p.getLikenessToDrink())&&(x > bar[0] - 30 && x < bar[0] + bar[2] + 30 &&
                    y > bar[1] - 30 && y < bar[1] + bar[3] + 30)){
                p.setNewMoneyToSpend(p.getLastMoneyToSpend()-1);
                p.setNewHasAlcohol(20);
            }
            if (p.getLastMoneyToSpend()==5){
                p.setLikenessToDrink(15);
            }
            if (p.getLastEnergy()==0){
                p.setLikenessToDrink(0);
            }
        }
        return crowd;
    }

    void TakeAction (Person p, int curStep) {
        // Determine what type of movement action the person wants to perform
        //SHOULD BE DETERMINED BY ALL OTHER RELATIONS: PLEASE FILL THIS IN
        int goal;
        if (Day.time < 22 || Day.time > 24)
            goal = 0; // Dance floor
        else
            goal = 1;
        MovePerson(p, goal, curStep);
    }

    private void MovePerson (Person p, int goal, int curStep) {
        float[] target = {0,0};
        float speed = 1.5f;
        float x = p.getLastPosition()[0]; float y = p.getLastPosition()[1];

        // Find out where to go given the personal goal
        switch (goal) {
            case 0: // Wants to dance
                // If already on the dance floor
                if (x > Club.barObjects[1][0] - 10 && x < Club.barObjects[1][0] + Club.barObjects[1][2] + 10 &&
                        y > Club.barObjects[1][1] - 10 && y < Club.barObjects[1][1] + Club.barObjects[1][3] + 10) {

                    // There is a chance he/she will choose a next target
                    if (random.nextInt(100) < 10) {
                        target[0] = Club.barObjects[1][0] + random.nextInt(Club.barObjects[1][2]);
                        target[1] = Club.barObjects[1][1] + random.nextInt(Club.barObjects[1][3]);
                        p.setGoalPosition(target);
                    }
                }

                // If first time
                if (p.getPrevGoal() != 0) {
                    target[0] = Club.barObjects[1][0] + random.nextInt(Club.barObjects[1][2]);
                    target[1] = Club.barObjects[1][1] + random.nextInt(Club.barObjects[1][3]);
                    p.setGoalPosition(target);
                    p.setPrevGoal(0);
                }
                else {
                    target = p.getGoalPosition();
                }

                break;
            case 1:
                int[] bar = Club.barObjects[0];

                // If already around the bar
                if (x > bar[0] - 30 && x < bar[0] + bar[2] + 30 &&
                        y > bar[1] - 30 && y < bar[1] + bar[3] + 30) {
                    System.out.println("TODO: IT SHOULD NOW BE POSSIBLE TO ORDER A DRINK");
                }

                // If first time
                // If first time
                if (p.getPrevGoal() != 1) {
                    target[0] = bar[0] + random.nextInt(bar[2]);
                    target[1] = bar[1] + random.nextInt(bar[3]);
                    p.setGoalPosition(target);
                    p.setPrevGoal(1);
                }
                else {
                    target = p.getGoalPosition();
                }
                //target[0] = 0;
                //target[1] = 0;
                break;
        }


        // Determine the movement of the person in this frame
        x = target[0] - p.getLastPosition()[0];
        y = target[1] - p.getLastPosition()[1];

        // Normalize if vector too big
        double sqrt = Math.sqrt(Math.abs(Math.pow(x,2)) + Math.abs(Math.pow(y,2)));
        if (sqrt > 1) {
            x = Math.round(x / sqrt) * speed;
            y = Math.round(y / sqrt) * speed;
        }

        // Outer wall collision check + set actual position vector
        x = Math.min(Math.max(p.getLastPosition()[0] + x, 40), 360);
        y = Math.min(Math.max(p.getLastPosition()[1] + y, 40), 360);

        //Collision check
        for (int i = 0; i < Club.barObjects.length; i++) {
            if (Club.barObjects[i][4] == 1 && x > Club.barObjects[i][0] - 10 && x < Club.barObjects[i][0] + Club.barObjects[i][2] + 10 &&
                    y > Club.barObjects[i][1] - 10 && y < Club.barObjects[i][1] + Club.barObjects[i][3] + 10) {
                x = p.getLastPosition()[0];
                y = p.getLastPosition()[1];
            }
        }
        p.setNewPosition(new float[] {x,y});
    }
}
