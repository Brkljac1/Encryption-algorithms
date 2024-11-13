package Encryption;

class Vigenere extends EncryptionCodes{

    public Vigenere() {
        super();
        
    }


    private void MakeKey(){//works!
        if(Key.length()==0) return;
        StringBuilder temp=new StringBuilder();

        for(int i=0;i<message.length();i++){
            temp.append(Key.charAt(i%Key.length()));
        }
        Key=temp.toString();
        System.out.println("The key used for conversion: "+ Key);
    }

    private void adjuststrings(){
        Key=Key.toUpperCase();
        message=message.toUpperCase();
        Key=Key.replaceAll("\\s", "");
        message=message.replaceAll("\\s", "");
    }
    @Override
    public String encryptMessage() {
        StringBuilder result=new StringBuilder();
        if (message.length() == 0) return "No message to encrypt";
        if (Key == null) return "Set the key first.";
        
        adjuststrings();
        MakeKey();
        
        for(int i=0;i<message.length();i++){
            int calculation=(message.charAt(i)-'A')+(Key.charAt(i)-'A');
            calculation=calculation%26+'A';
            result.append((char)(calculation));
        }
        
        return result.toString();
        
    }

    @Override
    public String decryptMessage(String message) {
        if (message.length() == 0) return "No message to encrypt";
        if (Key == null) return "Set the key first.";//make these into exception classes
        StringBuilder result = new StringBuilder();

        for(int i=0;i<message.length();i++){
            int calculation = (message.charAt(i) - 'A') - (Key.charAt(i) - 'A');
            calculation = (calculation + 26) % 26 + 'A';  
            result.append((char) calculation);
        }

        return result.toString();
        
    }};