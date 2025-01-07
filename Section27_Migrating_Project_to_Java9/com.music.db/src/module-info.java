module com.music.db {
	requires transitive com.music.common;
	requires java.sql;
	requires org.xerial.sqlitejdbc;

	exports com.music.db;
}