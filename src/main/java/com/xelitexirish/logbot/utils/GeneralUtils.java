package com.xelitexirish.logbot.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class GeneralUtils {

	public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy:MM:dd - HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public static ArrayList<File> listDirectoryFiles(String dirPath, int level) {
        ArrayList<File> allFiles = new ArrayList<>();

        File dir = new File(dirPath);
        File[] firstLevelFiles = dir.listFiles();
        if (firstLevelFiles != null && firstLevelFiles.length > 0) {
            for (File aFile : firstLevelFiles) {

                if (aFile.isDirectory()) {
                    allFiles.addAll(listDirectoryFiles(aFile.getAbsolutePath(), level + 1));
                } else {
                    allFiles.add(aFile);
                }
            }
        }
        return allFiles;
    }
}
