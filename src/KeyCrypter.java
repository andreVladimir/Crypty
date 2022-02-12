import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class KeyCrypter {

    public static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя .,-:;?!0123456789";

    public static void encrypt() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Enter full path of text you want to encrypt");
            String filePath = reader.readLine();
            String fileContent = getFIleContent(filePath);
            System.out.println("Enter the key to encrypt (0-20)");
            int key = Integer.parseInt(reader.readLine());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < fileContent.length(); i++) {
                char c = fileContent.charAt(i);
                int charIndex = ALPHABET.indexOf(c);
                int newCharIndex = (charIndex + key)%ALPHABET.length();
                char encryptedChar = ALPHABET.charAt(newCharIndex);
                stringBuilder.append(encryptedChar);
            }
            writeFileContent(stringBuilder.toString(), filePath, "-encrypted");
        }
        catch (Exception e) {
            e.  printStackTrace();
        }
    }

    public static void decrypt() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Enter full path of text you want to encrypt");
            String filePath = reader.readLine();
            String fileContent = getFIleContent(filePath);
            System.out.println("Enter the key to encrypt (0-20)");
            int key = Integer.parseInt(reader.readLine());
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < fileContent.length(); i++) {
                char c = fileContent.charAt(i);
                int charIndex = ALPHABET.indexOf(c);
                int newCharIndex;

                if (charIndex - key < 0 ) {
                    newCharIndex = charIndex*2 - key;
                }
                else {
                    newCharIndex = charIndex - key;
                }

                char decryptedChar = ALPHABET.charAt(newCharIndex);
                stringBuilder.append(decryptedChar);
            }


            writeFileContent(stringBuilder.toString(), filePath, "-decrypted");
        }
        catch (Exception e) {
            e.  printStackTrace();
        }
    }

    private static String getFIleContent(String filePath) {
        Path path = Path.of(filePath);
        try {
            byte[] bytes = Files.readAllBytes(path);
            return  new String(bytes);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void writeFileContent(String content, String prevFilePath, String status) {
        int dotIndex = prevFilePath.lastIndexOf(".");
        String fileBeforeDot = prevFilePath.substring(0, dotIndex);
        String fileAfterDot = prevFilePath.substring(dotIndex);
        String newFIleName = fileBeforeDot + status + fileAfterDot;

        try {
            Files.write(Path.of(newFIleName), content.getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
