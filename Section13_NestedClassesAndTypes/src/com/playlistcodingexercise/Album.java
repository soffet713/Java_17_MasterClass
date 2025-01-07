package com.playlistcodingexercise;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    // write code here
    private String name;
    private String artist;
    private SongList songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new SongList();
    }

    public boolean addSong(String s, double duration) {
        return songs.add(new Song(s,duration));
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
        Song checkedSong = songs.findSong(trackNumber);
        if(checkedSong!=null) {
            playList.add(checkedSong);
            return true;
        }
        return false;
    }

    public boolean addToPlayList(String trackName, LinkedList<Song> playList) {
        Song checkedSong = songs.findSong(trackName);
        if(checkedSong!=null) {
            playList.add(checkedSong);
            return true;
        }
        System.out.println("The song " + trackName + " is not in this album");
        return false;
    }

    public static class SongList {

        private ArrayList<Song> songs;

        private SongList() {
            songs = new ArrayList<Song>();
        }

        private boolean add(Song song) {
            if(songs.contains(song)) {
                return false;
            }
            songs.add(song);
            return true;
        }

        private Song findSong(String songTitle) {
            for(Song s : songs) {
                if(s.getTitle().equalsIgnoreCase(songTitle)) {
                    return s;
                }
            }
            return null;
        }

        private Song findSong(int trackNumber) {
            int index = trackNumber - 1;
            if((index>0)&&(index<songs.size())) {
                return songs.get(index);
            }
            return null;
        }
    }
}
