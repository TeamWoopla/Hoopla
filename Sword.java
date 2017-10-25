import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Sword implements ActionListener {

    private static int numOfObjects = 0;

    private double x = 490;
    private double y = 490;
    private double b;
    private double m;
    private int Width = 50;
    private int Height = 50;
    private int serialNum = numOfObjects;

    private boolean baseonX = false;
    private boolean Plus = false;
    private boolean KillMe = false;

    private JLabel TheSword = new JLabel();

    public boolean PlayerRange = true;

    int Left;
    int Right;
    int Top;
    int Bottom;

    private Timer t = new Timer(10, this);

    Sword(int mouseX, int mouseY) {
        try {
            File file = new File("Sounds\\Shuriken.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Left = (int)this.x;
        Right = (int)this.x + Width;
        Top = (int)this.y;
        Bottom = (int)this.y + Height;

        numOfObjects++;
        t.start();

        m = (y - mouseY) / (x - mouseX);
        b = y - m*x;
        if (mouseX > x){
            Plus = true;
        }
        if (1 > m && m > -1){
            baseonX = true;
        }
        if ((Plus && !baseonX) && m < -1){
            Plus = false;
        }
        else if ((!Plus && !baseonX) && m < -1){
            Plus = true;
        }

        // if you want to change the icon of the sword to a pic (NOT A GIF!!!!!!!) you this not the regular way :S
        //ImageIcon SwordIcon = Start.ChangeSize("pics\\IDK.png", Width, Height);

        String Path = "pics\\Skins_Shuriken\\ShurikenWhite.gif";

        try {
            SaveData data = (SaveData) ResourceManager.load("1.save");
            Path = data.ShurikenLoadedSkinPath;
        }
        catch (Exception e) {
            System.out.println("Couldn't load save data: " + e.getMessage());
        }

        ImageIcon SwordIcon = Start.ChangeSize(Path, Width, Height);
        TheSword.setIcon(SwordIcon);
    }

    void Draw() {
        if(!PlayerRange) {
            TheSword.setBounds((int) x, (int) y, Width, Height);
            Start.frame.add(TheSword);
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (((this.Left > 513) || (this.Right < 487) || (this.Top > 527) || (this.Bottom < 473))) {
            PlayerRange = false;
        }
        if (x < 1050 && x > -100 && y > -100 && y < 1050) {
            if (Plus) {
                if (baseonX) {
                    x += 10;
                    y = m * x + b;
                } else {
                    y += 10;
                    x = (y - b) / m;
                }
            } else {
                if (baseonX) {
                    x -= 10;
                    y = m * x + b;
                } else {
                    y -= 10;
                    x = (y - b) / m;
                }
            }
        } else {
            if (!KillMe) {
                for (int i = GameScreen.Swords.size() - 1; i > -1; i--) {
                    if (GameScreen.Swords.get(i).serialNum == this.serialNum) {
                        GameScreen.Swords.remove(i);
                        KillMe = true;
                        //t.stop();
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
