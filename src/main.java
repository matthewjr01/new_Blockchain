import java.util.Date;

public class main {

    public static String Algo = "MD5";
    public static void main(String[] agrs){


        Block block = new Block(null, Settings.G_Block_Hash, new Date().getTime(), null, Algo);
        Blockchain testblockchain = new Blockchain("Test", Algo, block);
        testblockchain.Test_Chain();
    }
}
