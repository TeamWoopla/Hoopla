import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Start {

    static final JFrame frame = new JFrame("H o O p L a");

    public static int coins = 0;
    //static boolean GameEnded = false;

    static void BTNsound() {
        try {
            File file = new File("Sounds\\ButtonClicked.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Start Loading");
            SaveData data = (SaveData) ResourceManager.load("1.save");
            System.out.println(data.coins);
            System.out.println("Finish Loading");
        }
        catch (Exception e) {
            System.out.println("Couldn't load save data: " + e.getMessage());
        }
        //System.out.println("The score is - " + bestScore);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("pics\\Monster.png"));
        } catch (IOException ignored) {
        }
        frame.setIconImage(image);

        new TheTimer();
        new Main();

        Main.StartBTN.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BTNsound();
                addGameScreen();
            }
        });
        Main.Shop.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BTNsound();
                frame.getContentPane().removeAll();
                Shop j = new Shop();
            }
        });
        Main.RestartStats.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BTNsound();
                SaveData data = new SaveData();
                data.bestScore = 0;
                data.coins = 0;
                for (int i = 1; i < data.PlayerSkins.length; i++){
                    data.PlayerSkins[i] = false;
                }
                data.PlayerSkins[0] = true;
                data.PlayerLoadedSkinPath = "pics\\Skins_Player\\PSkin_Normal.png";
                data.PlayerLoadedSkinNum = 0;
                for (int i = 1; i < data.ShurikenSkins.length; i++){
                    data.ShurikenSkins[i] = false;
                }
                data.ShurikenSkins[0] = true;
                data.ShurikenLoadedSkinPath = "pics\\Skins_Shuriken\\ShurikenWhite.gif";
                data.ShurikenLoadedSkinNum = 0;
                data.ShopSeletedType = "Player";
                try {
                    ResourceManager.save(data, "1.save");
                    System.out.println("Restarted :D");
                }
                catch (Exception eScore) {
                    System.out.println("Couldn't save: " + eScore.getMessage());
                }
            }
        });
    }

    static void addGameScreen(){
        frame.getContentPane().removeAll();
        new GameScreen();
    }

    static ImageIcon ChangeSize(String path, int width, int hight){
        ImageIcon Icon = new ImageIcon(path);
        Image image = Icon.getImage();
        Image newimg = image.getScaledInstance(width, hight,  java.awt.Image.SCALE_DEFAULT);
        Icon = new ImageIcon(newimg);
        return Icon;
    }
}
