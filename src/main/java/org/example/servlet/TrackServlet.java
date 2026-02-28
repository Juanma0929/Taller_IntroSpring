package org.example.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.context.AppContext;
import org.example.entity.Artist;
import org.example.entity.Track;
import org.example.service.ArtistService;
import org.example.service.TrackService;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(urlPatterns = {"/tracks", "/tracks/delete", "/tracks/search"}, loadOnStartup = 2)
public class TrackServlet extends HttpServlet {

    private TrackService trackService;
    private ArtistService artistService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        trackService = (TrackService) AppContext.getContext().getBean("trackServiceImpl");
        artistService = (ArtistService) AppContext.getContext().getBean("artistServiceImpl");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        InputStream is = getClass().getClassLoader().getResourceAsStream("tracks.html");
        String content = new String(is.readAllBytes());

        StringBuilder tracksHtmlSb = new StringBuilder();
        for(Track tracks : trackService.getTracksList()){
            tracksHtmlSb.append("<li>" + tracks.getTitle() + " - " + tracks.getGenre()
                    + " | Album: " + tracks.getAlbumTitle()
                    + " | Duración: " + tracks.getDuration() + "s"
                    + "<ul>");
            for (Artist artist : tracks.getArtists()) {
                tracksHtmlSb.append("<li>" + artist.getName() + "</li>");
            }
            tracksHtmlSb.append("</ul></li>");
        }


        content = content.replace("$tracks", tracksHtmlSb.toString());

        resp.getWriter().println(content);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Track track = new Track();

        String path = req.getServletPath();

        if (path.equals("/tracks/delete")){
            track.setId(Integer.parseInt(req.getParameter("id")));
            trackService.deleteTrack(track);
            resp.sendRedirect( req.getContextPath() + "/tracks");

        }else if(path.equals("/tracks")) {
            track.setId(Integer.parseInt(req.getParameter("id")));
            track.setTitle(req.getParameter("title"));
            track.setGenre(req.getParameter("genre"));
            track.setDuration(Integer.parseInt(req.getParameter("duration")));
            track.setAlbumTitle(req.getParameter("albumTitle"));


            String artistNames = req.getParameter("artistNames");
            if (artistNames != null && !artistNames.isEmpty()) {
                String[] names = artistNames.split(",");
                for (String name : names) {
                    Artist artist = artistService.findByName(name.trim());
                    if (artist != null) {
                        track.addArtists(artist);
                    }
                }
            }
            trackService.saveTrack(track);
            resp.sendRedirect(req.getContextPath() + "/tracks");
        }

    }
}
