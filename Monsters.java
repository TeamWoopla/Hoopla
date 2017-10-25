import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;


public class Monsters implements ActionListener {

    private static int numOfObjects = 0;
    static int speed = 1;

    private JLabel MonsterLbl = new JLabel();

    private double x, y, b , m;
    private int Left, Right, Top, Bottom;
    private boolean baseOnX = false;
    private boolean Plus = false;
    private int Width = 150;
    private int Height = 150;
    private int serialNum = numOfObjects;

    private boolean KillMe = false;
    public boolean typeCoin = false;

    private Timer t = new Timer(10, this);

    Monsters() {
        Left = (int)this.x;
        Right = (int)this.x + Width;
        Top = (int)this.y;
        Bottom = (int)this.y + Height;

        t.start();
        numOfObjects++;

        Random rand = new Random();
        int randomNum = rand.nextInt(1000) + 1;
        int randomPattren = rand.nextInt(4) + 1;
        switch (randomPattren) {
            case 1:
                x = -400;
                y = randomNum;
                break;
            case 2:
                x = 1400;
                y = randomNum;
                break;
            case 3:
                x = randomNum;
                y = -400;
                break;
            case 4:
                x = randomNum;
                y = 1400;
                break;

        }

        double dudeX = 425;
        double dudeY = 425;
        m = (y - dudeY) / (x - dudeX);
        b = y - m*x;
        if (dudeX > x){
            Plus = true;
        }
        if (1 > m && m > -1){
            baseOnX = true;
        }
        if ((Plus && !baseOnX) && m < -1){
            Plus = false;
        }
        else if ((!Plus && !baseOnX) && m < -1){
            Plus = true;
        }

        ImageIcon MonsterIcon = Start.ChangeSize("pics\\Monster.png", 150, 150);
        int randomCoin = rand.nextInt(10) + 1;
        if (randomCoin == 1){
            typeCoin = true;
            MonsterIcon = Start.ChangeSize("pics\\Coin.png", 150, 150);
        }

        MonsterLbl.setIcon(MonsterIcon);
    }

    void Drawer() {
        MonsterLbl.setBounds((int)x, (int)y, Width, Height);
        Start.frame.add(MonsterLbl);
    }

    void Kill() {
        t.stop();
    }

    boolean Intersects(Sword Sword){
        if (!((550 > x && x > 450) && (550 > y && y > 450))) {
            if ((this.Left > Sword.Right) || (this.Right < Sword.Left) || (this.Top > Sword.Bottom) || (this.Bottom < Sword.Top)) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Plus) {
            if (baseOnX) {
                x += speed;
                y = m * x + b;
            } else {
                y += speed;
                x = (y - b) / m;
            }
        } else {
            if (baseOnX) {
                x -= speed;
                y = m * x + b;
            } else {
                y -= speed;
                x = (y - b) / m;
            }
        }
        if (!((this.Left > 513) || (this.Right < 487) || (this.Top > 527) || (this.Bottom < 473))) {
            if(!KillMe){
                for (int i = GameScreen.Monster.size()-1; i > -1; i--){
                    if(GameScreen.Monster.get(i).serialNum == this.serialNum){
                        if(!typeCoin) {
                            try {
                                File file = new File("Sounds\\Hurt.wav");
                                AudioInputStream stream = AudioSystem.getAudioInputStream(file);
                                Clip clip = AudioSystem.getClip();
                                clip.open(stream);
                                clip.start();
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }
                            GameScreen.HP--;
                        }
                        GameScreen.Monster.remove(i);
                        KillMe = true;
                        t.stop();
                    }
                }
            }
        }
        Left = (int)this.x;
        Right = (int)this.x + Width;
        Top = (int)this.y;
        Bottom = (int)this.y + Height;
    }

}

