package at.fh.swenga.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class SongModel {

	private int id;
	private String songName;
	private String artist;
	private String album;
	
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@NotNull(message = "Date cannot be null")
	private Date releaseDate;
	//private int lenght;
	//private String parentalRatings;
	//private int totalPlayCount;
	//private double price;
	
	public SongModel() {
		super();
	}
	
	// Basic version of the constructor
	public SongModel(int id, String songName, String artist, String album, Date releaseDate) {
		super();
		this.id = id;
		this.songName = songName;
		this.artist = artist;
		this.album = album;
		this.releaseDate = releaseDate;
	}

	// Extended version of the constructor
	/*
	public SongModel(int id, String songName, String artist, String album, Date releaseDate, int lenght,
			String parentalRatings, int totalPlayCount, double price) {
		super();
		this.id = id;
		this.songName = songName;
		this.artist = artist;
		this.album = album;
		this.releaseDate = releaseDate;
		this.lenght = lenght;
		this.parentalRatings = parentalRatings;
		this.totalPlayCount = totalPlayCount;
		this.price = price;
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SongModel other = (SongModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return songName + " - " + artist;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	/*
	public int getLenght() {
		return lenght;
	}
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	public String getParentalRatings() {
		return parentalRatings;
	}
	public void setParentalRatings(String parentalRatings) {
		this.parentalRatings = parentalRatings;
	}
	public int getTotalPlayCount() {
		return totalPlayCount;
	}
	public void setTotalPlayCount(int totalPlayCount) {
		this.totalPlayCount = totalPlayCount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}*/
	
	
}