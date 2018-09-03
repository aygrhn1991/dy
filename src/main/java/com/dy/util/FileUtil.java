package com.dy.util;

public class FileUtil {
    public static String getFileExtensionName(String fileName) {
        return "." + fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
