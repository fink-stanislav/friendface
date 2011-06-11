package net.friendface.friendface.service.video;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;

/**
 * Author: S. Fink
 * Date: 11.06.11
 * Time: 15:44
 */

public class VideoConverter {

    public String convert(String filename) {
        // create a media reader
        IMediaReader mediaReader = ToolFactory.makeReader(filename);

        String outputFilename = filename + ".flv";

        // create a media writer
        IMediaWriter mediaWriter = ToolFactory.makeWriter(outputFilename, mediaReader);

        // add a writer to the reader, to create the output file
        mediaReader.addListener(mediaWriter);

        // read and decode packets from the source file and
        // and dispatch decoded audio and video to the writer
        while (mediaReader.readPacket() == null) {
            // empty loop
        }

        mediaReader.close();

        return outputFilename;
    }
}
