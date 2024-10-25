import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Symmetric {

    public Map<String, String> method(String payload) {
        Map<String, String> map = new HashMap<>();
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[16];
        secureRandom.nextBytes(key);
        byte[] initVector = new byte[16];
        secureRandom.nextBytes(initVector);

        String encrypted;
        try {
            encrypted = encrypt(key, initVector, payload);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                 InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
        map.put("encrypted", encrypted);

        String decrypted;
        try {
            decrypted = decrypted(key, initVector, encrypted);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                 InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
        map.put("decrypted", decrypted);

        boolean eq = decrypted.equals(payload);
        map.put("compare", Boolean.toString(eq));

        return map;
    }

    public static String encrypt(byte[] key, byte[] initVector, String value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, CryptographyConstant.AES);
        Cipher cipher = Cipher.getInstance(CryptographyConstant.CIPHER);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypted(byte[] key, byte[] initVector, String encrypt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, CryptographyConstant.AES);
        Cipher cipher = Cipher.getInstance(CryptographyConstant.CIPHER);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypt));
        return new String(original);
    }
}
