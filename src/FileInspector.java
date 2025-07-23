import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInspector {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        // Set JFileChooser to open in the src directory
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/src"));

        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;

            System.out.println("Reading file: " + selectedFile.getName());
            System.out.println("------------------------------------------------");

            try (Scanner fileScanner = new Scanner(selectedFile)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    System.out.println(line); // Echo the line to screen
                    lineCount++;

                    // Count words
                    String[] words = line.trim().split("\\s+");
                    if (!line.trim().isEmpty()) {
                        wordCount += words.length;
                    }

                    // Count characters (including spaces)
                    charCount += line.length();
                }

                // Summary report
                System.out.println("\n=== File Summary Report ===");
                System.out.println("File name: " + selectedFile.getName());
                System.out.println("Number of lines: " + lineCount);
                System.out.println("Number of words: " + wordCount);
                System.out.println("Number of characters: " + charCount);

            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found.");
            }
        } else {
            System.out.println("No file was selected.");
        }
    }
}
