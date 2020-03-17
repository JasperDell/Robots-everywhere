import java.awt.*;
import java.util.List;
import javax.swing.*;

public class Visualiser extends JPanel {
    Club club;
    int curStep;
    float time;

    public Visualiser(Club y, int z, float q) {
        setBackground(Color.WHITE);
        club = y;
        curStep = z;
        time = q;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawString(String.format(java.util.Locale.US,"%.2f", time), 30, 40);
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
        g.fillRect(club.barObjects[0][0],club.barObjects[0][1],club.barObjects[0][2],club.barObjects[0][3]);
        // Bar stools
        g.setColor(Color.GRAY);
        for (int i = 2; i < 5; i++) {
            g.fillRect(club.barObjects[i][0], club.barObjects[i][1], club.barObjects[i][2], club.barObjects[i][3]);
        }
        // Dance floor
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(club.barObjects[1][0],club.barObjects[1][1],club.barObjects[1][2],club.barObjects[1][3]);

        g.setColor(Color.RED);
        for (Person p: club.crowd[curStep]) {

            // Determine Color
            if (p.getGender() == 0)
                g.setColor(Color.BLUE);
            else
                g.setColor(Color.PINK);
            g.fillOval((int)(p.getPosition()[0] - 10), (int)(p.getPosition()[1] - 10),20,20);

            g.setColor(new Color(10,(int)p.getEnergy()/2,10));
            g.fillOval((int)(p.getPosition()[0] - 8), (int)(p.getPosition()[1] - 8),16,16);
        }

    }
}
