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

import java.io.IOException;
import java.io.InputStream;

@WebServlet(urlPatterns = {"/artists", "/artists/delete", "/artists/search"})
public class ArtistServlet extends HttpServlet {

    private ArtistService artistService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        artistService = (ArtistService) AppContext.getContext().getBean("artistServiceImpl");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        InputStream is = getClass().getClassLoader().getResourceAsStream("artists.html");
        String content = new String(is.readAllBytes());

        StringBuilder artistsHtmlSb = new StringBuilder();
        for(Artist artists : artistService.getArtistList()){
            artistsHtmlSb.append("<li>"+artists.getName()+"</li>");
        }

        content = content.replace("$artists", artistsHtmlSb.toString());
        resp.getWriter().println(content);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Artist artist = new Artist();

        String path = req.getServletPath();

        if (path.equals("/artists/delete")){
            artist.setId(Integer.parseInt(req.getParameter("id")));
            artistService.deleteArtist(artist);
            resp.sendRedirect( req.getContextPath() + "/artists");

        }else if(path.equals("/artists")){
            artist.setId(Integer.parseInt(req.getParameter("id")));
            artist.setName(req.getParameter("name"));
            artist.setNationality(req.getParameter("nationality"));

            artistService.saveArtist(artist);
            resp.sendRedirect("./artists");
        }else if(path.equals("/artists/search")){

            String name = req.getParameter("name");
            Artist artistFound = artistService.findByName(name);
            if(artistFound != null){

                InputStream is = getClass().getClassLoader().getResourceAsStream("artists.html");
                String content = new String(is.readAllBytes());

                StringBuilder artistsHtmlSb = new StringBuilder();
                artistsHtmlSb.append("<li>" + artistFound.getName() + "<ul>");

                for (Track track : artistFound.getTracks()) {
                    artistsHtmlSb.append("<li>" + track.getTitle() + " - " + track.getGenre() + "</li>");
                }

                artistsHtmlSb.append("</ul></li>");


                content = content.replace("$searchResult", artistsHtmlSb.toString());
                resp.getWriter().println(content);
            }

        }
    }
}
