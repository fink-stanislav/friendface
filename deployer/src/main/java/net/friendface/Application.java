package net.friendface;

import net.friendface.deployer.DeployHelper;

public class Application {
    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.equals("-truncate")) {
                DeployHelper.truncateDataBase();
            } else if (arg.equals("-createDB")) {
                DeployHelper.createDataBase();
            } else if (arg.equals("-createRepo")) {
                DeployHelper.createRepository();
            } else if (arg.equals("-deploy")) {
                DeployHelper.createDataBase();
                DeployHelper.createRepository();
            } else if (arg.equals("-redeploy")) {
                DeployHelper.createRepository();
                DeployHelper.truncateDataBase();
            }
        }
    }
}
