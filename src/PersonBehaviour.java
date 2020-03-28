public class PersonBehaviour {
    private static boolean firstTime = false;
    private static boolean hasBeenOnDanceFloor = false;
    private static boolean hasbeenAtBar = false;

    public static void updatePersons() {
        for(Person p : Main.people) {
            PersonState ps = p.getCurrentState();
            TakeAction(p);
            if (ps.hasAlcohol()) {
                ps.takeSip();
            }

            if (ps.getTime()%(1/30f) == 0){ //every two minutes
                ps.setEnergy(Math.max(ps.getEnergy() - 1, 0));
            }

            int drinkChance = Main.random.nextInt(101);
            int[] bar = Main.clubs.get(0).getBar();
            float x = ps.getPosition().getX(); float y = ps.getPosition().getY();
            if(!ps.hasAlcohol() && ps.getSpendableMoney()>0 &&(drinkChance<ps.getLikenessToDrink())&&(x > bar[0] - 30 && x < bar[0] + bar[2] + 30 &&
                    y > bar[1] - 30 && y < bar[1] + bar[3] + 30)){
                ps.buyNewDrink(1);
            }
            if (ps.getSpendableMoney()==5){
                ps.setLikenessToDrink(15);
            }
            if (ps.getEnergy()==0){
                ps.setLikenessToDrink(0);
            }
        }
    }

    static void TakeAction(Person p) {
        // Determine what type of movement action the person wants to perform
        //SHOULD BE DETERMINED BY ALL OTHER RELATIONS: PLEASE FILL THIS IN
        int goal;
        if (p.getCurrentState().getTime() < 22 || p.getCurrentState().getTime()  > 24)
            goal = 0; // Dance floor
        else
            goal = 1;
        MovePerson(p, goal);
    }

    private static void MovePerson(Person p, int goal) {
        float[] target = {0,0};
        float speed = 1.5f;
        PersonState ps =p.getCurrentState();
        float x = ps.getPosition().getX(); float y = ps.getPosition().getY();
        float targetx = ps.getGoalPosition().getX(); float targety = ps.getGoalPosition().getY();
        System.out.println(targetx + " " + targety);
        // Find out where to go given the personal goal
        switch (goal) {
            case 0: // Wants to dance
                // If first time
                target[0] = Main.clubs.get(0).getBarObjects()[1][0] + Main.random.nextInt(Main.clubs.get(0).getBarObjects()[1][2]);
                target[1] = Main.clubs.get(0).getBarObjects()[1][1] + Main.random.nextInt(Main.clubs.get(0).getBarObjects()[1][3]);
                ps.setGoalPosition(new Position(target));
                break;
            case 1:
                int[] bar = Main.clubs.get(0).getBarObjects()[0];
                // If already around the bar
                if (x > bar[0] - 30 && x < bar[0] + bar[2] + 30 &&
                        y > bar[1] - 30 && y < bar[1] + bar[3] + 30) {
                    System.out.println("TODO: IT SHOULD NOW BE POSSIBLE TO ORDER A DRINK");
                }
                if (targetx > bar[0] - 30 && targetx < bar[0] + bar[2] + 30 &&
                        targety > bar[1] - 30 && targety < bar[1] + bar[3] + 30) {
                    target[0] = targetx;
                    target[1] = targety;
                } else{
                    target[0] = bar[0] + Main.random.nextInt(bar[2]);
                    target[1] = bar[1] + Main.random.nextInt(bar[3]);
                    ps.setGoalPosition(new Position(target));
                }
                break;
        }


        // Determine the movement of the person in this frame
        x = target[0] - ps.getPosition().getX();
        y = target[1] - ps.getPosition().getY();

        // Normalize if vector too big
        double sqrt = Math.sqrt(Math.abs(Math.pow(x,2)) + Math.abs(Math.pow(y,2)));
        if (sqrt > 1) {
            x = Math.round(x / sqrt) * speed;
            y = Math.round(y / sqrt) * speed;
        }

        // Outer wall collision check + set actual position vector
        x = Math.min(Math.max(ps.getPosition().getX() + x, 40), 360);
        y = Math.min(Math.max(ps.getPosition().getY() + y, 40), 360);

        //Collision check
        for (int i = 0; i < Main.clubs.get(0).getBarObjects().length; i++) {
            if (Main.clubs.get(0).getBarObjects()[i][4] == 1 && x > Main.clubs.get(0).getBarObjects()[i][0] - 10 && x < Main.clubs.get(0).getBarObjects()[i][0] + Main.clubs.get(0).getBarObjects()[i][2] + 10 &&
                    y > Main.clubs.get(0).getBarObjects()[i][1] - 10 && y < Main.clubs.get(0).getBarObjects()[i][1] + Main.clubs.get(0).getBarObjects()[i][3] + 10) {
                x = ps.getPosition().getX();
                y = ps.getPosition().getY();
            }
        }
        ps.setPosition(new Position(x,y));
    }
}
