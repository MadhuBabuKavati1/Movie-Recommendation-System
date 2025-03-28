import java.io.*;
import java.util.*;

public class DataLoader {
    public static Map<String, Movie> loadMovies(String filePath) throws IOException {
        Map<String, Movie> movies = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        br.readLine(); // Skip header

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            String movieId = data[0];
            String title = data[1];
            List<String> genres = Arrays.asList(data[2].split("\\|"));
            movies.put(movieId, new Movie(movieId, title, genres));
        }
        br.close();
        return movies;
    }

    public static Map<String, User> loadRatings(String filePath) throws IOException {
        Map<String, User> users = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        br.readLine(); // Skip header

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            String userId = data[0];
            String movieId = data[1];
            double rating = Double.parseDouble(data[2]);

            users.putIfAbsent(userId, new User(userId));
            users.get(userId).addRating(movieId, rating);
        }
        br.close();
        return users;
    }
}
