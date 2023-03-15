//FILE NOT FOUND ERROR HANDLING

import java.io.*;

public class ExceptionHandlingExample {
    public static void main(String[] args) {
        try {
            File file = new File("file.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Error reading file");
        }
    }
}

//FILE HANDLING SAMPLE

import java.io.File;
import java.io.IOException;

public class FileHandlingExample {
    public static void main(String[] args) {
        // Create a new file object
        File file = new File("file.txt");

        try {
            // Check if the file exists
            if (file.exists()) {
                System.out.println("File already exists");
            } else {
                // Create a new file
                file.createNewFile();
                System.out.println("File created");
            }

            // Get the file name
            System.out.println("File name: " + file.getName());

            // Get the absolute path
            System.out.println("Absolute path: " + file.getAbsolutePath());

            // Check if the file is readable and writable
            System.out.println("Readable: " + file.canRead());
            System.out.println("Writable: " + file.canWrite());

            // Get the file size in bytes
            System.out.println("File size in bytes: " + file.length());

            // Delete the file
            file.delete();
            System.out.println("File deleted");
        } catch (IOException ex) {
            System.out.println("Error handling file");
        }
    }
}
