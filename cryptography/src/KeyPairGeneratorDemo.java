import java.security.*;
import java.util.HashMap;
import java.util.Map;

public class KeyPairGeneratorDemo {

    public Map<String, Object> method() {
        final KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(CryptographyConstant.RSA);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyPairGenerator.initialize(2048);
        final KeyPair keyPair = keyPairGenerator.generateKeyPair();

        final PublicKey publicKey = keyPair.getPublic();
        final PrivateKey privateKey = keyPair.getPrivate();
        Map<String, Object> result = new HashMap<>();
        result.put("keyPair", keyPair);
        result.put("publicKey", publicKey);
        result.put("privateKey", privateKey);
        return result;
    }
}
