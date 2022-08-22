package org.nmerrell;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileStructure {

    private final Map<String, String> mMountDetails = new HashMap<>();

    public FileStructure() {
        this.clearMountDetails();
    }

    public void clearMountDetails() {
        mMountDetails.clear();
    }

    public void findDetailsForMountPoint(String mountPoint) {
        try {
            final File folder = new File(mountPoint);
            final File[] listOfFiles = folder.listFiles();

            if (listOfFiles == null) {
                System.out.printf("Unable to locate mount point %s\n", mountPoint);
                return;
            }
            for (File file : listOfFiles) {
                if (file.isDirectory()) {
                    findDetailsForMountPoint(file.getAbsolutePath());
                } else {
                    Path filePath = Paths.get(file.getPath());
                    String fileSize = String.valueOf(Files.size(filePath));
                    mMountDetails.put(filePath.toString(), fileSize);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printMap() {
        final JsonDetails jsonDetails = new JsonDetails(mMountDetails);
        final String jsonDetailsString = jsonDetails.printDetails();
        final Scanner reader = new Scanner(System.in);
        System.out.println("Export list of files (Y/N)?");
        String userInput = reader.next();
        /*
         * Need to see if the user inputted the correct value
         * If they return "Y" or "Yes", then we ask for a location to output the file.
         * If they return "N" or "No", then we just print out the values and call it a day.
         */
        if (userInput.toLowerCase().contains("y")) {
            writeJsonOut(jsonDetailsString);
        }

        System.out.println(jsonDetailsString);
    }

    private void writeJsonOut(String jsonDetailsString) {
        System.out.println("Enter path for file to be exported as \"output.txt\" file");
        final Scanner reader = new Scanner(System.in);
        String userInput = reader.next();
        if (!userInput.endsWith("/") || !userInput.endsWith("\\")) {
            userInput = userInput + "/";
        }
        String fileName = userInput + "output.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(jsonDetailsString);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.printf("Unable to write the details to the provided path %s.\n", userInput);
        }
    }
}
