import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Date;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Block implements Serializable{
    /////////TRANSACTIONS IN BLOCK//////////////


    //Timestamp ts = new Timestamp(time);
    ///////////////////////////
    /////THIS IS BLOCK!!!
    public ArrayList<byte[]> Verified_By = new ArrayList<>(); //SIGNATURE OF SIGNER's
    private String PrevHash;
    private String blockHash = "";
    private int nonce;
    public ArrayList<Transaction> transactions;
    public String Merkleroot = ""; //Final Hash of ALL Transactions in Block
    public ArrayList<String> MR_HASHLIST = new ArrayList<>(); //List of All Transaction Hashes in Block
    public long timeStamp;
    public int diff = 1;

    public String Algorithm = "";



    public Block(ArrayList<Transaction> transactions, String previousHash, long timeStamp, PublicKey miner, String Algorithm) throws NullPointerException{

        if(!StringUtil.IsAlgo(Algorithm)){
           System.out.println("ALOGRITHM IS NON-VALID");
           System.exit(1000);
        }
        this.Algorithm += Algorithm;
        this.Merkleroot = Calculate_MerkleRoot();
        this.timeStamp = timeStamp;
        this.transactions = transactions;
        this.blockHash = calculateBlockHash(Algorithm);
        this.PrevHash = previousHash;


    }
    public String Calculate_MerkleRoot(){ // This function calculated the Blocks MR
        String MR = "";
        if(this.transactions == null){
            return "";
        }
        MR = StringUtil.ApplyHash(this.Algorithm, this.transactions.toString());
        return MR;
    }

    public String calculateBlockHash(String Algorithm) { //This function Calculated the Block Hash
        String dataToHash = this.PrevHash
                + Long.toString(timeStamp)
                + Integer.toString(nonce)
                + transactions;
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            //ORIGINAL Algorithm IS 'SHA-256'
            digest = MessageDigest.getInstance(Algorithm);
            bytes = digest.digest(dataToHash.getBytes(UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    public String mineBlock(int prefix) { ///Starts mining the Block
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!this.blockHash.substring(0, prefix).equals(prefixString)) {
            nonce++;
            System.out.println(Settings.RED_BOLD + Settings.BLACK_BACKGROUND +"Trying: "+ this.blockHash);
            this.blockHash = calculateBlockHash(this.Algorithm);
        }
        System.out.println(Settings.GREEN_BOLD + Settings.BLACK_BACKGROUND +"FOUND: "+ this.blockHash);
        return this.blockHash;
    }
    public String getPreviousHash() {  //Returns the Blocks PreviousHash
        return this.PrevHash;
    }
    public String getBlockHash(){ //Returns the Block HASH
        return this.blockHash;
    }

    public int get_nonce(){ //Returns the Block Nonce
        return this.nonce;
    }

    public int getDiff(){ //Returns the Block Difficulty
        return this.diff;
    }



}