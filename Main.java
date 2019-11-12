import java.util.Scanner;

public class Main {
    private static String plainText,key;
    public static void main(String[] args) {
        //encryption part
        Encryption encryption = new Encryption(key,plainText);
        System.out.println("The Encrypted Text for '"+plainText+"' : "+encryption.getEncryptedText());
        //decryption part
        Decryption decryption = new Decryption(key,encryption.getEncryptedText());
        decryption.getText();
    }
    static {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the plaintext");
        plainText = scanner.nextLine();
        System.out.println("Enter the keyword to be used");
        key = scanner.next();
        scanner.nextLine();
    }
}
