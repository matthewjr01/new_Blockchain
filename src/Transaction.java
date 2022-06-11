import java.util.ArrayList;

public class Transaction {

    public static String DATETIME = "";
    public static String TransactionHash = "";

    public static ArrayList<Transaction> UTXO_INPUTS = new ArrayList<>();

    public String From = "";
    public String TOO = "";

    public float Total_Value = 0;


    public Transaction(Wallet From, String TOO, float value){
        this.From = StringUtil.applySha256(From.publicKey.toString());
        this.TOO = TOO;
        this.Total_Value += value;
    }
}
