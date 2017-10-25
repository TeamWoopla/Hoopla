import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpScreen {
    HelpScreen() {
        Start.frame.setResizable(false);
        Start.frame.setVisible(true);
        Start.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Start.frame.setLayout(new FlowLayout());
        Start.frame.setSize(1000, 1000);
        Start.frame.setLayout(null);

        JLabel first = new JLabel("Game Logic Help : ");
        JButton Startplayin = new JButton("Start");

        JLabel Guide = new JLabel("You are the little character in the middle of the window, ");
        JLabel Guide1 = new JLabel("monsters will begin to spawn from random places,and you need to ");
        JLabel Guide12 = new JLabel("shoot them with your mouse(left mouse click).");
        JLabel Guide2 = new JLabel("At the top left corner you can see your score, health left, and level, ");
        JLabel Guide3 = new JLabel("the game will get harder as the score goes up. have fun!.");

        first.setFont(new Font("Arial", Font.PLAIN, 40));
        Guide.setFont(new Font("Arial", Font.PLAIN, 30));
        Guide1.setFont(new Font("Arial", Font.PLAIN, 30));
        Guide12.setFont(new Font("Arial", Font.PLAIN, 30));
        Guide2.setFont(new Font("Arial", Font.PLAIN, 30));
        Guide3.setFont(new Font("Arial", Font.PLAIN, 30));

        Startplayin.setFont(new Font("Arial", Font.PLAIN, 80));
        Startplayin.setBackground(Color.WHITE);
        Startplayin.setBorder(new LineBorder(new Color(0, 153, 0)));

        first.setBounds(300, -200, 500, 500);
        Startplayin.setBounds(300, 700, 400, 200);
        Main.Backlbl.setBounds(0, 0, 1000, 1000);
        Guide.setBounds(40, 100, 1000, 200);
        Guide1.setBounds(40, 200, 1000, 200);
        Guide12.setBounds(40, 300, 1000, 200);
        Guide2.setBounds(40, 400, 1000, 200);
        Guide3.setBounds(40, 500, 1000, 200);

        Start.frame.add(first);
        Start.frame.add(Guide);
        Start.frame.add(Guide1);
        Start.frame.add(Guide12);
        Start.frame.add(Guide2);
        Start.frame.add(Guide3);
        Start.frame.add(Startplayin);
        Start.frame.add(Main.Backlbl);



        Startplayin.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Start.BTNsound();
                Start.addGameScreen();
            }
        });
    }
}
