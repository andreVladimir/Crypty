import java.util.Scanner;

public class Crypty {
    public static void main(String[] args) {
        run();
    }
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type : ");
        System.out.println(" 1 to encrypting");
        System.out.println(" 2 to decrypting");
        int i;
        try {
            i = scanner.nextInt();
            switch (i) {
                case 1 :
                    System.out.println("Starting encryption");
                    KeyCrypter.encrypt();
                    break;
                case 2 :
                    System.out.println("Starting decryption");
                    KeyCrypter.decrypt();
                    break;
                case 0 :
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Incorrect selection. Try again");
                    run();

            }
        }
        catch (Exception e) {
            System.out.println("Please type correct number to select next action");
            run();
        }


    }
}
