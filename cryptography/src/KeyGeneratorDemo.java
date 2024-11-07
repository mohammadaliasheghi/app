import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class KeyGeneratorDemo {

    public String method() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(CryptographyConstant.AES);
            keyGenerator.init(256);
            SecretKey secretKey = keyGenerator.generateKey();
            return "Hex-encoded secret key is : " + Arrays.toString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
