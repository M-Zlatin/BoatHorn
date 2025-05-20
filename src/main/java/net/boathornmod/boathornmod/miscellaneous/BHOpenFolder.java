package net.boathornmod.boathornmod.miscellaneous;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class BHOpenFolder {
    public static void main(String[] args) {
        if (!Desktop.isDesktopSupported()) {
            System.out.println("Desktop is not supported on this platform.");
            return;
        }

        File dirToOpen = new File("C:\\Users\\mzlat\\AppData\\Roaming\\.minecraft\\sounds\\boathornmod");

        if (dirToOpen.exists() && dirToOpen.isDirectory()) {
            try {
                Desktop.getDesktop().open(dirToOpen);
            } catch (IOException e) {
                System.out.println("Failed to open the folder: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Folder not found: " + dirToOpen.getAbsolutePath());
        }
    }
}
