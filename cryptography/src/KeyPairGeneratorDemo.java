import java.security.*;

public class KeyPairGeneratorDemo {

    public String method() {
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

        return "PUBLIC KEY : " + publicKey + "\n PRIVATE KEY : " + privateKey;
    }
}
