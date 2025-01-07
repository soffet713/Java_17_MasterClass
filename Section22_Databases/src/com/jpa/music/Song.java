package com.jpa.music;

import jakarta.persistence.*;

@Entity
@Table(name = "songs")
public class Song implements Comparable <Song> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="song_id")
	private int songId;

	@Column(name="track_number")
	private int trackNumber;

	@Column(name="song_title")
	private String songTitle;

	@Column(name="album_id")
	private int albumId;

	public Song() {
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	@Override
	public String toString() {
		return "Song{" +
			   "songId=" + songId +
			   ", trackNumber=" + trackNumber +
			   ", songName='" + songTitle + '\'' +
			   ", albumId= " + albumId +
			   '}';
	}

	@Override
	public int compareTo(Song o) {
		return this.songTitle.compareTo(o.getSongTitle());
	}
}
