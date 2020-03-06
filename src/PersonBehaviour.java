import java.util.List;
import java.util.Random;


public class PersonBehaviour {

    Random random = new Random();

    List<Person> updatePerson (Main main, List<Person> crowd, int curStep) {
        for(Person p : crowd) {
            p.setArrayPointer(curStep+1);
            p.setMoneyToSpend(p.getMoneyToSpend(curStep));
            p.setEnergy(Math.max(p.getEnergy(curStep) - 1, 0));
            //p.setPosition(p.getPosition(curStep));

            // Create move vector
            int x = -random.nextInt(8) + random.nextInt(8);
            int y = -random.nextInt(8) + random.nextInt(8);
            // Normalize if vector too big
            if (Math.sqrt(x^2 + y^2) > 8) {
                x = (int)(x * Math.sqrt(x^2 + y^2) * 8);
                y = (int)(y * Math.sqrt(x^2 + y^2) * 8);
            }
            // Alter position
            x = Math.min(Math.max(p.getPosition(curStep)[0] + x, 40), 360);
            y = Math.min(Math.max(p.getPosition(curStep)[1] + y, 40), 360);

            //Collision check
            for (int i = 0; i < main.club.barObjects.length; i++) {
                if (x > main.club.barObjects[i][0] - 10 && x < main.club.barObjects[i][0] + main.club.barObjects[i][2] + 10 &&
                    y > main.club.barObjects[i][1] - 10 && y < main.club.barObjects[i][1] + main.club.barObjects[i][3] + 10) {
                    x = p.getPosition(curStep)[0];
                    y = p.getPosition(curStep)[1];
                }
            }
            p.setPosition(new int[] {x,y});

            // Behaviour decisions
            int[] bar = main.club.barObjects[0];
            if (x > bar[0] - 30 && x < bar[0] + bar[2] + 30 && y > bar[1] - 30 && y < bar[1] + bar[3] + 30) {
                if (p.getHasAlcohol(curStep) <= 0) {
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA they lose money but dont hold their drinks or receive hasDrinked");
                    p.setMoneyToSpend(p.getMoneyToSpend(curStep) - 1);
                    p.setHasAlcohol(1);
                }
            }

            // Alcohol consumption
            if (p.getHasAlcohol(curStep) <= 0.01f) {
                // Just finished his/her drink
                if (p.getHasAlcohol(curStep) > 0)
                    p.setDrinksConsumed(p.getDrinksConsumed() + 1);
            }
            p.setHasAlcohol(Math.max(p.getHasAlcohol(curStep) - 0.01f, 0));

            //System.out.println(p.getHasAlcohol(curStep));
        }
        return crowd;
    }
}
