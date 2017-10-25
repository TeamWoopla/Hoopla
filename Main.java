import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

class Main {

    static JButton StartBTN = new JButton("Start");
    static JButton Shop = new JButton("Shop");
    static JButton RestartStats = new JButton("RestartStats");
    static JLabel Backlbl = new JLabel();

    Main() {

        ImageIcon BackIcon = new ImageIcon("pics\\Background.png");

        Start.frame.getContentPane().removeAll();
        Start.frame.setResizable(false);
        Start.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Start.frame.setLayout(new FlowLayout());
        Start.frame.setSize(1000, 1000);
        Start.frame.setVisible(true);
        Start.frame.setLayout(null);

        Backlbl.setBounds(0, 0, 1000, 1000);
        StartBTN.setBounds(300, 275, 400, 200);
        Shop.setBounds(300, 525, 400, 200);
        RestartStats.setBounds(10, 10, 70, 20);

        Backlbl.setIcon(BackIcon);

        Shop.setFont(new Font("Arial", Font.PLAIN, 80));
        RestartStats.setFont(new Font("Arial", Font.PLAIN, 10));
        StartBTN.setFont(new Font("Arial", Font.PLAIN, 80));

        Shop.setBackground(Color.WHITE);
        RestartStats.setBackground(Color.WHITE);
        StartBTN.setBackground(Color.WHITE);

        Shop.setBorder(new LineBorder(new Color(0, 153, 0)));
        RestartStats.setBorder(new LineBorder(new Color(0, 153, 0)));
        StartBTN.setBorder(new LineBorder(new Color(0, 153, 0)));

        Shop.setFocusable(false);
        RestartStats.setFocusable(false);
        StartBTN.setFocusable(false);

        Start.frame.add(StartBTN);
        Start.frame.add(Shop);
        Start.frame.add(RestartStats);
        Start.frame.add(Backlbl);
    }

}
