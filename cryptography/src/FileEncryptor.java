import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class FileEncryptor {

    public void method() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[16];
        secureRandom.nextBytes(keyBytes);
        byte[] initializationVector = new byte[16];
        secureRandom.nextBytes(initializationVector);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, CryptographyConstant.AES);
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(CryptographyConstant.CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            final Path path = Files.createTempDirectory("crypto");
            final Path encryptedPath = path.resolve("path.txt.encrypted");
            try (InputStream inputStream = FileEncryptor.class.getResourceAsStream("path.txt");
                 OutputStream outputStream = Files.newOutputStream(encryptedPath);
                 CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher)) {
                final byte[] bytes = new byte[1024];
                if (inputStream == null) return;
                for (int length = inputStream.read(bytes);
                     length != -1;
                     length = inputStream.read(bytes))
                    cipherOutputStream.write(bytes, 0, length);
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                 | InvalidAlgorithmParameterException | InvalidKeyException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
