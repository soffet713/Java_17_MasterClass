package com.directoryfilemanipulationchallenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {

		Path innerFolders = Path.of("public", "assets", "icons");

		try {
			Files.createDirectories(innerFolders);
			generateIndexFile(innerFolders.getName(0));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		for (int i = 1; i <= innerFolders.getNameCount(); i++) {
			Path indexedPath = innerFolders.subpath(0, i).resolve("index.txt");
			Path backupPath = innerFolders.subpath(0, i).resolve("indexCopy.txt");
			try {
				Files.copy(indexedPath, backupPath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		try {
			generateIndexFile(innerFolders.getName(0));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void generateIndexFile(Path startingPath) throws IOException {
		Path indexFile = startingPath.resolve("index.txt");
		try (Stream<Path> contents = Files.find(startingPath, Integer.MAX_VALUE, (path, attr) -> true)) {
			String fileContents = contents
					.map((path) -> path.toAbsolutePath().toString())
					.collect(Collectors.joining(System.lineSeparator(), "Directory Contents: " +
					System.lineSeparator(), System.lineSeparator() + "Generated: " + LocalDateTime.now()));
			Files.writeString(indexFile, fileContents, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try (Stream<Path> contents = Files.list(startingPath)) {
			contents.filter(Files::isDirectory)
					.toList()
					.forEach(dir -> {
						try {
							generateIndexFile(dir);
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					} );
		}
	}
}
