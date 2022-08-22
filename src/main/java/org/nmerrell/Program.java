package org.nmerrell;

import java.util.Scanner;

public class Program {
    private FileStructure mFileStructure;
    private String mMountPoint;

    public Program() {
        new Program("");
    }
    public Program(String mountPoint) {

        this.mMountPoint = mountPoint;
        checkOperatingSystem();
    }

    public void acquireUserMountLocationInput() {
        final Scanner reader = new Scanner(System.in);
        System.out.println("Enter the desired mount location to check: ");
        String userInputtedLocation = reader.next();
        System.out.println("Check this location (Y/N)? \n\t\"" + userInputtedLocation + "\"");
        String userValidation = reader.next();

        /*
         * Need to see if the user inputted the correct value
         * If they return "Y" or "Yes", then we can continue on with grabbing the files.
         * If they return "N" or "No", then we jump back up to the top.
         */
        if (userValidation.toLowerCase().contains("y")) {
            mMountPoint = userInputtedLocation;
        } else {
            acquireUserMountLocationInput();
        }
    }

    public void acquireUserCheckAnotherInput() {
        final Scanner reader = new Scanner(System.in);
        System.out.println("Do you wish to check another location (Y/N)?");
        String userInput = reader.next();
        /*
         * Need to see if the user inputted the correct value
         * If they return "Y" or "Yes", then we start the process of checking another mount point.
         * If they return "N" or "No", then we jump out and terminate the program.
         */
        if (userInput.toLowerCase().contains("y")) {
            checkOperatingSystem();
        }
    }

    public void checkOperatingSystem() {

        mFileStructure = new FileStructure();

        if (mMountPoint.length() == 0) {
            acquireUserMountLocationInput();
        }

        mFileStructure.findDetailsForMountPoint(mMountPoint);

        printFileList();

        // Change the mount point value back to an empty string to continue on
        mMountPoint = "";
        acquireUserCheckAnotherInput();
    }

    public void printFileList() {
        mFileStructure.printMap();
    }
}
