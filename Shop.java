import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Shop{

    static JLabel Woodlbl = new JLabel();
    static JButton BackToMain = new JButton("Back");
    static JButton BTNPlayer = new JButton("Player Shop");
    static JButton BTNShuriken = new JButton("Shuriken Shop");
    static JLabel CoinsLBL = new JLabel("Null", SwingConstants.CENTER);

    static JLabel[] PlayerOptions = new JLabel[12];
    static JLabel[] PlayerOptionsCoinLabel = new JLabel[12];
    static int[] PlayerOptionsCoinInt = new int[12];
    static int PlayerChoosenSkin = 0;

    static JLabel[] ShurikenOptions = new JLabel[4];
    static JLabel[] ShurikenOptionsCoinLabel = new JLabel[4];
    static int[] ShurikenOptionsCoinInt = new int[4];
    static int ShurikenChoosenSkin = 0;

    static String ShopSeletedType = "Player";

    Shop() {
        SkinCost();
        Draw();
        Logic();
    }

    //Change the cost of the PlayerSkins here
    static void SkinCost(){
        //cost of the PlayerSkins
        PlayerOptionsCoinInt[1] = 5;
        PlayerOptionsCoinInt[2] = 10;
        PlayerOptionsCoinInt[3] = 20;
        PlayerOptionsCoinInt[4] = 35;
        PlayerOptionsCoinInt[5] = 50;
        PlayerOptionsCoinInt[6] = 50;
        PlayerOptionsCoinInt[7] = 70;
        PlayerOptionsCoinInt[8] = 90;
        PlayerOptionsCoinInt[9] = 110;
        PlayerOptionsCoinInt[10] = 150;
        PlayerOptionsCoinInt[11] = 200;
        ShurikenOptionsCoinInt[1] = 20;
        ShurikenOptionsCoinInt[2] = 60;
        ShurikenOptionsCoinInt[3] = 100;
    }

    //No need to change that :P
    static void Logic(){
        //BTNs
        BackToMain.addActionListener(e -> {
            if (TheTimer.ShopBTN) {
                TheTimer.ShopBTN = false;
                Start.BTNsound();
                new Main();
            }
        });
        BTNPlayer.addActionListener(e -> {
            if (TheTimer.ShopBTN) {
                TheTimer.ShopBTN = false;
                Start.BTNsound();
                if (ShopSeletedType != "Player") {
                    try {
                        SaveData data = (SaveData) ResourceManager.load("1.save");
                        data.ShopSeletedType = "Player";
                        try {
                            ResourceManager.save(data, "1.save");
                        } catch (Exception eScore) {
                            System.out.println("Couldn't save: " + eScore.getMessage());
                        }
                    } catch (Exception est) {
                        System.out.println("Couldn't load save data: " + est.getMessage());
                    }
                    ShopSeletedType = "Player";
                    new Shop();
                }
            }
        });
        BTNShuriken.addActionListener(e -> {
            if (TheTimer.ShopBTN) {
                TheTimer.ShopBTN = false;
                Start.BTNsound();
                if (ShopSeletedType != "Shuriken") {
                    try {
                        SaveData data = (SaveData) ResourceManager.load("1.save");
                        data.ShopSeletedType = "Shuriken";
                        try {
                            ResourceManager.save(data, "1.save");
                        } catch (Exception eScore) {
                            System.out.println("Couldn't save: " + eScore.getMessage());
                        }
                    } catch (Exception est) {
                        System.out.println("Couldn't load save data: " + est.getMessage());
                    }
                    ShopSeletedType = "Shuriken";
                    new Shop();
                }
            }
        });


        //Mouse Asapters for the Player lbls
        for (int i = 0; i < PlayerOptions.length; i++ ) {
            final int i1 = i;
            PlayerOptions[i].addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    BuySkin(i1, PlayerOptionsCoinInt[i1], i1);
                }
            });
        }

        //Mouse Asapters for the Shuriken lbls
        for (int i = 0; i < ShurikenOptions.length; i++ ) {
            final int i1 = i;
            ShurikenOptions[i].addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    BuySkin(i1, ShurikenOptionsCoinInt[i1], i1 + 100);
                }
            });
        }
    }

    //here you change the dir to the PlayerSkins :D
    static void Draw(){
        Start.frame.getContentPane().removeAll();
        Start.frame.setResizable(false);
        Start.frame.setVisible(true);
        Start.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Start.frame.setLayout(new FlowLayout());
        Start.frame.setSize(1000, 1000);
        Start.frame.setLayout(null);

        ImageIcon WoodIcon = new ImageIcon("pics\\Wood.png");
        Image image = WoodIcon.getImage();
        Image newimg = image.getScaledInstance(200, 1000,  java.awt.Image.SCALE_SMOOTH);
        WoodIcon = new ImageIcon(newimg);
        Woodlbl.setBounds(0, 0, 200, 1000);
        Woodlbl.setIcon(WoodIcon);

        CoinsLBL.setBounds(15, 100, 170, 50);
        CoinsLBL.setFont(new Font("Arial", Font.PLAIN, 40));
        CoinsLBL.setForeground(new Color(214, 197, 87));
        CoinsLBL.setOpaque(true);
        CoinsLBL.setBackground(Color.WHITE);

        BackToMain.setBounds(15, 30, 170, 50);
        BackToMain.setFont(new Font("Arial", Font.PLAIN, 50));
        BackToMain.setBackground(Color.WHITE);
        BackToMain.setBorder(new LineBorder(new Color(0, 153, 0)));
        BackToMain.setFocusable(false);

        BTNPlayer.setBounds(15, 800, 170, 50);
        BTNPlayer.setFont(new Font("Arial", Font.PLAIN, 30));
        BTNPlayer.setBackground(Color.WHITE);
        BTNPlayer.setBorder(new LineBorder(new Color(0, 153, 0)));
        BTNPlayer.setFocusable(false);

        BTNShuriken.setBounds(15, 870, 170, 50);
        BTNShuriken.setFont(new Font("Arial", Font.PLAIN, 25));
        BTNShuriken.setBackground(Color.WHITE);
        BTNShuriken.setBorder(new LineBorder(new Color(0, 153, 0)));
        BTNShuriken.setFocusable(false);

        try {
            SaveData data = (SaveData) ResourceManager.load("1.save");
            ShopSeletedType = data.ShopSeletedType;
            CoinsLBL.setText(Integer.toString(data.coins));
        }
        catch (Exception e) {
            System.out.println("Couldn't load save data: " + e.getMessage());
        }

        for (int i = 0; i < PlayerOptions.length; i++) {
            PlayerOptions[i] = new JLabel();
        }
        for (int i = 0; i < ShurikenOptions.length; i++) {
            ShurikenOptions[i] = new JLabel();
        }

        System.out.println("ShopSeletedType - " + ShopSeletedType);
        switch (ShopSeletedType){
            case "Player":
                try {
                    SaveData data = (SaveData) ResourceManager.load("1.save");
                    PlayerChoosenSkin = data.PlayerLoadedSkinNum;
                }
                catch (Exception e) {
                    System.out.println("Couldn't load save data: " + e.getMessage());
                }

                int lblx = 250;
                int lbly = 45;
                for (int i = 0; i < PlayerOptions.length; i++){
                    PlayerOptionsCoinLabel[i] = new JLabel("" + PlayerOptionsCoinInt[i], SwingConstants.CENTER);
                    ImageIcon PlayerIcon = new ImageIcon("Pics\\LockedDoor.png");
                    Image image1 = PlayerIcon.getImage(); // transform it
                    Image newimg1 = image1.getScaledInstance(138, 268,  java.awt.Image.SCALE_SMOOTH);
                    PlayerIcon = new ImageIcon(newimg1);
                    try {
                        SaveData data = (SaveData) ResourceManager.load("1.save");
                        if (data.PlayerSkins[i]) {
                            PlayerOptions[i].setBorder(new MatteBorder(4, 4, 4, 4, new Color(3, 3, 15)));
                            ////////////////////////////////////////////////////////////change skin here :D.
                            switch (i){
                                case 0:
                                    PlayerIcon =  new ImageIcon("pics\\Skins_Player\\PSkin_Normal.png");
                                    break;
                                case 1:
                                    PlayerIcon =  new ImageIcon("pics\\Skins_Player\\PSkin_Mostshio.png");
                                    break;
                                case 2:
                                    PlayerIcon =  new ImageIcon("pics\\Skins_Player\\PSkin_Red.png");
                                    break;
                                case 3:
                                    PlayerIcon =  new ImageIcon("pics\\Skins_Player\\PSkin_Cyan.png");
                                    break;
                                case 4:
                                    PlayerIcon =  new ImageIcon("pics\\Skins_Player\\PSkin_MrCoolGuy.png");
                                    break;
                                case 5:
                                    PlayerIcon =  new ImageIcon("pics\\Skins_Player\\PSkin_Derp.png");
                                    break;
                                case 6:
                                    PlayerIcon =  new ImageIcon("pics\\Skins_Player\\PSkin_Insideout.png");
                                    break;
                                case 7:
                                    PlayerIcon =  new ImageIcon("pics\\Skins_Player\\PSkin_Alien.png");
                                    break;
                                case 8:
                                    PlayerIcon =  new ImageIcon("pics\\Skins_Player\\PSkin_Zombie.png");
                                    break;
                                case 9:
                                    PlayerIcon =  new ImageIcon("pics\\Skins_Player\\PSkin_Pumpkin.png");
                                    break;
                                case 10:
                                    PlayerIcon =  new ImageIcon("pics\\Skins_Player\\PSkin_Rainbow.png");
                                    break;
                                case 11:
                                    PlayerIcon =  new ImageIcon("pics\\Skins_Player\\PSkin_Slender.png");
                                    break;
                            }
                            Image image2 = PlayerIcon.getImage(); // transform it
                            Image newimg2 = image2.getScaledInstance(130, 260,  java.awt.Image.SCALE_DEFAULT);
                            PlayerIcon = new ImageIcon(newimg2);
                        } else {
                            Start.frame.add(PlayerOptionsCoinLabel[i]);
                        }
                    }
                    catch (Exception e) {
                        System.out.println("11111" + e.getMessage());
                    }
                    PlayerOptions[i].setIcon(PlayerIcon);
                    PlayerOptions[i].setBounds(lblx, lbly, 138, 268);
                    PlayerOptionsCoinLabel[i].setFont(new Font("Arial", Font.PLAIN, 40));
                    PlayerOptionsCoinLabel[i].setForeground(new Color(214, 197, 87));
                    PlayerOptionsCoinLabel[i].setBounds(lblx , lbly -40, 138, 40);
                    lblx += 187;
                    if (lblx > 950){
                        lblx = 250;
                        lbly += 315;
                    }
                    PlayerOptions[i].setOpaque(true);
                    PlayerOptions[i].setBackground(new Color(176, 187, 194));
                    if (PlayerChoosenSkin == i){
                        PlayerOptions[i].setBackground(Color.RED);
                        PlayerOptions[i].setBorder(new MatteBorder(4, 4, 4, 4, new Color(174, 31, 19)));
                    }
                    Start.frame.add(PlayerOptions[i]);
                }
                break;
            case "Shuriken":
                System.out.println("Shuriken");
                try {
                    SaveData data = (SaveData) ResourceManager.load("1.save");
                    ShurikenChoosenSkin = data.ShurikenLoadedSkinNum;
                }
                catch (Exception e) {
                    System.out.println("Couldn't load save data: " + e.getMessage());
                }

                int lblx1 = 228;
                int lbly1 = 110;
                for (int i = 0; i < ShurikenOptions.length; i++){
                    ShurikenOptionsCoinLabel[i] = new JLabel("" + ShurikenOptionsCoinInt[i], SwingConstants.CENTER);
                    ImageIcon PlayerIcon = new ImageIcon("Pics\\LockedDoor.png");
                    Image image1 = PlayerIcon.getImage(); // transform it
                    Image newimg1 = image1.getScaledInstance(358, 358,  java.awt.Image.SCALE_DEFAULT);
                    PlayerIcon = new ImageIcon(newimg1);
                    try {
                        SaveData data = (SaveData) ResourceManager.load("1.save");
                        if (data.ShurikenSkins[i]) {
                            ShurikenOptions[i].setBorder(new MatteBorder(4, 4, 4, 4, new Color(3, 3, 15)));
                            ////////////////////////////////////////////////////////////change skin here :D.
                            switch (i){
                                case 0:
                                    PlayerIcon = new ImageIcon("pics\\Skins_Shuriken\\ShurikenWhite.gif");
                                    break;
                                case 1:
                                    PlayerIcon = new ImageIcon("pics\\Skins_Shuriken\\ShurikenBlack.gif");
                                    break;
                                case 2:
                                    PlayerIcon = new ImageIcon("pics\\Skins_Shuriken\\ShurikenPink.gif");
                                    break;
                                case 3:
                                    PlayerIcon = new ImageIcon("pics\\Skins_Shuriken\\ShurikenBlue.gif");
                                    break;
                            }
                            Image image2 = PlayerIcon.getImage(); // transform it
                            Image newimg2 = image2.getScaledInstance(350, 350,  java.awt.Image.SCALE_DEFAULT);
                            PlayerIcon = new ImageIcon(newimg2);
                        } else {
                            Start.frame.add(ShurikenOptionsCoinLabel[i]);
                        }
                    }
                    catch (Exception e) {
                        System.out.println("11111" + e.getMessage());
                    }
                    ShurikenOptions[i].setIcon(PlayerIcon);
                    ShurikenOptions[i].setBounds(lblx1, lbly1, 358, 358);
                    ShurikenOptionsCoinLabel[i].setFont(new Font("Arial", Font.PLAIN, 40));
                    ShurikenOptionsCoinLabel[i].setForeground(new Color(214, 197, 87));
                    ShurikenOptionsCoinLabel[i].setBounds(lblx1 , lbly1 -40, 358, 40);
                    lblx1 += 386;
                    if (lblx1 > 950){
                        lblx1 = 225;
                        lbly1 += 400;
                    }
                    ShurikenOptions[i].setOpaque(true);
                    ShurikenOptions[i].setBackground(new Color(176, 187, 194));
                    if (ShurikenChoosenSkin == i){
                        ShurikenOptions[i].setBackground(Color.RED);
                        ShurikenOptions[i].setBorder(new MatteBorder(4, 4, 4, 4, new Color(174, 31, 19)));
                    }
                    Start.frame.add(ShurikenOptions[i]);
                }
                break;
        }
        BackToMain.setFocusable(false);
        Start.frame.add(BTNPlayer);
        Start.frame.add(BTNShuriken);
        Start.frame.add(BackToMain);
        Start.frame.add(CoinsLBL);
        Start.frame.add(Woodlbl);
        Start.frame.add(Main.Backlbl);
    }

    //No need to change that :P
    static void BuySkin(int sirNum, int price, int SirNumForTheStr) {
        String SkinPath = "pics\\Skins_Player\\PSkin_Normal.png";
        switch (SirNumForTheStr){
            case 1:
                SkinPath = "pics\\Skins_Player\\PSkin_Mostshio.png";
                break;
            case 2:
                SkinPath = "pics\\Skins_Player\\PSkin_Red.png";
                break;
            case 3:
                SkinPath = "pics\\Skins_Player\\PSkin_Cyan.png";
                break;
            case 4:
                SkinPath = "pics\\Skins_Player\\PSkin_MrCoolGuy.png";
                break;
            case 5:
                SkinPath = "pics\\Skins_Player\\PSkin_Derp.png";
                break;
            case 6:
                SkinPath = "pics\\Skins_Player\\PSkin_Insideout.png";
                break;
            case 7:
                SkinPath = "pics\\Skins_Player\\PSkin_Alien.png";
                break;
            case 8:
                SkinPath = "pics\\Skins_Player\\PSkin_Zombie.png";
                break;
            case 9:
                SkinPath = "pics\\Skins_Player\\PSkin_Pumpkin.png";
                break;
            case 10:
                SkinPath = "pics\\Skins_Player\\PSkin_Rainbow.png";
                break;
            case 11:
                SkinPath = "pics\\Skins_Player\\PSkin_Slender.png";
                break;
            case 100:
                SkinPath = "pics\\Skins_Shuriken\\ShurikenWhite.gif";
                break;
            case 101:
                SkinPath = "pics\\Skins_Shuriken\\ShurikenBlack.gif";
                break;
            case 102:
                SkinPath = "pics\\Skins_Shuriken\\ShurikenBlue.gif";
                break;
            case 103:
                SkinPath = "pics\\Skins_Shuriken\\ShurikenPink.gif";
                break;
        }

        try {
            System.out.println("Start Loading");
            SaveData data = (SaveData) ResourceManager.load("1.save");
            switch (data.ShopSeletedType) {
                case "Player":
                    if (!data.PlayerSkins[sirNum]) {
                        if (price <= data.coins) {
                            data.coins -= price;
                            data.PlayerSkins[sirNum] = true;
                            try {
                                ResourceManager.save(data, "1.save");
                            } catch (Exception eScore) {
                                System.out.println("Couldn't save: " + eScore.getMessage());
                            }
                            SkinCost();
                            Draw();
                            Logic();
                        }
                    } else {
                        data.PlayerLoadedSkinPath = SkinPath;
                        data.PlayerLoadedSkinNum = sirNum;
                        try {
                            ResourceManager.save(data, "1.save");
                        } catch (Exception eScore) {
                            System.out.println("Couldn't save: " + eScore.getMessage());
                        }
                        SkinCost();
                        Draw();
                        Logic();
                    }
                    System.out.println("Finish Loading");
                    break;
                case "Shuriken":
                    if (!data.ShurikenSkins[sirNum]) {
                        if (price <= data.coins) {
                            data.coins -= price;
                            data.ShurikenSkins[sirNum] = true;
                            try {
                                ResourceManager.save(data, "1.save");
                            } catch (Exception eScore) {
                                System.out.println("Couldn't save: " + eScore.getMessage());
                            }
                            SkinCost();
                            Draw();
                            Logic();
                        }
                    } else {
                        data.ShurikenLoadedSkinPath = SkinPath;
                        data.ShurikenLoadedSkinNum = sirNum;
                        try {
                            ResourceManager.save(data, "1.save");
                        } catch (Exception eScore) {
                            System.out.println("Couldn't save: " + eScore.getMessage());
                        }
                        SkinCost();
                        Draw();
                        Logic();
                    }
                    System.out.println("Finish Loading");
                    break;
            }

        }
        catch (Exception e) {
            System.out.println("Couldn't load save data: " + e.getMessage());
        }
    }
}