package Encryption;
abstract class EncryptionCodes{
    protected String message;
    protected String Key;
    public EncryptionCodes(){
        
    }
    public abstract String encryptMessage();
    public void setMessage(String mess){message=mess;}
    public String getMessage(){return message;}
    public String getKey(){return Key;}
    public void setKey(String Key){this.Key=Key;}
    public abstract String decryptMessage(String message);
    
};