import java.awt.*;
import java.util.List;
import javax.swing.*;

public class Visualiser extends JPanel {
    Club club;
    int curStep;

    public Visualiser(Club y, int z) {
        setBackground(Color.WHITE);
        club = y;
        curStep = z;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.drawString("You will never escape this barren landscape", 80, 40);

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
        for (int i = 1; i < 4; i++) {
            g.fillRect(club.barObjects[i][0], club.barObjects[i][1], club.barObjects[i][2], club.barObjects[i][3]);
        }
        // Test object
        g.fillRect(club.barObjects[4][0],club.barObjects[4][1],club.barObjects[4][2],club.barObjects[4][3]);


        g.setColor(Color.RED);
        for (Person p: club.crowd[curStep]) {

            // Determine Color
            if (p.getGender() == 0)
                g.setColor(Color.BLUE);
            else
                g.setColor(Color.PINK);
            g.fillOval(p.getPosition()[0] - 10, p.getPosition()[1] - 10,20,20);

            g.setColor(new Color(10,(int)p.getEnergy(),10));
            g.fillOval(p.getPosition()[0] - 8, p.getPosition()[1] - 8,16,16);
        }

    }
}
