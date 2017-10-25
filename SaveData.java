public class SaveData implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    public int bestScore;
    public int coins;

    public boolean[] PlayerSkins = new boolean[12];
    public String PlayerLoadedSkinPath;
    public int PlayerLoadedSkinNum;

    public boolean[] ShurikenSkins = new boolean[4];
    public String ShurikenLoadedSkinPath;
    public int ShurikenLoadedSkinNum;

    public String ShopSeletedType;

    //If you need to add a ver to the save flie add it here and in the Restart Butten at the "Start" set it in the "Start".

}