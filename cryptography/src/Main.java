import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                choose the algorithm:\s
                 (KeyPairGenerator)\s
                 (Symmetric)\s
                 (HashingWithSalt)\s
                 (KeyGenerator)""");
        System.out.print("enter the algorithm: ");
        String algorithm = scanner.nextLine();

        var result = switch (algorithm) {
            case "KeyPairGenerator": {
                KeyPairGeneratorDemo demo = new KeyPairGeneratorDemo();
                Map<String, Object> map = demo.method();
                yield "key pair generation :" + map.get("keyPair") + "\n"
                        + "public key :" + map.get("publicKey") + "\n"
                        + "private key :" + map.get("privateKey");
            }
            case "Symmetric": {
                Symmetric symmetric = new Symmetric();
                System.out.print("Enter Your Text : ");
                String payload = scanner.nextLine();
                yield symmetric.method(payload);
            }
            case "HashingWithSalt": {
                HashingWithSalt hashingWithSalt = new HashingWithSalt();
                System.out.print("username : ");
                String username = scanner.nextLine();
                System.out.print("password : ");
                String password = scanner.nextLine();
                yield hashingWithSalt.method(username, password);
            }
            case "KeyGenerator": {
                KeyGeneratorDemo keyGeneratorDemo = new KeyGeneratorDemo();
                yield keyGeneratorDemo.method();
            }
            default:
                yield "Invalid algorithm";
        };

        System.out.println(result);
    }
}
