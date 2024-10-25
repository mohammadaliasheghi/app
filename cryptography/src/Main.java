import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                choose the algorithm:\s
                 (KeyPairGenerator)\s
                 (Symmetric)\s
                 (HashingWithSalt)""");
        System.out.print("enter the algorithm: ");
        String algorithm = scanner.nextLine();

        switch (algorithm) {
            case "KeyPairGenerator": {
                KeyPairGeneratorDemo demo = new KeyPairGeneratorDemo();
                System.out.println(demo.method());
                break;
            }
            case "Symmetric": {
                Symmetric symmetric = new Symmetric();
                System.out.print("Enter Your Text : ");
                String payload = scanner.nextLine();
                System.out.println(symmetric.method(payload));
                break;
            }
            case "HashingWithSalt": {
                HashingWithSalt hashingWithSalt = new HashingWithSalt();
                System.out.print("username : ");
                String username = scanner.nextLine();
                System.out.print("password : ");
                String password = scanner.nextLine();
                System.out.println(hashingWithSalt.method(username, password));
                break;
            }
            default:
                System.out.println("Invalid algorithm");
        }

        System.out.println("Successfully executed");
    }
}
