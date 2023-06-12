import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.Key;
import java.util.Arrays;

public class FileEncryptionUtility {
    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "YourSecretKey"; // Change this with your secret key

    public static void main(String[] args) {
        String filePath = "path/to/your/file"; // Provide the path to the file you want to encrypt/decrypt

        // Encrypt the file
        encryptFile(filePath);

        // Decrypt the file
        decryptFile(filePath);
    }

    private static void encryptFile(String filePath) {
        try {
            Path file = Paths.get(filePath);
            byte[] fileContent = Files.readAllBytes(file);

            Key secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedContent = cipher.doFinal(fileContent);

            Path encryptedFile = Paths.get(filePath + ".encrypted");
            Files.write(encryptedFile, encryptedContent, StandardOpenOption.CREATE);

            System.out.println("File encrypted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void decryptFile(String filePath) {
        try {
            Path encryptedFile = Paths.get(filePath + ".encrypted");
            byte[] encryptedContent = Files.readAllBytes(encryptedFile);

            Key secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decryptedContent = cipher.doFinal(encryptedContent);

            Path decryptedFile = Paths.get(filePath + ".decrypted");
            Files.write(decryptedFile, decryptedContent, StandardOpenOption.CREATE);

            System.out.println("File decrypted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
