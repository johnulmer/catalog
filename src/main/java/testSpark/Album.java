package testSpark;

import java.io.Serializable;

public class Album implements Serializable{
	private String title;
	private String artist;
	private int albumID;
	private String genre;

	public Album(String title, String artist) {
		this.title = title;
		this.artist = artist;
	}
	
	public Album(String title, String artist, String genre, int albumID) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.albumID = albumID;
	}

	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getAlbumID() {
		return albumID;
	}

	public void setAlbumID(int albumID) {
		this.albumID = albumID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
}
