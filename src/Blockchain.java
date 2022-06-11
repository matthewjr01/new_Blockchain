import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Blockchain {

    public static ArrayList<Block> Test_SubChain = new ArrayList<>(); //This is the Test Chain Inside this Blockchain Object used only for testing.
    public static ArrayList<Wallet> Test_Wallets = new ArrayList<>(); //This is the wallets generated used for the Testing

    public static ArrayList<Transaction> Test_Transactions = new ArrayList<>(); //This contains null and void test transactions for the test network!!
    public  String Blockchain_Name = ""; //This is the Name of the Blockchain
    public  String Algorithm = ""; //This is the Encryption Algorithm used in this Blockchain
    public  Block Firstblock; //This is the First Created Block in this Blockchain

    public Blockchain(String Name, String Algorithm, Block FirstBlock){ //This is the Generic Blockchain Constructor
        this.Blockchain_Name += Name;
        this.Algorithm += Algorithm;
        this.Firstblock = FirstBlock;
    }

    public void Test_Chain(){ //This function tests the test blockchain by making blocks and adding them and modifieing them!
        try{
            Random R = new Random(); //Used for testing
            Make_TestWallets();
            //////FIRST BLOCK IN CHAIN!!!/////
            Block G_block = new Block(Test_Transactions, Settings.G_Block_Hash, new Date().getTime(), Test_Wallets.get(0).publicKey, Algorithm);
            Test_SubChain.add(G_block);
            System.out.println("Added G-BLOCK!!!");
            /////MAKES TEST TRANSACTIONS///////
            for(int x = 0; x <= 98; x++){
                Transaction transaction = new Transaction(Test_Wallets.get(x), Test_Wallets.get(x+1).PublicAddress, 100);
                Test_Transactions.add(transaction);
                System.out.println("Added New Transaction!!!: "+ transaction.toString());
            }
            //////MAKES BLOCKS AND ADDS TO ARRAY////////
            for(int x = 0; x <= 100; x++){
                Block block = new Block(Test_Transactions, Test_SubChain.get(Test_SubChain.size() -1).getBlockHash(), new Date().getTime(), Test_Wallets.get(x).publicKey, Algorithm);
                block.mineBlock(2);
                Test_SubChain.add(block);
                System.out.println("Added New Block!!!: "+ block.getBlockHash());
            }

            ///MODFY BLOCKS
            for(int x = 0; x <= 10; x++) {
                    Block block = Test_SubChain.get(x);
                    ArrayList<Transaction> transactions = new ArrayList<>();
                    transactions.addAll(block.transactions); //Adds all Transactions from the block and adds them to the temp array
                    block.transactions.clear();

                    Transaction transaction = new Transaction(Test_Wallets.get(R.nextInt(60)), Test_Wallets.get(R.nextInt(60)).PublicAddress, R.nextInt(1000)); //This makes a new transaction
                    transactions.add(transaction); //This adds the clearly false transation to the array
                    block.transactions.addAll(transactions);//Adds all the transactions back but with an additional one that is clearly non-valid
                    System.out.println("Make False Transaction from: " + transaction.From +" With Value of: "+ transaction.Total_Value);
                }

            int TimesPassed = 0;
            //CHECK BLOCKCHAIN
            for(Block block: Test_SubChain){
                block.calculateBlockHash(Algorithm);
                for(int x = 1; x <= 100; x++){
                    if(!Test_SubChain.get(x).getPreviousHash().contains(block.getBlockHash())){
                        System.out.println(Settings.YELLOW_BACKGROUND + Settings.RED + "BLOCK HASH MISS-MATCH ON: "+ block.getBlockHash()+ Settings.RESET);//EXPECTED TO RUN
                        TimesPassed = TimesPassed +1;
                        System.out.println(Settings.GREEN + "TEST PASSED: "+ TimesPassed);
                    }
                }
            }
            System.out.println(Settings.PURPLE + "TEST BLOCKCHAIN SIZE: "+ Test_SubChain.size() + Settings.RESET);
            Test_SubChain.clear();
            System.out.println(Settings.PURPLE + "NEW TEST BLOCKCHAIN SIZE: "+ Test_SubChain.size() + Settings.RESET);

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
