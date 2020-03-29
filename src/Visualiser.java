import java.awt.*;
import javax.swing.*;

public class Visualiser extends JPanel {

    Day day;
    public Visualiser(Day day) {
        this.day = day;
        setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        int hours = (int) (day.currentTime); //rounds down
        float afterComma = (day.currentTime %1); //get everything after comma
        int minutes = (int) (afterComma * 60f);
        String minutesString;
        if (minutes<10){
            minutesString = "0" + Integer.toString(minutes);
        } else minutesString = Integer.toString(minutes);
        g.drawString(hours + "h " + minutesString+"m", 30, 40);
        g.setColor(Color.RED);
        g.drawString(" :You will never escape this barren landscape", 80, 40);

        // Walls
        g.setColor(Color.BLACK);
        g.fillRect(0,0,400,20);
        g.fillRect(380,0,20,400);
        g.fillRect(0,380,400,20);
        g.fillRect(0,0,20,400);

        // FILL BAR
        // Bar table
        g.setColor(Color.DARK_GRAY);
        g.fillRect(Main.clubs.get(0).getBarObjects()[0][0],Main.clubs.get(0).getBarObjects()[0][1],Main.clubs.get(0).getBarObjects()[0][2],Main.clubs.get(0).getBarObjects()[0][3]);
        // Bar stools
        g.setColor(Color.GRAY);
        for (int i = 2; i < 5; i++) {
            g.fillRect(Main.clubs.get(0).getBarObjects()[i][0], Main.clubs.get(0).getBarObjects()[i][1], Main.clubs.get(0).getBarObjects()[i][2], Main.clubs.get(0).getBarObjects()[i][3]);
        }
        // Dance floor
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(Main.clubs.get(0).getBarObjects()[1][0],Main.clubs.get(0).getBarObjects()[1][1],Main.clubs.get(0).getBarObjects()[1][2],Main.clubs.get(0).getBarObjects()[1][3]);

        g.setColor(Color.RED);
        for (Person p: Main.people) {

            // Determine Color
            if (p.getGender() == Gender.MALE)
                g.setColor(Color.BLUE);
            else
                g.setColor(Color.PINK);
            g.fillOval((int)(p.getCurrentState().getPosition().getX() - 10), (int)(p.getCurrentState().getPosition().getY()- 10),20,20);
            //System.out.println(p.getCurrentState().getEnergy()*255);
            g.setColor(new Color(10,(int)(p.getCurrentState().getEnergy()*255),10));
            g.fillOval((int)(p.getCurrentState().getPosition().getX() - 8), (int)(p.getCurrentState().getPosition().getY() - 8),16,16);
        }

    }
}
