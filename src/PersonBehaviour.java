import java.util.List;
import java.util.Random;


public class PersonBehaviour {

    Random random = new Random();

    List<Person> updatePerson (Main main, List<Person> crowd, int curStep, float time) {
        for(Person p : crowd) {
            p.setArrayPointer(curStep+1);
            TakeAction (main, p, time, curStep);

            if (p.getHasAlcohol(curStep)>0) {
                p.setHasAlcohol(p.getHasAlcohol(curStep) - 1);
                if (p.getHasAlcohol(curStep+1) == 0){
                    p.setDrinksConsumed(p.getDrinksConsumed(curStep)+1);
                } else{
                    p.setDrinksConsumed(p.getDrinksConsumed(curStep));
                }
            } else{
                p.setDrinksConsumed(p.getDrinksConsumed(curStep));
                p.setHasAlcohol(0);
            }

            p.setHappiness(p.getHappiness(curStep));

            p.setMoneyToSpend(p.getMoneyToSpend(curStep));
            if (curStep%4 == 0){
                p.setEnergy(Math.max(p.getEnergy(curStep) - 1, 0));
            } else {
                p.setEnergy(Math.max(p.getEnergy(curStep), 0));
            }
            int drinkChance = random.nextInt(101);
            int[] bar = main.club.barObjects[0];
            float x = p.getPosition(curStep)[0]; float y = p.getPosition(curStep)[1];
            if(p.getHasAlcohol(curStep) == 0 && p.getMoneyToSpend(curStep)>0 &&(drinkChance<p.getLikenessToDrink())&&(x > bar[0] - 30 && x < bar[0] + bar[2] + 30 &&
                    y > bar[1] - 30 && y < bar[1] + bar[3] + 30)){
                p.setMoneyToSpend(p.getMoneyToSpend(curStep)-1);
                p.setHasAlcohol(20);
            }
            if (p.getMoneyToSpend(curStep)==5){
                p.setLikenessToDrink(15);
            }
            if (p.getEnergy(curStep)==0){
                p.setLikenessToDrink(0);
            }
        }
        return crowd;
    }

    void TakeAction (Main main, Person p, float t, int curStep) {
        // Determine what type of movement action the person wants to perform
        //SHOULD BE DETERMINED BY ALL OTHER RELATIONS: PLEASE FILL THIS IN
        int goal;
        if (t < 22 || t > 24)
            goal = 0; // Dance floor
        else
            goal = 1;
        MovePerson(main, p, goal, curStep);
    }

    private void MovePerson (Main main, Person p, int goal, int curStep) {
        float[] target = {0,0};
        float speed = 1.5f;
        float x = p.getPosition(curStep)[0]; float y = p.getPosition(curStep)[1];

        // Find out where to go given the personal goal
        switch (goal) {
            case 0: // Wants to dance
                // If already on the dance floot
                if (x > main.club.barObjects[1][0] - 10 && x < main.club.barObjects[1][0] + main.club.barObjects[1][2] + 10 &&
                        y > main.club.barObjects[1][1] - 10 && y < main.club.barObjects[1][1] + main.club.barObjects[1][3] + 10) {

                    // There is a chance he/she will choose a next target
                    if (random.nextInt(100) < 10) {
                        target[0] = main.club.barObjects[1][0] + random.nextInt(main.club.barObjects[1][2]);
                        target[1] = main.club.barObjects[1][1] + random.nextInt(main.club.barObjects[1][3]);
                        p.setGoalPosition(target);
                    }
                }

                // If first time
                if (p.getPrevGoal() != 0) {
                    target[0] = main.club.barObjects[1][0] + random.nextInt(main.club.barObjects[1][2]);
                    target[1] = main.club.barObjects[1][1] + random.nextInt(main.club.barObjects[1][3]);
                    p.setGoalPosition(target);
                    p.setPrevGoal(0);
                }
                else {
                    target = p.getGoalPosition();
                }

                break;
            case 1:
                int[] bar = main.club.barObjects[0];

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
        x = target[0] - p.getPosition(curStep)[0];
        y = target[1] - p.getPosition(curStep)[1];

        // Normalize if vector too big
        double sqrt = Math.sqrt(Math.abs(Math.pow(x,2)) + Math.abs(Math.pow(y,2)));
        if (sqrt > 1) {
            x = Math.round(x / sqrt) * speed;
            y = Math.round(y / sqrt) * speed;
        }

        // Outer wall collision check + set actual position vector
        x = Math.min(Math.max(p.getPosition(curStep)[0] + x, 40), 360);
        y = Math.min(Math.max(p.getPosition(curStep)[1] + y, 40), 360);

        //Collision check
        for (int i = 0; i < main.club.barObjects.length; i++) {
            if (main.club.barObjects[i][4] == 1 && x > main.club.barObjects[i][0] - 10 && x < main.club.barObjects[i][0] + main.club.barObjects[i][2] + 10 &&
                    y > main.club.barObjects[i][1] - 10 && y < main.club.barObjects[i][1] + main.club.barObjects[i][3] + 10) {
                x = p.getPosition(curStep)[0];
                y = p.getPosition(curStep)[1];
            }
        }
        p.setPosition(new float[] {x,y});
    }
}
