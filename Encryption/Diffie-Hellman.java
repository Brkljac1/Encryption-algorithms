package Encryption;
import java.math.BigInteger;
import java.util.Scanner;
import java.security.SecureRandom;
class DiffieHellman extends EncryptionCodes{
    private BigInteger p;
    private BigInteger g;
    private BigInteger APrivateKey;
    private BigInteger BPrivateKey;
    public DiffieHellman() {
        super();
        System.out.println("This isn't an encryption algorithm per se\n as mush as it is a key exchanging algorithm!\n");
        System.out.println("Please type in value p(large prime number): ");
        Scanner scanner = new Scanner(System.in);
        p=(scanner.nextBigInteger());
        System.out.println("\n\nType in base(base<p): ");
        g=(scanner.nextBigInteger());
        SecureRandom secureRandom = new SecureRandom();
        APrivateKey=new BigInteger(p.bitLength() - 1, secureRandom).mod(p);
        BPrivateKey=new BigInteger(p.bitLength() - 1, secureRandom).mod(p);
        System.out.println("\nPerson A:\n\nYour secret key is: " + APrivateKey);
        System.out.println("\nPerson B:\n\nYour secret key is: " + BPrivateKey);
        //scanner.close();
    }
    public BigInteger APublicKey(){
        BigInteger AP = g.modPow(APrivateKey, p);
        return AP;
    }
    public BigInteger BPublicKey(){
        BigInteger BP = g.modPow(BPrivateKey,p);
        return BP;
    }
    public BigInteger sharedSecret(){
        //secret for person A is the same as the secret for person B
        BigInteger secret = APublicKey().modPow(BPrivateKey,p);
        return secret;
    }
    public void print(){
        System.out.println("Calculating all data for Diffie-Hellman algorithm...\n\n\nPerson A public key: " + APublicKey());
        System.out.println("\nPerson B public key: " + BPublicKey()+"\n");
        System.out.println("The shared secret is: "+ sharedSecret());

        
    }
    
    @Override
    public String encryptMessage() {
        throw new UnsupportedOperationException("Unimplemented method 'encryptMessage'");
    }

    @Override
    public String decryptMessage(String message) {
        throw new UnsupportedOperationException("Unimplemented method 'decryptMessage'");
    }};