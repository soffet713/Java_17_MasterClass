package com.httpproject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.httpproject.handlers.JsonBodyHandler;
import com.httpproject.handlers.ThreadSafeFileHandler;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentRequests {

	private static final Lock lock = new ReentrantLock();

	private static final Path orderTracking = Path.of("orderTracking.json");

	public static void main(String[] args) {

		Map<String, Integer> orderMap =
				Map.of("apples", 500,
						"oranges", 1000,
						"bananas", 750,
						"carrots", 2000,
						"cantaloupes", 100,
						"mangos", 700,
						"sweet_potatoes", 900,
						"blueberries", 1500);

		String urlParams = "product=%s&amount=%d";

		String urlBase = "http://localhost:8080";

		List<URI> sites = new ArrayList<>();
		orderMap.forEach((k,v) -> sites.add(URI.create(urlBase + "?" + urlParams.formatted(k,v))));

		HttpClient client = HttpClient.newHttpClient();
		//sendGets(client, sites);

		if (!Files.exists(orderTracking)) {
			try {
				Files.createFile(orderTracking);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		//sendPosts(client, urlBase, urlParams, orderMap);
		//sendPostsWithFileResponse(client, urlBase, urlParams, orderMap);
		//sendPostsSafeFileWrite(client, urlBase, urlParams, orderMap);
		//sendPostsFileHandler(client, urlBase, urlParams, orderMap);
		sendPostsGetJSON(client, urlBase, urlParams, orderMap);
	}

	public static void writeToFile(String content) {
		lock.lock();
		try {
			Files.writeString(orderTracking, content + "\r", StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			lock.unlock();
		}
	}

	private static void sendGets(HttpClient client, List<URI> uris) {

		var futures = uris.stream()
				.map(uri -> HttpRequest.newBuilder(uri))
				.map(HttpRequest.Builder::build)
				.map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
				.toList();

		var allFutureRequests = CompletableFuture.allOf(
				futures.toArray(new CompletableFuture<?>[0]));

		allFutureRequests.join();

		futures.forEach(f -> {
			System.out.println(f.join().body());
		});
	}

	private static void sendPosts(HttpClient client, String uriBase, String urlParams,
								  Map<String, Integer> orderMap) {

		var futures = orderMap.entrySet().stream()
				.map(e -> urlParams.formatted(e.getKey(), e.getValue()))
				.map(s -> HttpRequest.newBuilder(URI.create(uriBase)).POST(HttpRequest.BodyPublishers.ofString(s)))
				.map(HttpRequest.Builder::build)
				.map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
				.toList();

		var allFutureRequests = CompletableFuture.allOf(
				futures.toArray(new CompletableFuture<?>[0]));

		allFutureRequests.join();
		List<String> lines = new ArrayList<>();

		futures.forEach(f -> {
			lines.add(f.join().body());
		});

		try {
			Files.write(orderTracking, lines, StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void sendPostsGetJSON(HttpClient client, String uriBase, String urlParams,
								  Map<String, Integer> orderMap) {

		ObjectMapper objectMapper = new ObjectMapper();
		var handler = JsonBodyHandler.create(objectMapper);

		var futures = orderMap.entrySet().stream()
				.map(e -> urlParams.formatted(e.getKey(), e.getValue()))
				.map(s -> HttpRequest.newBuilder(URI.create(uriBase)).POST(HttpRequest.BodyPublishers.ofString(s)))
				.map(HttpRequest.Builder::build)
				.map(request -> client.sendAsync(request, handler))
				.toList();

		var allFutureRequests = CompletableFuture.allOf(
				futures.toArray(new CompletableFuture<?>[0]));

		allFutureRequests.join();

		futures.forEach(f -> {
			JsonNode node = f.join().body().get("order");
			System.out.printf("Order Id:%s | Expected Delivery Date: %s%n", node.get("orderId"),
					node.get("orderDeliveryDate").asText());
		});
	}

	private static void sendPostsWithFileResponse(HttpClient client, String uriBase, String urlParams,
								  Map<String, Integer> orderMap) {

		var futures = orderMap.entrySet().stream()
				.map(e -> urlParams.formatted(e.getKey(), e.getValue()))
				.map(s -> HttpRequest.newBuilder(URI.create(uriBase)).POST(HttpRequest.BodyPublishers.ofString(s)))
				.map(HttpRequest.Builder::build)
				.map(request -> client.sendAsync(request,
						HttpResponse.BodyHandlers.ofFile(orderTracking, StandardOpenOption.APPEND)))
				.toList();

		var allFutureRequests = CompletableFuture.allOf(
				futures.toArray(new CompletableFuture<?>[0]));

		allFutureRequests.join();
	}

	private static void sendPostsFileHandler(HttpClient client, String uriBase, String urlParams,
												  Map<String, Integer> orderMap) {

		var handler = new ThreadSafeFileHandler(orderTracking);
		var futures = orderMap.entrySet().stream()
				.map(e -> urlParams.formatted(e.getKey(), e.getValue()))
				.map(s -> HttpRequest.newBuilder(URI.create(uriBase)).POST(HttpRequest.BodyPublishers.ofString(s)))
				.map(HttpRequest.Builder::build)
				.map(request -> client.sendAsync(request, handler))
				.toList();

		var allFutureRequests = CompletableFuture.allOf(
				futures.toArray(new CompletableFuture<?>[0]));

		allFutureRequests.join();
	}

	private static void sendPostsSafeFileWrite(HttpClient client, String uriBase, String urlParams,
												  Map<String, Integer> orderMap) {

		var futures = orderMap.entrySet().stream()
				.map(e -> urlParams.formatted(e.getKey(), e.getValue()))
				.map(s -> HttpRequest.newBuilder(URI.create(uriBase)).POST(HttpRequest.BodyPublishers.ofString(s)))
				.map(HttpRequest.Builder::build)
				.map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
						.thenAcceptAsync(r -> writeToFile(r.body())))
				.toList();

		var allFutureRequests = CompletableFuture.allOf(
				futures.toArray(new CompletableFuture<?>[0]));

		allFutureRequests.join();
	}
}
