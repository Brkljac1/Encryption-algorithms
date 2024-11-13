package Encryption;

class Ceasar extends EncryptionCodes {
    
    
    public Ceasar() {
        super();
    }

    

    @Override
    public String encryptMessage() {
        if (message.length() == 0) return "No message to encrypt";
        if (Key == null) return "Set the key first.";
        int step = Integer.parseInt(Key);
        
        StringBuilder cypher = new StringBuilder();
        for (char a : message.toCharArray()) { 
            cypher.append((char)(a + step));  
        }

        return cypher.toString();
    }

    @Override
    public String decryptMessage(String cypher) {
        StringBuilder solved = new StringBuilder();
        int step = Integer.parseInt(Key);
        for (char a : cypher.toCharArray()) {
            solved.append((char)(a - step)); 
        }

        return solved.toString();
    }
}
