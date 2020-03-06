import java.util.List;
import java.util.Random;


public class PersonBehaviour {

    Random random = new Random();

    List<Person> updatePerson (Main main, List<Person> crowd, int curStep) {
        for(Person p : crowd) {
            p.setArrayPointer(curStep+1);
            p.setEnergy(Math.max(p.getEnergy(curStep) - 1, 0));
            //p.setPosition(p.getPosition(curStep));
            int x = Math.min(Math.max(p.getPosition(curStep)[0] -8 + random.nextInt(16), 40), 360);
            int y = Math.min(Math.max(p.getPosition(curStep)[1] -8 + random.nextInt(16), 40), 360);

            //Collision check
            for (int i = 0; i < main.club.barObjects.length; i++) {
                if (x > main.club.barObjects[i][0] - 10 && x < main.club.barObjects[i][0] + main.club.barObjects[i][2] + 10&&
                    y > main.club.barObjects[i][1] - 10 && y < main.club.barObjects[i][1] + main.club.barObjects[i][3] + 10) {
                    x = p.getPosition(curStep)[0];
                    y = p.getPosition(curStep)[1];
                }
            }

            p.setPosition(new int[] {x,y});
        }
        return crowd;
    }
}
