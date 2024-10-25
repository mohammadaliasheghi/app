import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

public class HashingWithSalt {

    public String method(String username, String password) {
        final int iterations = 32;
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), username.getBytes(), iterations, 512);
        SecretKeyFactory secretKeyFactory;
        try {
            secretKeyFactory = SecretKeyFactory.getInstance(CryptographyConstant.HASHING_WITH_SALT_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hashed;
        try {
            hashed = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return "THE SHA-256 VALUE SALTED WITH PBKDF2 IS : " + Arrays.toString(hashed);
    }
}
