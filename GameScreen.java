import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

class GameScreen implements ActionListener {

    static ArrayList<Sword> Swords = new ArrayList<>();
    static ArrayList<Monsters> Monster = new ArrayList<>();
    static int HP = 10;
    static int ScoreInt = 0;

    private JLabel Score = new JLabel();
    private JLabel Lvl = new JLabel();
    private JLabel HPLBL = new JLabel();
    private JLabel CoinsLBL = new JLabel();
    private JLabel BackLbl = new JLabel();
    private JLabel PlayerLbl = new JLabel();
    private JButton RestartBTN = new JButton("Restart");
    private JButton BackToMainFromGameBTN = new JButton("Main Page");

    private JLabel tutorialLBL = new JLabel("Left click anywhere on", SwingConstants.LEFT);
    private JLabel tutorialLBL1 = new JLabel("the screen to shot", SwingConstants.LEFT);

    private int spawnRate = 200;
    private int coins = 0;
    private String Level = "Starters";
    private boolean dead = false;
    private boolean tutorial = true;

    private Timer ALTimer = new Timer(5, this);

    GameScreen() {
        ALTimer.start();

        String PlayerPaph = "Pics\\Skins_Player\\PSkin_Normal.png";

        try {
            SaveData data = (SaveData) ResourceManager.load("1.save");
            PlayerPaph = data.PlayerLoadedSkinPath;
        }
        catch (Exception e) {
            System.out.println("Couldn't load save data: " + e.getMessage());
        }

        ImageIcon PlayerIcon = Start.ChangeSize(PlayerPaph, 40, 80);
        ImageIcon BackIcon = Start.ChangeSize("pics\\Background.png", 1000, 1000);

        Score.setFont(new Font("Arial", Font.PLAIN, 30));
        tutorialLBL.setFont(new Font("Arial", Font.PLAIN, 30));
        tutorialLBL1.setFont(new Font("Arial", Font.PLAIN, 30));
        Lvl.setFont(new Font("Arial", Font.PLAIN, 30));
        HPLBL.setFont(new Font("Arial", Font.PLAIN, 30));
        CoinsLBL.setFont(new Font("Arial", Font.PLAIN, 30));

        BackLbl.setBounds(0, 0, 1000, 1000);
        Score.setBounds(10, 0, 250, 100);
        Lvl.setBounds(10, 80, 1000, 100);
        HPLBL.setBounds(10, 120, 1000, 100);
        CoinsLBL.setBounds(10, 40, 1000, 100);
        PlayerLbl.setBounds(480, 460, 40, 80);
        tutorialLBL.setBounds(500, 50, 700, 30);
        tutorialLBL1.setBounds(525, 90, 700, 30);

        PlayerLbl.setIcon(PlayerIcon);
        BackLbl.setIcon(BackIcon);

        BackLbl.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int x = e.getX();
                    int y = e.getY();
                    GameScreen.Swords.add(new Sword(x, y));
                }
            }
        });
    }

    private void View() {
        Start.frame.setResizable(false);
        Start.frame.setLayout(new FlowLayout());
        Start.frame.setLayout(null);
        // draw
        Score.setText("Score : " + ScoreInt);
        Lvl.setText("Level : " + Level);
        HPLBL.setText("Health Left : " + HP);
        CoinsLBL.setText("Coins : " + coins);
        Start.frame.getContentPane().removeAll();
        if(tutorial){
            Start.frame.add(tutorialLBL);
            Start.frame.add(tutorialLBL1);
        }

        if (dead) {
            RestartBTN.setBounds(540, 100, 400, 200);
            RestartBTN.setFont(new Font("Arial", Font.PLAIN, 80));
            RestartBTN.setBackground(Color.WHITE);
            RestartBTN.setBorder(new LineBorder(new Color(0, 153, 0)));
            RestartBTN.setFocusable(false);
            BackToMainFromGameBTN.setBounds(70, 100, 400, 200);
            BackToMainFromGameBTN.setFont(new Font("Arial", Font.PLAIN, 80));
            BackToMainFromGameBTN.setBackground(Color.WHITE);
            BackToMainFromGameBTN.setBorder(new LineBorder(new Color(0, 153, 0)));
            BackToMainFromGameBTN.setFocusable(false);
            RestartBTN.addActionListener(e -> {
                Start.BTNsound();
                Swords.clear();
                Monster.clear();
                HP = 10;
                ScoreInt = 0;
                coins = 0;
                Start.addGameScreen();
            });
            BackToMainFromGameBTN.addActionListener(e -> {
                Start.BTNsound();
                Swords.clear();
                Monster.clear();
                HP = 10;
                ScoreInt = 0;
                coins = 0;
                new Main();
            });
            Start.frame.add(RestartBTN);
            Start.frame.add(BackToMainFromGameBTN);
        }
        Start.frame.add(PlayerLbl);
        Start.frame.add(Score);
        Start.frame.add(Lvl);
        Start.frame.add(HPLBL);
        Start.frame.add(CoinsLBL);
        for (Sword Sword : Swords) {
            Sword.Draw();
        }
        for (Monsters Monster : Monster) {
            Monster.Drawer();
        }
        Start.frame.add(BackLbl);
    }


    public void actionPerformed(ActionEvent e) {
        switch (ScoreInt) {
            case 0 :
                Level = "Starters";
                spawnRate = 400;
                Monsters.speed = 1;
                break;
            case 50 :
                Level = "Easy";
                spawnRate = 180;
                Monsters.speed = 1;
                tutorialLBL.setText("Good Luck :D");
                tutorialLBL1.setText("");
                tutorialLBL.setBounds(550, 80, 300, 30);
                break;
            case 100 :
                Level = "Normal";
                spawnRate = 150;
                Monsters.speed = 2;
                tutorial = false;
                break;
            case 200 :
                Level = "Medium";
                spawnRate = 130;
                Monsters.speed = 3;
                break;
            case 300:
                Level = "Hard";
                spawnRate = 120;
                Monsters.speed = 4;
                break;
            case 400 :
                Level = "Harder Than Hard";
                spawnRate = 110;
                Monsters.speed = 5;
                break;
            case 500 :
                Level = "Insane";
                spawnRate = 110;
                Monsters.speed = 6;
                break;
            case 600 :
                Level = "God Himself";
                spawnRate = 100;
                break;
            case 800 :
                Level = "Dank Memez";
                spawnRate = 90;
                break;
            case 1000 :
                Level = "Dammmmn Daniel";
                spawnRate = 80;
                break;
            case 1200 :
                Level = "DIE POTATO";
                spawnRate = 70;
                Monsters.speed = 8;
                break;
            case 1500 :
                Level = "WTF DUDE JUST DIE ALREADY!!!";
                spawnRate = 100;
                Monsters.speed = 9;
                break;
            case 1750 :
                Level = "Fuck this shit i'm out!";
                spawnRate = 80;
                break;
            case 2000 :
                Level = "I WORSHIP YOU, YOU ARE A GOD.";
                spawnRate = 110;
                Monsters.speed = 12;
                break;
            case 2250 :
                Level = "HOOOOOPLA!";
                spawnRate = 80;
                break;
            case 2500 :
                Level = "Super Seitan";
                spawnRate = 100;
                Monsters.speed = 14;
                break;
            case 2750 :
                Level = "Super Seitan";
                spawnRate = 70;
                break;
            case 3000 :
                Level = "SuperMarket";
                spawnRate = 50;
            case 4000 :
                Level = "Lamo I'm done";
                spawnRate = 45;
                break;
            case 6000 :
                Level = "Bye Bye Bitch!";
                spawnRate = 60;
                Monsters.speed = 16;
                break;
        }

        Random rand = new Random();
        int randomNum = rand.nextInt(spawnRate) + 1;
        if (randomNum == 1){
            Monster.add(new Monsters());
        }

        //if (Mouse)

        for (int i1 = Swords.size() - 1; i1 >= 0; i1--) {
            if (!Swords.get(i1).PlayerRange) {
                boolean Killed = false;
                for (int i = Monster.size() - 1; i >= 0; i--) {
                    if (!Killed) {
                        if (Monster.get(i).Intersects(Swords.get(i1))) {
                            if (Monster.get(i).typeCoin) {
                                coins++;
                                System.out.println("Coins - " + coins);
                                try {
                                    File file = new File("Sounds\\CoinSound.wav");
                                    AudioInputStream stream = AudioSystem.getAudioInputStream(file);
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(stream);
                                    clip.start();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                                //need to add a play sound for coins
                            } else {
                                ScoreInt += 10;
                                try {
                                    File file = new File("Sounds\\DeadMonster.wav");
                                    AudioInputStream stream = AudioSystem.getAudioInputStream(file);
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(stream);
                                    clip.start();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }
                            Monster.get(i).Kill();
                            Monster.remove(i);
                            Killed = true;
                        }
                    }
                }
            }
        }

        if (HP == 0) {
            try {
                File file = new File("Sounds\\GameOver.wav");
                AudioInputStream stream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(stream);
                clip.start();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            try {
                SaveData data = (SaveData) ResourceManager.load("1.save");
                Start.coins += this.coins;
                data.coins = Start.coins;
                try {
                    ResourceManager.save(data, "1.save");
                }
                catch (Exception eScore) {
                    System.out.println("Couldn't save: " + eScore.getMessage());
                }
            }
            catch (Exception est) {
                System.out.println("Couldn't load save data: " + est.getMessage());
            }
            dead = true;
            ALTimer.stop();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

        View();

    }
}
