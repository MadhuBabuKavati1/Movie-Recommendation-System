import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String, Movie> movies = DataLoader.loadMovies("movies.csv");
            Map<String, User> users = DataLoader.loadRatings("ratings.csv");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter user ID: ");
            String userId = scanner.nextLine();

            List<String> recommendations = RecommendationEngine.getRecommendations(userId, users, movies);
            System.out.println("Recommended Movies: " + recommendations);
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }
}
