package net.friendface.friendface.controllers.actions.videos;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Author: S. Fink
 * Date: 03.06.11
 * Time: 21:53
 */

public class ShowVideo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer videoId = Integer.getInteger(req.getParameter("videoId"));
            //Video video = FriendfaceService.getService().getVideoService().getVideoById(videoId);
            //InputStream inputStream = video.getContent().getStream();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("flowplayer-700.flv");
            BufferedInputStream stream = new BufferedInputStream(inputStream);

            OutputStream outputStream = resp.getOutputStream();

            byte[] content = new byte[1024];
            int counter = 0;
            while (counter != -1) {
                counter = stream.read(content);
                outputStream.write(content);
            }

            resp.setContentLength(counter);
            resp.setContentType("video/x-flv");

            stream.close();
            outputStream.close();
        } catch (Exception e) {
            // do nothing
        }
    }
}
