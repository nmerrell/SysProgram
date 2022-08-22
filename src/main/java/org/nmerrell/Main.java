package org.nmerrell;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {

            final String mountPoint = args[0];
            new Program(mountPoint);
        } else {
            new Program();
        }
    }
}