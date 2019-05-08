package at.fh.swenga.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.web.bind.annotation.ModelAttribute;

import at.fh.swenga.enums.ParentalRating;

public class SongModel {

	private int id;
	private String songName;
	private String artist;
	private String album;

	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@NotNull(message = "Date cannot be null")
	private Date releaseDate;

	private ParentalRating parentalRating;
	// Note: save price in cents?
	@NumberFormat(pattern = "###.##")
	private double price;

	public SongModel() {
		super();
	}

	public SongModel(int id, String songName, String artist, String album, Date releaseDate, double price) {
		super();
		this.id = id;
		this.songName = songName;
		this.artist = artist;
		this.album = album;
		this.releaseDate = releaseDate;
		this.parentalRating = ParentalRating.RatingPending;
		this.price = price;
	}

	public SongModel(int id, String songName, String artist, String album, Date releaseDate, double price,
			ParentalRating parentalRatings) {
		super();
		this.id = id;
		this.songName = songName;
		this.artist = artist;
		this.album = album;
		this.releaseDate = releaseDate;
		this.parentalRating = parentalRatings;
		this.price = price;
	}

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

	public ParentalRating getParentalRating() {
		return parentalRating;
	}

	public void setParentalRating(ParentalRating parentalRating) {
		this.parentalRating = parentalRating;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}