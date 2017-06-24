package testSpark;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class AlbumList implements Serializable {
    static int nextID; // variable used to assign next ID when adding albums 

    public AlbumList() {
        this.albums = new ArrayList<Album>();
        nextID = 0;
        //System.out.println("before load");
//        loadAlbums();
//      nextID = 5;
     //    *** Manual load of the data prior to incorporating function to write and load from disk
         albums.add(new Album("Different Days","The Charlatans","BBC",nextID++));
         albums.add(new Album("Common Sense","J Hus","BBC",nextID++));
         albums.add(new Album("More Life","Drake","BBC",nextID++));
         albums.add(new Album("The Amazons","The Amazons","BBC",nextID++));
         albums.add(new Album("One More Light","Linkin Park","BBC",nextID++));
       // System.out.println("after load");
    }

    ArrayList<Album> albums;

    public Album getAlbum(int id) {
        for (int i = 0; i < albums.size(); i++) {
            Album album = (Album) albums.get(i);
            if (album.getAlbumID() == id) {
                return album;              
            }
        }
        return null;
    }

    public int addAlbum(String title, String artist, String genre) {
        int id = nextID++;
        Album album = new Album(title, artist, genre, id);
        albums.add(album);
        persistAlbums();
        return id;
    }

    public void persistAlbums() {
        try {
            FileOutputStream fileOut = new FileOutputStream("albums.ser");            
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(albums);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in albums.ser");            
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void loadAlbums() {
        try {
            FileInputStream fis = new FileInputStream("albums2.ser");
//            FileInputStream fis = new FileInputStream("albums.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    this.albums = (ArrayList<Album>) ois.readObject();
                    nextID = albums.size();
                    System.out.println("after loada number = " + nextID);
                } catch (IOException e) {
                    break;
                }
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String formatDisplay() {
        String htmlString = "<html><body><table><tr><th>Title</th><th>Album</th><th>Genre</th></tr>";
        for (int i = 0; i < albums.size(); i++) {
            Album album = (Album) albums.get(i); // Object returned needs to be
                                                    // down cast
            htmlString += "<tr><td>" + album.getTitle() + "</td><td>" + album.getArtist() + "</td><td>" + album.getGenre()
                    + "</td></tr>";
        }
        htmlString += "</table></body></html>";
        return htmlString;
    }
}