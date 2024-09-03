import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

public class HashingWithSalt {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = "12345";
        String salt = "user@example.com";
        final int iterations = 32;
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), iterations, 512);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hashed = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        System.out.println("THE SHA-256 VALUE SALTED WITH PBKDF2 IS : " + Arrays.toString(hashed));
    }
}
