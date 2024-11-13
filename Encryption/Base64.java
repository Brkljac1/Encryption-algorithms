package Encryption;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

class Base64 extends EncryptionCodes {
    private static final char[] BASE64_MAP = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final HashMap<Character, Integer> BASE64_INDEX_MAP = new HashMap<>();

    static {
        for (int i = 0; i < BASE64_MAP.length; i++) {
            BASE64_INDEX_MAP.put(BASE64_MAP[i], i);
        }
    }

    public Base64() {
        super();
    }

    @Override
    public String encryptMessage() {
        byte[] temp = message.getBytes();
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        for (int i = 0; i < temp.length; i += 3) {
            int a = temp[i] & 0xFF;
            int b = (i + 1 < temp.length) ? temp[i + 1] & 0xFF : 0;
            int c = (i + 2 < temp.length) ? temp[i + 2] & 0xFF : 0;

            int buffer = (a << 16) | (b << 8) | c;

            result.write(BASE64_MAP[(buffer >> 18) & 0x3F]);
            result.write(BASE64_MAP[(buffer >> 12) & 0x3F]);
            result.write(i + 1 < temp.length ? BASE64_MAP[(buffer >> 6) & 0x3F] : '=');
            result.write(i + 2 < temp.length ? BASE64_MAP[buffer & 0x3F] : '=');
        }
        return result.toString();
    }

    
    @Override
    public String decryptMessage(String encodedMessage) {
        int paddingCount = 0;
        if (encodedMessage.endsWith("==")) paddingCount = 2;
        else if (encodedMessage.endsWith("=")) paddingCount = 1;
    
        // Remove any padding for the calculation
        String base64String = encodedMessage.replace("=", "");
        ByteArrayOutputStream result = new ByteArrayOutputStream();
    
        for (int i = 0; i < base64String.length(); i += 4) {
            int b1 = BASE64_INDEX_MAP.get(base64String.charAt(i));
            int b2 = BASE64_INDEX_MAP.get(base64String.charAt(i + 1));
            int b3 = (i + 2 < base64String.length()) ? BASE64_INDEX_MAP.get(base64String.charAt(i + 2)) : 0;
            int b4 = (i + 3 < base64String.length()) ? BASE64_INDEX_MAP.get(base64String.charAt(i + 3)) : 0;
    
            // Combine 6-bit groups into a 24-bit buffer
            int buffer = (b1 << 18) | (b2 << 12) | (b3 << 6) | b4;
    
            // Extract the bytes and add them to the result
            result.write((buffer >> 16) & 0xFF);  // First 8 bits
            if (i + 2 < base64String.length() || paddingCount < 2) result.write((buffer >> 8) & 0xFF); // Second 8 bits
            if (i + 3 < base64String.length() || paddingCount == 0) result.write(buffer & 0xFF);        // Third 8 bits
        }
    
        // Convert the byte array back into a string
        return new String(result.toByteArray());
    }
    
};
