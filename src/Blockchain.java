import java.util.ArrayList;

public class Blockchain {

    public static ArrayList<Blockchain> Test_SubChain = new ArrayList<>(); //This is the Test Chain Inside this Blockchain Object used only for testing.
    public static ArrayList<Wallet> Test_Wallets = new ArrayList<>(); //This is the wallets generated used for the Testing
    public  String Blockchain_Name = ""; //This is the Name of the Blockchain
    public  String Algorithm = ""; //This is the Encryption Algorithm used in this Blockchain
    public  Block Firstblock; //This is the First Created Block in this Blockchain

    public Blockchain(String Name, String Algorithm, Block FirstBlock){ //This is the Generic Blockchain Constructor
        this.Blockchain_Name += Name;
        this.Algorithm += Algorithm;
        this.Firstblock = FirstBlock;
    }

    public static void Test_Chain(){ //This function tests the test blockchain by making blocks and adding them and modifieing them!
        try{

        }catch (Exception ex){
            System.out.println(ex); //Prints exception to console
        }
    }

    public static void Make_TestWallets(){ //Makes Test Wallets for running tests and putus them into a test_wallet array
        Wallet wallet;
        for(int x = 0; x <= 100; x++){
            wallet = new Wallet();
            Test_Wallets.add(wallet);
            System.out.println("Adding new Test wallet with address: "+ wallet.PublicAddress);
        }
        return;
    }

}
