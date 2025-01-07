package com.jpa.music;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album implements Comparable <Album> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="album_id")
	private int albumId;

	@Column(name="album_name")
	private String albumName;

	@OneToMany
	@JoinColumn(name="album_id")
	private List<Song> songList = new ArrayList<>();

	public Album() {
	}

	public Album(String albumName) {
		this.albumName = albumName;
	}

	public Album(int albumId, String albumName) {
		this.albumId = albumId;
		this.albumName = albumName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public List<Song> getSongList() {
		return songList;
	}

	public String printSongList() {
		List<Song> playList = getSongList();
		StringBuilder songList = new StringBuilder();
		playList.sort(Comparator.comparing(Song::getTrackNumber));
		for (Song song : playList) {
			songList.append("%d. %s%n".formatted(song.getTrackNumber(), song.getSongTitle()));
		}
		return songList.toString();
	}

	@Override
	public String toString() {
		return "Album{" +
			   "albumId=" + albumId +
			   ", albumName='" + albumName + '\'' + "\n" +
			   ", songList = " + printSongList() +
			   '}';
	}

	@Override
	public int compareTo(Album o) {
		return this.albumName.compareTo(o.getAlbumName());
	}
}
