package Encryption;
import java.math.BigInteger;
import java.security.SecureRandom;
class RSA extends EncryptionCodes{
    private  BigInteger publicKey;
    private BigInteger privateKey;
    private BigInteger n;
    private BigInteger cypher;
    public RSA() {
        super();
        System.out.println("Reccomended to use 2048bit key sizes minimum! Reccomended public key = 65537");
        
    }
    private BigInteger getPrivateKey(){return privateKey;}
    public BigInteger getModuo(){return n;}
    public BigInteger getPublicKey(){return publicKey;}
    private void setKeys(){
        publicKey=new BigInteger(Key);
        SecureRandom random = new SecureRandom();
        BigInteger prime1= BigInteger.probablePrime(2048, random);
        BigInteger prime2= BigInteger.probablePrime(2048, random);

        n=prime1.multiply(prime2);
        BigInteger phi = (prime1.subtract(BigInteger.ONE)).multiply(prime2.subtract(BigInteger.ONE));
        //System.out.println("p = " + prime1 + " q = "+ prime2 + " moduo = "+ n);
        privateKey=publicKey.modInverse(phi);
        

    }
    @Override
    public String encryptMessage() {
        setKeys();
        BigInteger temp = new BigInteger(message.getBytes());
        cypher = temp.modPow(getPublicKey(), getModuo());
        byte[] newtemp = cypher.toByteArray();
        return new String(newtemp);
    }

    @Override
    public String decryptMessage(String message) {
        //exceptions...
        System.out.println("Deciphering using private key: " + getPrivateKey() + "\n Deciphering using moduo: " + getModuo());
        BigInteger temp = cypher.modPow(getPrivateKey(),getModuo() );
        byte[] newtemp = temp.toByteArray();
        return new String(newtemp);
    }};