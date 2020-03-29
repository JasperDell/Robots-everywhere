import java.awt.*;
import javax.swing.*;

public class Frame {
    static JFrame gui;

    private Frame(){
    }

    public static void Start (Day day) {
        gui = new JFrame("Club environment");
        gui.setSize(600, 500);
        Visualiser panel = new Visualiser(day);
        Container pane = gui.getContentPane();
        pane.add(panel);
        gui.setVisible(true);
    }

    public static void Update (Day day) {
        Visualiser panel = new Visualiser(day);
        Container pane = gui.getContentPane();
        pane.add(panel);
        gui.setVisible(true);
    }
}
