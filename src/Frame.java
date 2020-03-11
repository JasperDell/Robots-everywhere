import java.awt.*;
import java.util.List;
import javax.swing.*;

public class Frame {

    JFrame gui;

    public void Start (Club club, int curStep, float time) {

        gui = new JFrame("Club environment");
        gui.setSize(600, 500);
        //gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Visualiser panel = new Visualiser(club, curStep, time);


        Container pane = gui.getContentPane();

        pane.add(panel);
        gui.setVisible(true);
    }

    public void Update (Club club, int curStep, float time) {
        // If you require individual panels: create a new gui every update
        //gui = new JFrame("Club environment");
        //gui.setSize(600, 500);

        Visualiser panel = new Visualiser(club, curStep, time);

        Container pane = gui.getContentPane();

        pane.add(panel);
        gui.setVisible(true);
    }
}
