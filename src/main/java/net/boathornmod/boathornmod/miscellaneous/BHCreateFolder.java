package net.boathornmod.boathornmod.miscellaneous;

import java.io.File;

public class BHCreateFolder {
    public static void main(String[] args) {
        // Path of the folder you want to create
        File folder = new File("C:\\Users\\mzlat\\AppData\\Roaming\\.minecraft\\sounds\\boathornmod");

        // Check if the folder already exists
        if (!folder.exists()) {
            boolean created = folder.mkdirs(); // mkdirs for nested directories
            if (created) {
                System.out.println("Folder created successfully: " + folder.getAbsolutePath());
            } else {
                System.out.println("Failed to create folder.");
            }
        } else {
            System.out.println("Folder already exists: " + folder.getAbsolutePath());
        }
    }
}
