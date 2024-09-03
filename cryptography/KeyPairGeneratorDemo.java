import java.security.*;

public class KeyPairGeneratorDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        final KeyPair keyPair = keyPairGenerator.generateKeyPair();

        final PublicKey publicKey = keyPair.getPublic();
        final PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("PUBLIC KEY : " + publicKey);
        System.out.println("PRIVATE KEY : " + privateKey);
    }
}
