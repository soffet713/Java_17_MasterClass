package com.jpa;

import com.jpa.music.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Main {

	public static void main(String[] args) {

		try (var sessionFactory = Persistence.createEntityManagerFactory(
				"com.jpa.music");
			EntityManager entityManager = sessionFactory.createEntityManager();
			) {

			var transaction = entityManager.getTransaction();
			transaction.begin();
			Artist artist = entityManager.find(Artist.class, 103);
			//Artist artist = new Artist(202, "Muddy Water");
			System.out.println(artist);
			//artist.removeDuplicates();
			//Album album = artist.getAlbums().get(1);
			//System.out.println(album);

			//String[] songsToAdd = {"I Just Want to Make Love to You","Long Distance Call","Louisiana Blues" +
			//					   "Honey Bee","Rollin' Stone","I'm Ready","Hoochie Coochie","She Moves Me" +
			//					   "I Want You to Love Me","Standing Around Crying","Still a Fool" +
			//					   "I Can't Be Satisfied"};

			//for(String song : songsToAdd) {
			//	album.addSong(song);
			//}

			//System.out.println(album);
			//artist.setArtistName("Muddy Waters");
			//entityManager.remove(artist);
			//entityManager.persist(new Artist("Muddy Water"));
			//entityManager.merge(artist);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
