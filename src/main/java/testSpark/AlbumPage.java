package testSpark;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.util.ArrayList;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import com.google.gson.Gson;

import spark.Request;
import spark.Response;

public class AlbumPage {

    public static void main(String[] args) {
        port(3004);
        staticFiles.location("/public");
        AlbumList albumList = new AlbumList();
        
        get("/album/:id", (request, response) -> {
            Album album = albumList.getAlbum(Integer.parseInt(request.params(":id")));
            if (album == null){
                return "Album was not found.";
            }
            return ("Hello: "+album.getTitle()+" "+album.getArtist()+" "+album.getGenre());
        });

        get("/addAlbum/:title/:artist/:genre", (request, response) -> {
            int newID = albumList.addAlbum(request.params(":title"), request.params(":artist"), request.params(":genre"));
            return (newID);
        });
        
        post("/addAlbumByButton", (request, response) -> {
        	System.out.println("adding album via post");
        	String title = request.queryParams("title");
        	String artist = request.queryParams("artist");
        	String genre = request.queryParams("genre");
        	int newID = albumList.addAlbum(title, artist, genre);
            return "album added";
        });
        
        get("/jtwigAlbums", (request, response) -> {
        	ArrayList test = new ArrayList();
        	for (int i = 0; i < 3; i++) {
        		test.add(new Album("title" + i, "artist" +i, "genre" + i, i));
        	}
	        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/album.jtwig");
	        JtwigModel model = JtwigModel.newModel().with("albums", albumList.albums);
	
	        return template.render(model);
        });
        
        get("/gsonAlbums", (req, res) -> {
            Gson gson = new Gson();
            System.out.println(gson.toJson(albumList.albums));
            return gson.toJson(albumList.albums);
        });
}
}