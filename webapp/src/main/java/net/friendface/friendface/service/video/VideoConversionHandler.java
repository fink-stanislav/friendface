package net.friendface.friendface.service.video;

import net.friendface.friendface.model.dao.videos.VideoDAO;
import net.friendface.friendface.model.entities.Video;
import org.apache.jackrabbit.value.BinaryImpl;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;

/**
 * Author: S. Fink
 * Date: 11.06.11
 * Time: 22:12
 */

public class VideoConversionHandler implements Runnable {
    private String filename;
    private VideoDAO dao;
    private Video video;

    private VideoConversionHandler(File file, Video video, VideoDAO dao) {
        this.filename = file.getPath();
        this.video = video;
        this.dao = dao;
    }

    public static void runConversion(File file, Video video, VideoDAO dao) {
        (new Thread(new VideoConversionHandler(file, video, dao))).start();
    }

    @Override
    public void run() {
        try {
            String resultPath = new VideoConverter().convert(filename);
            FileInputStream fileInputStream = new FileInputStream(resultPath);

            video.setContent(new BinaryImpl(fileInputStream));
            video.setConverted(true);

            fileInputStream.close();
            File resultFile = new File(resultPath);
            resultFile.delete();

            dao.addContent(video);
        } catch (Exception e) {
            // do nothing
        }
    }
}