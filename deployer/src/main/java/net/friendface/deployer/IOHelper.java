package net.friendface.deployer;

import java.io.File;

/**
 * Author: S. Fink
 * Date: 04.06.11
 * Time: 16:45
 */

public class IOHelper {
    public static boolean deleteDirectory(String path) {
        File dir = new File(path);
        return deleteDirectory(dir);
    }

    public static boolean createPath(String path) {
        File dir = new File(path);
        return dir.exists() || new File(path).mkdirs();
    }

    private static boolean deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children) {
                boolean success = deleteDirectory(new File(dir, child));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
