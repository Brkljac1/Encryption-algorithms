package Encryption;
import java.util.Scanner;
public class Main {

    public Main() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose an encryption algorithm you would like to use:");
            System.out.println("[1] Caesar's algorithm");
            System.out.println("[2] Vigenere's algorithm");
            System.out.println("[3] RSA");
            System.out.println("[4] Diffie-Hellman's algorithm");
            System.out.println("[5] Blowfish algorithm");
            System.out.println("[6] Base64");
            System.out.println("[7] AES");
            System.out.println("[8] SHA");
            System.out.println("[9] Exit");


            int choice = scanner.nextInt();
            boolean out = false;
            boolean valid=true;
            EncryptionCodes encrypt=null;
            switch (choice) {
                case 1:
                    // Caesar
                    encrypt = new Ceasar();
                    break;
                case 2:
                    // Vigenere
                    encrypt= new Vigenere();
                    break;
                case 3:
                    // RSA
                    encrypt=new RSA();
                    break;
                case 4:
                    // Diffie-Hellman
                    encrypt = new DiffieHellman();
                    break;
                case 5:
                    // Blowfish - need to implement
                    encrypt = new Blowfish();
                    break;
                case 6:
                    // Base64
                    encrypt = new Base64();
                    break;
                case 7:
                    // AES
                    encrypt = new AES();
                    break;
                case 8:
                    // SHA - need to implement
                    encrypt = new SHA();
                    break;
                case 9:
                    out = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    valid=false;
                    break;
            }

            if (out) break;
            if(valid){
                if(!(encrypt instanceof DiffieHellman)){
                    System.out.println("Please type in the message:");
                    scanner.nextLine();
                    String message = scanner.nextLine();
                    encrypt.setMessage(message); 
                }
                

                if(!(encrypt instanceof Base64) || !(encrypt instanceof DiffieHellman)){
                    System.out.println("Please type in the key");
                    System.out.println("If you are using RSA algorithm, type public key only: ");
                    //System.out.println("Separated by a space bar, first public, then private key: ");
                    String step = scanner.nextLine(); 
                    encrypt.setKey(step);
                }
             if(!(encrypt instanceof DiffieHellman)){  
                System.out.println("Encrypting message...");
                String cypher = encrypt.encryptMessage(); 

                
                System.out.println("The result is: " + cypher);

                
                System.out.println("Do you want to decipher this message, or type in a new encrypted message?");
                System.out.println("[1] Use old ciphered message");
                System.out.println("[2] I want another message to be deciphered");

                
                int decryptChoice = scanner.nextInt();
                if (decryptChoice == 2) {
                    
                    System.out.println("Please type in your own encrypted message:");
                    scanner.nextLine(); 
                    cypher = scanner.nextLine(); 
                }
                System.out.println("Deciphering message using the previously chosen encryption algorithm and given key...");
                cypher=encrypt.decryptMessage(cypher);
                System.err.println("Result of decryption is: " + cypher);
            }
            else{
                ((DiffieHellman)encrypt).print();
                
            }
                }
            
            
        }

        
        System.out.println("Thank you for using this simple software!");
        scanner.close();
    }

    public static void main(String[] args) {
        new Main();
    }
}
