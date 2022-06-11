import org.bouncycastle.asn1.ocsp.TBSRequest;

import javax.crypto.*;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import javax.crypto.SecretKey;

public class Wallet {
    public PrivateKey privateKey; //The Wallets Private Key
    public String PublicAddress = "";
    public PublicKey publicKey; //The Wallets Public Key
    public SecretKey secretKey; //This Wallets SecretKey
    public Cipher cipher; //The Wallets Cipher


    public Wallet() {
        generateKeyPair();
        this.PublicAddress += StringUtil.applySha256(this.publicKey.toString());
    }


    public void generateKeyPair() {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            // Initialize the key generator and generate a KeyPair
            keyGen.initialize(ecSpec, random);   //256 bytes provides an acceptable security level
            KeyPair keyPair = keyGen.generateKeyPair();
            // Set the public and private keys from the keyPair

            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}