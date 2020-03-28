import java.awt.*;
import javax.swing.*;

public class Visualiser extends JPanel {

    public Visualiser() {
        setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawString(String.format(java.util.Locale.US,"%.2f", Day.time), 30, 40);
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
        g.fillRect(Club.barObjects[0][0],Club.barObjects[0][1],Club.barObjects[0][2],Club.barObjects[0][3]);
        // Bar stools
        g.setColor(Color.GRAY);
        for (int i = 2; i < 5; i++) {
            g.fillRect(Club.barObjects[i][0], Club.barObjects[i][1], Club.barObjects[i][2], Club.barObjects[i][3]);
        }
        // Dance floor
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(Club.barObjects[1][0],Club.barObjects[1][1],Club.barObjects[1][2],Club.barObjects[1][3]);

        g.setColor(Color.RED);
        for (Person p: Club.crowd[Day.getLastStep()]) {

            // Determine Color
            if (p.getGender() == 0)
                g.setColor(Color.BLUE);
            else
                g.setColor(Color.PINK);
            g.fillOval((int)(p.getLastPosition()[0] - 10), (int)(p.getLastPosition()[1] - 10),20,20);

            g.setColor(new Color(10,(int)p.getLastEnergy()/2,10));
            g.fillOval((int)(p.getLastPosition()[0] - 8), (int)(p.getLastPosition()[1] - 8),16,16);
        }

    }
}
